Project 2 Change Order: Fuzzy Sets
Extend your existing Set and Multiset implementation to support a fuzzy set. This change order asks your team to adapt its design to a new membership model while preserving existing functionality. Include the implementation, testing evidence, and a brief Change Impact Analysis in your Project 2 submission.

Lecture and student handout
Fuzzy Sets Lecture (PowerPoint)Download Fuzzy Sets Lecture (PowerPoint) — concepts, worked examples, and discussion prompts.
Fuzzy Sets Student Handout (Word)Download Fuzzy Sets Student Handout (Word) — implementation requirements, testing expectations, and Change Impact Analysis questions.
What changes
In a conventional set, membership is either absent or present. In a fuzzy set, each element has a floating-point membership degree between 0.0 and 1.0, inclusive. A degree of 0.0 means no membership, 1.0 means full membership, and an intermediate value means partial membership under your defined model. A membership degree does not represent a probability.

Conceptually, represent each entry as (element, membershipDegree). Your implementation must allow the membership degree of an element to be retrieved.

Required operations
Apply each operation separately to each element x. The notation μA(x) means the membership degree of x in fuzzy set A.

Fuzzy-set operation definitions
Operation	Resulting membership degree
Complement or negation	1.0 − μA(x)
Union	max(μA(x), μB(x))
Intersection	min(μA(x), μB(x))
Example: If A(x) = 0.70 and B(x) = 0.40, complement A(x) = 0.30, union(x) = 0.70, and intersection(x) = 0.40.

Missing entries and complement
Declare the universe U, the domain of elements under consideration. For a finite demonstration, use the same explicit universe for both fuzzy sets. An element within U with no stored entry has membership 0.0.

Complement applies to every element of U, including elements with no stored entry. For example, if U = {x, y}, A(x) = 0.70, and y has no stored entry in A, then complement A(x) = 0.30 and complement A(y) = 1.00.

Implementation and testing expectations
Store and retrieve valid membership degrees in [0.0, 1.0]. Document how you handle invalid input.
Implement complement, union, and intersection using the definitions above.
Handle elements present in both fuzzy sets, only one set, and neither set within U.
Test 0.0, 1.0, and intermediate degrees. Show inputs, expected results, and actual results.
Document a small tolerance for comparing floating-point results.
Re-run original Set and Multiset tests to verify that their behavior remains correct.
Appropriate refactoring is allowed. Reuse existing components where it makes sense and explain your design decisions. The temperature membership function in the lecture and handout is an illustration, not an additional implementation requirement.

Change Impact Analysis and the Project 2 rubric
The Change Impact Analysis represents 10% of your overall Project 2 grade. It addresses this rubric criterion:

Specific, evidence-based analysis of what changed, what did not, refactoring, design strengths/weaknesses, and lessons learned.

Include a brief analysis in your existing project documentation. Address each of the following:

What changed: Identify the classes, functions, modules, or data representations you added or modified, and explain why.
What did not change: Identify components you reused without modification and explain what allowed that reuse.
Refactoring: Describe any restructuring of existing code. Explain how it supported the new functionality and how you verified existing Set and Multiset behavior.
Design strengths and weaknesses: Identify original assumptions or design choices that made the extension easier or more difficult.
Lessons learned: Explain what you would design differently if starting again and connect that lesson to this change order.
Support your claims with evidence. Refer to relevant source files and functions, before-and-after code, repository commits or diffs, and test results. Use evidence pointers that make the referenced work easy to locate.

Example of a specific evidence pointer: “In FuzzySet.union(), we reused the existing element traversal helper and changed the membership combination to max. See the repository commit that introduced this method and the test case union_missing_element for the expected and actual results.” Use your own implementation names and actual repository references.

A statement such as “we added fuzzy sets and learned about modularity” does not explain the changes or provide evidence. Your analysis should connect each design claim to concrete implementation or testing details.

What to include in your Project 2 submission
The fuzzy-set implementation alongside your working Set and Multiset implementations.
Verifiable testing evidence for the required fuzzy operations and existing functionality.
The Change Impact Analysis within your project documentation.
Follow the existing Project 2 submission instructions for packaging and submission.
