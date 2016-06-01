package com.threenary.sorting;

/**
 * Given an integer X and a non-empty zero-indexed array consisting of N
 * integers.
 * 
 * Find which elements of the array are equal to X and which different.
 * 
 * The goal is to split the array in two parts, such that the number of elements
 * equal to X in the first part is the same as the number of elements different
 * from X in the other part.
 * 
 * 0 <= K <= N and, the number of elements equal to X in A[0...K-1] is the same
 * as the number of elements different from X in A[K...N-1].
 * 
 * (For K=0, A[0..K-1] does not contain any elemtns, for K=N, A[K..N-1] does not
 * contain any elements)
 * 
 * For example: X = 5 A - [5,5,1,7,2,3,5]
 * 
 * Then K=4, because: two of the elements A[0..3] are equal to X two of the
 * elementos of A[4..6] are different from X
 * 
 * @author Gonzalo Gómez Sullain
 *
 */
public class ContentSplitter {

	public int contentSplitter(int X, int[] A) {
		int c, j, counterLeft, counterRight;

		for (int i = 0; i < A.length; i++) {
			counterLeft = 0;
			counterRight = 0;
			for (j = 0; j <= 0; j++) {
				if (A[j] == X) {
					counterLeft++;
				}
			}

			for (c = A.length - 1; c > i; c--) {
				if (A[c] != X) {
					counterRight++;
				}
			}

			if (counterRight == counterLeft) {
				return i + 1;
			}
		}
		return -1;
	}
	
	

}
