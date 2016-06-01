package com.threenary.sorting;

/**
 * <b>Insertion Sort<b> is all about finding proper place for current number.
 * Once you find the proper place, you need to shift existing element to make
 * place for this new number.
 * 
 * 1) Consider first element is sorted and its on proper place, that is index 0
 * for your array.
 * 
 * 2) Now go to second element (index 1 in array), and compare it with what is
 * in your hand (the part of the array, which is already sorted). Which means
 * you compare this element going backward towards index zero.
 * 
 * 3) If current number is smaller than previous number (which is in proper
 * place), we need to put our current number before that. How will we do that?
 * Well for that we need to shift existing number. But what if there is another
 * element which is greater than our current element. It means we need to
 * continue comparing until we found proper place for our current number, which
 * again means current number> existing number or we are at start of the list
 * (index 0 in array).
 * 
 * 4) We need to repeat this process for all the numbers in the list. Once we
 * finish that, we have a sorted list or array.
 * 
 * O(n^2)
 * 
 * @author Gonzalo Gómez Sullain
 *
 */
public class InsertionSort {

	public void insertionSort(int[] a) {

		int i, j, key;

		for (i = 1; i < a.length; i++) {
			key = a[i];
			j = i - 1;
			while (j >= 0 && key < a[j]) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = key;
		}
		for (int k : a) {
			System.out.println(k);
		}
	}

}
