// Code partially provided by Prof. Hossein Darbandi
/*
*	Written by: Juarez Pistore (jpistorejunior00@mylangara.bc.ca)
*				Prof. Hossein Darbandi
*	Written on: Nov 19th 2018 
*	Course: CPSC 1181 - 002 - Object Oriented Programming
*	Langara College, Vancouver, BC, Canada
*	Instructor: H. Darbandi
*/
import java.util.Random;

/**
 * Thread that produces elements to the bucket class given stated conditions
 * @author juarezpistore
 *
 */
public class Producer implements Runnable
{
   private Bucket bucket;
   private static final int DELAY = 10; 
   private static final int MAX = 1000;
   Random rand;
   /**
    * Constructor
    * @param bucket 
    */
   public Producer(Bucket bucket)
   {
      this.bucket = bucket;
      rand = new Random();
   }
   /**
    * Overrides the method run to add elements to the bucket until the bucket is full (10 elements)
    */
   public void run() {
      try {
         while(true && !Thread.interrupted()) {
        	 Thread.sleep(DELAY);
        	 if (bucket.getSize() == 10)
        		 continue;
        	 bucket.add(rand.nextInt(MAX));
         }
      }
      catch (InterruptedException e) {
    	  System.out.println(e.getMessage());
      }     
   }
}

