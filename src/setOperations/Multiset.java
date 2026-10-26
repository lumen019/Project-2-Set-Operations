/*
 * Team name: LADS
 * Team members: Lia, Angel, David, Saul
 * Course: CSIS 2430 Section 501
 * Programming Project 2 Fall 2026
 * File Author: Saul
 }
 */

package setOperations;

import java.util.List;

public class Multiset {
  private final Universe universe;
  private final int[] counts;

  public Multiset(Universe universe, List<String> names) {
    this.universe = universe;
    this.counts = new int[universe.size()];
    for (String name : names) {
      counts[universe.indexOf(name)]++;
    }
  }

  // used to wrap the result of an operation
  private Multiset(Universe universe, int[] counts) {
    this.universe = universe;
    this.counts = counts;
  }

  private void checkSameUniverse(Multiset other) {
    if (this.universe != other.universe) {
      throw new IllegalArgumentException("Multiset use different univserses");
    }
  }

  // Union: maximum rule
  public Multiset union(Multiset other) {
    checkSameUniverse(other);
    int[] result = new int[counts.length];
    for (int i = 0; i < counts.length; i++) {
      result[i] = Math.max(counts[i], other.counts[i]);
    }
    return new Multiset(universe, result);
  }

  // Intersection: minimum rule
  public Multiset intersection(Multiset other) {
    checkSameUniverse(other);
    int[] result = new int[counts.length];
    for (int i = 0; i < counts.length; i++) {
      result[i] = Math.min(this.counts[i], other.counts[i]);
    }
    return new Multiset(universe, result);
  }

  // Difference subtraction rule
  public Multiset difference(Multiset other) {
    checkSameUniverse(other);
    int[] result = new int[counts.length];
    for (int i = 0; i < counts.length; i++) {
      result[i] = Math.max(0, this.counts[i] - other.counts[i]);
    }
    return new Multiset(universe, result);
  }

  // Sum: addition rule
  public Multiset sum(Multiset other) {
    checkSameUniverse(other);
    int[] result = new int[counts.length];
    for (int i = 0; i < counts.length; i++) {
      result[i] = this.counts[i] + other.counts[i];
    }
    return new Multiset(universe, result);
  }

  public int[] getCounts() {
    return counts.clone();
  }

  @Override
  public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	for(int i = 0; i < universe.size(); i++) {
		if(i > 0) {
			sb.append(", ");
		}
		
		sb.append(universe.nameAt(i) + " = " + counts[i]);
	}
	sb.append("}");
	
	return sb.toString();
  }
  
  
}
