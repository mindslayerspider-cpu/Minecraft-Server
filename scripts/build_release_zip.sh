#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
OUT_DIR="${ROOT_DIR}/dist"
VERSION="${1:-v1}"
ZIP_NAME="DexOS-11-ginkgo-starter-${VERSION}.zip"

mkdir -p "${OUT_DIR}"

cd "${ROOT_DIR}"
zip -r "${OUT_DIR}/${ZIP_NAME}" \
  README.md \
  rom/redmi-note8-gaming \
  docs/INSTALL.md >/dev/null

echo "Created: ${OUT_DIR}/${ZIP_NAME}"
