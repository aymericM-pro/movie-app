#!/bin/zsh

set -o pipefail

DOCKER_REGISTRY="${DOCKER_REGISTRY:-}"
IMAGE_NAME="${IMAGE_NAME:-}"
DOCKERFILE="${DOCKERFILE:-Dockerfile}"
CONTEXT="${CONTEXT:-.}"

DO_PUSH="NO"
NO_CACHE="NO"
_retcode=0

exitWithCode() {
  exit "$_retcode"
}

toLowerCase() {
  echo "$*" | awk '{print tolower($0)}'
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

  docker login "$DOCKER_REGISTRY" -u "$user" -p "$pass" >/dev/null 2>&1 || {
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

buildImage() {
  local tag
  tag="$(toLowerCase "$1")"

  if [[ -z "$IMAGE_NAME" ]]; then
    echo "ERROR: IMAGE_NAME is not set" >&2
    _retcode=1
    return
  fi

  local image="$IMAGE_NAME:$tag"
  local args=()

  [[ "$NO_CACHE" == "YES" ]] && args+=("--no-cache")

  docker build \
      -f "$DOCKERFILE" \
      "${args[@]}" \
      -t "$image" \
      "$CONTEXT" || {
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
      --no-cache) NO_CACHE="YES" ;;
      --user) dockerUser="$2"; shift ;;
      --pass) dockerPass="$2"; shift ;;
      -h|--help)
        echo "Usage:"
        echo "  IMAGE_NAME=repo/app ./docker.sh [options] <tag>"
        echo
        echo "Options:"
        echo "  --push            Push image after build"
        echo "  --no-cache        Disable Docker cache"
        echo "  --user <u>        Docker registry user"
        echo "  --pass <p>        Docker registry password"
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
  buildImage "$tag"
}

(return 0 2>/dev/null) && sourced=1 || sourced=0

if [[ "$sourced" -eq 0 ]]; then
  MAIN "$@"
  exitWithCode
fi
