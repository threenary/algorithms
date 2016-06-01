package com.threenary.sorting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ContentSpitterTest {

	ContentSplitter testSubject = new ContentSplitter();

	@Test
	public void contentSplitterTest() {
		// Given
		int X = 5;
		int[] A = { 5, 5, 1, 7, 2, 3, 5 };

		// When
		int result = testSubject.contentSplitter(X, A);

		// Then
		assertThat(result, is(4));
	}

}
