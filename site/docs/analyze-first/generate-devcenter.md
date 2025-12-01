---
sidebar_position: 4
---

# Generate a DevCenter Dashboard

To get a high-level overview of your repositories' state, including upgrade progress, migrations, and security vulnerabilities, you can use the Moderne CLI's `mod devcenter` command to generate an interactive HTML dashboard.

This is particularly useful when working with multiple repositories, as it provides a centralized view of metrics and data tables from recipe runs across all your projects.

## Prerequisites

Before generating a DevCenter dashboard, you need:

- [Moderne CLI](https://docs.moderne.io/user-documentation/moderne-cli/getting-started/cli-intro) installed and configured
- Repositories cloned to a shared local directory
- Built LSTs (Lossless Semantic Trees) for your repositories

## Steps

1. Clone your repositories to a shared directory:
```bash title="shell"
mkdir ~/devcenter-repos
cd ~/devcenter-repos
git clone https://github.com/your-org/repo1.git
git clone https://github.com/your-org/repo2.git
# ... clone additional repositories
```

2. Build the LSTs for all repositories:
```bash title="shell"
mod build ~/devcenter-repos/
```

3. Install the DevCenter starter recipes:
```bash title="shell"
mod config recipes jar install io.moderne.recipe:rewrite-devcenter:LATEST
```

4. Run the DevCenter starter recipe across your repositories:
```bash title="shell"
mod run ~/devcenter-repos/ --recipe io.moderne.devcenter.DevCenterStarter
```

5. Generate the DevCenter dashboard:
```bash title="shell"
mod devcenter ~/devcenter-repos/ --last-recipe-run
```

## View the Results

After the command completes, the CLI outputs the path to the generated HTML file:

```
open ~/devcenter-repos/devcenter.html
```

Open this file in a web browser to view the interactive DevCenter dashboard. The dashboard visualizes repository metrics and data tables from the executed recipe, giving you insight into:

- Overall repository health
- Upgrade and migration progress
- Security vulnerabilities
- Other metrics collected by the DevCenter recipes

## Customization

You can create custom DevCenter recipes by modifying the starter recipe YAML. To use a custom recipe:

1. Create your custom recipe YAML file (e.g., `my-devcenter.yml`)
2. Install the custom recipe:
```bash title="shell"
mod config recipes yaml install my-devcenter.yml
```
3. Run your custom recipe instead of the default starter
4. Generate the dashboard with `--last-recipe-run`
