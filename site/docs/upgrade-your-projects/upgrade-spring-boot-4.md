---
sidebar_position: 7
---
import RunRecipe from '@site/src/components/RunRecipe';

# Upgrade to Spring Boot 4.0

To upgrade your project from Spring Boot 3.x to Spring Boot 4.0, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/spring/boot4/upgradespringboot_4_0-community-edition) `org.openrewrite.java.spring.boot4.UpgradeSpringBoot_4_0` from `rewrite-spring`.

This recipe will automatically update your Spring Boot dependencies to version 4.0 and apply the necessary code changes to ensure compatibility, including updating deprecated APIs, migrating configuration properties, and adapting to framework changes introduced in Spring Boot 4.0.

You can run the migration recipe using one of the following methods.

<RunRecipe
  recipeName="org.openrewrite.java.spring.boot4.UpgradeSpringBoot_4_0"
  recipeDisplayName="Upgrade to Spring Boot 4.0"
  artifact="org.openrewrite.recipe:rewrite-spring"
/>

## Next steps

[Customize the Spring Boot 4.0 upgrade recipe](https://docs.moderne.io/user-documentation/moderne-platform/how-to-guides/new-recipe-builder) to fit your project's specific needs by extending it with additional transformations or configurations as required.
This can be to bring along internal libraries or frameworks as you upgrade, or to expand the recipes beyond what is covered out of the box.
As always: if you develop any recipes useful to others, [consider contributing back to the community](https://github.com/openrewrite/.github/blob/main/CONTRIBUTING.md)!
