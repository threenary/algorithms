package com.threenary.sorting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class QuickSortTest {

	QuickSort testSubject = new QuickSort();

	@Test
	public void quickSortTest() {
		// Given
		int[] array = { 1, 12, 5, 26, 7, 14, 3, 7, 2 };

		// When
		testSubject.quickSort(array);

		// Then
		int[] expected = { 1, 2, 3, 5, 7, 7, 12, 14, 26 };
		assertThat(array, is(expected));
	}

}
