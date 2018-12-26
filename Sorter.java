// Code partially provided by Prof. Hossein Darbandi
/*
*	Written by: Juarez Pistore (jpistorejunior00@mylangara.bc.ca)
*				Prof. Hossein Darbandi
*	Written on: Nov 19th 2018 
*	Course: CPSC 1181 - 002 - Object Oriented Programming
*	Instructor: H. Darbandi
*/

/**
 * Thread that sorts elements to the bucket class
 * @author juarezpistore
 *
 */
public class Sorter implements Runnable
{
   private boolean done = false;
   private final int LIMIT = 100; // how many times the program iterates
   private Bucket bucket;
   private static final int DELAY = 10;
   /**
    * Constructor
    * @param bucket
    */
   public Sorter(Bucket bucket)
   {
      this.bucket = bucket;
   }
   /**
    * Overrides the method run to sort elements of the bucket if the bucket is full (10 elements)
    */
   public void run() {
      try {
    	  int i = 0; // number of times that sort method was called
    	  while(true && !Thread.interrupted()) {
    		  
	    	  bucket.sort(); 
	    	  System.out.println(bucket);
	    	  if (bucket.getSize() != 10)
	    		  throw new InterruptedException("List size not equal to 10. It is equal to " + bucket.getSize());	    	  
	    	  bucket.clear();
	    	  System.out.println("iteration: " + (++i));
	    	  if (i >= LIMIT)
	    		  done = true;
	    	  Thread.sleep(DELAY);
    	  }
      }
      catch (InterruptedException exception) {
    	  System.out.println(exception.getMessage());
      }
   }
   public boolean getDone() {
	   return done;
   }
}

