Project-2-Set-Operations

Team LADS (Lia, Angel, David, Saul), CS 2430 Section 501, Fall 2026

Implementation Lead Angel Verification Lead Lia Communications Lead Saul Assist David

What this project does

This project stores sets of planets (a universe of 10 elements in a fixed order) in three ways and runs operations on them:

Ordinary sets (OrdinarySet.java): one true/false value per element. Operations are not, union, intersection, difference, and symmetric difference. Results print as a Boolean array and as a list of names.
Multisets (Multiset.java): one count per element. Operations are union (maximum), intersection (minimum), difference (never below 0), and sum.
Fuzzy sets (FuzzySet.java, added for the Change Request): one degree from 0.0 to 1.0 per element. Operations are union (maximum), intersection (minimum), and complement (1.0 minus the degree).

Universe.java holds the fixed order of the elements, and all three set types use it.

Requirements

Java 11 or newer. The project was built in Eclipse, and the repository includes the Eclipse project files.

How to build and run

In Eclipse: use File > Import > Existing Projects into Workspace, and select this folder. Then right-click Main.java (the demo) or SetTests.java (the tests) and choose Run As > Java Application.

From a terminal, in the folder that contains src:

javac -d out src/setOperations/*.java
java -cp out setOperations.Main
java -cp out setOperations.SetTests

Main prints the demo for ordinary sets, multisets, and fuzzy sets. SetTests prints PASS or FAIL for each check and a total at the end (83 of 83 checks should pass).

Project layout
src/setOperations/: the source code (Universe, OrdinarySet, Multiset, FuzzySet, Main, SetTests)
docs/: project plan, scope, design notes, the change order, the Change Impact Analysis (change_impact_analysis.md), and test results (test-results.md)
CONTRIBUTIONS.md: who did what, with evidence from the Git history
Git history

The tag pre-change-baseline marks the last working commit before the fuzzy set change (09c89d5).
