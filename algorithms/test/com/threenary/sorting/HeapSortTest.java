package com.threenary.sorting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class HeapSortTest {

	HeapSort testSubject = new HeapSort();

	@Test
	public void heapSortTest() {
		// Given
		int[] array = { 5, 8, 9, 7, 1, 2, 3 };

		// When
		testSubject.heapSort(array);

		// Then
		int[] expected = { 1, 2, 3, 5, 7, 8, 9 };
		assertThat(array, is(expected));
	}

}
