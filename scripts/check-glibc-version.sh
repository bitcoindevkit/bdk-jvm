#!/usr/bin/env bash
set -euo pipefail

readonly DEFAULT_LIBRARY_PATH="lib/src/main/resources/linux-x86-64/libbdkffi.so"
readonly DEFAULT_MAX_GLIBC_VERSION="2.35"

library_path="${1:-$DEFAULT_LIBRARY_PATH}"
max_glibc_version="${2:-$DEFAULT_MAX_GLIBC_VERSION}"

if ! command -v objdump >/dev/null 2>&1; then
    echo "error: objdump is required to inspect the native library" >&2
    exit 1
fi

if [[ ! -f "$library_path" ]]; then
    echo "error: native library not found: $library_path" >&2
    exit 1
fi

required_versions="$(
    objdump -T "$library_path" |
        sed -nE 's/.*\(GLIBC_([0-9]+(\.[0-9]+)+)\).*/\1/p' |
        sort -Vu
)"

if [[ -z "$required_versions" ]]; then
    echo "error: no versioned GLIBC symbols were found in $library_path" >&2
    exit 1
fi

highest_required_version="$(printf '%s\n' "$required_versions" | tail -n 1)"

echo "Native library: $library_path"
echo "Allowed maximum GLIBC version: $max_glibc_version"
echo "Required GLIBC versions:"
printf '  GLIBC_%s\n' $required_versions
echo "Highest required version: GLIBC_$highest_required_version"

highest_version="$(
    printf '%s\n%s\n' "$max_glibc_version" "$highest_required_version" |
        sort -Vu |
        tail -n 1
)"

if [[ "$highest_version" != "$max_glibc_version" ]]; then
    echo >&2
    echo "error: $library_path requires GLIBC_$highest_required_version," >&2
    echo "but the supported maximum is GLIBC_$max_glibc_version." >&2
    exit 1
fi

echo "GLIBC compatibility check passed."
