package com.threenary.sorting;

/**
 * QuickSort uses the divide-and-conquer strategy.
 * 
 * Choose a pivot value <br>
 * We take the value of the middle element as pivot value, but it can be any
 * value, which is in range of sorted values, even if it doesn't present in the
 * array. Partition. Rearrange elements in such a way, that all elements which
 * are lesser than the pivot go to the left part of the array and all elements
 * greater than the pivot, go to the right part of the array. Values equal to
 * the pivot can stay in any part of the array. Notice, that array may be
 * divided in non-equal parts. Sort both parts. Apply quicksort algorithm
 * recursively to the left and the right parts.
 * 
 * Partition algorithm in detail:<br>
 * There are two indices i and j and at the very beginning of the partition
 * algorithm i points to the first element in the array and j points to the last
 * one. Then algorithm moves i forward, until an element with value greater or
 * equal to the pivot is found. Index j is moved backward, until an element with
 * value lesser or equal to the pivot is found. If i ≤ j then they are swapped
 * and i steps to the next position (i + 1), j steps to the previous one (j -
 * 1). Algorithm stops, when i becomes greater than j.
 * 
 * After partition, all values before i-th element are less or equal than the
 * pivot and all values after j-th element are greater or equal to the pivot.
 * 
 * O(n log n)
 * 
 * @author Gonzalo Gómez Sullain
 *
 */

public class QuickSort {

	/**
	 * Single parameter call to QuickSort
	 * 
	 * @param array
	 *            the array to sort
	 */
	public void quickSort(int[] array) {
		this.recursiveQuickSort(array, 0, array.length - 1);
	}

	/**
	 * Recursive quicksort logic
	 *
	 * @param array
	 *            input array
	 * @param startIndex
	 *            start index of the array
	 * @param endIndex
	 *            end index of the array
	 */
	public void recursiveQuickSort(int[] array, int startIndex, int endIndex) {

		int pivote = partition(array, startIndex, endIndex);

		// Recursively call quicksort with left part of the partitioned array
		if (startIndex < pivote - 1) {
			recursiveQuickSort(array, startIndex, pivote - 1);
		}

		// Recursively call quick sort with right part of the partitioned array
		if (endIndex > pivote) {
			recursiveQuickSort(array, pivote, endIndex);
		}
	}

	/**
	 * Divides array from pivot, left side contains elements less than Pivot
	 * while right side contains elements greater than pivot.
	 *
	 * @param array
	 *            array to partitioned
	 * @param left
	 *            lower bound of the array
	 * @param right
	 *            upper bound of the array
	 * @return the partition index
	 */
	public int partition(int[] array, int left, int right) {

		int pivot = array[left]; // taking first element as pivot

		while (left <= right) {
			// searching number which is greater than pivot, bottom up
			while (array[left] < pivot) {
				left++;
			}
			// searching number which is less than pivot, top down
			while (array[right] > pivot) {
				right--;
			}

			// swap the values
			if (left <= right) {
				int tmp = array[left];
				array[left] = array[right];
				array[right] = tmp;

				// increment left index and decrement right index
				left++;
				right--;
			}
		}
		return left;
	}
}
