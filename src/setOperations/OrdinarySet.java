package setOperations;

import java.util.List;

public class OrdinarySet {
	private Universe universe;
	private boolean[] set1;
	
	public OrdinarySet(Universe universe, List<String> names) {
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
	
	  private void checkSameUniverse(OrdinarySet set2) {
		    if (this.universe != set2.universe) {
		      throw new IllegalArgumentException("Multiset use different univserses");
		    }
		  }
	
	public boolean[] notA(OrdinarySet set2) {
		checkSameUniverse(set2);
		
		boolean[] result = new boolean[set1.length];
		for(int i = 0; i < set1.length; i++) {
			result[i] = !set1[i];
		}
		
		return result;
		
	}
	
	public boolean[] union(OrdinarySet set2) {
		checkSameUniverse(set2);
		
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] || set2.set1[i];
		}
		
		return result;
	}
	
	
	public boolean[] intersection(OrdinarySet set2) {
		checkSameUniverse(set2);
		
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] && set2.set1[i];
		}
		
		return result;
	}
	
	public boolean[] difference(OrdinarySet set2) {
		checkSameUniverse(set2);
		
		boolean[] result = new boolean[set1.length];
		
		for(int i = 0; i < set1.length; i++) {
			result[i] = set1[i] && !set2.set1[i];
		}
		
		return result;
	}
	
	public boolean[] symmetricDifference(OrdinarySet set2) {
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

	@Override
	public String toString() {
		return convertToList(set1).toString();
	}
	
	
}
