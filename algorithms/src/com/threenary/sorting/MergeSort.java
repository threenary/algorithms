package com.threenary.sorting;

/**
 * Merge sort is a divide and conquer algorithm.
 * 
 * 1) Divide the unsorted array into n partitions, each partition contains 1
 * element. Here the one element is considered as sorted. 2) Repeatedly merge
 * partitioned units to produce new sublists until there is only 1 sublist
 * remaining. This will be the sorted list at the end.
 * 
 * O(n log n)
 * 
 * @author Gonzalo Gómez Sullain
 */
public class MergeSort {

	private int[] array;
	private int[] tempMergArr;
	private int length;

	public void mergeSort(int inputArr[]) {
		this.array = inputArr;
		this.length = inputArr.length;
		this.tempMergArr = new int[length];
		doMergeSort(0, length - 1);
	}

	private void doMergeSort(int lowerIndex, int higherIndex) {

		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;

			// Below step sorts the left side of the array
			doMergeSort(lowerIndex, middle);

			// Below step sorts the right side of the array
			doMergeSort(middle + 1, higherIndex);

			// Now merge both sides
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	private void mergeParts(int lowerIndex, int middle, int higherIndex) {

		// Copy both parts into the helper array
		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempMergArr[i] = array[i];
		}

		int bottomIterator = lowerIndex;
		int middleIterator = middle + 1;
		int orderingPosition = lowerIndex;

		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (bottomIterator <= middle && middleIterator <= higherIndex) {
			if (tempMergArr[bottomIterator] <= tempMergArr[middleIterator]) {
				array[orderingPosition] = tempMergArr[bottomIterator];
				bottomIterator++;
			} else {
				array[orderingPosition] = tempMergArr[middleIterator];
				middleIterator++;
			}
			orderingPosition++;
		}

		// Copy the rest of the left side of the array into the target array
		while (bottomIterator <= middle) {
			array[orderingPosition] = tempMergArr[bottomIterator];
			orderingPosition++;
			bottomIterator++;
		}

	}

}
