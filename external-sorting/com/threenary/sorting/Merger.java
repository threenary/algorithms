package com.threenary.sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Merger
 * 
 * @author Gonzalo Gómez Sullain
 */
public class Merger {
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
			//EOF should be checked at the begging of the files.
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
