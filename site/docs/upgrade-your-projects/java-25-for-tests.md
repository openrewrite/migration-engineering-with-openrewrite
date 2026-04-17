---
sidebar_position: 2
---
import RunRecipe from '@site/src/components/RunRecipe';

# Java 25 for tests

The OpenRewrite [UpgradeToJava25](https://docs.openrewrite.org/recipes/java/migrate/upgradetoJava25) recipe can automatically convert string concatenation to text blocks in your Java code, as well as upgrade other parts of your application to align with Java 25 best practices, like using `getFirst()` and `getLast()` on sequenced collections.

In this case we're going to look at upgrading just the tests to Java 25; not yet upgrading the main source code.
This split in the Java version used for `src/main` and `src/test` is possible with both Maven and Gradle.
With Maven, you can set the `maven.compiler.testRelease` property to 21 in the `maven-compiler-plugin` configuration.

We find starting out with newer Java versions in tests only is often a good way to start adoption.
Developers can write new tests and update existing tests to use newer Java features,
while the main application code can be upgraded at a more leisurely pace as it continues to target the version you're on.
You'll be able to adapt your build pipelines already, and prove to management that the newer Java version work well for your team.

We will first create a custom recipe file in the root of your project, that applies the `UpgradeToJava25` recipe only to test code,
by using a dedicated precondition that matches test code only.

```yaml title="rewrite.yml"
---
type: specs.openrewrite.org/v1beta/recipe
name: org.openrewrite.Java25ForTests
displayName: Adopt Java 25 for tests
description: Upgrade your tests to Java 25.
preconditions:
  - org.openrewrite.java.search.IsLikelyTest
recipeList:
  - org.openrewrite.java.migrate.UpgradeToJava25
```

You can run OpenRewrite recipes directly from IntelliJ IDEA Ultimate; after adding the file to your repository,
you should see a run icon in the left margin offering to run the recipe.

If you're not using IntelliJ IDEA Ultimate, you can run the above recipe using one of the following methods.

<RunRecipe
  recipeName="org.openrewrite.Java25ForTests"
  recipeDisplayName="Adopt Java 25 for tests"
  artifact="org.openrewrite.recipe:rewrite-migrate-java"
  intellijWrapperName="org.openrewrite.Java25ForTests"
  intellijDescription="Upgrade your tests to Java 25."
  requiresYamlInstall={true}
/>


## Next steps

If you want to run the Java 25 upgrade recipe on your main source code as well, you can remove the precondition from the `rewrite.yml` file, or just run the `UpgradeToJava25` recipe directly.
