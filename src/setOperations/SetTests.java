/*
 * Team name: LADS
 * Team members: Lia, Angel, David, Saul
 * Course: CSIS 2430
 * Programming Project 2
 * File Author: Angel, ___, ___
 */

package setOperations;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class SetTests {
	private static final double TOLERANCE = 0.000001;
	private static int total = 0;
	private static int passed = 0;
	private static final List<String> NAMES = Arrays.asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "The Moon");
	private static final Universe UNIVERSE = new Universe(NAMES);
	
	private static boolean close(double actual, double expected) {
		return Math.abs(actual - expected) < TOLERANCE;
	}
	
	private static void check(String label, boolean ok) {
		total++;
	    if (ok) {
	        passed++;
	        System.out.println("PASS  " + label);
	    } else {
	        System.out.println("FAIL  " + label);
	    }
	}
	
	private static void checkClose(String label, double expected, double actual) {
	    boolean ok = close(actual, expected);
	    check(label + " | expected " + expected + ", actual " + actual, ok);
	}
	

	private static void checkList(String label, List<String> expected, List<String> actual) {
	    check(label + " | expected " + expected + ", actual " + actual, expected.equals(actual));
	}

	private static void checkCounts(String label, int[] expected, int[] actual) {
	    check(label + " | expected " + Arrays.toString(expected) + ", actual " + Arrays.toString(actual),
	          Arrays.equals(expected, actual));
	}

	public static void main(String[] args) {
	    System.out.println("Set Tests");
	    System.out.println("------------------------------");
	    testOrdinarySet();
	    testMultiset();
	    testFuzzySet();
	    System.out.println();
	    System.out.println(passed + " of " + total + " checks passed");
	}

	private static void testFuzzySet() {
		//building maps for Fuzzy Set Tests
		System.out.println();
		System.out.println("Fuzzy Set Section");
	    System.out.println("------------------------------");
	    Map<String, Double> mapA = new HashMap<>();
	    mapA.put("Mercury", 0.2);
	    mapA.put("Venus", 0.0);
	    mapA.put("Earth", 0.7);
	    mapA.put("Mars", 1.0);
	    mapA.put("Jupiter", 0.5);
	    mapA.put("Uranus", 0.9);

	    Map<String, Double> mapB = new HashMap<>();
	    mapB.put("Venus", 0.0);
	    mapB.put("Earth", 0.4);
	    mapB.put("Jupiter", 0.5);
	    mapB.put("Saturn", 0.5);
	    mapB.put("Uranus", 1.0);
	    mapB.put("Neptune", 0.1);
	    
	    FuzzySet fuzzyA = new FuzzySet(UNIVERSE, mapA);
	    FuzzySet fuzzyB = new FuzzySet(UNIVERSE, mapB);
	    
	    FuzzySet union = fuzzyA.union(fuzzyB);
	    FuzzySet intersection = fuzzyA.intersection(fuzzyB);
	    FuzzySet complement = fuzzyA.complement();
	    
	    //Earth tests
	    checkClose("union Earth", 0.7, union.getDegree("Earth"));
	    checkClose("intersection Earth", 0.4, intersection.getDegree("Earth"));
	    checkClose("complement Earth", 0.3, complement.getDegree("Earth"));
	    
	    //Mars tests
	    checkClose("union Mars", 1.0, union.getDegree("Mars"));
	    checkClose("intersection Mars", 0.0, intersection.getDegree("Mars"));
	    checkClose("complement Mars", 0.0, complement.getDegree("Mars"));
	    
	    //Mercury tests
	    checkClose("union Mercury", 0.2, union.getDegree("Mercury"));
	    checkClose("intersection Mercury", 0.0, intersection.getDegree("Mercury"));
	    checkClose("complement Mercury", 0.8, complement.getDegree("Mercury"));

	    //Venus tests
	    checkClose("union Venus", 0.0, union.getDegree("Venus"));
	    checkClose("intersection Venus", 0.0, intersection.getDegree("Venus"));
	    checkClose("complement Venus", 1.0, complement.getDegree("Venus"));

	    //Jupiter tests
	    checkClose("union Jupiter", 0.5, union.getDegree("Jupiter"));
	    checkClose("intersection Jupiter", 0.5, intersection.getDegree("Jupiter"));
	    checkClose("complement Jupiter", 0.5, complement.getDegree("Jupiter"));

	    //Saturn tests
	    checkClose("union Saturn", 0.5, union.getDegree("Saturn"));
	    checkClose("intersection Saturn", 0.0, intersection.getDegree("Saturn"));
	    checkClose("complement Saturn", 1.0, complement.getDegree("Saturn"));

	    //Uranus tests
	    checkClose("union Uranus", 1.0, union.getDegree("Uranus"));
	    checkClose("intersection Uranus", 0.9, intersection.getDegree("Uranus"));
	    checkClose("complement Uranus", 0.1, complement.getDegree("Uranus"));

	    //Neptune tests
	    checkClose("union Neptune", 0.1, union.getDegree("Neptune"));
	    checkClose("intersection Neptune", 0.0, intersection.getDegree("Neptune"));
	    checkClose("complement Neptune", 1.0, complement.getDegree("Neptune"));

	    //Pluto tests
	    checkClose("union Pluto", 0.0, union.getDegree("Pluto"));
	    checkClose("intersection Pluto", 0.0, intersection.getDegree("Pluto"));
	    checkClose("complement Pluto", 1.0, complement.getDegree("Pluto"));

	    //The Moon tests
	    checkClose("union The Moon", 0.0, union.getDegree("The Moon"));
	    checkClose("intersection The Moon", 0.0, intersection.getDegree("The Moon"));
	    checkClose("complement The Moon", 1.0, complement.getDegree("The Moon"));
	    
	    Map<String, Double> badMap = new HashMap<>();
	    badMap.put("Earth", 1.5);
	    
	    //Edge case Test > 1.0
	    try {
	    	new FuzzySet(UNIVERSE, badMap);
	    	check("degree 1.5 rejected (no exception thrown)", false);
	    }
	    catch (IllegalArgumentException e) {
	    	check("degree 1.5 rejected | message: " + e.getMessage(), true);
	    }
	    
	    //Edge case Test < 0.0
	    badMap.clear();
	    badMap.put("Earth", -0.2);
	    try {
	    	new FuzzySet(UNIVERSE, badMap);
	    	check("degree -0.2 rejected (no exception thrown)", false);
	    }
	    catch (IllegalArgumentException e) {
	    	check("degree -0.2 rejected | message: " + e.getMessage(), true);
	    }
	    
	    //Edge case Test NaN
	    badMap.clear();
	    badMap.put("Earth", Double.NaN);
	    try {
	    	new FuzzySet(UNIVERSE, badMap);
	    	check("degree NaN rejected (no exception thrown)", false);
	    }
	    catch (IllegalArgumentException e) {
	    	check("degree NaN rejected | message: " + e.getMessage(), true);
	    }
	    
	    //Edge case Test not in universe
	    badMap.clear();
	    badMap.put("John", 0.5);
	    try {
	    	new FuzzySet(UNIVERSE, badMap);
	    	check("name John (not in universe) rejected", false);
	    }
	    catch (IllegalArgumentException e) {
	    	check("name John (not in universe) rejected | message: " + e.getMessage(), true);
	    }
	    
	    //Edge case Test Other universe
	    Universe other = new Universe(Arrays.asList("Mercury", "Venus", "Earth"));
	    Map<String, Double> otherMap = new HashMap<>();
	    otherMap.put("Earth", 0.5);
	    FuzzySet fuzzyC = new FuzzySet(other, otherMap);
	    try {
	    	fuzzyA.union(fuzzyC);
	    	check("union across different universes rejected", false);
	    }
	    catch (IllegalArgumentException e) {
	    	check("union across different universes rejected | message: " + e.getMessage(), true);
	    }
	    
	  //Edge case Test Empty Map
	    badMap.clear();
	    FuzzySet empty = new FuzzySet(UNIVERSE, badMap);
	    checkClose("empty set degree Earth", 0.0, empty.getDegree("Earth"));
	    checkClose("empty set complement Earth", 1.0, empty.complement().getDegree("Earth"));
	    checkClose("empty set union fuzzyA Earth", 0.7, empty.union(fuzzyA).getDegree("Earth"));

	    //Edge case Test Unset vs Explicit 0.0
	    checkClose("Venus explicit 0.0 equals Pluto unset", fuzzyA.getDegree("Venus"), fuzzyA.getDegree("Pluto"));
	    
	  //Edge case Test null degree
	    badMap.clear();
	    badMap.put("Earth", null);
	    try {
	        new FuzzySet(UNIVERSE, badMap);
	        check("null degree rejected (no exception thrown)", false);
	    }
	    catch (IllegalArgumentException e) {
	        check("null degree rejected | message: " + e.getMessage(), true);
	    }

	    //Edge case Test null map
	    try {
	        new FuzzySet(UNIVERSE, (Map<String, Double>) null);
	        check("null map rejected (no exception thrown)", false);
	    }
	    catch (IllegalArgumentException e) {
	        check("null map rejected | message: " + e.getMessage(), true);
	    }

	    //Edge case Test null other set
	    try {
	        fuzzyA.union(null);
	        check("union with null set rejected (no exception thrown)", false);
	    }
	    catch (IllegalArgumentException e) {
	        check("union with null set rejected | message: " + e.getMessage(), true);
	    }
	}
	

	//Runs one representative ordinary set case: builds A and B, then checks all five operations
	private static void ordinaryCase(String name, List<String> aNames, List<String> bNames,
	        List<String> expNotA, List<String> expUnion, List<String> expIntersection,
	        List<String> expDifference, List<String> expSymmetric) {
	    OrdinarySet a = new OrdinarySet(UNIVERSE, aNames);
	    OrdinarySet b = new OrdinarySet(UNIVERSE, bNames);
	    System.out.println(name + ": A = " + aNames + ", B = " + bNames);
	    checkList(name + " not A", expNotA, a.convertToList(a.notA(b)));
	    checkList(name + " union", expUnion, a.convertToList(a.union(b)));
	    checkList(name + " intersection", expIntersection, a.convertToList(a.intersection(b)));
	    checkList(name + " difference (A - B)", expDifference, a.convertToList(a.difference(b)));
	    checkList(name + " symmetric difference", expSymmetric, a.convertToList(a.symmetricDifference(b)));
	}

	private static void testOrdinarySet() {
	    System.out.println();
	    System.out.println("Ordinary Set Section");
	    System.out.println("------------------------------");

	    //Representative case 1: A and B overlap in one element (Mars)
	    ordinaryCase("ordinary case 1",
	        Arrays.asList("Mercury", "Venus", "Earth", "Mars"),
	        Arrays.asList("Jupiter", "Saturn", "Uranus", "Mars"),
	        Arrays.asList("Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "The Moon"),
	        Arrays.asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus"),
	        Arrays.asList("Mars"),
	        Arrays.asList("Mercury", "Venus", "Earth"),
	        Arrays.asList("Mercury", "Venus", "Earth", "Jupiter", "Saturn", "Uranus"));

	    //Representative case 2: A and B are disjoint (no shared elements)
	    ordinaryCase("ordinary case 2",
	        Arrays.asList("Mercury", "Earth", "Jupiter"),
	        Arrays.asList("Venus", "Saturn", "Pluto"),
	        Arrays.asList("Venus", "Mars", "Saturn", "Uranus", "Neptune", "Pluto", "The Moon"),
	        Arrays.asList("Mercury", "Venus", "Earth", "Jupiter", "Saturn", "Pluto"),
	        Arrays.asList(),
	        Arrays.asList("Mercury", "Earth", "Jupiter"),
	        Arrays.asList("Mercury", "Venus", "Earth", "Jupiter", "Saturn", "Pluto"));

	    //Representative case 3: A is a subset of B
	    ordinaryCase("ordinary case 3",
	        Arrays.asList("Earth", "Mars"),
	        Arrays.asList("Mercury", "Earth", "Mars", "Neptune"),
	        Arrays.asList("Mercury", "Venus", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "The Moon"),
	        Arrays.asList("Mercury", "Earth", "Mars", "Neptune"),
	        Arrays.asList("Earth", "Mars"),
	        Arrays.asList(),
	        Arrays.asList("Mercury", "Neptune"));

	    //Edge case Tests
	    OrdinarySet a = new OrdinarySet(UNIVERSE, Arrays.asList("Mercury", "Venus", "Earth", "Mars"));
	    OrdinarySet empty = new OrdinarySet(UNIVERSE, Arrays.asList());
	    checkList("ordinary edge: empty set union A", Arrays.asList("Mercury", "Venus", "Earth", "Mars"),
	              a.convertToList(empty.union(a)));
	    checkList("ordinary edge: A minus A is empty", Arrays.asList(), a.convertToList(a.difference(a)));

	    try {
	        new OrdinarySet(UNIVERSE, Arrays.asList("John"));
	        check("ordinary name John (not in universe) rejected", false);
	    }
	    catch (IllegalArgumentException e) {
	        check("ordinary name John (not in universe) rejected | message: " + e.getMessage(), true);
	    }

	    Universe other = new Universe(Arrays.asList("Mercury", "Venus", "Earth"));
	    OrdinarySet c = new OrdinarySet(other, Arrays.asList("Earth"));
	    try {
	        a.union(c);
	        check("ordinary union across different universes rejected", false);
	    }
	    catch (IllegalArgumentException e) {
	        check("ordinary union across different universes rejected | message: " + e.getMessage(), true);
	    }
	}

	//Runs one representative multiset case: builds A and B, then checks the counts and all four operations
	//Order of counts: Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto, The Moon
	private static void multisetCase(String name, List<String> aNames, List<String> bNames,
	        int[] expA, int[] expB, int[] expUnion, int[] expIntersection, int[] expDifference, int[] expSum) {
	    Multiset a = new Multiset(UNIVERSE, aNames);
	    Multiset b = new Multiset(UNIVERSE, bNames);
	    System.out.println(name + ": A = " + aNames + ", B = " + bNames);
	    checkCounts(name + " A counts", expA, a.getCounts());
	    checkCounts(name + " B counts", expB, b.getCounts());
	    checkCounts(name + " union (max)", expUnion, a.union(b).getCounts());
	    checkCounts(name + " intersection (min)", expIntersection, a.intersection(b).getCounts());
	    checkCounts(name + " difference (A - B, never below 0)", expDifference, a.difference(b).getCounts());
	    checkCounts(name + " sum", expSum, a.sum(b).getCounts());
	}

	private static void testMultiset() {
	    System.out.println();
	    System.out.println("Multiset Section");
	    System.out.println("------------------------------");

	    //Representative case 1: same inputs as Main (Mercury and Earth repeat in both A and B)
	    multisetCase("multiset case 1",
	        Arrays.asList("Earth", "Earth", "Mercury", "Mercury", "Saturn", "Saturn", "Pluto"),
	        Arrays.asList("Mercury", "Mercury", "Mercury", "Earth", "Earth", "The Moon"),
	        new int[] {2, 0, 2, 0, 0, 2, 0, 0, 1, 0},
	        new int[] {3, 0, 2, 0, 0, 0, 0, 0, 0, 1},
	        new int[] {3, 0, 2, 0, 0, 2, 0, 0, 1, 1},
	        new int[] {2, 0, 2, 0, 0, 0, 0, 0, 0, 0},
	        new int[] {0, 0, 0, 0, 0, 2, 0, 0, 1, 0},
	        new int[] {5, 0, 4, 0, 0, 2, 0, 0, 1, 1});

	    //Representative case 2: partial overlap, three repeated elements in each of A and B
	    multisetCase("multiset case 2",
	        Arrays.asList("Venus", "Venus", "Venus", "Mars", "Mars", "Jupiter",
	                      "Neptune", "Neptune", "Neptune", "Neptune"),
	        Arrays.asList("Venus", "Mars", "Mars", "Uranus", "Uranus", "Neptune", "Neptune", "Pluto"),
	        new int[] {0, 3, 0, 2, 1, 0, 0, 4, 0, 0},
	        new int[] {0, 1, 0, 2, 0, 0, 2, 2, 1, 0},
	        new int[] {0, 3, 0, 2, 1, 0, 2, 4, 1, 0},
	        new int[] {0, 1, 0, 2, 0, 0, 0, 2, 0, 0},
	        new int[] {0, 2, 0, 0, 1, 0, 0, 2, 0, 0},
	        new int[] {0, 4, 0, 4, 1, 0, 2, 6, 1, 0});

	    //Representative case 3: B is contained in A, so the intersection equals B
	    multisetCase("multiset case 3",
	        Arrays.asList("Mercury", "Mercury", "Mercury", "Mercury", "Earth", "Earth", "Earth", "Pluto", "Pluto"),
	        Arrays.asList("Mercury", "Mercury", "Earth", "Earth"),
	        new int[] {4, 0, 3, 0, 0, 0, 0, 0, 2, 0},
	        new int[] {2, 0, 2, 0, 0, 0, 0, 0, 0, 0},
	        new int[] {4, 0, 3, 0, 0, 0, 0, 0, 2, 0},
	        new int[] {2, 0, 2, 0, 0, 0, 0, 0, 0, 0},
	        new int[] {2, 0, 1, 0, 0, 0, 0, 0, 2, 0},
	        new int[] {6, 0, 5, 0, 0, 0, 0, 0, 2, 0});

	    //Edge case Tests
	    Multiset a = new Multiset(UNIVERSE,
	        Arrays.asList("Earth", "Earth", "Mercury", "Mercury", "Saturn", "Saturn", "Pluto"));
	    Multiset empty = new Multiset(UNIVERSE, Arrays.asList());
	    checkCounts("multiset edge: empty set union A", a.getCounts(), empty.union(a).getCounts());
	    checkCounts("multiset edge: A minus A is all zeros", new int[10], a.difference(a).getCounts());

	    try {
	        new Multiset(UNIVERSE, Arrays.asList("John"));
	        check("multiset name John (not in universe) rejected", false);
	    }
	    catch (IllegalArgumentException e) {
	        check("multiset name John (not in universe) rejected | message: " + e.getMessage(), true);
	    }

	    Universe other = new Universe(Arrays.asList("Mercury", "Venus", "Earth"));
	    Multiset c = new Multiset(other, Arrays.asList("Earth"));
	    try {
	        a.union(c);
	        check("multiset union across different universes rejected", false);
	    }
	    catch (IllegalArgumentException e) {
	        check("multiset union across different universes rejected | message: " + e.getMessage(), true);
	    }
	}

}