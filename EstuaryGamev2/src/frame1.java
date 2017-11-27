import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class frame1{

	
	public static void main(String[] args) {
	
		
		Controller controller = new Controller(new Model(), new foodPanel());
		(new Thread(controller)).start();
		
		//creating food
		foodPanel food = new foodPanel();
		//creating frame
		JFrame f = new JFrame("Who's Meal is it Anyway?");
		f.setSize(500, 500);
		f.add(food);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
}
