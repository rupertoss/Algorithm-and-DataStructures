package exercises;

import java.util.Arrays;

public class Ex31TwoDimensionalArraySpiralWay {
	
	public static void printTwoDimensionalArraySpiralWay(int[][] array) {
		for(int circleCount = 0; circleCount < array.length/2 + 1; circleCount++) {
			printRightToLeft(array, circleCount);
			printTopToBottom(array, circleCount);
			printLeftToRight(array, circleCount);
			printBottomToTop(array, circleCount);
		}
	}
	
	private static void printRightToLeft(int[][] array, int circleCount) {
		for(int i = circleCount; i < array.length - circleCount; i++) {
			System.out.print(array[circleCount][i] + " ");
		}
		System.out.println();
	}
	
	private static void printTopToBottom(int[][] array, int circleCount) {
		for(int i = 1 + circleCount; i < array.length - circleCount; i++) {
			System.out.print(array[i][array.length - circleCount - 1] + " ");
		}
		System.out.println();
	}
	
	private static void printLeftToRight(int[][] array, int circleCount) {
		for(int i = array.length - 2 - circleCount; i >= circleCount; i--) {
			System.out.print(array[array.length - circleCount - 1][i] + " ");
		}
		System.out.println();
	}
	
	private static void printBottomToTop(int[][] array, int circleCount) {
		for(int i = array.length - 2 - circleCount; i > circleCount; i--) {
			System.out.print(array[i][circleCount] + " ");
		}
		System.out.println();
	}
	
	//test
	public static void main(String[] args) {
		
		int size = 7;
		int[][] array = new int[size][size];
		
		for(int i=0; i < size; i++) {
			for(int j=0; j < size; j++) {
				array[i][j] = i*size + j + 1;
			}
		}
		
		for(int k = 0; k < size; k++) {
			System.out.println(Arrays.toString(array[k]));	
		}
		
		printTwoDimensionalArraySpiralWay(array);
	}
}
