[group("Repo")]
[doc("Default command; list all available commands.")]
@list:
  just --list --unsorted

[group("Repo")]
[doc("Open repo on GitHub in your default browser.")]
repo:
  open https://github.com/bitcoindevkit/bdk-jvm

[group("Repo")]
[doc("Build the API docs.")]
docs:
  ./gradlew :lib:dokkaGeneratePublicationHtml

[group("Repo")]
[doc("Publish the library to your local Maven repository.")]
publish-local:
  ./gradlew publishToMavenLocal -P localBuild

[group("Submodule")]
[doc("Initialize bdk-ffi submodule to committed hash.")]
submodule-init:
  git submodule update --init

[group("Submodule")]
[doc("Hard reset the bdk-ffi submodule to committed hash.")]
submodule-reset:
  git submodule update --force

[group("Submodule")]
[doc("Checkout the bdk-ffi submodule to the latest commit on master.")]
submodule-to-master:
  cd ./bdk-ffi/ \
  && git fetch origin \
  && git checkout master \
  && git pull origin master

[group("Build")]
[doc("Build the library for given ARCH. Will use the committed version of the submodule.")]
build ARCH="macos-aarch64":
  bash ./scripts/build-{{ARCH}}.sh

[group("Build")]
[doc("List available architectures for the build command.")]
@list-architectures:
    echo "Available architectures:"
    echo "    - linux-x86_64"
    echo "    - macos-aarch64"
    echo "    - macos-x86_64"
    echo "    - windows-x86_64"

[group("Build")]
[doc("Remove all caches and previous build directories to start from scratch.")]
clean:
  rm -rf ./bdk-ffi/bdk-ffi/target/
  rm -rf ./build/
  rm -rf ./lib/build/
  rm -rf ./examples/build/
  rm -rf ./examples/data/

[group("Test")]
[doc("Run all tests, unless a specific test is provided.")]
test *TEST:
  ./gradlew test {{ if TEST == "" { "" } else { "--tests " + TEST } }}
