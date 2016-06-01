package com.threenary.sorting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InsertionSortTest {

	InsertionSort testSubject = new InsertionSort();

	@Test
	public void insertionSortTest() {
		// Given
		int[] array = { 5, 1, 6, 2, 4, 3 };
		

		// When
		testSubject.insertionSort(array);

		// Then
		int expected[] = { 1, 2, 3, 4, 5, 6 };
		assertThat(array, is(expected));
	}

}
