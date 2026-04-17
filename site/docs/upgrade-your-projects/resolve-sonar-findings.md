---
sidebar_position: 6
---
import RunRecipe from '@site/src/components/RunRecipe';

# Resolve Sonar Findings

To automatically resolve common static analysis findings reported by SonarQube and similar tools, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/staticanalysis/commonstaticanalysis) `org.openrewrite.staticanalysis.CommonStaticAnalysis` from `rewrite-static-analysis`.

This recipe applies a comprehensive set of static analysis best practices to your codebase, including fixing code smells, improving code quality, removing unnecessary code, and addressing common programming mistakes that tools like SonarQube would flag.

You can run the migration recipe using one of the following methods.

<RunRecipe
  recipeName="org.openrewrite.staticanalysis.CommonStaticAnalysis"
  recipeDisplayName="Resolve Sonar Findings"
  artifact="org.openrewrite.recipe:rewrite-static-analysis"
/>
