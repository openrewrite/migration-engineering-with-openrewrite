---
sidebar_position: 2
---
import RunRecipe from '@site/src/components/RunRecipe';

# Dependency Insight

To identify where a specific dependency is being used in your project, including its transitive dependencies and version conflicts, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/dependencies/dependencyinsight) `org.openrewrite.java.dependencies.DependencyInsight` from `rewrite-java-dependencies`.

This recipe provides detailed insight into a dependency's usage across your project, showing both direct and transitive dependencies. It helps you understand dependency trees, identify version conflicts, and trace where specific libraries are coming from in your build. The recipe generates a data table with comprehensive dependency information.

You can run the search recipe using one of the following methods. Because `DependencyInsight` takes required `groupIdPattern` / `artifactIdPattern` arguments, you'll first wrap it in a small local `rewrite.yml` so the build tool can pass those arguments.

:::tip Pin versions in CI

Throughout this workshop we use `LATEST` (Maven) and `latest.release` (Gradle) so you always get the newest recipes. For reproducible CI and production builds, pin a specific version instead (e.g. `<version>1.54.0</version>`).

:::

Add the following recipe definition to a `rewrite.yml` at the **root of your project** (or, if you're following along on the workshop's `books/` module, append a new `---` document to its existing `books/rewrite.yml`):

```yaml
---
type: specs.openrewrite.org/v1beta/recipe
name: com.yourorg.DependencyInsightExample
displayName: Dependency insight for Gradle and Maven example
recipeList:
  - org.openrewrite.java.dependencies.DependencyInsight:
      groupIdPattern: "*"
      artifactIdPattern: "*"
```

:::warning Multi-module projects

In multi-module Maven builds, the rewrite-maven-plugin looks for `rewrite.yml` at the **root** project directory (the same location as the parent `pom.xml`), not in each module. If your `rewrite.yml` lives inside a sub-module, add `-Drewrite.configLocation=rewrite.yml` (path relative to the module) to the Maven CLI command below, otherwise you will see:

```
Recipe(s) not found: com.yourorg.DependencyInsightExample
```

:::

<RunRecipe
  recipeName="com.yourorg.DependencyInsightExample"
  recipeDisplayName="Dependency insight for Gradle and Maven example"
  artifact="org.openrewrite.recipe:rewrite-java-dependencies"
  intellijWrapperName="com.yourorg.DependencyInsightExample"
  intellijDescription="Dependency insight for Gradle and Maven example."
  requiresYamlInstall={true}
/>

## Analyze the Results

After running the recipe, you will find a data table for `DependenciesInUse` in a timestamped subdirectory under `target/rewrite/datatables/` for Maven, or under `build/` for Gradle, or in the output directory specified for the Moderne CLI.
Compare the versions of dependencies in use with the [Spring Boot 4 managed dependency versions](https://docs.spring.io/spring-boot/4.0/appendix/dependency-versions/coordinates.html) to identify any discrepancies that may need to be addressed before upgrading.
Dependencies to look out for include Hibernate, Jackson, Kafka, ElasticSearch, among others, all of which see major version updates in Spring Boot 4.
