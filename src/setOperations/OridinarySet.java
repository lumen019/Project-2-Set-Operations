package setOperations;

import java.util.List;

public class OridinarySet {
	private Universe universe;
	private boolean[] set1;
	
	public OridinarySet(Universe universe, List<String> names) {
		this.universe = universe;
		set1 = new boolean[universe.size()];
		
		//marks each name true for its corresponding index in boolean array elements.
		for(String name: names) {
			int index = universe.indexOf(name);
			set1[index] = true;
		}
	}
	
	public boolean[] notA() {
		boolean[] complement = new boolean[set1.length];
		for(int i = 0; i < set1.length; i++) {
			complement[i] = !set1[i];
		}
		
		return complement;
		
	}
	
	public boolean[] union(OridinarySet set2) {
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] || set2.set1[i];
		}
		
		return result;
	}
	
	
	public boolean[] intersection(OridinarySet set2) {
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] && set2.set1[i];
		}
		
		return result;
	}
	
	public boolean[] difference(OridinarySet set2) {
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] && !set2.set1[i];
		}
		
		return result;
	}
	
	public boolean[] symmetricDifference(OridinarySet set2) {
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] ^ set2.set1[i];
		}
		
		return result;
	}
}
