package com.threenary.string;

/**
 * Different implementations to string reversion
 * 
 * @author Gonzalo Gómez Sullain
 */
public class StringReverser {

	/**
	 * A simple approach using StringBuilder
	 * 
	 * @param stringToRevert
	 *            the string we are willing to revert
	 * @return the reversed string
	 */
	public String reverseBuilder(String strintToReverse) {
		return new StringBuilder(strintToReverse).reverse().toString();
	}

	/**
	 * An iterative approach to String reversion
	 * 
	 * @param stringToRevert
	 *            the string we are willing to revert
	 * @return the reversed string
	 */
	public String reverseIterative(String stringToRevert) {
		StringBuilder strBuilder = new StringBuilder();
		String[] words = stringToRevert.split(" ");

		for (int i = words.length - 1; i >= 0; i--) {
			strBuilder.append(words[i]);
			if (i > 0) {
				// Workaround to avoid last white space
				strBuilder.append(" ");
			}
		}

		return strBuilder.toString();
	}

	/**
	 * A revursive approach to String reversion
	 * 
	 * @param stringToRevert
	 *            the string we are willing to revert
	 * @return the reversed string
	 */
	public String reverseRecursive(String stringToReverse) {
		String[] split = stringToReverse.split(" ");
		StringBuffer stringBuilder = new StringBuffer();
		for (int i = split.length; i > 0; i--) {
			stringBuilder.append(split[i - 1] + " ");
		}
		return stringBuilder.substring(0, stringBuilder.length() - 1);
	}
	
	public static void main(String args[]){
		String str = "Sony is going to introduce Internet TV soon";
        System.out.println("Original String: " + str);
		
		String reverseStr = new StringBuffer(str).reverse().toString();
        System.out.println("Reverse String in Java using StringBuffer: " + reverseStr);
	}
}
