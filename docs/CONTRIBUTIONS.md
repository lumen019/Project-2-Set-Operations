# Contributions

Team LADS (Lia, Angel, David, Saul), CS 2430 Section 501, Fall 2026

Repository: [https\://github.com/lumen019/Project-2-Set-Operations](https://github.com/lumen019/Project-2-Set-Operations)

## Who is who on GitHub

The commit history shows several GitHub names, so this table matches each name to a team member.

| Team member | GitHub name(s) in the history | Role in the Project Plan |
| :---- | :---- | :---- |
| Angel | `angel789` (commits from Eclipse) and `boneless123` (files uploaded on the GitHub website). Both are the same GitHub account. | Implementation Lead |
| Lia | `Lumen019` \[TODO: Lia to confirm\] | Verification Lead |
| Saul | `bro556` | Communications Lead |
| David | `Darkness9823` | Assist |

## Dividing line between before and after the Change Request

We use the tag `pre-change-baseline` as the dividing line. It marks commit `09c89d5`, the last commit with working ordinary set and multiset code and no fuzzy set code. The tag was created on Oct 25, after the fact, on the correct commit. Commit `5b33943` is the first commit of the fuzzy set work.

## Before the Change Request (up to `pre-change-baseline`)

**David** wrote the planning and documentation files in `README.md`, `docs/Project 2 Plan`, `docs/proj_2_scope.md`, `docs/proj_2_design-notes.md`, and `docs/ChangeOrderRemember.md`. Evidence: commits `f5a813f`, `24b5504`, `5a510a1`, `19863a1`, `682469a`, `d54aab4`, `b2dac9a`, `8663537`, and `ca19d7d`.

**Saul** wrote `Universe.java`, which fixes the order of the universe's elements, and `Multiset.java`, which stores a count for each element and implements union (maximum), intersection (minimum), difference (never below 0), and sum. Evidence: commit `08d2d44` (Universe), commit `4dcc026` (Multiset), and merge `a5dffb7`.

**Lia** wrote the ordinary set operations in `OrdinarySet.java` (not, union, intersection, difference, symmetric difference) and the first version of the ordinary set and multiset demo in `Main.java`. Evidence: commits `8399e92`, `2839a4b`, `1fb1f78`, `157ad1a`, and `09c89d5`.

**Angel** has no commits before the baseline. \[TODO: Angel to add any earlier contribution that is not visible in Git, such as design discussion, or leave this line as it is.\]

## After the Change Request (after `pre-change-baseline`)

**Angel** implemented the fuzzy set and the testing around it.

- `FuzzySet.java` (new class): commit `5b33943`  
- Fuzzy set demo in `Main.java`: commit `cd040f7`  
- Renamed `OridinarySet` to `OrdinarySet` and made the output labels consistent: commit `9231caa`  
- `SetTests.java` with the fuzzy set checks and the decimal tolerance: commit `97bb031`  
- Handling of `null` inputs in `FuzzySet`, with tests: commit `eee187a`  
- Regression tests for ordinary sets and multisets (3 representative cases each plus edge cases): commit `7c53d4f`  
- Header fixes, Javadoc comments for `FuzzySet`, and test updates: commits `d5e3e9e` and `b631571`  
- The `pre-change-baseline` tag on commit `09c89d5`  
- `docs/change_impact_analysis.md` and `docs/test-results.md`, uploaded through the GitHub website: commits `0c7bafc`, `a34fd59`, `f3cfd78`, and `3122cd7`

**Lia** added headers to all source files and changed `Main.java` so each ordinary set result prints both the Boolean array and the element names. Evidence: commit `b6d80dd`. Commit `eb371bf` is a later push by the same account; its message ("zsh:1: command not found: q") is accidental terminal text and does not describe the change.

**Saul** \[TODO: Saul to list his work after the change, such as the report, README, packaging, or the screencast, with evidence pointers.\]

**David** \[TODO: David to list his work after the change, with evidence pointers.\]

## Other deliverables

- Team report (PDF): \[TODO: who wrote it, and where it is\]  
- Screencast: \[TODO: who recorded it, and the link\]  
- Weekly status reports and peer reviews are submitted separately in Canvas.