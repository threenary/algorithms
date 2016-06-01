package com.threenary.sorting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortTest {

	MergeSort testSubject = new MergeSort();

	@Test
	public void bubbleSortTest() {
		// Given
		int[] array = { 5, 8, 9, 7, 1, 4, 1, 2, 3 };

		// When
		testSubject.mergeSort(array);

		// Then
		int[] expected = { 1, 1, 2, 3, 4, 5, 7, 8, 9 };
		assertThat(array, is(expected));
	}

}
