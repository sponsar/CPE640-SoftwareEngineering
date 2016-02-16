package Ball;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

import java.awt.Container;

public class Main {
   // Entry main program
   public static void main(String[] args) {
      // Run UI in the Event Dispatcher Thread (EDT), instead of Main thread
      //javax.swing.SwingUtilities.invokeLater(
	   SwingUtilities.invokeLater(
    		  new Runnable() {
    			  public void run() {
    				  JFrame frame = new JFrame("A World of Balls");
    				  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    				  //frame.add(new JSlider());
    				  frame.setContentPane(new BallWorld(640, 480)); // BallWorld is a JPanel
    				  //Container c = frame.getContentPane();
    				  //c.add(new BallWorld(640,480));
    				  //frame.add(new BallWorld(640,480));//两句话有区别,但意义上,没有区别...
    				  frame.pack();            // Preferred size of BallWorld
    				  frame.setVisible(true);  // Show it
    			  }
    		  }
    		  );
   }
}
