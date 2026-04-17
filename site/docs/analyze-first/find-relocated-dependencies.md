---
sidebar_position: 3
---
import RunRecipe from '@site/src/components/RunRecipe';

# Find Relocated Dependencies

To identify dependencies in your project that have been relocated to new group IDs or artifact IDs, you can [use the OpenRewrite recipe](https://docs.openrewrite.org/recipes/java/dependencies/relocateddependencycheck) `org.openrewrite.java.dependencies.RelocatedDependencyCheck` from `rewrite-java-dependencies`.

This recipe searches your project's dependencies for artifacts that have been moved to new Maven coordinates, helping you discover when libraries have changed their group ID or artifact ID. This is common when projects change ownership, restructure their modules, or move to new organizations. The recipe generates a data table showing all relocated dependencies found.

You can run the search recipe using one of the following methods.

<RunRecipe
  recipeName="org.openrewrite.java.dependencies.RelocatedDependencyCheck"
  recipeDisplayName="Find Relocated Dependencies"
  artifact="org.openrewrite.recipe:rewrite-java-dependencies"
/>

## Analyze the Results

After running the recipe, you will find a data table for `RelocatedDependencyReport` in the `target/rewrite/datatables/` directory for Maven, or `build` for Gradle build, or in the output directory specified for the Moderne CLI.
You will also see relocated dependencies called out in the source code.
