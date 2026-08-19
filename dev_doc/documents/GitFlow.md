# GitFlow Conventions

Conventions to use working on the project while following the GitFlow model.

## Branch

The first best-practise is for the Repository to always have a Stable stream. For this reason, every snapshot of `main` has to be functioning and consistent.

The `main` branch can also be made a _protected_ branch so that no direct modifications can be made on it (in this project it will not). The branch is only updated through Merge Requests (except in the case of a **Hotfix** (see below), where it is updated by any merge).

In order to keep the Git Repository even more clean and organized, and to further avoid the risk of having a broken `main` branch, also the branches which merge into `main` should not be updated directly by commits, but only through merging.

- The branches which are updated only though Merge Requests or Branch Merging are called **Persistent Branches**. Persistent Branches are _protected_ branches to avoid direct commits on them.
- The Branches which are not Persistent Branches are called **Ephemeral Branches**. The Ephemeral Branches are created for a specific purpose and then deleted after.

### Persistent Branches

In the project there are three Persistent Branches:
- `main`: the Main Branch, where the production-ready code is stored.
- `dev`: the Development Branch, used when developing new features.
- `test`: the Quality Assurance Branch, used when testing the application.

### Semi-Persistent Branches

For the pre-development phases of the project, there is a category of two so-called "Semi-Persistent Branches": `requirements` and `design`. These branches are Persistent only for the duration of their respective phases, after which merged into `main` and deleted through a Merge Request.
- `requirements`: the Requirements Branch, used during the requirement engineering phase of the project.
- `design`: the Design Branch, used the design phases of the project.

In case of eventual future modifications to the Requirements or Design, these branches are recreated as Ephemeral and are to be merged into `dev`.

### Ephemeral Branches

Ephemeral Branches have to comply with the following naming convention:
```
<branch tag>/#<issueID>_<very-brief-desc>
```
- `branch tag`: see below
- `issueID`: the ID of the issue (on the ALM tool) that this Branch is meant to solve
- `very-brief-desc`: a very brief description of the purpose of the Branch. Style: `kebab-case`

If the Branch executes a modification that is not related to any Issue, the `#<issueID>_` segment can be omitted:
```
<branch tag>/<very-brief-desc>
```

#### Branch Tags

Branch Tags are prefixes used to better specify the Ephemeral Branches in relation to the Persistent Branch they are meant to be merged into.:

| Tag     | Related Persistent Branch |
|:--------|:--------------------------|
| feature | dev                       |
| hotfix  | main                      |
| bugfix  | test                      |
| req     | requirements \| dev       |
| des     | design \| dev             |

_Ephemeral Branches Names Examples:_

`req/#2-update-requirements` <br><br>
`feature/#543-impl-sensor` <br><br>
`hotfix/fix-activity-crash` <br><br>
`hotfix/#13-fix-rest-module`


## Commit

There must not be any "orphan" commit, that is, a commit not related to any Ephemeral Branch. Every commit must be related to an Ephemeral Branch and (after merging) to its corresponding Persistent Branch.

Commit Messages must follow this convention:
```
<type>: <Description>

[Optional Body]
```

Where:
- `type`: is one of the following:
  - `Fix`: (fixing something)
  - `Add`: (adding something)
  - `Update`: (updating something)
  - `Remove`: (removing something)
  - `Refactor`: (refactoring something)
- `Description`: brief description
- `Optional Body`: long description in case the Commit requires it

_Commit Message Examples:_
```
Fix: NullPointerException bug in Network.java
```
```
Update: added getters and setters to Gateway.java
```
```
Refactor: moved sql.java from db/ to dao/
```
```
Refactor: renamed db/ into database/

Former names was ambiguous and could create confusion with other packages.
```
