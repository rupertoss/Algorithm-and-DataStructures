/**
 * Exemplary results
 * 
 * Enter the number of members: 
 * 250000
 * Enter the number of timestamps: 
 * 1000000
 * File generated in = H:0 M:0 S:2 nS:337806924
 * Instances generated in = H:0 M:0 S:0 nS:13396823
 * ======================================================
 * QuickFindUF
 * Couldn't find connection between all members.
 * Number of components: 89
 * Time = H:0 M:0 S:37 nS:519162104
 * ======================================================
 * QuickUnionUF
 * Couldn't find connection between all members.
 * Number of components: 89
 * Time = H:0 M:8 S:41 nS:381639539
 * ======================================================
 * WeightedQuickUnionUF
 * Couldn't find connection between all members.
 * Number of components: 89
 * Time = H:0 M:0 S:0 nS:468117044
 * ======================================================
 * WeightedQuickUnionByHeightUF
 * Couldn't find connection between all members.
 * Number of components: 89
 * Time = H:0 M:0 S:0 nS:427341836
 * ======================================================
 * QuickUnionPathCompressionUF
 * Couldn't find connection between all members.
 * Number of components: 89
 * Time = H:0 M:0 S:0 nS:437867707
 * ======================================================
 * WeightedQuickUnionPathCompressionUF
 * Couldn't find connection between all members.
 * Number of components: 89
 * Time = H:0 M:0 S:0 nS:334399415
 * 
 * 
 * Times Comparison
 * ======================================================
 * QuickFindUF				112,19865
 * QuickUnionUF				1559,15835
 * WeightedQuickUnionUF			1,39987
 * WeightedQuickUnionByHeightUF		1,27794
 * QuickUnionPathCompressionUF		1,30942
 * WeightedQuickUnionPathCompressionUF	1,00000
 */

package exercises;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import resources.QuickFindUF;
import resources.QuickUnionPathCompressionUF;
import resources.QuickUnionUF;
import resources.UF;
import resources.WeightedQuickUnionByHeightUF;
import resources.WeightedQuickUnionPathCompressionUF;
import resources.WeightedQuickUnionUF;

public class Ex01SocialNetworkConnectivity {
	private static int numberOfMembers;
	private static int numberOfTimestamps;
	private static QuickFindUF quickFind;
	private static QuickUnionUF quickUnion;
	private static WeightedQuickUnionUF weightedQuickUnion;
	private static WeightedQuickUnionByHeightUF weightedQuickUnionByHeight;
	private static QuickUnionPathCompressionUF quickUnionPathCompression;
	private static WeightedQuickUnionPathCompressionUF weightedQuickUnionPathCompression;
	
	private static int p;
	private static int q;
	private static String time;
	
	private static long startTime;
	private static long timeLast;
	private static long hours;
	private static long minutes;
	private static long seconds;
	private static long nanoSeconds;
	
	private static String path;

	public static void main(String[] args) {
		
		/**
		 * Getting user input of numberOfMembers and numberOfTimestamps
		 */
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of members: ");
		numberOfMembers = scanner.nextInt();
		System.out.println("Enter the number of timestamps: ");
		numberOfTimestamps = scanner.nextInt();
		scanner.close();
		
		/**
		 * Generating logFile with values given by user,
		 * calculating time taken to generate and printing that time.
		 */
		startTime = System.nanoTime();
		generateLogFile(numberOfMembers, numberOfTimestamps);
		timeLast = System.nanoTime() - startTime;
		calculateTime("File generated in", timeLast);
		
		/**
		 * Initializing algorithm instances,
		 * calculating time taken to initialize and printing that time.
		 */
		startTime = System.nanoTime();
		quickFind = new QuickFindUF(numberOfMembers);
		quickUnion = new QuickUnionUF(numberOfMembers);
		weightedQuickUnion = new WeightedQuickUnionUF(numberOfMembers);
		weightedQuickUnionByHeight = new WeightedQuickUnionByHeightUF(numberOfMembers);
		quickUnionPathCompression = new QuickUnionPathCompressionUF(numberOfMembers);
		weightedQuickUnionPathCompression = new WeightedQuickUnionPathCompressionUF(numberOfMembers);
		timeLast = System.nanoTime() - startTime;
		calculateTime("Instances generated in", timeLast);
		
		/**
		 * Running algorithms and initializing variables of time taken.
		 */
		double quickFindTime = runAlgorithm(quickFind);
		double quickUnionTime = runAlgorithm(quickUnion);
		double weightedQuickUnionTime = runAlgorithm(weightedQuickUnion);
		double weightedQuickUnionByHeightTime = runAlgorithm(weightedQuickUnionByHeight);
		double quickUnionPathCompressionTime =runAlgorithm(quickUnionPathCompression);
		double weightedQuickUnionPathCompressionTime = runAlgorithm(weightedQuickUnionPathCompression);
		
		/**
		 * Creating list of times taken to run by different algorithms
		 * to get the shortest time taken.
		 */
		ArrayList<Double> algorithmTimes = new ArrayList<>();
		algorithmTimes.add(quickFindTime);
		algorithmTimes.add(quickUnionTime);
		algorithmTimes.add(weightedQuickUnionTime);
		algorithmTimes.add(weightedQuickUnionByHeightTime);
		algorithmTimes.add(quickUnionPathCompressionTime);
		algorithmTimes.add(weightedQuickUnionPathCompressionTime);
		Collections.sort(algorithmTimes);
		double shortestTime = algorithmTimes.get(0);
		
		/**
		 * Printing comparison of time taken by algorithms in relation to the shortest time. 
		 */
		System.out.println("\n");
		System.out.println("Times Comparison");
		System.out.println("======================================================");
		System.out.printf(quickFind.getClass().getSimpleName() + "\t\t\t\t%.5f%n"
				+ quickUnion.getClass().getSimpleName() + "\t\t\t\t%.5f%n"
				+ weightedQuickUnion.getClass().getSimpleName() + "\t\t\t%.5f%n"
				+ weightedQuickUnionByHeight.getClass().getSimpleName() + "\t\t%.5f%n"
				+ quickUnionPathCompression.getClass().getSimpleName() + "\t\t%.5f%n"
				+ weightedQuickUnionPathCompression.getClass().getSimpleName() + "\t%.5f%n",
				quickFindTime/shortestTime, quickUnionTime/shortestTime,weightedQuickUnionTime/shortestTime, 
				weightedQuickUnionByHeightTime/shortestTime, quickUnionPathCompressionTime/shortestTime, weightedQuickUnionPathCompressionTime/shortestTime);
	}
	
	/**
	 * Returning the times of solving given problem by algorithm {@code uf}.
	 * Firstly reads data from earlier generated logFile. Then runs the algorithm. 
	 * Printing results and time taken to run.
	 * 
	 * @param uf type of algorithm
	 * @return the time of solving problem by given algorithm
	 */
	public static long runAlgorithm(UF uf) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String input;
			startTime = System.nanoTime();
			while ((uf.count() != 1) && ((input = br.readLine()) != null)) {
				String[] line = input.split(" ");
				time = line[0];
				p = Integer.parseInt(line[1]);
				q = Integer.parseInt(line[2]);
				uf.union(p, q);
			}
			
			timeLast = System.nanoTime() - startTime;
			
			System.out.println("======================================================");
			System.out.println(uf.getClass().getSimpleName());
			if (uf.count() == 1) {
				System.out.println("All members connected. Earliest time: " + time);
			} else {
				System.out.println("Couldn't find connection between all members.");
				System.out.println("Number of components: " + uf.count());
			}
			calculateTime("Time", timeLast);

		} catch (IOException e ) {
			e.printStackTrace();
		}
		return timeLast;
	}
	
	/**
	 * Generates logFile with random values by format:
	 * timestamp member1id member2id
	 * where members id's are the id's of members connected on timestamp
	 * 
	 * @param numberOfMembers the number of members
	 * @param numberOfTimestamps the number of entries in logFile
	 */
	public static void generateLogFile (int numberOfMembers, int numberOfTimestamps) {
		try {
			Path logFile = Files.createTempFile("logFile", ".txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(logFile.toFile()));
			Random rand = new Random();
			LinkedList<String> list = new LinkedList<>();
			for (int i = 0; i < numberOfTimestamps; i++) {
				int hours = rand.nextInt(24);
				int minutes = rand.nextInt(60);
				int seconds = rand.nextInt(60);
				int p = rand.nextInt(numberOfMembers);
				int q;
				do {
				q = rand.nextInt(numberOfMembers);
				} while (q == p);
				StringBuilder sb = new StringBuilder();
				sb.append(hours);
				sb.append(":");
				sb.append(minutes);
				sb.append(":");
				sb.append(seconds);
				sb.append(" ");
				sb.append(p);
				sb.append(" ");
				sb.append(q);
				sb.append("\n");
				list.add(sb.toString());
			}
			Collections.sort(list);
			for (String line : list) {
				bw.write(line);	
			}
			bw.close();
			path = logFile.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints the text {@code text} and time {@code time}
	 * converted to hours, minutes, seconds and nanoseconds. 
	 * 
	 * @param text the text to print before time results
	 * @param time the time in nanoseconds
	 */
	public static void calculateTime (String text, long time) {
		hours = TimeUnit.NANOSECONDS.toHours(time);
		minutes = TimeUnit.NANOSECONDS.toMinutes(time)%60;
		seconds = TimeUnit.NANOSECONDS.toSeconds(time)%60;
		nanoSeconds = time%1000000000;
		System.out.printf("%s = H:%d M:%d S:%d nS:%d%n", text, hours, minutes, seconds, nanoSeconds);
	}
	
}
