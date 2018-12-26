// Code partially provided by Prof. Hossein Darbandi
/*
*	Written by: Juarez Pistore (jpistorejunior00@mylangara.bc.ca)
*				Prof. Hossein Darbandi
*	Written on: Nov 19th 2018 
*	Course: CPSC 1181 - 002 - Object Oriented Programming
*	Instructor: H. Darbandi
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Tests the threads Produces, Sorter, and the bucket class
 * @author juarezpistore
 *
 */
public class BucketThreadTester
{
   private static final int DURATION = 10;
   private static Timer t;
   private static ArrayList<Thread> list;
   public static void main(String[] args)
   {
	  final int MAX = 10; // maximum threads in the pool
      Bucket bucket = new Bucket();
      ExecutorService pool = Executors.newFixedThreadPool(MAX);
      list = new ArrayList<Thread>();
      
      startTimeCounter();
      Producer p1 = new Producer(bucket);
      Producer p2 = new Producer(bucket);
      Producer p3 = new Producer(bucket);
      Producer p4 = new Producer(bucket);
      Sorter  s = new Sorter(bucket);
   
      Thread tp1 = new Thread(p1);
      list.add(tp1);
      Thread tp2 = new Thread(p2);
      list.add(tp2);
      Thread tp3 = new Thread(p3);
      list.add(tp3);
      Thread tp4 = new Thread(p4);
      list.add(tp4);
      Thread ts = new Thread(s);
      list.add(ts);
      
      pool.execute(tp1);
      pool.execute(tp2);
      pool.execute(tp3);
      pool.execute(tp4);
      ts.start();
    }
   
   	public static void startTimeCounter() {
   	    class TimerListener implements ActionListener {
   	      int i=0;
   	      public void actionPerformed(ActionEvent event) {
   	        i++;
   	        if(i==DURATION) {
   	          for(Thread tr: list)
   	            tr.interrupt();
   	          t.stop();
   	          System.out.println("Time limit ( " + DURATION + " sec ) was reached.");
   	        }
   	      }
   	    }
   	    t = new Timer(1000, new TimerListener());
   	    t.start(); 
   	}
   
}
