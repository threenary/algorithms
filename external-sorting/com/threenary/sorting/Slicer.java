package com.threenary.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Slicer
 * 
 * @author Gonzalo Gómez Sullain
 */
public class Slicer {

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
}