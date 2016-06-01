package com.threenary.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Example of external sorting
 * 
 * @author Gonzalo Gómez Sullain
 *
 */
public class ExternalSorting {

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
		List<File> temporaryFiles = sliceAndSort(inputFile, COMPARATOR, sliceSize);

		// Seccond Step: Merge the sorted slices into one final output file
		mergeSort(temporaryFiles, outputFile, COMPARATOR);
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

	/**
	 * Iterates the inputFile reading sliceSize lines each time, then sort this
	 * sliceSize lines using the comparator criteria, and creates a file for
	 * every chunk of the original file.
	 * 
	 * The results is
	 * 
	 * @param inputFile
	 *            a {@link File} with the original data to sort
	 * @param comparator
	 *            an object that extends {@link Comparator} with the sortin
	 *            criteria
	 * @param sliceSize
	 *            an <b>int</b> indicating the number of rows to read each time
	 * @return a {@link List} of (inputFile lines) / slizeSize temporary ordered
	 *         {@link File}
	 * @throws IOException
	 */
	public static List<File> sliceAndSort(File inputFile, Comparator<String> comparator, int sliceSize)
			throws IOException {
		List<File> files = new ArrayList<File>();

		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
		List<String> slices = new ArrayList<String>();

		int indexFiles = 0;
		String line = bufferReader.readLine();
		while (line != null) {

			for (int i = 0; i < sliceSize; i++) {

				if (line == null) {
					line = null;
					break;
				}
				if (!line.isEmpty())
					slices.add(line);
				line = bufferReader.readLine();
			}
			if (!slices.isEmpty()) {
				// Sort slice
				Collections.sort(slices, comparator);

				// Create temp files
				files.add(createTempFile(slices, indexFiles));

				indexFiles++;
				slices.clear();
			}
		}
		bufferReader.close();

		return files;
	}

	/**
	 * Creates and saves each temporary slice of the original file into a
	 * temporary file in disck
	 * 
	 * @param slices
	 *            a {@link List} of {@link String} sorted to be saved
	 * @param indexFiles
	 *            an <b>int</b> that indicates the index of the temporary file
	 * @return a {@link File}
	 * @throws IOException
	 */
	private static File createTempFile(List<String> slices, int indexFiles) throws IOException {
		File tempFile = File.createTempFile("slice_", String.valueOf(indexFiles));
		tempFile.deleteOnExit();
		OutputStream out = new FileOutputStream(tempFile);
		BufferedWriter bufferWritter = new BufferedWriter(new OutputStreamWriter(out));
		for (String row : slices) {
			bufferWritter.write(row);
			bufferWritter.newLine();
		}
		bufferWritter.close();
		return tempFile;
	}

	/**
	 * Receives the list of temporary sorted files to order, and applys the
	 * comparision criteria received as parameter using a {@link TreeMap}, which
	 * reduces the sorting in a logarithmic scale.
	 * 
	 * <b>To get the expected final behavior, the sorting criteria should be the
	 * same used to sort the original file slices.</b>
	 *
	 * @param temporaryFiles
	 *            a {@link List} of temporary sorted {@link File}
	 * @param outFile
	 *            a {@link File} that will be filled with the resuling merge
	 * @param comparator
	 *            the sorting criteria that implements the {@link Comparator}
	 *            interfaz
	 * @throws IOException
	 */
	public static void mergeSort(List<File> temporaryFiles, File outFile, Comparator<String> comparator)
			throws IOException {

		BufferedReader[] readers = new BufferedReader[temporaryFiles.size()];
		PrintWriter writer = new PrintWriter(outFile);
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(comparator);
		try {
			// EOF should be checked at the begging of the files.
			int r = 0;
			for (File chunkFile : temporaryFiles) {
				readers[r] = new BufferedReader(new FileReader(chunkFile));
				String line = readers[r].readLine();
				treeMap.put(line, Integer.valueOf(r));
				r++;
			}

			while (!treeMap.isEmpty()) {
				Map.Entry<String, Integer> nextToGo = treeMap.pollFirstEntry();
				int fileIndex = nextToGo.getValue().intValue();

				writer.println(nextToGo.getKey());

				String line = readers[fileIndex].readLine();
				if (line != null) {
					treeMap.put(line, Integer.valueOf(fileIndex));
				}
			}
		} finally {
			for (int i = 0; i < readers.length; i++) {
				readers[i].close();
			}
			writer.close();
			for (File file : temporaryFiles) {
				file.delete();
			}
		}
	}

}
