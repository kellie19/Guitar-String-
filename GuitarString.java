// Kellie Gui
// CSE 143 BF with Chrish Thakalath
// Homework 2 GuitarString
// GuitarString simulates a guitar string object with a given frequency
// and keeps track of a ring buffer 
import java.util.*;

public class GuitarString {
   public static final double ENERGY_DECAY_FACTOR = 0.996; // energy decay constant 
   private Queue<Double> buffer; // buffer is to 
                                                            // keep track of a ring buffer 
   
   // pre: the frequency > 0 ,
   //      and the size of the ring buffer >= 2,
   //      throw an IllegalArgumentException if not
   // post: constructs a guitar string of the given frequency
   //       It creates a ring burffer and calcuates its size 
   //       and initiates the buffer with 0 
   public GuitarString(double frequency) {
      buffer = new LinkedList<Double>();
      if ( frequency < 0 || frequency == 0) {
         throw new IllegalArgumentException();
      } 
      int size = Math.round((float)StdAudio.SAMPLE_RATE / (float) frequency);
      if (size < 2) {
         throw new IllegalArgumentException();
      }
      buffer = new LinkedList<Double>();
      for (int i = 0; i < size; i++) {
         buffer.add(0.0);
      }
   }
   
   // pre: the size of the given array cannot smaller than 2
   //      throw an IllegalArgumentExpection if not
   // post: constructs a guitar string and creates a 
   //       ring buffer filled by the value in the array
   //       This constructor is only for testing purpose
   public GuitarString(double[] init) {
      if( init.length < 2) {
         throw new IllegalArgumentException();
      }
      buffer = new LinkedList<Double>();
      for (int i = 0; i < init.length; i++) {
         buffer.add(init[i]);
      }
   }
   
   // post: replace random values (from -0.5 inclusive to 0.5 exclusive)
   //       from the previous vaules in the ring buffer  
   public void pluck() {
      Random r = new Random();
      for (int i = 0; i < buffer.size(); i++) {
         buffer.remove();
         buffer.add(r.nextDouble() - 0.5);
      }
   }
   
   // post: update the ring buffer applied the Karplus-Strong algorithm 
   //       by taking first two values and add to the end of the ring buffer 
      public void tic() {
      double first = buffer.remove();
      double second = buffer.peek();
      double result = ENERGY_DECAY_FACTOR * (first + second) * 0.5;
      buffer.add(result);
   }
   
   // post: returns the value at the front of the ring buffer at the moment
   public double sample() {
      return buffer.peek();
   }
   
   
}


