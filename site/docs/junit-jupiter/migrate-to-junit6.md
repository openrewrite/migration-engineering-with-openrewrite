---
sidebar_position: 2
---
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Migrate to JUnit 6

JUnit 6 was released in 2025. The main change is a baseline requirement of Java 17, which affects tests using conditional execution annotations like `@EnabledOnJre` and `@DisabledOnJre`.

## Java version baseline

JUnit 6 requires Java 17 as a minimum, dropping support for Java 8, 11, and earlier versions.
This impacts conditional test execution based on Java versions.

<Tabs>
<TabItem value="before" label="Before">

```java title="ConditionalTest.java"
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionalTest {

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
        assertTrue(true);
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void onlyOnJava11() {
        assertTrue(true);
    }

    @Test
    @DisabledOnJre(JRE.JAVA_8)
    void notOnJava8() {
        assertTrue(true);
    }
}
```

:::warning

With JUnit 6's Java 17 baseline, tests conditioned on Java 8-16 will never run on supported JDK versions.
These annotations become dead code that should be cleaned up.

:::

</TabItem>
<TabItem value="after" label="After">

```java title="ConditionalTest.java"
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionalTest {

    // Tests conditioned on Java 8-16 are removed since they can never run

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void onlyOnJava17() {
        assertTrue(true);
    }

    @Test
    @EnabledOnJre(JRE.JAVA_21)
    void onlyOnJava25() {
        assertTrue(true);
    }

    @Test
    @DisabledOnJre(JRE.JAVA_17)
    void notOnJava17() {
        assertTrue(true);
    }
}
```

:::tip

After migrating to JUnit 6:
- Remove tests with `@EnabledOnJre` for Java 8-16 (they'll never run)
- Remove `@DisabledOnJre` for Java 8-16 (they're always enabled now)
- Update remaining conditional annotations to use Java 17+

:::

</TabItem>
</Tabs>

## Cleaning up obsolete conditions

Tests that were disabled on old Java versions can now be unconditionally enabled.

<Tabs>
<TabItem value="before" label="Before">

```java title="ModernFeatureTest.java"
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModernFeatureTest {

    @Test
    @DisabledOnJre({JRE.JAVA_8, JRE.JAVA_11})
    void usesTextBlocks() {
        String text = """
                This is a
                multi-line
                string
                """;
        assertEquals(3, text.lines().count());
    }

    @Test
    @DisabledOnJre(JRE.JAVA_8)
    void usesVar() {
        var list = List.of(1, 2, 3);
        assertEquals(3, list.size());
    }
}
```

:::info

These tests were disabled on older Java versions but can now run unconditionally since JUnit 6 requires Java 17.

:::

</TabItem>
<TabItem value="after" label="After">

```java title="ModernFeatureTest.java"
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModernFeatureTest {

    @Test
    void usesTextBlocks() {
        String text = """
                This is a
                multi-line
                string
                """;
        assertEquals(3, text.lines().count());
    }

    @Test
    void usesVar() {
        var list = List.of(1, 2, 3);
        assertEquals(3, list.size());
    }
}
```

:::tip

Remove obsolete `@DisabledOnJre` annotations for Java versions below 17, as these tests will always run on supported platforms.

:::

</TabItem>
</Tabs>

## Parameterized Classes (JUnit 6)

When you have multiple test methods that all need to operate on the same object instance with different configurations, duplicating test logic becomes tedious. While `@ParameterizedTest` is great for testing a single method with different inputs, `@ParameterizedClass` takes parameterization to the class level, running all test methods for each parameter set.

### Understanding the use case

Imagine you're testing various aspects of a `ShoppingCart` object—checking if it calculates totals correctly, applies discounts properly, handles taxes, etc. Each test method currently creates the same cart configuration. To test with different cart scenarios, you face two unappealing options:
1. Duplicate the entire test class for each cart scenario you want to test
2. Convert every test method to use `@ParameterizedTest`, adding complexity to each method

`@ParameterizedClass` solves this by parameterizing the entire class: you write your test suite once, and it automatically runs against multiple object instances. Think of it as "`@ParameterizedTest` for an entire class."

### How it works

With `@ParameterizedClass`:
- The parameterization annotations are placed on the test **class** (not individual methods)
- All `@Test` methods run once for each parameter set provided
- Parameters can be injected via constructor or fields annotated with `@Parameter`
- Each parameter set gets its own fresh test class instance
- Perfect for testing the same object behavior with different configurations

<Tabs>
<TabItem value="problem" label="Without @ParameterizedClass">

```java title="ShoppingCartTest.java"
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ShoppingCartTest {

    @Test
    void cartIsNotNull() {
        ShoppingCart cart = new ShoppingCart(new BigDecimal("100.00"), new BigDecimal("0.10"));
        assertThat(cart).isNotNull();
    }

    @Test
    void cartCalculatesSubtotal() {
        ShoppingCart cart = new ShoppingCart(new BigDecimal("100.00"), new BigDecimal("0.10"));
        assertThat(cart.getSubtotal()).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void cartAppliesDiscount() {
        ShoppingCart cart = new ShoppingCart(new BigDecimal("100.00"), new BigDecimal("0.10"));
        assertThat(cart.getDiscount()).isEqualByComparingTo(new BigDecimal("10.00"));
    }

    @Test
    void cartCalculatesTotal() {
        ShoppingCart cart = new ShoppingCart(new BigDecimal("100.00"), new BigDecimal("0.10"));
        assertThat(cart.getTotal()).isEqualByComparingTo(new BigDecimal("90.00"));
    }
}
```

:::warning

Every test method creates the same `ShoppingCart` instance. To test multiple cart scenarios, you'd need to duplicate the entire test class or use `@ParameterizedTest` on each method individually.

:::

</TabItem>
<TabItem value="solution" label="With @ParameterizedClass">

```java title="ShoppingCartTest.java"
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ParameterizedClass
@CsvSource({
    "100.00, 0.10, 10.00, 90.00",   // 10% discount
    "250.00, 0.20, 50.00, 200.00",  // 20% discount
    "50.00, 0.00, 0.00, 50.00"      // No discount
})
class ShoppingCartTest {

    private final ShoppingCart cart;
    private final BigDecimal expectedDiscount;
    private final BigDecimal expectedTotal;

    // Constructor injection: parameters are injected via the constructor
    ShoppingCartTest(BigDecimal subtotal, BigDecimal discountRate,
                     BigDecimal expectedDiscount, BigDecimal expectedTotal) {
        this.cart = new ShoppingCart(subtotal, discountRate);
        this.expectedDiscount = expectedDiscount;
        this.expectedTotal = expectedTotal;
    }

    @Test
    void cartIsNotNull() {
        assertThat(cart).isNotNull();
    }

    @Test
    void cartCalculatesSubtotal() {
        assertThat(cart.getSubtotal()).isPositive();
    }

    @Test
    void cartAppliesDiscount() {
        assertThat(cart.getDiscount()).isEqualByComparingTo(expectedDiscount);
    }

    @Test
    void cartCalculatesTotal() {
        assertThat(cart.getTotal()).isEqualByComparingTo(expectedTotal);
    }
}
```

:::tip

With `@ParameterizedClass`:
- All 4 test methods run for each of the 3 cart scenarios = 12 test executions total
- No duplication of the `ShoppingCart` instance creation
- Easy to add more scenarios by adding a line to `@CsvSource`
- All tests stay organized in one class

:::

</TabItem>
</Tabs>


### Using Java Records as Test Class

For modern Java projects (Java 16+), you can make the entire test class a record for maximum conciseness:

```java title="ParameterizedExampleTest.java"
package com.github.timtebeek.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

/**
 * Test class demonstrating JUnit 6's @ParameterizedClass as a Java record.
 * The entire test class is a record - concise and expressive!
 */
@ParameterizedClass
@MethodSource("books")
record ParameterizedExampleTest(String title, String author, int year) {

    static Stream<Arguments> books() {
        return Stream.of(
                argumentSet("Effective Java", "Effective Java", "Joshua Bloch", 2001),
                argumentSet("Java Concurrency", "Java Concurrency in Practice", "Brian Goetz", 2006),
                argumentSet("Clean Code", "Clean Code", "Robert C. Martin", 2008)
        );
    }

    @Test
    void bookHasTitle() {
        assertThat(title).isNotBlank();
    }

    @Test
    void bookHasAuthor() {
        assertThat(author).isNotBlank();
    }

    @Test
    void bookHasYear() {
        assertThat(year).isPositive();
    }

    @Test
    void bookIsClassic() {
        assertThat(year)
                .as("Books published before 2010 are considered classics")
                .isLessThan(2010);
    }

    @Test
    void bookToStringContainsTitle() {
        assertThat(toString()).contains(title);
    }
}
```

:::tip

**Benefits of using test class as a record:**
- **Ultra-concise**: No explicit fields, constructor, or getters needed
- **Direct parameter access**: The record components (`title`, `author`, `year`) are automatically available in all test methods
- **Immutable by design**: Perfect for test data that shouldn't change
- **Built-in `toString()`**: Provides clear test output showing all parameter values
- **Natural fit**: Parameters flow directly from `@MethodSource` to record components to test methods

This is the most elegant approach when your test class only needs to hold the parameterized data!

:::

## Automated migration

OpenRewrite provides a recipe to automatically migrate from JUnit 5 to JUnit 6.

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
mod config recipes jar install org.openrewrite.recipe:rewrite-testing-frameworks:LATEST
```

3. Run the recipe.
```shell title="shell"
mod run ~/workspace/ --recipe org.openrewrite.java.testing.junit6.JUnit5to6Migration
```

</TabItem>
<TabItem value="maven-command-line" label="Maven Command Line">

You will need to have [Maven](https://maven.apache.org/download.cgi) installed on your machine before you can run the following command.

```shell title="shell"
mvn -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-testing-frameworks:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.testing.junit6.JUnit5to6Migration -Drewrite.exportDatatables=true
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
            <recipe>org.openrewrite.java.testing.junit6.JUnit5to6Migration</recipe>
          </activeRecipes>
        </configuration>
        <dependencies>
          <dependency>
            <groupId>org.openrewrite.recipe</groupId>
            <artifactId>rewrite-testing-frameworks</artifactId>
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
        rewrite("org.openrewrite.recipe:rewrite-testing-frameworks:latest.release")
    }
    rewrite {
        activeRecipe("org.openrewrite.java.testing.junit6.JUnit5to6Migration")
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
    activeRecipe("org.openrewrite.java.testing.junit6.JUnit5to6Migration")
    setExportDatatables(true)
}

repositories {
    mavenCentral()
}

dependencies {
    rewrite("org.openrewrite.recipe:rewrite-testing-frameworks:latest.release")
}
```

2. Run `gradle rewriteRun` to run the recipe.

</TabItem>
<TabItem value="intelliJ" label="IntelliJ IDEA Ultimate">

You can run OpenRewrite recipes directly from IntelliJ IDEA Ultimate, by adding a `rewrite.yml` file to your project.

```yaml title="rewrite.yml"
---
type: specs.openrewrite.org/v1beta/recipe
name: org.openrewrite.AdoptJUnitJupiter
displayName: Adopt JUnit 6
description: Adopt JUnit 6 and apply best practices to assertions.
recipeList:
  - org.openrewrite.java.testing.junit6.JUnit5to6Migration
```

After adding the file, you should see a run icon in the left margin offering to run the recipe.

</TabItem>
</Tabs>

:::tip

OpenRewrite's [JUnit5to6Migration](https://docs.openrewrite.org/recipes/java/testing/junit6/junit5to6migration) recipe automatically:
- Removes tests with `@EnabledOnJre` for Java 8-16
- Removes `@DisabledOnJre` annotations for Java 8-16
- Cleans up obsolete conditional execution logic
- Updates JUnit dependencies to version 6.x

:::

## What hasn't changed

Despite the version number change, JUnit 6 is not a breaking change in terms of API.

:::info

JUnit 6 maintains API compatibility with JUnit 5:
- All JUnit 5 APIs continue to work
- Package names remain `org.junit.jupiter.api.*`
- Annotation names and behavior are unchanged
- The main changes are internal improvements and the Java 17 baseline

The version bump to 6 primarily reflects the breaking change in minimum Java version requirements rather than API changes.

:::
