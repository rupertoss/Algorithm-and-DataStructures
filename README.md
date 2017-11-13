# Algorithm-and-DataStructures

This repository contains examples of using algorithms and data structures in programs written in Java.

Exercises are based on Coursera - Algorithms Part I.
Programs uses algorithms written by mentors in above-mentioned course.
These mentors are: Robert Sedgewick and Kevin Wayne from Princeton University. 
For additional documentation, see <a href="https://algs4.cs.princeton.edu/home/">Algorithms, 4th Edition</a>.



Exercise 1. Social Network Connectivity
 * Given a social network containing N members and a log file containing M
 * timestamps at which times pairs of members formed friendships, design an
 * algorithm to determine the earliest time at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend).
 * Assume that the log file is sorted by timestamp and that friendship is an
 * equivalence relation. The running time of your algorithm should be MlogN or
 * better and use extra space proportional to N.
 *
 * Solving contains comparison of times of different algorithms. 
 
Exercise 2. Union find with canonical element
 * Add a method find() to the 
 * union-find data type so that find(i) returns the largest element in the 
 * connected component containing i. The operations, union(), connected(), and 
 * find() should all take logarithmic time or better.
 * 
 * For example, if one of the connected components is {1,2,6,9}, then the find() 
 * method should return 9 for each of the four elements in the connected 
 * components because 9 is larger 1, 2, and 6.