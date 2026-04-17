---
sidebar_position: 4
---
import RunRecipe from '@site/src/components/RunRecipe';

# Upgrade to Jackson 3

To upgrade your project from Jackson 2.x to Jackson 3.x, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/jackson/upgradejackson_2_3) `org.openrewrite.java.jackson.UpgradeJackson_2_3` from `rewrite-jackson`.

This recipe will automatically update your Jackson dependencies and apply the necessary code changes to ensure compatibility with Jackson 3.x, including updating package names from `com.fasterxml.jackson` to `tools.jackson` where applicable and handling API changes between versions.

You can run the migration recipe using one of the following methods.

<RunRecipe
  recipeName="org.openrewrite.java.jackson.UpgradeJackson_2_3"
  recipeDisplayName="Upgrade to Jackson 3"
  artifact="org.openrewrite.recipe:rewrite-jackson"
/>
