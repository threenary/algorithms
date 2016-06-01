package com.threenary.search;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class ArraySearchTest {

	ArraySearch testSubject = new ArraySearch();

	@Test
	public void linearSearchTest() {
		// Given
		int[] array = { 8, 2, 6, 3, 5 };

		// When
		int result = testSubject.linearSearch(array, 5);

		// Then
		assertThat(result, is(4));
	}

	@Test
	public void linearSearchNotFoundTest() {
		// Given
		int[] array = { 8, 2, 6, 3, 5 };

		// When
		int result = testSubject.linearSearch(array, 88);

		// Then
		assertThat(result, is(-1));
	}

	@Test
	public void binarySearchTest() {
		// Given
		int[] array = { 1, 2, 3, 8, 10, 55, 99 };

		// When
		int result = testSubject.binarySearch(array, 8);

		// Then
		assertThat(result, is(3));
	}

	@Test
	public void binarySearchNotFoundTest() {
		// Given
		int[] array = { 1, 2, 3, 8, 10, 55, 99 };

		// When
		int result = testSubject.binarySearch(array, 5);

		// Then
		assertThat(result, is(-1));
	}

	@Test
	public void binarySearchUnsortedDataSetTest() {
		// Given
		int[] array = { 1, 2, 3, 88, 10, 55, 99 };

		// When
		int result = testSubject.binarySearch(array, 55);

		// Then
		assertThat(result, is(-1));
	}

}
