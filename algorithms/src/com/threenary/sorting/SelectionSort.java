package com.threenary.sorting;

/**
 * Selection Sort is conceptually the most simplest sorting algorithm. This
 * algorithm first finds the smallest element in the array and exchanges it with
 * the element in the first position, then find the second smallest element and
 * exchange it with the element in the second position, and continues in this
 * way until the entire array is sorted.
 * 
 * O(n^2)
 * 
 * @author Gonzalo Gómez Sullain
 *
 */
public class SelectionSort {

	public void selectionSort(int array[]) {

		int i, j, min, temp;

		for (i = 0; i < array.length - 1; i++) {

			min = i; // setting min as i

			for (j = i + 1; j < array.length; j++) {
				if (array[j] < array[min]) {
					// if element at j is less than element at min position
					min = j; // then set min as j
				}
			}
			temp = array[i];
			array[i] = array[min];
			array[min] = temp;
		}
	}

}
