// Code partially provided by Prof. Hossein Darbandi
/*
*	Written by: Juarez Pistore (jpistorejunior00@mylangara.bc.ca)
*				Prof. Hossein Darbandi
*	Written on: Nov 19th 2018 
*	Course: CPSC 1181 - 002 - Object Oriented Programming
*	Instructor: H. Darbandi
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Creates a bucket with 10 random integer numbers
 * @author juarezpistore
 *
 */
public class Bucket
{
   ArrayList<Integer> list;
   private Lock myLock; // locker to avoid Race Condition
   private Condition sufficientElements; // temporarily opening the lock until condition is satisfied
   
   /**
    * Constructor
    */
   public Bucket()
   {
        list = new ArrayList<Integer>();
        myLock = new ReentrantLock();
        sufficientElements = myLock.newCondition();
   }

   /**
    * Adds and element to the bucket
    * @param x
    */
   public void add(int x)
   {
	  myLock.lock();
	  try {
		  while (list.size() == 10) // It can only add 10 elements to the list
			  sufficientElements.await();
		  list.add(x);
	  } catch (Exception e) {}
	   finally {  
		  sufficientElements.signalAll();
		  myLock.unlock();
	  }
   }
   
   /**
    * Sorts the elements of the bucket
    */
   public void sort()
   {
	  myLock.lock();	  
	  try {
		  while (list.size() != 10) // It can only sort if there are 10 elements in the array list
			  sufficientElements.await();
		  Collections.sort(list);
	  } catch (Exception e) {} 
	  finally {
		  sufficientElements.signalAll();
		  myLock.unlock();
	  }
   }
  
   /**
    * Getter
    * @return size of the list
    */
   public int getSize()
   {
	  return list.size();
   }
   
   /**
    * Clears the list (removes all its elements)
    */
   public void clear(){
      list.clear();
   }
   
   /**
    * Overriding toString method
    */
   public String toString(){
	  myLock.lock();
	  try {
		  return list.toString(); // check this out later
	  } finally {
		  myLock.unlock();
	  }
   } 
}
