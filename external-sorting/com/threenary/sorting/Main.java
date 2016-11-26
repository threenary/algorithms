package com.threenary.sorting;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Main
 * 
 * @author Gonzalo Gómez Sullain
 *
 */
public class Main {

	private static final int DEFAULT_SLICE = 10000;

	private static final String DEFAULT_OUTPUT = "sortedOutputFile";

	/**
	 * Entry point of execution
	 * 
	 * @param args
	 *            {@link String[]} in which we expect<br>
	 *            <b>arg0:</b>File origin with data to be sorted <br>
	 *            <b>arg1:</b>Expected destination file <br>
	 *            <b>arg2:</b>Number of slices to read each time <br>
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// We should be validating all the the input args
		File inputFile = new File(args[0]);

		File outputFile;
		try {
			outputFile = new File(args[1]);
		} catch (Exception e1) {
			outputFile = new File(DEFAULT_OUTPUT);
		}

		int sliceSize;
		try {
			sliceSize = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			sliceSize = DEFAULT_SLICE;
		}

		// First Step: Slice big file in smaller parts, each should be sorted.
		List<File> temporaryFiles = Slicer.sliceAndSort(inputFile, COMPARATOR, sliceSize);

		// Seccond Step: Merge the sorted slices into one final output file
		Merger.mergeSort(temporaryFiles, outputFile, COMPARATOR);
	}

	private static Comparator<String> COMPARATOR = new Comparator<String>() {
		@Override
		public int compare(String obj1, String obj2) {
			if (obj1 == obj2) {
				return 0;
			}
			if (obj1 == null) {
				return -1;
			}
			if (obj2 == null) {
				return 1;
			}
			return obj1.compareTo(obj2);
		}
	};

}
