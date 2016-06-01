package com.threenary.sorting;

/**
 * Bubble Sort is an algorithm which is used to sort N elements that are given
 * in a memory for eg: an Array with N number of elements. Bubble Sort compares
 * all the element one by one and sort them based on their values.
 * 
 * It is called Bubble sort, because with each iteration the smaller element in
 * the list bubbles up towards the first place, just like a water bubble rises
 * up to the water surface.
 * 
 * Sorting takes place by stepping through all the data items one-by-one in
 * pairs and comparing adjacent data items and swapping each pair that is out of
 * order.
 * 
 * O(n^2)
 * 
 * @author Gonzalo Gómez Sullain
 *
 */
public class BubbleSort {

	public void bubbleSort(int[] array) {

		int k, swap;

		for (int m = array.length; m >= 0; m--) {
			
			boolean flag = false; // To limit iterations
			
			for (int i = 0; i < array.length - 1; i++) {
				k = i + 1;
				
				if (array[i] > array[k]) {
					swap = array[i];
					array[i] = array[k];
					array[k] = swap;
					flag = true;
				}
			}
			if (!flag)
				break; // If no swapping, then the array is ordered
		}
	}
}
