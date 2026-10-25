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
	
	public static void main(String[] args) {
	    System.out.println("Set Tests");
	    System.out.println("------------------------------");
	    testFuzzySet();
	    System.out.println();
	    System.out.println(passed + " of " + total + " checks passed");
	}

	private static void testFuzzySet() {
		//building maps for Fuzzy Set Tests
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
	}
	

}

