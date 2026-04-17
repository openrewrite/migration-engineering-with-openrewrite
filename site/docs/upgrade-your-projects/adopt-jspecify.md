---
sidebar_position: 5
---
import RunRecipe from '@site/src/components/RunRecipe';

# Adopt JSpecify Best Practices

To adopt JSpecify nullability annotations and apply all associated best practices, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/jspecify/jspecifybestpractices) `org.openrewrite.java.jspecify.JSpecifyBestPractices` from `rewrite-migrate-java`.

JSpecify provides a standard set of annotations for expressing nullability in Java code, helping catch potential null pointer exceptions at compile time. This recipe will help you migrate to JSpecify annotations and apply best practices for nullability checks in your codebase.

You can run the migration recipe using one of the following methods.

<RunRecipe
  recipeName="org.openrewrite.java.jspecify.JSpecifyBestPractices"
  recipeDisplayName="Adopt JSpecify Best Practices"
  artifact="org.openrewrite.recipe:rewrite-migrate-java"
/>
