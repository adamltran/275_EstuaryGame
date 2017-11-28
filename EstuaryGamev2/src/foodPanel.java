import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

public class foodPanel extends JPanel implements MouseListener,
		MouseMotionListener {
	private int size = 50;

	public void setSize(int width, int height) {
	}

	public void setSize(java.awt.Dimension d) {
	}

	public void setLocationRelativeTo(java.awt.Component component) {
	}

	private int foodx = 175;

	private int foody = 175;

	private int dragFromX = 0;
	private int dragFromY = 0;

	private boolean canDrag = false;

	// final int frameCount = 10;
	int picNum = 0;

	protected boolean gameOver = false;

	BufferedImage topLeft;
	BufferedImage botRight;
	BufferedImage foodPic;
	private JTextArea txtrTime;

	public foodPanel() {

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblScore = new JLabel("Score: ");
		add(lblScore);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut);
		
		JLabel lblTimeLeft = new JLabel("Time Left: ");
		add(lblTimeLeft);
	}

	public boolean checkFood() {
		if (foodx == 75 && foody == 75)
			return true;
		return false;
	}

	public void paintComponent(Graphics g) {

		topLeft = createImage(new File("Animals/Fish_east_1.png"));
		botRight = createImage(new File("Animals/Sonny stand west.png"));
		foodPic = createImage(new File("Animals/apple_core.png"));
		BufferedImage backg = createImage(new File("Background/underwater.png"));

		super.paintComponent(g);

		g.drawImage(backg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(foodPic, foodx, foody, Color.blue, this);
		g.drawImage(topLeft, 0, 0, Color.blue, this);
		g.drawImage(botRight, 375, 325, Color.blue, this);
	}

	private BufferedImage createImage(File img) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(img);
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (x >= foodx && x <= (foodx + size) && y >= foody
				&& y <= (foody + size)) {

			canDrag = true;
			dragFromX = x - foodx;
			dragFromY = y - foody;
		}

		else {
			canDrag = false;
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (canDrag) {

			// change food position
			foodx = e.getX() - dragFromX;
			foody = e.getY() - dragFromY;

			// food doesnt drag off screen
			foodx = Math.max(foodx, 0);
			foodx = Math.min(foodx, getWidth() - size);

			foody = Math.max(foody, 0);
			foody = Math.min(foody, getHeight() - size);

			if (foodx <= 80 && foody <= 80 && gameOver == false) {
				System.out.println("you win");
				gameOver = true;
			}
			this.repaint();
		}
	}

	public void mouseExited(MouseEvent e) {
		canDrag = false;
	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}
}
