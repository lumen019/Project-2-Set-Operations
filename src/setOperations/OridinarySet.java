package setOperations;

import java.util.List;

public class OridinarySet {
	private Universe universe;
	private boolean[] set1;
	
	public OridinarySet(Universe universe, List<String> names) {
		if (universe == null || names == null) {
			throw new IllegalArgumentException("Universe and names can't be null");
		}
		
		this.universe = universe;
		set1 = new boolean[universe.size()];
		
		for(String name: names) {
			if(name == null || name.isBlank()) {
				throw new IllegalArgumentException("Name can't be null or blank");
			}
			
			int index = universe.indexOf(name);
			set1[index] = true;
		}
	}
	
	  private void checkSameUniverse(OridinarySet set2) {
		    if (this.universe != set2.universe) {
		      throw new IllegalArgumentException("Multiset use different univserses");
		    }
		  }
	
	public boolean[] notA(OridinarySet set2) {
		checkSameUniverse(set2);
		
		boolean[] result = new boolean[set1.length];
		for(int i = 0; i < set1.length; i++) {
			result[i] = !set1[i];
		}
		
		return result;
		
	}
	
	public boolean[] union(OridinarySet set2) {
		checkSameUniverse(set2);
		
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] || set2.set1[i];
		}
		
		return result;
	}
	
	
	public boolean[] intersection(OridinarySet set2) {
		checkSameUniverse(set2);
		
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] && set2.set1[i];
		}
		
		return result;
	}
	
	public boolean[] difference(OridinarySet set2) {
		checkSameUniverse(set2);
		
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] && !set2.set1[i];
		}
		
		return result;
	}
	
	public boolean[] symmetricDifference(OridinarySet set2) {
		checkSameUniverse(set2);
		
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] ^ set2.set1[i];
		}
		
		return result;
	}
	
	public List<String> convertToList (boolean[] result){
		List<String> resultList = new java.util.ArrayList<String>();
		
		for (int i = 0; i<result.length; i++) {
			if(result[i]) {
				resultList.add(universe.nameAt(i));
			}
		}
	
		return resultList;
	}
}
