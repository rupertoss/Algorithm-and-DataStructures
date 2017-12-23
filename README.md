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
 <br>
 
Exercise 2. Union find with canonical element
 * Add a method find() to the 
 * union-find data type so that find(i) returns the largest element in the 
 * connected component containing i. The operations, union(), connected(), and 
 * find() should all take logarithmic time or better.
 * 
 * For example, if one of the connected components is {1,2,6,9}, then the find() 
 * method should return 9 for each of the four elements in the connected 
 * components because 9 is larger 1, 2, and 6.
 <br>
 
Exercise 3. Successor with delete 
 * Given a set of N integers S={0,1,...,N?1} and a sequence of requests of the following form:
 *  - Remove x from S
 *  - Find the successor of x: the smallest y in S such that y>=x.
 * design a data type so that all operations (except construction) should take logarithmic time or better.
 <br>
 
 Exercise 4. Percolation
 * Given a N-by-N grid of sites. Each site is either open or blocked. 
 * A full site is an open site that can be connected to an open site 
 * in the top row via a chain of neighboring (left, right, up, down) open sites. 
 * We say the system percolates if there is a full site in the bottom row. 
 * In other words, a system percolates if we fill all open sites connected 
 * to the top row and that process fills some open site on the bottom row.
 * More details: <a href="http://coursera.cs.princeton.edu/algs4/assignments/percolation.html">Programming Assignment: Percolation</a>
 <br>
 
 Exercise 5. Queue with two stacks
 * Implement a queue with two stacks so that each queue operations 
 * takes a constant amortized number of stack operations.
 <br>
 
 Exercise 6. Stack with max
 * Create a data structure that efficiently supports the stack 
 * operations (push and pop) and also a return-the-maximum operation. 
 * Assume the elements are reals numbers so that you can compare them.
 <br>
 
 Exercise 7. Dequeue
 * A double-ended queue or deque (pronounced “deck”) is a generalization 
 * of a stack and a queue that supports adding and removing items 
 * from either the front or the back of the data structure.
 * More details: <a href="http://coursera.cs.princeton.edu/algs4/assignments/queues.html">Programming Assignment: Deques and Randomized Queues</a>
 <br>