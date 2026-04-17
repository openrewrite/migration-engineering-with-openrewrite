---
sidebar_position: 2
---
import RunRecipe from '@site/src/components/RunRecipe';

# Remediate OWASP Top Ten Vulnerabilities

To identify and remediate vulnerabilities from the OWASP Top Ten, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/security/owasptopten) `org.openrewrite.java.security.OwaspTopTen` from `rewrite-java-security`.

This recipe automatically scans your Java codebase to detect and fix security issues aligned with the OWASP Top Ten, which represents the most critical web application security risks. It covers categories including broken access control, cryptographic failures, injection, security misconfiguration, vulnerable components, and software integrity failures.

:::note

The `rewrite-java-security` recipes are only available through the [Moderne CLI](https://docs.moderne.io/user-documentation/moderne-cli/getting-started/cli-intro).

:::

<RunRecipe
  recipeName="org.openrewrite.java.security.OwaspTopTen"
  artifact="org.openrewrite.recipe:rewrite-java-security"
  cliOnly={true}
/>

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
