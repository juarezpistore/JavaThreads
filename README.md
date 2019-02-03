# Java Multithreading
A Java multithreading exercise, using 4 producers (producing integers) and 1 sorter to rule them all.

* Four producers create random integer numbers and, one at a time, add those numbers to a bucket. Whenever this bucket reaches the amount of 10 integers, it stops all producers threads, sorts the bucket and prints it.
After printing, the bucket is emptied, and the process of producing integers resumes.

* The program runs for 10 seconds, according to the line 23 (BucketThreadTester.java),
     private static final int DURATION = 10;  // time in seconds
If you wish to change the working time, feel free to do it.

* To run: 
Compile and execute BucketThreadTester.java
