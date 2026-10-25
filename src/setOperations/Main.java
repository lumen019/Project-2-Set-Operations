package setOperations;

import java.util.Arrays;
import java.util.List;

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
  }

}
