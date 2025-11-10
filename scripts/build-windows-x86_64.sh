#!/bin/bash

COMPILATION_TARGET="x86_64-pc-windows-msvc"
TARGET_DIR="target/x86_64-pc-windows-msvc/release-smaller"
RESOURCE_DIR="resources/win32-x86-64"
LIB_NAME="bdkffi.dll"

printf "\nSubmodule check...\n"
if [[ "$1" != "--skip-submodule-update" ]]; then
  git submodule update --init
  printf "Submodule is checked out at commit: $(git submodule status)\n\n"
else
  printf "Skipping submodule update, using local changes.\n"
  printf "Submodule is checked out at commit: $(git submodule status)\n\n"
fi

# Move to the Rust library directory
cd ./bdk-ffi/bdk-ffi/ || exit

# Build the Rust library
rustup target add $COMPILATION_TARGET
cargo build --profile release-smaller --target $COMPILATION_TARGET

# Generate Kotlin bindings using uniffi-bindgen
cargo run --bin uniffi-bindgen generate --library ./$TARGET_DIR/$LIB_NAME --language kotlin --out-dir ../../lib/src/main/kotlin/ --no-format

# Copy the binary to the resources directory
mkdir -p ../../lib/src/main/$RESOURCE_DIR/
cp ./$TARGET_DIR/$LIB_NAME ../../lib/src/main/$RESOURCE_DIR/
