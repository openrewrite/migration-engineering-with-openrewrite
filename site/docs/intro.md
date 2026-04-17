---
sidebar_position: 0
---

# Intro

Today, we're going to apply migration engineering with OpenRewrite.

This workshop consists of several sections, each focusing on a different aspect of improving your code.

| # | Section | Time | Level |
|---|---------|------|-------|
| 1 | [Analyze first](./category/analyze-first/) - Find what you're currently using, and how that would need to change. | ~30 min | Beginner |
| 2 | [Outdated patterns](./category/outdated-patterns/) - Common legacy testing frameworks and libraries that are still in use today. | ~45 min | Beginner |
| 3 | [JUnit Jupiter](./category/junit-jupiter/) - Migrate to JUnit 5 (Jupiter), learn about JUnit 6, and explore features like parameterized and nested tests. | ~45 min | Beginner - Intermediate |
| 4 | [Adopt AssertJ](./category/adopt-assertj/) - Dive into AssertJ for more expressive assertions. | ~60 min | Intermediate |
| 5 | [Upgrade your projects](./category/upgrade-your-projects/) - Apply what we've learned to upgrade real-world projects. | ~75 min | Intermediate |
| 6 | [Secure your projects](./category/secure-your-projects/) - Scan for vulnerable dependencies and remediate common OWASP findings. | ~30 min | Beginner - Intermediate |
| 7 | [Recipe development](./category/recipe-development/) - Create custom OpenRewrite recipes to automate improvements in your own codebases. | ~60 min | Advanced (optional) |

:::tip

**Short on time?** Sections 1-4 form a self-contained "quick wins" track (~3 hours) that covers the most impactful improvements. Sections 5-7 go deeper and can be tackled later.

:::

When you're done, see [Further resources](./further-resources) for links to keep learning.

## Getting Started

### Clone the repository

To get started, clone the workshop repository:

```bash
git clone https://github.com/openrewrite/migration-engineering-with-openrewrite.git
cd migration-engineering-with-openrewrite
```

This repository contains example code that you'll use throughout the workshop to explore the topics, write tests, and practice refactoring techniques.

The repository has three top-level directories you should know about:

- `books/` - Example projects that the recipes get applied to. You'll see before/after transformations here.
- `recipes/` - Refaster templates and tests you'll complete in the [Recipe development](./category/recipe-development/) section.
- `site/` - This documentation site. You generally won't edit anything here.

### Workshop structure

We recommend going through the sections in order, but you're welcome to go at your own pace.

You're also welcome to use your own projects to try out the improvements alongside the provided examples.

### What you'll need

To run recipes (sections 1-6):

- Java 8+
- [Moderne CLI](https://docs.moderne.io/user-documentation/moderne-cli/getting-started/cli-intro) (recommended), or
- Maven 3+, or
- Gradle 4.10+

To develop your own recipes (the [Recipe development](./category/recipe-development/) section only):

- Java 25
