---
sidebar_position: 2
---
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Remediate OWASP Top Ten Vulnerabilities

To identify and remediate vulnerabilities from the OWASP Top Ten, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/security/owasptopten) `org.openrewrite.java.security.OwaspTopTen` from `rewrite-java-security`.

This recipe automatically scans your Java codebase to detect and fix security issues aligned with the OWASP Top Ten, which represents the most critical web application security risks. It covers categories including broken access control, cryptographic failures, injection, security misconfiguration, vulnerable components, and software integrity failures.

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
mod run ~/workspace/ --recipe org.openrewrite.java.security.OwaspTopTen
```

</TabItem>
</Tabs>

## What's covered

This recipe addresses the following OWASP Top Ten 2021 categories:

- **A01:2021 Broken Access Control** - Fixes access control vulnerabilities
- **A02:2021 Cryptographic Failures** - Remediates weak cryptographic implementations
- **A03:2021 Injection** - Addresses SQL injection, XSS, and other injection flaws
- **A05:2021 Security Misconfiguration** - Corrects security configuration issues
- **A06:2021 Vulnerable and Outdated Components** - Upgrades dependencies with known vulnerabilities
- **A08:2021 Software and Data Integrity Failures** - Fixes integrity-related security issues

## Next steps

Review the changes made by the recipe and run your test suite to verify that the security fixes don't introduce regressions. Consider running individual OWASP category recipes if you want more targeted remediation.
