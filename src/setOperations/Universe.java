/*
 * Team name: LADS
 * Team members: Lia, Angel, David, Saul
 * Course: CSIS 2430
 * Programming Project 2
 * File Author: Saul
 }
 */
package setOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Universe {
  private final List<String> elements;
  private final Map<String, Integer> indexByName;

  public Universe(List<String> names) {
    if (names == null || names.isEmpty()) {
      throw new IllegalArgumentException("Universe cannot be empty");
    }
    this.elements = new ArrayList<>();
    this.indexByName = new HashMap<>();
    for (String name : names) {
      if (name == null || indexByName.containsKey(name)) {
        throw new IllegalArgumentException("Null or duplicate element: " + name);
      }
      indexByName.put(name, elements.size());
      elements.add(name);
    }
  }

  public int size() {
    return elements.size();
  }

  public boolean contains(String name) {
    return indexByName.containsKey(name);
  }

  public int indexOf(String name) {
    Integer i = indexByName.get(name);
    if (i == null) {
      throw new IllegalArgumentException("Not in universe: " + name);
    }
    return i;
  }

  public String nameAt(int index) {
    return elements.get(index);
  }

  public List<String> asList() {
    return Collections.unmodifiableList(elements);
  }
}
