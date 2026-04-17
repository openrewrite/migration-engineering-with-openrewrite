---
sidebar_position: 1
---
import RunRecipe from '@site/src/components/RunRecipe';

# Find and Fix Vulnerable Dependencies

To detect and upgrade dependencies with publicly disclosed vulnerabilities, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/dependencies/dependencyvulnerabilitycheck) `org.openrewrite.java.dependencies.DependencyVulnerabilityCheck` from `rewrite-java-security`.

This Software Composition Analysis (SCA) recipe scans your project for dependencies with known vulnerabilities using data from the GitHub Security Advisory Database and the National Vulnerability Database. It generates a vulnerability report and automatically upgrades vulnerable dependencies to patched versions.

:::note

The `rewrite-java-security` recipes are only available through the [Moderne CLI](https://docs.moderne.io/user-documentation/moderne-cli/getting-started/cli-intro).

:::

<RunRecipe
  recipeName="org.openrewrite.java.dependencies.DependencyVulnerabilityCheck"
  artifact="org.openrewrite.recipe:rewrite-java-security"
  cliOnly={true}
  modRunArgs={`-P "scope=runtime" -P "overrideTransitive=True" -P "maximumUpgradeDelta=minor"`}
/>

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
