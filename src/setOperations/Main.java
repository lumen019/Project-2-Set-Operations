/*
 * Team name: LADS
 * Team members: Lia, Angel, David, Saul
 * Course: CSIS 2430
 * Programming Project 2
 * File Author: Angel, Lia
 */

package setOperations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
	List<String> names = Arrays.asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "The Moon");
	
	Universe universe = new Universe(names);
	//Ordinary Set Section
	
    OrdinarySet a = new OrdinarySet(universe, Arrays.asList("Mercury", "Venus", "Earth", "Mars"));
    OrdinarySet b = new OrdinarySet(universe, Arrays.asList("Jupiter", "Saturn", "Uranus", "Mars"));
    
    System.out.println("Ordinary Set Section");
    System.out.println("------------------------------");
    
    System.out.println("Ordinary Set A Boolean Array: " + Arrays.toString(a.getSet()));
    System.out.println("Ordinary Set A Elements: " + a);
    
    System.out.println("Ordinary Set B Boolean Array: " + Arrays.toString(b.getSet()));
    System.out.println("Ordinary Set B: " + b);
    
    System.out.println();
    
    System.out.println("Not(A) Boolean Array: " + Arrays.toString(a.notA(b)));
    System.out.println("Not(A) Elements: " + a.convertToList(a.notA(b)));
    
    System.out.println();
    
    System.out.println("Union Boolean Array: " + Arrays.toString(a.union(b)));
    System.out.println("Union Elements: " + a.convertToList(a.union(b)));
    
    System.out.println();
    
    System.out.println("Intersection Boolean Array: " + Arrays.toString(a.intersection(b)));
    System.out.println("Intersection Elements: " + a.convertToList(a.intersection(b)));
    
    System.out.println();
    
    System.out.println("Set Difference Boolean Array: " + Arrays.toString(a.difference(b)));
    System.out.println("Set Difference Elements: " + a.convertToList(a.difference(b)));
    
    System.out.println();
    
    System.out.println("Symmetric Difference Elements: " + Arrays.toString(a.symmetricDifference(b)));
    System.out.println("Symmetric Difference Elements: " + a.convertToList(a.symmetricDifference(b)));
    
    
    System.out.println();
    //Multiset Section

    Multiset multia = new Multiset(universe, Arrays.asList("Earth", "Earth", "Mercury", "Mercury", "Saturn", "Saturn", "Pluto"));
    Multiset multib = new Multiset(universe, Arrays.asList("Mercury", "Mercury", "Mercury", "Earth", "Earth", "The Moon"));
    System.out.println("Multiset Section");
    System.out.println("------------------------------");
    System.out.println("Multiset A: " + multia);
    System.out.println("Multiset B: " + multib);
    System.out.println();
    System.out.println("Multiset union: " + multia.union(multib));
    System.out.println("Multiset intersection: " + multia.intersection(multib));
    System.out.println("Multiset difference: " + multia.difference(multib));
    System.out.println("Multiset sum: " + multia.sum(multib));
    
    System.out.println();
    
    //FuzzySet Section
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
    
    FuzzySet fuzzyA = new FuzzySet(universe, mapA);
    FuzzySet fuzzyB = new FuzzySet(universe, mapB);

    System.out.println("Fuzzy Set A: " + fuzzyA);
    System.out.println("Fuzzy Set B: " + fuzzyB);
    System.out.println();
    System.out.println("Fuzzy Set A Union B: " + fuzzyA.union(fuzzyB));
    System.out.println("Fuzzy Set A Intersection B: " + fuzzyA.intersection(fuzzyB));
    System.out.println("Fuzzy Set A Complement: " + fuzzyA.complement());
  }

}
