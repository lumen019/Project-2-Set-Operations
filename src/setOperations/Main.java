package setOperations;

import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
	List<String> names = Arrays.asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "Moon");
	
	Universe universe = new Universe(names);
	  
    OridinarySet a = new OridinarySet(universe, Arrays.asList("Mercury", "Venus", "Earth", "Mars"));
    OridinarySet b = new OridinarySet(universe, Arrays.asList("Jupiter", "Saturn", "Uranus", "Neptune"));

    
    System.out.println("A + B " + a.convertToList(a.union(b)));
  }

}
