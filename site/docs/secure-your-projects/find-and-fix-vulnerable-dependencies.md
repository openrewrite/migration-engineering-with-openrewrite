---
sidebar_position: 1
---
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Find and Fix Vulnerable Dependencies

To detect and upgrade dependencies with publicly disclosed vulnerabilities, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/dependencies/dependencyvulnerabilitycheck) `org.openrewrite.java.dependencies.DependencyVulnerabilityCheck` from `rewrite-java-security`.

This Software Composition Analysis (SCA) recipe scans your project for dependencies with known vulnerabilities using data from the GitHub Security Advisory Database and the National Vulnerability Database. It generates a vulnerability report and automatically upgrades vulnerable dependencies to patched versions.

You can run the recipe using one of the following methods.

<Tabs groupId="projectType">
<TabItem value="moderne-cli" label="Moderne CLI">

The Moderne CLI allows you to run OpenRewrite recipes on your project without needing to modify your build files,
against serialized Lossless Semantic Tree (LST) of your project for a considerable performance boost & across projects.

You will need to have configured the [Moderne CLI](https://docs.moderne.io/user-documentation/moderne-cli/getting-started/cli-intro) on your machine before you can run the following command.

1. If project serialized Lossless Semantic Tree is not yet available locally, then build the LST.
   This is only needed the first time, or after extensive changes:
```bash title="shell"
mod build ~/workspace/
```

2. If the recipe is not available locally yet, then you can install it once using:
```shell title="shell"
mod config recipes jar install org.openrewrite.recipe:rewrite-java-security:LATEST
```

3. Run the recipe.
```shell title="shell"
mod run ~/workspace/ --recipe org.openrewrite.java.dependencies.DependencyVulnerabilityCheck
```

</TabItem>
</Tabs>

## Recipe options

This recipe supports several options to customize its behavior:

| Option | Description | Default |
|--------|-------------|---------|
| `scope` | Filter dependencies by scope (`compile`, `test`, `runtime`, `provided`) | `runtime` |
| `overrideTransitive` | When enabled, transitive dependencies with vulnerabilities will have their versions overridden | `false` |
| `maximumUpgradeDelta` | Maximum allowable upgrade level (`patch`, `minor`, `major`) | `patch` |
| `minimumSeverity` | Vulnerability severity threshold (`low`, `moderate`, `high`, `critical`) | `low` |
| `cvePattern` | Regex pattern to filter specific CVEs | — |

## Next steps

Review the generated vulnerability report in the build output to understand which dependencies were flagged and upgraded. Consider adjusting the `maximumUpgradeDelta` option if patch-level upgrades are insufficient to address certain vulnerabilities.
