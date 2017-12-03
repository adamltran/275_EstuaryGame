<<<<<<< HEAD:EstuaryGamev2/src/game/Start.java
package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Start extends JPanel implements ActionListener{

	
	private Image image;
	
	JButton start = new JButton("Start");
	
	JLabel name = new JLabel("Who's Meal is it Anyway?");
	
	
	
	public Start(Image image) {
		Font font = new Font("Who's Meal is it Anyway?", Font.BOLD, 35);
		name.setFont(font);
		name.setForeground(Color.RED);
		
		this.image = image;
		
		setLayout(new GridBagLayout());
		
		add(start, createGbc(0,1,0,0));	
		add(name, createGbc(0,0,0,1));
		
		
		
		
		
		start.addActionListener(this);
	}
	
	
	
	 private GridBagConstraints createGbc(int x, int y, int w, int h) {
	      GridBagConstraints gbc = new GridBagConstraints();
	      gbc.gridx = x;
	      gbc.gridy = y;
	      gbc.gridwidth = w;
	      gbc.gridheight = h;

	      gbc.weightx = 0.0; // bunches stuff in center in x orientation
	      gbc.weighty = 0.0; // bunches stuff in center in y orientation
	      gbc.fill = GridBagConstraints.BOTH;
	      gbc.insets = new Insets(5, 5, 5, 5);

	      return gbc;
	   }

	    @Override
	    public Dimension getPreferredSize() {
	       Dimension superSize = super.getPreferredSize();
	       if (isPreferredSizeSet() || image == null) {
	          return superSize;
	       }
	       int prefW = Math.max(image.getWidth(null), superSize.width);
	       int prefH = Math.max(image.getHeight(null), superSize.height);
	       return new Dimension(prefW, prefH);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       if (image != null) {
	          g.drawImage(image, 0, 0, null);
	       }
	    }
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")) {
			java.awt.EventQueue.invokeLater(new Runnable() {
		         public void run() {
		            createAndShowUI();
		         }
		      });
		
	}

}
	
	 private static void createAndShowUI() {
	      Image image = null;
	      try {
	         image = ImageIO.read(new File("Background/Background_05.png"));
	         // JLabel label = new JLabel(new ImageIcon(image));
	         Difficulty backg = new Difficulty(image);

	         JFrame frame = new JFrame("Who's Meal is it Anyway?");
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
=======

 import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.GridBagConstraints;
 import java.awt.GridBagLayout;
 import java.awt.Image;
 import java.awt.Insets;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import java.awt.image.BufferedImage;
 import java.io.File;
 import java.io.IOException;
 
 import javax.imageio.ImageIO;
 import javax.swing.JButton;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 
 public class Start extends JPanel implements ActionListener {
 
 	private Image image;
 	JButton start = new JButton("Start Game");
 	JLabel name = new JLabel("Who's Meal is it Anyway?");
 	JButton Easy = new JButton("Easy");
 	JButton Medium = new JButton("Medium");
 	JButton Hard = new JButton("Hard");
 
 	public Start(Image image) {
 
 		Font font = new Font("Who's Meal is it Anyway?", Font.BOLD, 35);
 		name.setFont(font);
 		name.setForeground(Color.GREEN);
 
 		this.image = image;
 		setLayout(new GridBagLayout());
 		add(name, createGbc(0, 0, 0, 0));
 		add(Easy, createGbc(0, 0, 1, 1));
 		add(Medium, createGbc(1, 0, 1, 1));
 		add(Hard, createGbc(0, 1, 0, 0));
 
 		Easy.addActionListener(this);
 		Medium.addActionListener(this);
 		Hard.addActionListener(this);
 	}
 
 	private GridBagConstraints createGbc(int x, int y, int w, int h) {
 		GridBagConstraints gbc = new GridBagConstraints();
 		gbc.gridx = x;
 		gbc.gridy = y;
 		gbc.gridwidth = w;
 		gbc.gridheight = h;
 
 		gbc.weightx = 0.0; // bunches stuff in center in x orientation
 		gbc.weighty = 0.0; // bunches stuff in center in y orientation
 		gbc.fill = GridBagConstraints.BOTH;
 		gbc.insets = new Insets(5, 5, 5, 5);
 
 		return gbc;
 	}
 
 	@Override
 	public Dimension getPreferredSize() {
 		Dimension superSize = super.getPreferredSize();
 		if (isPreferredSizeSet() || image == null) {
 			return superSize;
 		}
 		int prefW = Math.max(image.getWidth(null), superSize.width);
 		int prefH = Math.max(image.getHeight(null), superSize.height);
 		return new Dimension(prefW, prefH);
 	}
 
 	@Override
 	protected void paintComponent(Graphics g) {
 		super.paintComponent(g);
 		if (image != null) {
 			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
 
 		}
 	}
 
 	@Override
 	public void actionPerformed(ActionEvent e) {
 		if (e.getActionCommand().equals("Easy")
 				|| e.getActionCommand().equals("Medium")
 				|| e.getActionCommand().equals("Hard")) {
 			// creating food
 			foodPanel food = new foodPanel();
 
 			// creating frame
 			JFrame game = new JFrame("Who's Meal is it Anyway?");
 			game.setSize(500, 500);
 			game.add(food);
 			game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 			game.setVisible(true);
 
 			// dispose();
 		}
 
 	}
 
 }
>>>>>>> d9def9bd736d6a9c6e8ceaf74d19817f7f406c5f:EstuaryGamev2/src/Start.java
