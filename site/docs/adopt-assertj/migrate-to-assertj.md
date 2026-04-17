---
sidebar_position: 5
---
import RunRecipe from '@site/src/components/RunRecipe';

# Migrate to AssertJ

You can easily convert existing assertions to use [AssertJ](https://assertj.github.io/doc/) using OpenRewrite's [AssertJ recipe](https://docs.openrewrite.org/recipes/java/testing/assertj/assertj-best-practices).
This recipe contains many Refaster rules from [Error Prone Support](https://error-prone.picnic.tech/refasterrules/AssertJStringRules/).
There are multiple ways to run OpenRewrite recipes on your project, depending on your build tool and preferences.

## What you'll learn

By completing this exercise, you'll gain hands-on experience with:

- Converting verbose JUnit assertions to expressive AssertJ assertions.
- Using AssertJ's fluent API for better readability.
- Handling BigDecimal comparisons elegantly.
- Writing assertions that clearly convey test intent.
- Creating better failure messages.

We recommend the Moderne CLI when interested in running recipes across multiple projects, or when running multiple recipes in succession.
If you want to try out the recipe on a single project, you can also use the OSS Maven and Gradle plugins.

:::note

Building the Lossless Semantic Tree (LST) of your project will take some time, proportional to the size of your project.
With the Moderne CLI this only needs to be done once, after which subsequent recipe runs will be much faster.

:::

<RunRecipe
  recipeName="org.openrewrite.java.testing.assertj.Assertj"
  recipeDisplayName="Adopt AssertJ"
  artifact="org.openrewrite.recipe:rewrite-testing-frameworks"
  intellijWrapperName="org.openrewrite.AdoptAssertJ"
  intellijDescription="Adopt AssertJ and apply best practices to assertions."
/>

## Exercise A

Run the above command on the `books` project in this repository, and check the changes made to the test files.

:::note

Notice how nearly all the bad and outdated practices have been replaced with AssertJ's fluent assertions.
Existing use of AssertJ has been improved by for instance chaining assertions and removing redundant calls.

:::

## Exercise B

Run the above command on your own project, and check the changes made to the test files.

:::info

At this point you can choose whether you want to keep the changes or revert them.
If you'd prefer a smaller set of changes, you can also try out individual recipes from the [AssertJ recipe family](https://docs.openrewrite.org/recipes/java/testing/assertj).

:::
