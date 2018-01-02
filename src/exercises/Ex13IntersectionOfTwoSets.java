package exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Ex13IntersectionOfTwoSets {
	
	private ArrayList<Point> arrayA;
	private ArrayList<Point> arrayB;
	private ArrayList<Point> intersection;
	private int maxPoints;

	public Ex13IntersectionOfTwoSets(int numberOfPoints, int range) {
		this.arrayA = new ArrayList<>();
		this.arrayB = new ArrayList<>();
		this.intersection = new ArrayList<>();
		this.maxPoints = (2*range +1)*(2*range +1);
		
		for(int i = 0; i < numberOfPoints && arrayA.size() < maxPoints; i++) {
			Point p = new Point(range);
			while(arrayA.contains(p)) {
				p = new Point(range);
			}
			arrayA.add(p);
			
			p = new Point(range);
			while(arrayB.contains(p)) {
				p = new Point(range);
			}
			arrayB.add(p);
		}
		
		Collections.sort(arrayA);
		Collections.sort(arrayB);
		
		for(Point pFromA: arrayA) {
			for(Point pFromB: arrayB) {
				if(pFromA.equals(pFromB)) {
					intersection.add(pFromA);
					break;
				}
			}
		}
		
		for(Point p: intersection) {
			System.out.println(p);
		}
	}

	class Point implements Comparable<Point> {
		private int x;
		private int y;
		
		public Point(int range) {
			x = ThreadLocalRandom.current().nextInt(-range, range + 1);
			y = ThreadLocalRandom.current().nextInt(-range, range + 1);
		}

		@Override
		public int compareTo(Point that) {
			if(this.x > that.x)
				return 1;
			else if(this.x < that.x) 
				return -1;
			else if(this.y > that.y)
				return 1;
			else if(this.y < that.y)
				return -1;
			else
				return 0;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

		@Override
		public boolean equals(Object obj) {
			
			if(obj == this)		return true;
			
			if(obj == null)		return false;
			
			if(this.getClass() != obj.getClass()) 	return false;
			
			Point that = (Point) obj;
			if(this.x != that.x) 	return false;
			if(this.y != that.y)	return false;
			return true;
		}
	}
	
	public static void main(String[] args) {
		Ex13IntersectionOfTwoSets intersection = new Ex13IntersectionOfTwoSets(50, 10);
	}
}
