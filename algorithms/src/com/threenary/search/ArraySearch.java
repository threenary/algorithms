package com.threenary.search;

/**
 * Array searching algorithms
 * 
 * @author Gonzalo
 *
 */
public class ArraySearch {

	/**
	 * A linear search is the basic and simple search algorithm. A linear search
	 * searches an element or value from an array till the desired element or
	 * value is not found and it searches in a sequence order. It compares the
	 * element with all the other elements given in the list and if the element
	 * is matched it returns the value index else it return -1. Linear Search is
	 * applied on the unsorted or unordered list when there are fewer elements
	 * in a list.
	 * 
	 * @param values
	 *            the array where to look for the target
	 * @param target
	 *            the number to be found
	 * @return the result index
	 */
	public int linearSearch(int[] values, int target) {
		for (int i = 0; i < values.length; ++i) {
			if (values[i] == target) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Binary Search is applied on the sorted array or list.
	 * 
	 * In binary search, we first compare the value with the elements in the
	 * middle position of the array. If the value is matched, then we return the
	 * value. If the value is less than the middle element, then it must lie in
	 * the lower half of the array and if it's greater than the element then it
	 * must lie in the upper half of the array. We repeat this procedure on the
	 * lower (or upper) half of the array. Binary Search is useful when there
	 * are large numbers of elements in an array.
	 * 
	 * @param values
	 *            the array where to look for the target
	 * @param target
	 *            the number to be found
	 * @return the result index
	 */
	public int binarySearch(int[] values, int target) {
		return binarySearch(values, target, 0, values.length - 1);
	}

	/**
	 * Auxiliar function for recursion purposes
	 * 
	 * @param values
	 *            the array where to look for the target
	 * @param target
	 *            the number to be found
	 * @param start
	 *            index to start the binary search
	 * @param end
	 *            index to finish the binary search
	 * @return the result index
	 */
	public int binarySearch(int[] values, int target, int start, int end) {
		if (start > end) {
			return -1;
		} // does not exist

		int middle = (start + end) / 2;
		int value = values[middle];

		if (value > target) {
			return binarySearch(values, target, start, middle - 1);
		}
		if (value < target) {
			return binarySearch(values, target, middle + 1, end);
		}
		return middle; // found!
	}

}
