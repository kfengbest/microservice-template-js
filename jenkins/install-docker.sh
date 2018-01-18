#!/bin/bash

BASE_URL="https://download.docker.com/linux/static/stable/x86_64/"
DIRECTORY_LISTING=$(curl -s ${BASE_URL} | awk 'BEGIN{ RS="<a *href *= *\""} NR>2 {sub(/".*/,"");print; }')
RELEASES=(${DIRECTORY_LISTING// / })
CURRENT_RELEASE=${RELEASES[-1]}
CURRENT_RELEASE_URL="${BASE_URL}${CURRENT_RELEASE}"
TMP_DIR="$HOME/.tmp-docker-install-files"

mkdir ${TMP_DIR}
cd ${TMP_DIR}

echo "Download docker binaries from ... ${CURRENT_RELEASE_URL}"
curl -s ${CURRENT_RELEASE_URL} --output "${TMP_DIR}/${CURRENT_RELEASE}"

echo "Extracting binaries ..."
tar xzf "${TMP_DIR}/${CURRENT_RELEASE}"

echo "Making binaries accessable ..."
rm "${TMP_DIR}/docker/dockerd"
cp "${TMP_DIR}/docker/docker" /usr/local/bin

echo "Cleaning up ..."
rm -rf ${TMP_DIR}
