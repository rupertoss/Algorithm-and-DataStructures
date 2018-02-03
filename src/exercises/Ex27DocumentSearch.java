package exercises;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;

public class Ex27DocumentSearch {

	public Integer getSmallestRangeOfWordsInDocument(File documentFile, File wordsToFindFile) {
		
		In documentInput = new In(documentFile);				// reading document file
		String[] document = documentInput.readAllStrings();		// document data
		In wordsToFindInput = new In(wordsToFindFile);			// reading wordsToFind file
		ArrayList<String> wordsToFind = new ArrayList<>(Arrays.asList(wordsToFindInput.readAllStrings())); // wordsToFind data
		
		// results array : [number of words to find] X [0 = index of first word, 1 = range from first to last]
		Integer[][] results = new Integer[wordsToFind.size()][2];  
		
		for(int i=0; i < document.length; i++) {		// for each word from document
			if(wordsToFind.contains(document[i])) {		// if the word is one to find
				
				int numberOfWord = wordsToFind.indexOf(document[i]);	// index of the word to find
				
				if(numberOfWord == 0) {
					results[0][0] = i;		// if it is first, just add its index 
				} else {
					if(results[numberOfWord - 1][0] != null) {	// if not first and has a predecessor
						if(numberOfWord == wordsToFind.size()) {	// check if it is last
							if(results[numberOfWord][0] != null) { 	// check if last was already found
								if(results[numberOfWord][1] > i - results[numberOfWord - 1][0]) // check if had longer range
									results[numberOfWord][1] = i - results[numberOfWord - 1][0]; // new one has shorter range -> replace
							} else {
								results[numberOfWord][1] = i - results[numberOfWord - 1][0];	// wasn't found earlier -> add
							}
						} else { 
							results[numberOfWord][0] = results[numberOfWord - 1][0]; // not first or last and has a predecessor -> add
						}
					}
				}
			} 
		}
		if(results[wordsToFind.size()][1] == null) {	// all words wasn't find in given order -> return -1
			return -1;
		} else {
			return results[wordsToFind.size()][1];	// all words was found -> return shortest range
		}
	}
}
