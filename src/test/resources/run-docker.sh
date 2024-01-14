#!/usr/bin/env bash

set -e

SCRIPT_DIR="$(dirname "$(readlink -f "${BASH_SOURCE}")")"
ROOT_DIR="${SCRIPT_DIR}/../../.."

OPS_USERS=
if [[ -z "${MINECRAFT_USERS}" ]]; then
  printf "MINECRAFT_USERS not set, not setting OPS (admin users)\n"
else
  OPS_USERS="-e OPS=${MINECRAFT_USERS}"
  printf "Setting OPS (admin users): %s\n" "${OPS_USERS}"
fi

# mvn clean package -f "${ROOT_DIR}"

JAR_FILE="$(find target/*.jar -maxdepth 1 -type f | grep -v original)"

CONTAINER_NAME="mc-plugin-playground"

docker rm "${CONTAINER_NAME}" || true

# ${OPS_USERS} \
docker run \
    -d \
    -it \
    --name "${CONTAINER_NAME}" \
    -p 25565:25565 \
    -e EULA=TRUE \
    -e TYPE=SPIGOT \
    -e JVM_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" \
    -p 5005:5005 \
    --mount type=bind,source="${ROOT_DIR}/${JAR_FILE},target=/plugins/$(basename "${JAR_FILE}")",readonly \
    itzg/minecraft-server