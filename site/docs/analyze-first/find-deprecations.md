---
sidebar_position: 1
---
import RunRecipe from '@site/src/components/RunRecipe';

# Find Deprecated Methods and Classes

To identify all uses of deprecated methods, classes, and other deprecated elements in your codebase, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/search/finddeprecateduses) `org.openrewrite.java.search.FindDeprecatedUses` from `rewrite-java`.

This recipe searches your codebase for any usage of APIs marked with the `@Deprecated` annotation, helping you understand where technical debt exists and what needs to be updated before upgrading dependencies or migrating to newer framework versions. The recipe generates a data table showing all deprecated usages found.

You can run the search recipe using one of the following methods.

<RunRecipe
  recipeName="org.openrewrite.java.search.FindDeprecatedUses"
  recipeDisplayName="Find Deprecated Uses"
  artifact="org.openrewrite:rewrite-java"
/>

## Analyze the Results

After running the recipe, you will find a data table for `MethodCalls` in the `target/rewrite/datatables/` directory for Maven, or `build` for Gradle build, or in the output directory specified for the Moderne CLI.
You will also see deprecated usages reported in both the console output, as in the source code.
