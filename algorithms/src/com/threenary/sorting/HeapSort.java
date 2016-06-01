package com.threenary.sorting;

/**
 * Heapsort is a sorting algorithm which can be split into two distinct stages.
 * 
 * The first stage is to build a tree-based heap data structure from the
 * supplied input. Conforming to the heap property:
 * 
 * Shape property: A binary heap is a complete binary tree; that is, all levels
 * of the tree, except possibly the last one (deepest) are fully filled, and, if
 * the last level of the tree is not complete, the nodes of that level are
 * filled from left to right.
 * 
 * Heap property: All nodes are either greater than or equal to or less than or
 * equal to each of its children, according to a comparison predicate defined
 * for the heap.
 * 
 * Either requires the structure follow that all parent nodes are greater than
 * or equal to their children (with the highest at the root), or the inverse.
 * 
 * The second step simply builds up the sorted array, with the next element
 * removed from the heap structure (reconstructing the heap after each
 * iteration), until no elements are left.
 * 
 * @author Gonzalo Gómez Sullain
 */
public class HeapSort {

	private int total;

	private void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	private void heapify(int[] arr, int i) {
		int leftNode = i * 2;
		int rightNode = (i * 2) + 1;
		int root = i;

		if (leftNode <= total && arr[leftNode] > arr[root])
			root = leftNode;
		if (rightNode <= total && arr[rightNode] > arr[root])
			root = rightNode;
		if (root != i) {
			// If parent has changed, then we swap
			swap(arr, i, root);
			heapify(arr, root);
		}
	}

	public void heapSort(int[] arr) {
		total = arr.length - 1;

		for (int i = total ; i >= 0; i--)
			heapify(arr, i);

		for (int i = total; i > 0; i--) {
			swap(arr, 0, i);
			total--;
			heapify(arr, 0);
		}
	}
}
