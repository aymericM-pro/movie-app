#!/bin/bash

set -o pipefail

PROJECT_NAME="my-app"
REGISTRY_HOST="registry.example.com"
IMAGE_NAME="$REGISTRY_HOST/$PROJECT_NAME"
DOCKERFILE="Dockerfile"
JAR_PATTERN="target/*-SNAPSHOT.jar"

MAVEN=${MAVEN_COMMAND:-mvn}

_retcode=0
DO_PUSH="NO"

exitWithCode() {
  exit "$_retcode"
}

toLowerCase() {
  echo "$*" | awk '{print tolower($0)}'
}

seekMavenVersion() {
  local version
  version=$(
    $MAVEN -q \
      -DforceStdout \
      -Dexpression=project.version \
      org.apache.maven.plugins:maven-help-plugin:3.3.0:evaluate 2>/dev/null
  )

  if [[ -z "$version" || "$version" == *"[ERROR]"* ]]; then
    echo "ERROR: Unable to resolve Maven version" >&2
    _retcode=1
    return
  fi

  MAVEN_VERSION="$version"
}

dockerLogin() {
  local user="$1"
  local pass="$2"

  if [[ -z "$user" && -z "$pass" ]]; then
    return
  fi

  if [[ -z "$user" || -z "$pass" ]]; then
    echo "ERROR: docker login requires both --user and --pass" >&2
    _retcode=1
    return
  fi

  docker login "$REGISTRY_HOST" -u "$user" -p "$pass" >/dev/null 2>&1 || {
    echo "ERROR: docker login failed" >&2
    _retcode=1
  }
}

uploadImage() {
  local image="$1"

  if [[ "$DO_PUSH" == "YES" ]]; then
    docker push "$image" || _retcode=1
  fi
}

initialiseJarPath() {
  local jars
  mapfile -t jars < <(ls $JAR_PATTERN 2>/dev/null)

  if [[ "${#jars[@]}" -ne 1 ]]; then
    echo "ERROR: expected exactly one JAR, found ${#jars[@]}" >&2
    _retcode=1
    return
  fi

  APP_JAR="${jars[0]}"
}

BUILD() {
  local tag
  tag=$(toLowerCase "$1")

  initialiseJarPath || return
  seekMavenVersion || return

  local image="$IMAGE_NAME:$tag"

  docker build \
    -f "$DOCKERFILE" \
    --build-arg JAR_FILE="$APP_JAR" \
    -t "$image" . || {
      _retcode=1
      return
    }

  uploadImage "$image"
}

MAIN() {
  local tag=
  local dockerUser=
  local dockerPass=

  while [[ $# -gt 0 ]]; do
    case "$1" in
      --push) DO_PUSH="YES" ;;
      --user) dockerUser="$2"; shift ;;
      --pass) dockerPass="$2"; shift ;;
      -h|--help)
        echo "Usage: ./build.sh [--push] [--user u --pass p] <tag>"
        return
        ;;
      *)
        tag="$1"
        ;;
    esac
    shift
  done

  if [[ -z "$tag" ]]; then
    echo "ERROR: missing tag" >&2
    _retcode=1
    return
  fi

  dockerLogin "$dockerUser" "$dockerPass" || return
  BUILD "$tag"
}

MAIN "$@"
exitWithCode
