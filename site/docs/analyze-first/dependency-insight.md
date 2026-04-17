---
sidebar_position: 2
---
import RunRecipe from '@site/src/components/RunRecipe';

# Dependency Insight

To identify where a specific dependency is being used in your project, including its transitive dependencies and version conflicts, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/dependencies/dependencyinsight) `org.openrewrite.java.dependencies.DependencyInsight` from `rewrite-java-dependencies`.

This recipe provides detailed insight into a dependency's usage across your project, showing both direct and transitive dependencies. It helps you understand dependency trees, identify version conflicts, and trace where specific libraries are coming from in your build. The recipe generates a data table with comprehensive dependency information.

You can run the search recipe using one of the following methods, after creating a local `rewrite.yml` file in your project.

:::tip Pin versions in CI

Throughout this workshop we use `LATEST` (Maven) and `latest.release` (Gradle) so you always get the newest recipes. For reproducible CI and production builds, pin a specific version instead (e.g. `<version>1.54.0</version>`).

:::

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

<RunRecipe
  recipeName="com.yourorg.DependencyInsightExample"
  recipeDisplayName="Dependency insight for Gradle and Maven example"
  artifact="org.openrewrite.recipe:rewrite-java-dependencies"
  intellijWrapperName="com.yourorg.DependencyInsightExample"
  intellijDescription="Dependency insight for Gradle and Maven example."
  requiresYamlInstall={true}
/>

## Analyze the Results

After running the recipe, you will find a data table for `DependenciesInUse` in the `target/rewrite/datatables/` directory for Maven, or `build` for Gradle build, or in the output directory specified for the Moderne CLI.
Compare the versions of dependencies in use with the [Spring Boot 4 managed dependency versions](https://docs.spring.io/spring-boot/4.0/appendix/dependency-versions/coordinates.html) to identify any discrepancies that may need to be addressed before upgrading.
Dependencies to look out for include Hibernate, Jackson, Kafka, ElasticSearch, among others, all of which see major version updates in Spring Boot 4.
