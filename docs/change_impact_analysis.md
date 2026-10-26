# Project 2 Change Impact Analysis: Fuzzy Sets

Team LADS (Lia, Angel, David, Saul), CSIS 2430

## What changed

- **`FuzzySet.java` (new, commit `5b33943`).** A fuzzy set needs a decimal degree from 0.0 to 1.0 for each element, so it could not reuse the `boolean[]` from `OrdinarySet` or the `int[]` from `Multiset`. `FuzzySet` stores a `double[] degrees` indexed through `Universe`. Its constructor takes a `Map<String, Double>` of element names to degrees. `getDegree(String name)` retrieves a degree. `union` (max), `intersection` (min), and `complement` (1.0 minus the degree) each return a new `FuzzySet`.  
- **Invalid input.** `FuzzySet.checkDegree()` throws an `IllegalArgumentException` for any degree outside \[0.0, 1.0\]. It is written as `!(degree >= 0.0 && degree <= 1.0)` so that `NaN` is rejected too, since every comparison with `NaN` is false. A name that is not in the universe is rejected by `Universe.indexOf()`. A `null` degree in the map is not handled and would throw a `NullPointerException`.  
- **Missing entries.** An element with no entry stays at 0.0, because Java fills a new `double[]` with 0.0. This matches the change order, and its complement is 1.0.  
- **Demo (commit `cd040f7`).** A fuzzy section was added to `Main`.  
- **Tests (commit `97bb031`).** `SetTests.testFuzzySet()` runs 39 checks. They cover all 10 elements for union, intersection, and complement (elements in both sets, in one set, and in neither, with degrees 0.0, 1.0, and in between), plus edge cases: degrees 1.5, \-0.2, and `NaN`, a name not in the universe, sets from two different universes, an empty map, and an unset element versus an explicit 0.0. The tolerance for comparing decimals is `0.000001` (`SetTests.TOLERANCE`). Result: 39 of 39 checks passed. Complement of Earth gives `0.30000000000000004` and complement of Uranus gives `0.09999999999999998`, which is why a tolerance is needed.

## What did not change

- **`Universe.java`** was reused without modification. It only maps names to fixed positions and does not care what value is stored at each position, so a `double[]` works the same way an `int[]` or `boolean[]` does.  
- The logic of `OrdinarySet` and `Multiset` operations was not modified by the fuzzy work. The fuzzy commits (`5b33943`, `cd040f7`) touched only `FuzzySet.java` and `Main.java`.  
- `FuzzySet` reuses `Multiset`'s structure, but by copying the pattern, not by sharing code: an array indexed through `Universe`, a private constructor that wraps a result array, a `checkSameUniverse` method, and operations that return a new object. `Multiset.union` and `Multiset.intersection` already use `Math.max` and `Math.min`, which is exactly what fuzzy union and intersection need.

## Refactoring

- **Commit `9231caa`:** renamed `OridinarySet` to `OrdinarySet` and made the console output in `Main` consistent, with a labeled section for each set type.  
- **Commit `97bb031`:** changed `Multiset.toString()` from printing the raw count array to printing `name = count` pairs, so all three set types print in the same style as `FuzzySet`.  
- **How existing behavior was verified.** The Multiset counts printed by `Main` before and after are the same (for example, union went from `[3, 0, 2, 0, 0, 2, 0, 0, 1, 1]` to the same counts with names attached), and the ordinary set results are identical. \[TODO: add the Set and Multiset checks from `SetTests.testOrdinarySet()` and `SetTests.testMultiset()` once they are written, with results.\]

## Design strengths and weaknesses

**Strengths.**

- The fixed-position `Universe` made the extension easy. Every set type is one value per position, so each fuzzy operation is a single loop over positions.  
- Using an array gave the "missing entry is 0.0" rule for free.  
- The private wrapping constructor and `final` fields made each set immutable, so operations could not accidentally change an input.

**Weaknesses.**

- Our design notes (`docs/proj_2_design-notes.md`) called for a common interface or base class for the set types. We never built one, so `FuzzySet` could not plug into anything shared.  
- `checkSameUniverse` is written three times: `OrdinarySet`, `Multiset`, and `FuzzySet`. The first two even share the same message text, including a typo.  
- The operations have no common return type. `OrdinarySet` returns `boolean[]`, `Multiset` returns `Multiset`, and `FuzzySet` returns `FuzzySet`. `OrdinarySet.notA()` also takes a second set that it does not use, while `FuzzySet.complement()` takes none.  
- Universes are compared by identity (`!=`), so two universes with the same names are rejected. This is why `SetTests` uses one shared `UNIVERSE` constant.

## Lessons learned

If we started again, we would define the shared operations (union, intersection, complement, and the universe check) in an interface or abstract class before writing the first set type. Then `FuzzySet` would have been a third implementation of an existing contract, the universe check would exist once, and one test loop could have exercised all three types. Without it, adding fuzzy sets meant copying the same pattern a third time and writing separate tests, and the same inconsistencies now exist in all three classes.