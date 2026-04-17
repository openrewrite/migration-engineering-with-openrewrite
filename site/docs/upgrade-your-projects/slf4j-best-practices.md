---
sidebar_position: 7
---
import RunRecipe from '@site/src/components/RunRecipe';

# SLF4J Logging Best Practices

To apply SLF4J logging best practices to your codebase, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/logging/slf4j/slf4jbestpractices) `org.openrewrite.java.logging.slf4j.Slf4jBestPractices` from `rewrite-logging-frameworks`.

This recipe applies a comprehensive set of logging best practices including parameterized logging, proper logger declarations, and other common SLF4J usage patterns that improve performance and maintainability.

You can run the migration recipe using one of the following methods.

<RunRecipe
  recipeName="org.openrewrite.java.logging.slf4j.Slf4jBestPractices"
  recipeDisplayName="SLF4J Logging Best Practices"
  artifact="org.openrewrite.recipe:rewrite-logging-frameworks"
/>
