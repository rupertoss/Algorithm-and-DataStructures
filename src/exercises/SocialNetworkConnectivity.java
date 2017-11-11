/**
 * Social network connectivity
 *
 * Given a social network containing N members and a log file containing M
 * timestamps at which times pairs of members formed friendships, design an
 * algorithm to determine the earliest time at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend).
 * Assume that the log file is sorted by timestamp and that friendship is an
 * equivalence relation. The running time of your algorithm should be MlogN or
 * better and use extra space proportional to N.
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
import library.QuickFindUF;
import library.QuickUnionPathCompressionUF;
import library.QuickUnionUF;
import library.UF;
import library.WeightedQuickUnionByHeightUF;
import library.WeightedQuickUnionPathCompressionUF;
import library.WeightedQuickUnionUF;

public class SocialNetworkConnectivity {
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
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of members: ");
		numberOfMembers = scanner.nextInt();
		System.out.println("Enter the number of timestamps: ");
		numberOfTimestamps = scanner.nextInt();
		scanner.close();
		
		startTime = System.nanoTime();
		generateLogFile(numberOfMembers, numberOfTimestamps);
		timeLast = System.nanoTime() - startTime;
		
		hours = TimeUnit.NANOSECONDS.toHours(timeLast);
		minutes = TimeUnit.NANOSECONDS.toMinutes(timeLast)%60;
		seconds = TimeUnit.NANOSECONDS.toSeconds(timeLast)%60;
		nanoSeconds = timeLast%1000000000;
		System.out.printf("File generated in = H:%d M:%d S:%d nS:%d%n", hours, minutes, seconds, nanoSeconds);
		
		
		startTime = System.nanoTime();
		quickFind = new QuickFindUF(numberOfMembers);
		quickUnion = new QuickUnionUF(numberOfMembers);
		weightedQuickUnion = new WeightedQuickUnionUF(numberOfMembers);
		weightedQuickUnionByHeight = new WeightedQuickUnionByHeightUF(numberOfMembers);
		quickUnionPathCompression = new QuickUnionPathCompressionUF(numberOfMembers);
		weightedQuickUnionPathCompression = new WeightedQuickUnionPathCompressionUF(numberOfMembers);
		timeLast = System.nanoTime() - startTime;

		hours = TimeUnit.NANOSECONDS.toHours(timeLast);
		minutes = TimeUnit.NANOSECONDS.toMinutes(timeLast)%60;
		seconds = TimeUnit.NANOSECONDS.toSeconds(timeLast)%60;
		nanoSeconds = timeLast%1000000000;
		System.out.printf("Instances generated in = H:%d M:%d S:%d nS:%d%n", hours, minutes, seconds, nanoSeconds);
		
		
		double quickFindTime = runAlgorithm(quickFind);
		double quickUnionTime = runAlgorithm(quickUnion);
		double weightedQuickUnionTime = runAlgorithm(weightedQuickUnion);
		double weightedQuickUnionByHeightTime = runAlgorithm(weightedQuickUnionByHeight);
		double quickUnionPathCompressionTime =runAlgorithm(quickUnionPathCompression);
		double weightedQuickUnionPathCompressionTime = runAlgorithm(weightedQuickUnionPathCompression);
		
		ArrayList<Double> algorithmTimes = new ArrayList<>();
		algorithmTimes.add(quickFindTime);
		algorithmTimes.add(quickUnionTime);
		algorithmTimes.add(weightedQuickUnionTime);
		algorithmTimes.add(weightedQuickUnionByHeightTime);
		algorithmTimes.add(quickUnionPathCompressionTime);
		algorithmTimes.add(weightedQuickUnionPathCompressionTime);
		Collections.sort(algorithmTimes);
		
		double shortestTime = algorithmTimes.get(0);
		
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
	
	public static long runAlgorithm(UF un) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String input;
			startTime = System.nanoTime();
			while ((un.count() != 1) && ((input = br.readLine()) != null)) {
				String[] line = input.split(" ");
				time = line[0];
				p = Integer.parseInt(line[1]);
				q = Integer.parseInt(line[2]);
				un.union(p, q);
			}
			
			timeLast = System.nanoTime() - startTime;
			
			hours = TimeUnit.NANOSECONDS.toHours(timeLast);
			minutes = TimeUnit.NANOSECONDS.toMinutes(timeLast)%60;
			seconds = TimeUnit.NANOSECONDS.toSeconds(timeLast)%60;
			nanoSeconds = timeLast%1000000000;
			
			System.out.println("======================================================");
			System.out.println(un.getClass().getSimpleName());
			if (un.count() == 1) {
				System.out.println("All members connected. Earliest time: " + time);
			} else {
				System.out.println("Couldn't find connection between all members.");
				System.out.println("Number of components: " + un.count());
			}
			System.out.printf("Time = H:%d M:%d S:%d nS:%d%n", hours, minutes, seconds, nanoSeconds);

		} catch (IOException e ) {
			e.printStackTrace();
		}
		return timeLast;
	}
	
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
	
}
