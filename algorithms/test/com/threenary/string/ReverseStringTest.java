package com.threenary.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.threenary.string.StringReverser;

public class ReverseStringTest {

	StringReverser testSubject = new StringReverser();

	@Test
	public void reverseRecursiveTest() {
		// Given
		String stringToReverse = "my friend drinks a beer in the garden";

		// When
		String result = testSubject.reverseRecursive(stringToReverse);

		// Then
		String expectedReversedString = "garden the in beer a drinks friend my";
		assertThat(result, is(expectedReversedString));
	}

	@Test
	public void reverseIterativeTest() {
		// Given
		String stringToReverse = "my friend drinks a beer in the garden";

		// When
		String result = testSubject.reverseIterative(stringToReverse);

		// Then
		String expectedReversedString = "garden the in beer a drinks friend my";
		assertThat(result, is(expectedReversedString));
	}

	@Test
	public void reverseBuilderTest() {
		// Given
		String stringToReverse = "hola que tal";

		// When
		String result = testSubject.reverseBuilder(stringToReverse);

		// Then
		String expectedReversedString = "lat euq aloh";
		assertThat(result, is(expectedReversedString));
	}

}
