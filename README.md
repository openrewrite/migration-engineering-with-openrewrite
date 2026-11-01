# [Migration Engineering with OpenRewrite](https://openrewrite.github.io/migration-engineering-with-openrewrite/)

> [!NOTE]
> This repository is archived. The workshop was presented at conferences, but is unlikely to be presented again.
> The material remains available as-is for self-study, but it is no longer maintained:
> recipe versions, dependencies and instructions may drift out of date.

A hands-on workshop on using [OpenRewrite](https://docs.openrewrite.org/) to analyze, modernize and secure Java codebases,
with a focus on testing frameworks and assertions.
The [workshop site](https://openrewrite.github.io/migration-engineering-with-openrewrite/) walks through these sections:

1. **Analyze first**: find dependencies, deprecated APIs and relocated artifacts before migrating.
2. **Outdated patterns**: move away from JUnit 3 and 4, Hamcrest, older Java idioms and Spring MockMvc patterns.
3. **JUnit Jupiter**: migrate to JUnit 5 and 6, and apply Jupiter best practices.
4. **Adopt AssertJ**: migrate to AssertJ and adopt more expressive, type-specific assertions.
5. **Upgrade your projects**: Spring Boot 4, Jackson 3, Java 25 for tests, JSpecify, Mockito and SLF4J best practices, Sonar findings and rollout order.
6. **Secure your projects**: find and fix vulnerable dependencies and remediate OWASP Top Ten findings.
7. **Recipe development**: write and test custom OpenRewrite recipes.

## Repository layout

- `books/`: an example project that the recipes are applied to.
- `recipes/`: Refaster templates and tests completed in the recipe development section.
- `site/`: the [Docusaurus](https://docusaurus.io/) source of the workshop site.
