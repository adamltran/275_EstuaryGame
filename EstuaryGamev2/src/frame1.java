import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class frame1 {

	public static void main(String[] args) {

		Controller controller = new Controller(new Model(), new foodPanel());
		(new Thread(controller)).start();

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI();
			}
		});

		/*
		 * //creating food foodPanel food = new foodPanel();
		 * 
		 * //creating frame JFrame game = new
		 * JFrame("Who's Meal is it Anyway?"); game.setSize(500, 500);
		 * game.add(food); game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * game.setVisible(true);
		 */
	}

	private static void createAndShowUI() {
		Image image = null;
		try {
			image = ImageIO.read(new File("Background/Background_05.png"));
			// JLabel label = new JLabel(new ImageIcon(image));
			Start backg = new Start(image);

			int frameWidth = 500;
			int frameHeight = 500;
			JFrame frame = new JFrame("Who's Meal is it Anyway?");
			frame.getContentPane().add(backg);
			backg.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setLocationRelativeTo(null);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setBounds((int) screenSize.getWidth() - frameWidth, 0,
					frameWidth, frameHeight);
			frame.setVisible(true);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
