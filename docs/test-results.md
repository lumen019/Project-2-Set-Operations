# Project 2 Test Results

Team LADS (Lia, Angel, David, Saul), CS 2430 Section 501, Fall 2026

## How to run

Run `SetTests.java` (package `setOperations`) as a Java Application. In Eclipse this is Run As \> Java Application. `Main.java` runs the demo for all three set types, and `SetTests.java` runs the checks below. Every check prints PASS or FAIL, and the last line gives the total.

## How the checks work

- **Expected values** were worked out by hand from the definitions in the assignment and the change order. They were not copied from the program output.  
- **Decimal comparison (fuzzy sets).** Decimal results are compared with a tolerance of `0.000001` (`SetTests.TOLERANCE`). Two numbers match if their difference is smaller than that. This is needed because Java gives `1.0 - 0.7 = 0.30000000000000004` and `1.0 - 0.9 = 0.09999999999999998`, and both are shown in the output below.  
- **Rejected input.** For inputs that should be rejected, the check passes only if an `IllegalArgumentException` is thrown, and the exception message is printed.  
- **Universe.** All sets use the same ordered universe of 10 elements: Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto, The Moon. Multiset counts are listed in that order.

## Summary

| Section | Checks | Passed |
| :---- | :---- | :---- |
| Ordinary sets (3 representative cases plus edge cases) | 19 | 19 |
| Multisets (3 representative cases plus edge cases) | 22 | 22 |
| Fuzzy sets (all 10 elements for each operation plus edge cases) | 42 | 42 |
| **Total** | **83** | **83** |

## Inputs for the fuzzy set checks

Fuzzy set A: Mercury 0.2, Venus 0.0, Earth 0.7, Mars 1.0, Jupiter 0.5, Uranus 0.9 (all other elements are unset, which means 0.0).

Fuzzy set B: Venus 0.0, Earth 0.4, Jupiter 0.5, Saturn 0.5, Uranus 1.0, Neptune 0.1 (all other elements are unset, which means 0.0).

The checks cover degrees of 0.0, 1.0, and values in between, and elements that are in both sets, in only one set, and in neither set. Complement is of A. The inputs for the ordinary set and multiset cases are printed in the output below.

## Full output

Set Tests

\------------------------------

Ordinary Set Section

\------------------------------

ordinary case 1: A \= \[Mercury, Venus, Earth, Mars\], B \= \[Jupiter, Saturn, Uranus, Mars\]

PASS  ordinary case 1 not A \| expected \[Jupiter, Saturn, Uranus, Neptune, Pluto, The Moon\], actual \[Jupiter, Saturn, Uranus, Neptune, Pluto, The Moon\]

PASS  ordinary case 1 union \| expected \[Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus\], actual \[Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus\]

PASS  ordinary case 1 intersection \| expected \[Mars\], actual \[Mars\]

PASS  ordinary case 1 difference (A \- B) \| expected \[Mercury, Venus, Earth\], actual \[Mercury, Venus, Earth\]

PASS  ordinary case 1 symmetric difference \| expected \[Mercury, Venus, Earth, Jupiter, Saturn, Uranus\], actual \[Mercury, Venus, Earth, Jupiter, Saturn, Uranus\]

ordinary case 2: A \= \[Mercury, Earth, Jupiter\], B \= \[Venus, Saturn, Pluto\]

PASS  ordinary case 2 not A \| expected \[Venus, Mars, Saturn, Uranus, Neptune, Pluto, The Moon\], actual \[Venus, Mars, Saturn, Uranus, Neptune, Pluto, The Moon\]

PASS  ordinary case 2 union \| expected \[Mercury, Venus, Earth, Jupiter, Saturn, Pluto\], actual \[Mercury, Venus, Earth, Jupiter, Saturn, Pluto\]

PASS  ordinary case 2 intersection \| expected \[\], actual \[\]

PASS  ordinary case 2 difference (A \- B) \| expected \[Mercury, Earth, Jupiter\], actual \[Mercury, Earth, Jupiter\]

PASS  ordinary case 2 symmetric difference \| expected \[Mercury, Venus, Earth, Jupiter, Saturn, Pluto\], actual \[Mercury, Venus, Earth, Jupiter, Saturn, Pluto\]

ordinary case 3: A \= \[Earth, Mars\], B \= \[Mercury, Earth, Mars, Neptune\]

PASS  ordinary case 3 not A \| expected \[Mercury, Venus, Jupiter, Saturn, Uranus, Neptune, Pluto, The Moon\], actual \[Mercury, Venus, Jupiter, Saturn, Uranus, Neptune, Pluto, The Moon\]

PASS  ordinary case 3 union \| expected \[Mercury, Earth, Mars, Neptune\], actual \[Mercury, Earth, Mars, Neptune\]

PASS  ordinary case 3 intersection \| expected \[Earth, Mars\], actual \[Earth, Mars\]

PASS  ordinary case 3 difference (A \- B) \| expected \[\], actual \[\]

PASS  ordinary case 3 symmetric difference \| expected \[Mercury, Neptune\], actual \[Mercury, Neptune\]

PASS  ordinary edge: empty set union A \| expected \[Mercury, Venus, Earth, Mars\], actual \[Mercury, Venus, Earth, Mars\]

PASS  ordinary edge: A minus A is empty \| expected \[\], actual \[\]

PASS  ordinary name John (not in universe) rejected \| message: Not in universe: John

PASS  ordinary union across different universes rejected \| message: Sets use different univserses

Multiset Section

\------------------------------

multiset case 1: A \= \[Earth, Earth, Mercury, Mercury, Saturn, Saturn, Pluto\], B \= \[Mercury, Mercury, Mercury, Earth, Earth, The Moon\]

PASS  multiset case 1 A counts \| expected \[2, 0, 2, 0, 0, 2, 0, 0, 1, 0\], actual \[2, 0, 2, 0, 0, 2, 0, 0, 1, 0\]

PASS  multiset case 1 B counts \| expected \[3, 0, 2, 0, 0, 0, 0, 0, 0, 1\], actual \[3, 0, 2, 0, 0, 0, 0, 0, 0, 1\]

PASS  multiset case 1 union (max) \| expected \[3, 0, 2, 0, 0, 2, 0, 0, 1, 1\], actual \[3, 0, 2, 0, 0, 2, 0, 0, 1, 1\]

PASS  multiset case 1 intersection (min) \| expected \[2, 0, 2, 0, 0, 0, 0, 0, 0, 0\], actual \[2, 0, 2, 0, 0, 0, 0, 0, 0, 0\]

PASS  multiset case 1 difference (A \- B, never below 0\) \| expected \[0, 0, 0, 0, 0, 2, 0, 0, 1, 0\], actual \[0, 0, 0, 0, 0, 2, 0, 0, 1, 0\]

PASS  multiset case 1 sum \| expected \[5, 0, 4, 0, 0, 2, 0, 0, 1, 1\], actual \[5, 0, 4, 0, 0, 2, 0, 0, 1, 1\]

multiset case 2: A \= \[Venus, Venus, Venus, Mars, Mars, Jupiter, Neptune, Neptune, Neptune, Neptune\], B \= \[Venus, Mars, Mars, Uranus, Uranus, Neptune, Neptune, Pluto\]

PASS  multiset case 2 A counts \| expected \[0, 3, 0, 2, 1, 0, 0, 4, 0, 0\], actual \[0, 3, 0, 2, 1, 0, 0, 4, 0, 0\]

PASS  multiset case 2 B counts \| expected \[0, 1, 0, 2, 0, 0, 2, 2, 1, 0\], actual \[0, 1, 0, 2, 0, 0, 2, 2, 1, 0\]

PASS  multiset case 2 union (max) \| expected \[0, 3, 0, 2, 1, 0, 2, 4, 1, 0\], actual \[0, 3, 0, 2, 1, 0, 2, 4, 1, 0\]

PASS  multiset case 2 intersection (min) \| expected \[0, 1, 0, 2, 0, 0, 0, 2, 0, 0\], actual \[0, 1, 0, 2, 0, 0, 0, 2, 0, 0\]

PASS  multiset case 2 difference (A \- B, never below 0\) \| expected \[0, 2, 0, 0, 1, 0, 0, 2, 0, 0\], actual \[0, 2, 0, 0, 1, 0, 0, 2, 0, 0\]

PASS  multiset case 2 sum \| expected \[0, 4, 0, 4, 1, 0, 2, 6, 1, 0\], actual \[0, 4, 0, 4, 1, 0, 2, 6, 1, 0\]

multiset case 3: A \= \[Mercury, Mercury, Mercury, Mercury, Earth, Earth, Earth, Pluto, Pluto\], B \= \[Mercury, Mercury, Earth, Earth\]

PASS  multiset case 3 A counts \| expected \[4, 0, 3, 0, 0, 0, 0, 0, 2, 0\], actual \[4, 0, 3, 0, 0, 0, 0, 0, 2, 0\]

PASS  multiset case 3 B counts \| expected \[2, 0, 2, 0, 0, 0, 0, 0, 0, 0\], actual \[2, 0, 2, 0, 0, 0, 0, 0, 0, 0\]

PASS  multiset case 3 union (max) \| expected \[4, 0, 3, 0, 0, 0, 0, 0, 2, 0\], actual \[4, 0, 3, 0, 0, 0, 0, 0, 2, 0\]

PASS  multiset case 3 intersection (min) \| expected \[2, 0, 2, 0, 0, 0, 0, 0, 0, 0\], actual \[2, 0, 2, 0, 0, 0, 0, 0, 0, 0\]

PASS  multiset case 3 difference (A \- B, never below 0\) \| expected \[2, 0, 1, 0, 0, 0, 0, 0, 2, 0\], actual \[2, 0, 1, 0, 0, 0, 0, 0, 2, 0\]

PASS  multiset case 3 sum \| expected \[6, 0, 5, 0, 0, 0, 0, 0, 2, 0\], actual \[6, 0, 5, 0, 0, 0, 0, 0, 2, 0\]

PASS  multiset edge: empty set union A \| expected \[2, 0, 2, 0, 0, 2, 0, 0, 1, 0\], actual \[2, 0, 2, 0, 0, 2, 0, 0, 1, 0\]

PASS  multiset edge: A minus A is all zeros \| expected \[0, 0, 0, 0, 0, 0, 0, 0, 0, 0\], actual \[0, 0, 0, 0, 0, 0, 0, 0, 0, 0\]

PASS  multiset name John (not in universe) rejected \| message: Not in universe: John

PASS  multiset union across different universes rejected \| message: Multiset use different univserses

Fuzzy Set Section

\------------------------------

PASS  union Earth \| expected 0.7, actual 0.7

PASS  intersection Earth \| expected 0.4, actual 0.4

PASS  complement Earth \| expected 0.3, actual 0.30000000000000004

PASS  union Mars \| expected 1.0, actual 1.0

PASS  intersection Mars \| expected 0.0, actual 0.0

PASS  complement Mars \| expected 0.0, actual 0.0

PASS  union Mercury \| expected 0.2, actual 0.2

PASS  intersection Mercury \| expected 0.0, actual 0.0

PASS  complement Mercury \| expected 0.8, actual 0.8

PASS  union Venus \| expected 0.0, actual 0.0

PASS  intersection Venus \| expected 0.0, actual 0.0

PASS  complement Venus \| expected 1.0, actual 1.0

PASS  union Jupiter \| expected 0.5, actual 0.5

PASS  intersection Jupiter \| expected 0.5, actual 0.5

PASS  complement Jupiter \| expected 0.5, actual 0.5

PASS  union Saturn \| expected 0.5, actual 0.5

PASS  intersection Saturn \| expected 0.0, actual 0.0

PASS  complement Saturn \| expected 1.0, actual 1.0

PASS  union Uranus \| expected 1.0, actual 1.0

PASS  intersection Uranus \| expected 0.9, actual 0.9

PASS  complement Uranus \| expected 0.1, actual 0.09999999999999998

PASS  union Neptune \| expected 0.1, actual 0.1

PASS  intersection Neptune \| expected 0.0, actual 0.0

PASS  complement Neptune \| expected 1.0, actual 1.0

PASS  union Pluto \| expected 0.0, actual 0.0

PASS  intersection Pluto \| expected 0.0, actual 0.0

PASS  complement Pluto \| expected 1.0, actual 1.0

PASS  union The Moon \| expected 0.0, actual 0.0

PASS  intersection The Moon \| expected 0.0, actual 0.0

PASS  complement The Moon \| expected 1.0, actual 1.0

PASS  degree 1.5 rejected \| message: Degree must be in \[0.0, 1.0\]: 1.5

PASS  degree \-0.2 rejected \| message: Degree must be in \[0.0, 1.0\]: \-0.2

PASS  degree NaN rejected \| message: Degree must be in \[0.0, 1.0\]: NaN

PASS  name John (not in universe) rejected \| message: Not in universe: John

PASS  union across different universes rejected \| message: FuzzySet uses different universes

PASS  empty set degree Earth \| expected 0.0, actual 0.0

PASS  empty set complement Earth \| expected 1.0, actual 1.0

PASS  empty set union fuzzyA Earth \| expected 0.7, actual 0.7

PASS  Venus explicit 0.0 equals Pluto unset \| expected 0.0, actual 0.0

PASS  null degree rejected \| message: Degree can't be null for: Earth

PASS  null map rejected \| message: Universe and entries can't be null

PASS  union with null set rejected \| message: Other set can't be null

83 of 83 checks passed