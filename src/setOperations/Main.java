package setOperations;

import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
	List<String> names = Arrays.asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "The Moon");
	
	Universe universe = new Universe(names);
	  
    OridinarySet a = new OridinarySet(universe, Arrays.asList("Mercury", "Venus", "Earth", "Mars"));
    OridinarySet b = new OridinarySet(universe, Arrays.asList("Jupiter", "Saturn", "Uranus", "Mars"));

    
    System.out.println("Not(A): " + a.convertToList(a.notA(b)));
    System.out.println("Union: " + a.convertToList(a.union(b)));
    System.out.println("Intersection: " + a.convertToList(a.intersection(b)));
    System.out.println("Set Difference: " + a.convertToList(a.difference(b)));
    System.out.println("Symmetric Difference: " + a.convertToList(a.symmetricDifference(b)));
  }

}
