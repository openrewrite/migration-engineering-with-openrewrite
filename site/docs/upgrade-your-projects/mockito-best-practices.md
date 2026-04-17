---
sidebar_position: 4
---
import RunRecipe from '@site/src/components/RunRecipe';

# Mockito Best Practices

To apply Mockito testing best practices to your codebase, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/testing/mockito/mockitobestpractices) `org.openrewrite.java.testing.mockito.MockitoBestPractices` from `rewrite-testing-frameworks`.

This recipe applies a comprehensive set of Mockito best practices including modernizing mock creation, improving assertion patterns, and applying other common Mockito usage patterns that enhance test readability and maintainability.

You can run the migration recipe using one of the following methods.

<RunRecipe
  recipeName="org.openrewrite.java.testing.mockito.MockitoBestPractices"
  recipeDisplayName="Mockito Best Practices"
  artifact="org.openrewrite.recipe:rewrite-testing-frameworks"
/>
