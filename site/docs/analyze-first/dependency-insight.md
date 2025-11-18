---
sidebar_position: 2
---
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Dependency Insight

To identify where a specific dependency is being used in your project, including its transitive dependencies and version conflicts, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/dependencies/dependencyinsight) `org.openrewrite.java.dependencies.DependencyInsight` from `rewrite-java-dependencies`.

This recipe provides detailed insight into a dependency's usage across your project, showing both direct and transitive dependencies. It helps you understand dependency trees, identify version conflicts, and trace where specific libraries are coming from in your build. The recipe generates a data table with comprehensive dependency information.

You can run the search recipe using one of the following methods, after creating a local `rewrite.yml` file in your project.

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
mod config recipes jar install org.openrewrite.recipe:rewrite-java-dependencies:LATEST
```

3. Run the recipe.
```shell title="shell"
mod run ~/workspace/  --recipe DependencyInsight --recipe-option "groupIdPattern=*" --recipe-option "artifactIdPattern=*"
```

</TabItem>
<TabItem value="maven-command-line" label="Maven Command Line">

You will need to have [Maven](https://maven.apache.org/download.cgi) installed on your machine before you can run the following command.

```shell title="shell"
mvn -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-java-dependencies:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.dependencies.DependencyInsight -Drewrite.exportDatatables=true
```

</TabItem>
<TabItem value="maven" label="Maven POM">

You may add the plugin to your `pom.xml` file, so that it is available for all developers and CI/CD pipelines.

1. Add the following to your `pom.xml` file:

```xml title="pom.xml"
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>org.openrewrite.maven</groupId>
        <artifactId>rewrite-maven-plugin</artifactId>
        <version>LATEST</version>
        <configuration>
          <exportDatatables>true</exportDatatables>
          <activeRecipes>
            <recipe>org.openrewrite.java.dependencies.DependencyInsight</recipe>
          </activeRecipes>
        </configuration>
        <dependencies>
          <dependency>
            <groupId>org.openrewrite.recipe</groupId>
            <artifactId>rewrite-java-dependencies</artifactId>
            <version>LATEST</version>
          </dependency>
        </dependencies>
      </plugin>
    </plugins>
  </build>
</project>
```

2. Run the recipe.
```shell title="shell"
mvn rewrite:run
```

</TabItem>
<TabItem value="gradle-init-script" label="Gradle init script">

Gradle init scripts are a good way to try out a recipe without modifying your `build.gradle` file.

1. Create a file named `init.gradle` in the root of your project.

```groovy title="init.gradle"
initscript {
    repositories {
        maven { url "https://plugins.gradle.org/m2" }
    }
    dependencies { classpath("org.openrewrite:plugin:latest.release") }
}
rootProject {
    plugins.apply(org.openrewrite.gradle.RewritePlugin)
    dependencies {
        rewrite("org.openrewrite.recipe:rewrite-java-dependencies:latest.release")
    }
    rewrite {
        activeRecipe("org.openrewrite.java.dependencies.DependencyInsight")
        setExportDatatables(true)
    }
    afterEvaluate {
        if (repositories.isEmpty()) {
            repositories {
                mavenCentral()
            }
        }
    }
}
```

2. Run the recipe.

```shell title="shell"
gradle --init-script init.gradle rewriteRun
```

</TabItem>
<TabItem value="gradle" label="Gradle">

You can add the plugin to your `build.gradle` file, so that it is available for all developers and CI/CD pipelines.

1. Add the following to your `build.gradle` file:

```groovy title="build.gradle"
plugins {
    id("org.openrewrite.rewrite") version("latest.release")
}

rewrite {
    activeRecipe("org.openrewrite.java.dependencies.DependencyInsight")
    setExportDatatables(true)
}

repositories {
    mavenCentral()
}

dependencies {
    rewrite("org.openrewrite.recipe:rewrite-java-dependencies:latest.release")
}
```

2. Run `gradle rewriteRun` to run the recipe.

</TabItem>
</Tabs>

## Analyze the Results

After running the recipe, you will find a data table for `DependenciesInUse` in the `target/rewrite/datatables/` directory for Maven, or `build` for Gradle build, or in the output directory specified for the Moderne CLI.
Compare the versions of dependencies in use with the [Spring Boot 4 managed dependency versions](https://docs.spring.io/spring-boot/4.0/appendix/dependency-versions/coordinates.html) to identify any discrepancies that may need to be addressed before upgrading.
Dependencies to look out for include Hibernate, Jackson, Kafka, ElasticSearch, among others, all of which see major version updates in Spring Boot 4.
