import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class AbtMain 
{

	public static void main(String[] args) 
	{
		// Create a new object of the AbtMerge class
		AbtMerge abm = new AbtMerge();
		
		//Set up the new frame and layout
		Frame frame = new JFrame();
		frame.setLayout(new GridLayout(1, 1));
		// Add the object to the frame
		frame.add(abm);
		// Initialize and start teh applet
		abm.init();
		abm .start();

		frame.setVisible(true);

	}
}
