package setOperations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
	List<String> names = Arrays.asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "The Moon");
	
	Universe universe = new Universe(names);
	
    OridinarySet a = new OridinarySet(universe, Arrays.asList("Mercury", "Venus", "Earth", "Mars"));
    OridinarySet b = new OridinarySet(universe, Arrays.asList("Jupiter", "Saturn", "Uranus", "Mars"));
    
    System.out.println("Ordinary Set A: " + a);
    System.out.println("Ordinary Set B: " + b);
    System.out.println("Not(A): " + a.convertToList(a.notA(b)));
    System.out.println("Union: " + a.convertToList(a.union(b)));
    System.out.println("Intersection: " + a.convertToList(a.intersection(b)));
    System.out.println("Set Difference: " + a.convertToList(a.difference(b)));
    System.out.println("Symmetric Difference: " + a.convertToList(a.symmetricDifference(b)));
    
    System.out.println();
    
    Multiset multia = new Multiset(universe, Arrays.asList("Earth", "Earth", "Mercury", "Mercury", "Saturn", "Saturn", "Pluto"));
    Multiset multib = new Multiset(universe, Arrays.asList("Mercury", "Mercury", "Mercury", "Earth", "Earth", "The Moon"));
    
    System.out.println("Multiset A: " + multia);
    System.out.println("Multiset A: " + multib);
    System.out.println("Multiset union: " + multia.union(multib));
    System.out.println("Multiset union: " + multia.intersection(multib));
    System.out.println("Multiset union: " + multia.difference(multib));
    System.out.println("Multiset union: " + multia.sum(multib));
    
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
