/*
 * Team name: LADS
 * Team members: Lia, Angel, David, Saul
 * Course: CSIS 2430 Section 501
 * Programming Project 2 Fall 2026
 * File Author: Angel
 * 
 */

package setOperations;

import java.util.Map;

public class FuzzySet {
	private final Universe universe;
	private final double[] degrees;
	
	//Throws if the degree is not a number in the range of 0.0 to 1.0 (inclusive)
	private static void checkDegree(double degree) {
		if (!(degree >= 0.0 && degree <= 1.0)) {
			throw new IllegalArgumentException("Degree must be in [0.0, 1.0]: " + degree);
		}
	}

	public FuzzySet(Universe universe, Map<String, Double> entries) {
	    if (universe == null || entries == null) {
	        throw new IllegalArgumentException("Universe and entries can't be null");
	    }
	    this.universe = universe;
	    this.degrees = new double[universe.size()];
	    for (Map.Entry<String, Double> entry : entries.entrySet()) {
	        Double degree = entry.getValue();
	        if (degree == null) {
	            throw new IllegalArgumentException("Degree can't be null for: " + entry.getKey());
	        }
	        checkDegree(degree);
	        int pos = universe.indexOf(entry.getKey());
	        degrees[pos] = degree;
	    }
	}
	
	public double getDegree(String name) {
		int pos = universe.indexOf(name);
		return degrees[pos];
	}
	
	private FuzzySet(Universe universe, double[] degrees) {
		this.universe = universe;
		this.degrees = degrees;
	}
	
	private void checkSameUniverse(FuzzySet other) {
		if (other == null) {
		    throw new IllegalArgumentException("Other set can't be null");
		}
		
		if(this.universe != other.universe) {
			throw new IllegalArgumentException("FuzzySet uses different universes");
		}
	}
	
	//Union: Maximum rule
	public FuzzySet union(FuzzySet other) {
		checkSameUniverse(other);
		double[] result = new double[degrees.length];
		
		for (int i = 0; i < degrees.length; i++) {
			result[i] = Math.max(degrees[i], other.degrees[i]);
		}
		
		return new FuzzySet(universe, result);
	}
	
	//Intersection: Minimum rule
	public FuzzySet intersection(FuzzySet other) {
		checkSameUniverse(other);
		double[] result = new double[degrees.length];
		
		for (int i = 0; i < degrees.length; i++) {
			result[i] = Math.min(degrees[i], other.degrees[i]);
		}
		
		return new FuzzySet(universe, result);
	}
	
	//Complement: 1 - degree
	public FuzzySet complement() {
		double[] result = new double[degrees.length];
		
		for (int i = 0; i < degrees.length; i++) {
			result[i] = 1.0 - degrees[i];
		}
		
		return new FuzzySet(universe, result);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < universe.size(); i++) {
			if(i > 0) {
				sb.append(", ");
			}
			
			sb.append(universe.nameAt(i) + " = " + String.format("%.2f", degrees[i]));
		}
		sb.append("}");
		
		return sb.toString();
	}
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
