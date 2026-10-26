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

/**
 * Represents a fuzzy set, where each element has a membership degree from 0.0 to 1.0.
 *
 * A degree of 0.0 means the element is not in the set at all, 1.0 means it is fully in
 * the set, and anything in between means it is partly in the set. The degrees are stored
 * in an array with one spot for each element of the universe, in the same order the
 * universe uses. Elements that are never given a degree stay at 0.0.
 *
 * A FuzzySet can't be changed after it is made. Union, intersection, and complement each
 * return a new FuzzySet and leave the original sets alone.
 *
 * @author Angel
 */
public class FuzzySet {
	/** The universe that decides which element goes in which spot of the array. */
	private final Universe universe;

	/** The membership degree of each element, in the same order as the universe. */
	private final double[] degrees;

	/**
	 * Checks that a degree is a number from 0.0 to 1.0, including both ends.
	 *
	 * The check is written as "is it in range?" instead of "is it out of range?" so that
	 * NaN is rejected too. Every comparison with NaN is false, so NaN fails the range
	 * check and gets thrown out.
	 *
	 * @param degree the degree to check
	 * @throws IllegalArgumentException if the degree is below 0.0, above 1.0, or NaN
	 */
	private static void checkDegree(double degree) {
		if (!(degree >= 0.0 && degree <= 1.0)) {
			throw new IllegalArgumentException("Degree must be in [0.0, 1.0]: " + degree);
		}
	}

	/**
	 * Creates a fuzzy set from a map of element names to membership degrees.
	 *
	 * Any element in the universe that is not in the map gets a degree of 0.0. If any
	 * entry is invalid, an exception is thrown and no set is created.
	 *
	 * @param universe the universe that holds every element the set can use
	 * @param entries  the element names and their degrees, such as "Earth" and 0.7
	 * @throws IllegalArgumentException if the universe or the map is null, if a degree is null or not from 0.0 to 1.0, or if a name is 
	 *  not in the universe
	 */
	public FuzzySet(Universe universe, Map<String, Double> entries) {
		// A fuzzy set can't be built without a universe or a map of entries
		if (universe == null || entries == null) {
			throw new IllegalArgumentException("Universe and entries can't be null");
		}
		this.universe = universe;

		// Java fills a new double array with 0.0, so unset elements start at 0.0
		this.degrees = new double[universe.size()];

		// Check each entry, then store its degree in the spot for that element
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

	/**
	 * Returns the membership degree of an element.
	 *
	 * If the element is in the universe but was never given a degree, this returns 0.0.
	 *
	 * @param name the name of the element to look up
	 * @return the degree of the element, from 0.0 to 1.0
	 * @throws IllegalArgumentException if the name is not in the universe
	 */
	public double getDegree(String name) {
		int pos = universe.indexOf(name);
		return degrees[pos];
	}

	/**
	 * Creates a fuzzy set directly from an array of degrees.
	 *
	 * Union, intersection, and complement use this to wrap the array they just built.
	 * It doesn't check the degrees, because they come from sets that were already checked.
	 * It is private so outside code can't pass in an array and change it later.
	 *
	 * @param universe the universe the degrees belong to
	 * @param degrees  the degrees, in the same order as the universe
	 */
	private FuzzySet(Universe universe, double[] degrees) {
		this.universe = universe;
		this.degrees = degrees;
	}

	/**
	 * Checks that another set is not null and uses the same universe as this set.
	 *
	 * The universes are compared with !=, so they have to be the exact same Universe
	 * object. This matters because spot 0 in one universe might be a different element
	 * than spot 0 in another one.
	 *
	 * @param other the other fuzzy set to check
	 * @throws IllegalArgumentException if the other set is null or uses a different universe
	 */
	private void checkSameUniverse(FuzzySet other) {
		if (other == null) {
			throw new IllegalArgumentException("Other set can't be null");
		}

		if (this.universe != other.universe) {
			throw new IllegalArgumentException("FuzzySet uses different universes");
		}
	}

	/**
	 * Returns the union of this set and another set.
	 *
	 * For each element, the new degree is the larger of the two degrees (the maximum rule).
	 * For example, if an element is 0.7 here and 0.4 in the other set, it is 0.7 in the union.
	 *
	 * @param other the set to combine with this one
	 * @return a new FuzzySet with the larger degree for every element
	 * @throws IllegalArgumentException if the other set is null or uses a different universe
	 */
	public FuzzySet union(FuzzySet other) {
		checkSameUniverse(other);
		double[] result = new double[degrees.length];

		// Go through every element and keep the larger degree
		for (int i = 0; i < degrees.length; i++) {
			result[i] = Math.max(degrees[i], other.degrees[i]);
		}

		return new FuzzySet(universe, result);
	}

	/**
	 * Returns the intersection of this set and another set.
	 *
	 * For each element, the new degree is the smaller of the two degrees (the minimum rule).
	 * For example, if an element is 0.7 here and 0.4 in the other set, it is 0.4 in the
	 * intersection.
	 *
	 * @param other the set to compare with this one
	 * @return a new FuzzySet with the smaller degree for every element
	 * @throws IllegalArgumentException if the other set is null or uses a different universe
	 */
	public FuzzySet intersection(FuzzySet other) {
		checkSameUniverse(other);
		double[] result = new double[degrees.length];

		// Go through every element and keep the smaller degree
		for (int i = 0; i < degrees.length; i++) {
			result[i] = Math.min(degrees[i], other.degrees[i]);
		}

		return new FuzzySet(universe, result);
	}

	/**
	 * Returns the complement of this set.
	 *
	 * For each element, the new degree is 1.0 minus its degree here. This includes
	 * elements that were never given a degree, because they are 0.0 and so their
	 * complement is 1.0. Decimal math can leave tiny rounding errors, such as
	 * 1.0 - 0.7 giving 0.30000000000000004, so compare results with a small tolerance.
	 *
	 * @return a new FuzzySet with 1.0 minus the degree for every element
	 */
	public FuzzySet complement() {
		double[] result = new double[degrees.length];

		// Go through every element and flip its degree
		for (int i = 0; i < degrees.length; i++) {
			result[i] = 1.0 - degrees[i];
		}

		return new FuzzySet(universe, result);
	}

	/**
	 * Returns the set as text, with each element name and its degree.
	 *
	 * Degrees are shown with two decimal places, like {Mercury = 0.20, Venus = 0.00}.
	 *
	 * @return the set as a string, in the same order as the universe
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < universe.size(); i++) {
			// Put a comma between entries, but not before the first one
			if (i > 0) {
				sb.append(", ");
			}

			sb.append(universe.nameAt(i) + " = " + String.format("%.2f", degrees[i]));
		}
		sb.append("}");

		return sb.toString();
	}

}