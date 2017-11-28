import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class BackgroundImage extends JComponent {

	 private static void createAndShowUI() {
	      Image image = null;
	      try {
	         image = ImageIO.read(new File("Background/Background_05.png"));
	         // JLabel label = new JLabel(new ImageIcon(image));
	         Difficulty backg = new Difficulty(image);

	         JFrame frame = new JFrame();
	         frame.getContentPane().add(backg);
	         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	         frame.pack();
	         frame.setLocationRelativeTo(null);
	         frame.setVisible(true);
	      } catch (IOException e) {
	         e.printStackTrace();
	      }

	   }
}