package bogus;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.Animal;

public class View extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	private Controller controller;

	private BufferedImage topLeft = createImage(new File("Animals/Fish_east_1.png"));
	private BufferedImage botRight = createImage(new File("Animals/Sonny stand west.png"));
	private BufferedImage foodPic = createImage(new File("Animals/apple_core.png"));
	private BufferedImage backg = createImage(new File("Background/underwater.png"));
	private BufferedImage strike = createImage(new File("Background/red_x.png"));
	private BufferedImage bubble = createImage(new File("Background/b1.png"));
	private JLabel lblTimeLeft;
	private JLabel lblScore;
	private JLabel lblTutorialHint;
	private JButton btnMainMenu;
	JLabel endlabel = new JLabel("That's it, the game is over!");
	JLabel score = new JLabel("Score: ");
	JButton again = new JButton("Play again to get a higher score?");
	private JButton btnViewHighScores;
	private boolean tutorial;
	private boolean end;
	
	private Image image;
	JButton start = new JButton("Start Game");
	JLabel name = new JLabel("Whose Prey is it Anyway?");
	JButton Easy = new JButton("Easy");
	JButton Medium = new JButton("Medium");
	JButton Hard = new JButton("Hard");

	private BufferedImage[] animals = new BufferedImage[] { createImage(new File("Eaters/blueCrab_east.png")),
			createImage(new File("Eaters/blueCrab_west.png")), createImage(new File("Eaters/Harbor-Seal-2_east.png")), 
			createImage(new File("Eaters/Harbor-Seal-2_west.png")), createImage(new File("Eaters/horseshoe_east.png")), 
			createImage(new File("Eaters/horseshoe_west.png")), createImage(new File("Eaters/Stingray1_0_east.png")), 
			createImage(new File("Eaters/Stingray1_0_west.png")), createImage(new File("Eaters/stripedbass_east.png")), 
			createImage(new File("Eaters/stripedbass_west.png"))};
	private BufferedImage[] foods = new BufferedImage[] { createImage(new File("Food/algae.png")),
			createImage(new File("Food/clam.png")), createImage(new File("Food/crab.png")),
			createImage(new File("Food/deadfish.png")), createImage(new File("Food/fish.png")),
			createImage(new File("Food/fly.png")), createImage(new File("Food/pikefish.png")),
			createImage(new File("Food/shrimp.png")), createImage(new File("Food/snail.png")),
			createImage(new File("Food/worm.png")) };


	public View(Controller controller, boolean tutorial, boolean end) {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.controller = controller;
		this.tutorial = tutorial;
		this.end = end;
		
		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnMainMenu.setPreferredSize(new Dimension(220, 75));
		btnMainMenu.addActionListener(btnMainMenuListener);
		
		lblTutorialHint = new JLabel("Hint");
		lblTutorialHint.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTutorialHint.setForeground(Color.RED);
		lblTutorialHint.setToolTipText("Drag the food to its predator");
		
		if (tutorial) {
			
			add(btnMainMenu);
			add(lblTutorialHint);
			
		}
		Font font = new Font("Whose Prey is it Anyway?", Font.BOLD, 35);
		name.setFont(font);
		name.setForeground(Color.GREEN);

		this.image = image;
		Component horizontalStrut3 = Box.createHorizontalStrut(70);
		add(horizontalStrut3);
		if (!tutorial && !end) {
			lblTimeLeft = new JLabel("Time Left: ");
			lblTimeLeft.setFont(new Font("Tahoma", Font.BOLD, 25));
			add(lblTimeLeft);
		}
		
		else{
			
			
			
			
			
		}
		
		start.addActionListener(this);
		
		// Easy.addActionListener(this);
		// Medium.addActionListener(this);
		// Hard.addActionListener(this);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		add(horizontalStrut_2);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut);

		if (!tutorial) {
			lblScore = new JLabel("Score: ");
			lblScore.setFont(new Font("Tahoma", Font.BOLD, 25));
			add(lblScore);
		}
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
	

	public void paintComponent(Graphics g) {
		Model m = controller.getModel();
		if (!tutorial) {
			if (m.getTimeRemaining() > 15) {
				lblTimeLeft.setText("Time Left: " + m.getTimeRemaining());
			}
			else if (m.getTimeRemaining() <= 15 && m.getTimeRemaining() > 5) {
				lblTimeLeft.setText("Time Left: " + m.getTimeRemaining());
				lblTimeLeft.setForeground(Color.YELLOW);
			}
			else if (m.getTimeRemaining() <= 5) {
				lblTimeLeft.setText("Time Left: " + m.getTimeRemaining());
				lblTimeLeft.setForeground(Color.RED);
			}
			while (m.getTimeRemaining() == 1) {
				
				GridBagLayout gridBagLayout = new GridBagLayout();
				gridBagLayout.columnWidths = new int[] { 20, 220, 0, 189, 73, 1, 1, 1,
						1, 1, 20, 0 };
				gridBagLayout.rowHeights = new int[] { 54, 0, 44, 0, 0, 0, 0, 0, 0, 0,
						0 };
				gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
						0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
						0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				setLayout(gridBagLayout);

				Component glue_3 = Box.createGlue();
				GridBagConstraints gbc_glue_3 = new GridBagConstraints();
				gbc_glue_3.anchor = GridBagConstraints.WEST;
				gbc_glue_3.insets = new Insets(0, 0, 5, 5);
				gbc_glue_3.gridx = 5;
				gbc_glue_3.gridy = 0;
				add(glue_3, gbc_glue_3);


				Component glue_4 = Box.createGlue();
				GridBagConstraints gbc_glue_4 = new GridBagConstraints();
				gbc_glue_4.anchor = GridBagConstraints.WEST;
				gbc_glue_4.insets = new Insets(0, 0, 5, 5);
				gbc_glue_4.gridx = 9;
				gbc_glue_4.gridy = 0;
				add(glue_4, gbc_glue_4);

				JLabel lblThatsItGames = new JLabel("That's it, Game's over!        ");
				lblThatsItGames.setFont(new Font("Tahoma", Font.BOLD, 45));
				lblThatsItGames.setForeground(Color.RED);
				GridBagConstraints gbc_lblThatsItGames = new GridBagConstraints();
				gbc_lblThatsItGames.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblThatsItGames.insets = new Insets(0, 0, 5, 5);
				gbc_lblThatsItGames.gridwidth = 4;
				gbc_lblThatsItGames.gridx = 1;
				gbc_lblThatsItGames.gridy = 1;
				add(lblThatsItGames, gbc_lblThatsItGames);

				JLabel lblFinalScore = new JLabel("   Final Score: " + m.getScore()+ "   ");
				lblFinalScore.setForeground(Color.BLUE);  
				lblFinalScore.setFont(new Font("Tahoma", Font.BOLD, 50));
				GridBagConstraints gbc_lblFinalScore = new GridBagConstraints();
				gbc_lblFinalScore.anchor = GridBagConstraints.NORTHEAST;
				gbc_lblFinalScore.insets = new Insets(0, 0, 5, 5);
				gbc_lblFinalScore.gridwidth = 2;
				gbc_lblFinalScore.gridx = 1;
				gbc_lblFinalScore.gridy = 3;
				add(lblFinalScore, gbc_lblFinalScore);
				GridBagConstraints gbc_again = new GridBagConstraints();
				gbc_again.anchor = GridBagConstraints.WEST;
				gbc_again.insets = new Insets(0, 0, 5, 5);
				gbc_again.gridx = 1;
				gbc_again.gridy = 5;
				add(again, gbc_again);

				again.addActionListener(this);
;
			}
			lblScore.setText("Score: " + m.getScore());
		}

		super.paintComponent(g);

		g.drawImage(backg, 0, 0, getWidth(), getHeight(), this);
		for (Animal a : m.getAnimals()) {
			BufferedImage i;
			switch (a.getType()) {
			case BLUECRAB:
				if (a.getDirection())
					i = animals[0];
				else
					i = animals[1];
				break;
			case HARBORSEAL:
				if (a.getDirection())
					i = animals[2];
				else
					i = animals[3];
				break;
			case HORSESHOECRAB:
				if (a.getDirection())
					i = animals[4];
				else
					i = animals[5];
				break;
			case STINGRAY:
				if (a.getDirection())
					i = animals[6];
				else
					i = animals[7];
				break;
			case STRIPEDBASS:
				if (a.getDirection())
					i = animals[8];
				else
					i = animals[9];
				break;
			default:

				i = createImage(new File("Animals/apple_core.png"));
				break;
			}
			g.drawImage(i, (int) a.getX() - 50, (int) a.getY() - 50, getWidth() / 4, getHeight() / 4, this);

			BufferedImage foodi;
			switch (m.getFood().getType()) {
			case ALGAE:
				foodi = foods[0];
				break;
			case CLAM:
				foodi = foods[1];
				break;
			case CRAB:
				foodi = foods[2];
				break;
			case DEADFISH:
				foodi = foods[3];
				break;
			case FISH:
				foodi = foods[4];
				break;
			case FLY:
				foodi = foods[5];
				break;
			case SHRIMP:
				foodi = foods[6];
				break;
			case SNAIL:
				foodi = foods[7];
				break;
			case SQUIGGLYWORMLOOKINFISHTHATSTICKSOUTTATHESAND:
				foodi = foods[8];
				break;
			case WORM:
				foodi = foods[9];
				break;
			default:
				foodi = createImage(new File("Animals/apple_core.png"));
				break;

			}
			
			g.drawImage(foodi, (int) m.getFood().getX() - 75 / 2, (int) m.getFood().getY() - 75 / 2, getWidth() / 10,
					getHeight() / 10, this);

		}
		for (int i = 0; i < controller.getModel().getNumberOfStrikes(); i++) {
			g.drawImage(strike, 55 * i + 100, 15, this);
		}
		
	}
	
	
	
	ActionListener btnMainMenuListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.displayMenuScreen();
			JComponent source = (JComponent) e.getSource();
			Window window = SwingUtilities.getWindowAncestor(source);
			window.dispose();
		}
	};
	

	@Override
	public void mouseDragged(MouseEvent e) {
		controller.getModel().moveFood(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		controller.getModel().grabFood(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		controller.getModel().dropFood();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Play again to get a higher score?")) {
				JFrame game = new JFrame("Who's Meal is it Anyway?");
		game.setSize(500, 500);
		game.getContentPane();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);

		// dispose();
	}
	}

}
