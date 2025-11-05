---
name: "Release"
about: "Create a new release [for release managers only]"
title: "Release MAJOR.MINOR.PATCH"
---

- [ ] Bump the bdk-ffi submodule to the release tag in bdk-ffi and merge that to `master`.
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
Release 2.2.0

For information on this release, see the bdk-ffi repository and [our release notes for the 2.2.0 release](https://github.com/bitcoindevkit/bdk-ffi/releases/tag/v2.2.0) as well as our [Changelog](https://github.com/bitcoindevkit/bdk-ffi/blob/master/CHANGELOG.md).
```

```shell
git tag v2.3.0 --sign --edit
# Add message above
git push upstream v2.3.0
```

- [ ] Trigger release through the workflow dispatch with the new tag.
- [ ] Bump the version on master while keeping the `SNAPSHOT` suffix, e.g. from `1.1.0-SNAPSHOT` to `1.2.0-SNAPSHOT`.
- [ ] Update this release workflow if necessary.
- [ ] Build API docs and push them to the `gh-pages` branch, which will publish them automatically to.
