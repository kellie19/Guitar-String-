// Kellie Gui
// CSE 143 BF with Chrish Thakalath
// Homework 2 Guitar37
// Guitar37 sitmulates 37 different guitar strings 
// that implements the guitar interface 
// It can use the computer keyboard as each guitar string 
// or type in an index to play a guitar string 

public class Guitar37 implements Guitar {
   public static final String KEYBOARD =
   "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   private int time; // keep track of how many times vic method being called
   private GuitarString[] stringX = new GuitarString[KEYBOARD.length()]; 
                                             // keep track of all the guitar strings
   
   // post: constructs 37 guitar strings  
   //       by calculating the frequency of each guitar string 
   //       by using the index of the guitar string 
   public Guitar37() {
      double frequency = 0.0;
      this.time = 0;
      for (int i = 0; i < KEYBOARD.length(); i++) {
         frequency = 440.0 * Math.pow(2, ((i - 24) / 12.0));
         stringX[i] = new GuitarString(frequency);
      }
   }
   
   // post: plucks a certain guitar string if the pitch 
   //       is in the chromatic scale. 
   //       If the given pitch is not in the corresponding scale,
   //       it is ignored. 
   //       A pitch of 0 corresponds to concert-A chromatic scale  
   public void playNote(int pitch) {
      pitch = pitch + 24;
      if (pitch >= 0 && pitch < KEYBOARD.length()) {
         stringX[pitch].pluck();
      }
   }
   
   // post: retrun true if the given string is a key in the keyboard
   public boolean hasString(char key) {
      return KEYBOARD.contains("" + key);
   }
   
   // pre: pluck the key included in the keyboard
   //      throw IllegalArgumentException if not 
   // post: plucks the string of the given key at the corresponding index  
   public void pluck(char key) {
      if (! KEYBOARD.contains(key + "")) {
         throw new IllegalArgumentException();
      }
      stringX[KEYBOARD.indexOf(key + "")].pluck();
   }
   
   // post: return the sum of the current first value
   //       stroed in the ring buffer of each guitar string
   public double sample() {
      double sum = 0.0;
      for(int i = 0; i < KEYBOARD.length(); i++) {
         sum += stringX[i].sample();
      }
      return sum;
   }
   
   // post: tics every guitar string 
   //       and records how many times it tics 
   public void tic() {
      for(int i = 0; i < KEYBOARD.length(); i++) {
         stringX[i].tic();
      }
      time++;
   }
   
   // post: return the sum of times calling tic method 
   public int time() {
      return time;
   }
   
}