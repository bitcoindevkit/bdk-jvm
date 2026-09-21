---
name: "Release"
about: "Create a new release [for release managers only]"
title: "Release MAJOR.MINOR.PATCH"
---

## Todo list for publishing the release

- [ ] Bump the bdk-ffi submodule to the release tag in bdk-ffi.
- [ ] Delete the `target` directory in bdk-ffi and the `build` directory in `lib` to make sure you're building the library from scratch without any caches.
- [ ] Build the library.
- [ ] Run the tests and adjust them if necessary.

```shell
just clean
just build
just test
```

- [ ] Update the readme if necessary
- [ ] PR any changes from the steps above if necessary and get them into `master`.
- [ ] Create a new branch off of `master` called `release/<feature version>`, e.g. `release/2.3`.
- [ ] Update library version from `SNAPSHOT` version to release version, e.g. `2.0.0-SNAPSHOT` to `2.0.0`.
- [ ] Create the tag for the release and make sure to add a link to the bdk-ffi changelog to the tag. Push the tag to GitHub. An example of the tag message would be:

```md
Release 3.0.0

For details on this release, see the bdk-ffi repository and [our release notes for the 3.0.0 release](https://github.com/bitcoindevkit/bdk-ffi/releases/tag/v3.0.0) as well as our [Changelog](https://github.com/bitcoindevkit/bdk-ffi/blob/master/CHANGELOG.md).
```

```shell
git tag v2.3.0 --sign --edit
# Add message above
git push upstream v2.3.0
```

- [ ] Build and upload the release through the workflow dispatch with the new tag (`publish.yml`). Select the release tag in the _Run workflow > Use workflow from_ dropdown. The workflow checks that the library version matches the tag, builds the native libraries for all targets, runs the tests, and uploads the signed deployment to the Central Portal.
- [ ] Publish the release on Maven Central by logging in to https://central.sonatype.com, reviewing the staged deployment, and clicking _Publish_. Note that a published version can never be replaced or removed.
- [ ] Go to https://javadoc.io/versions/org.bitcoindevkit/bdk-jvm and download the latest version of the API docs, which will publish them at https://javadoc.io/doc/org.bitcoindevkit/bdk-jvm/latest/index.html.
- [ ] Bump the version on `master` while keeping the `SNAPSHOT` suffix, e.g., from `1.1.0-SNAPSHOT` to `1.2.0-SNAPSHOT`.
- [ ] Update this release workflow if necessary.
