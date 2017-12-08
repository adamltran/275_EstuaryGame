package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mvc.Controller;
import mvc.Model;

public class Tutorial extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
	
	private Controller controller;
	

	private BufferedImage backg = createImage(new File("Background/underwater.png"));
	private BufferedImage strike = createImage(new File("Background/red_x.png"));
	private BufferedImage[] animals = new BufferedImage[] { createImage(new File("Eaters/blueCrab.png")),
			createImage(new File("Eaters/Harbor-Seal-2.png")), createImage(new File("Eaters/horseshoe.png")),
			createImage(new File("Eaters/Stingray1_0.png")), createImage(new File("Eaters/stripedbass.png")), };
	private BufferedImage[] foods = new BufferedImage[] { createImage(new File("Food/algae.png")),
			createImage(new File("Food/clam.png")), createImage(new File("Food/crab.png")),
			createImage(new File("Food/deadfish.png")), createImage(new File("Food/fish.png")),
			createImage(new File("Food/fly.png")), createImage(new File("Food/pikefish.png")),
			createImage(new File("Food/shrimp.png")), createImage(new File("Food/snail.png")),
			createImage(new File("Food/worm.png")), };
	
	/**
	 * Creates a new instance of {@code Tutorial} with the given controller.
	 * 
	 * @param controller a controller.
	 */
	public Tutorial(Controller controller) {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.controller = controller;
		
		add(new TutorialLayout());
	}
	
	public class TutorialLayout extends JPanel {
		
		/**
		 * Creates a new instance of {@code TutorailLayout}.
		 */
		public TutorialLayout() {
		
			this.setBounds(0, 0, WIDTH, HEIGHT);
			setLayout(new GridBagLayout());
			setBackground(new Color(0, 0, 0, 0));
			GridBagConstraints gbcTitle = new GridBagConstraints();
			gbcTitle.gridx = 1;
			gbcTitle.gridy = 0;
			gbcTitle.insets = new Insets(5, 5, 5, 5);
			
			gbcTitle.gridy++;
			gbcTitle.gridx = 0;
			
			JLabel spacer = new JLabel();
			spacer.setPreferredSize(new Dimension(1700, 900));
			add(spacer, gbcTitle);
			
			gbcTitle.gridx = 1;
			gbcTitle.gridy++;
			
			JButton btnMainMenu = new JButton("Main Menu");
			btnMainMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnMainMenu.setPreferredSize(new Dimension(150, 75));
			btnMainMenu.addActionListener(mainMenuButtonListener);
			add(btnMainMenu, gbcTitle);
		
		}
		
	}
	
	ActionListener mainMenuButtonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.displayMenuScreen();
			setVisible(false);
		}
	};
	
	public void paintComponent(Graphics g) {
		
		Model m = controller.getModel();
		super.paintComponent(g);

		g.drawImage(backg, 0, 0, getWidth(), getHeight(), this);
		
		for (Animal a : m.getAnimals()) {
			BufferedImage i;
			switch (a.getType()) {
			case BLUECRAB:
				i = animals[0];
				break;
			case HARBORSEAL:
				i = animals[1];
				break;
			case HORSESHOECRAB:
				i = animals[2];
				break;
			case STINGRAY:
				i = animals[3];
				break;
			case STRIPEDBASS:
				i = animals[4];
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
	
	/**
	 * Creates a {@code BufferedImage} from the given file.
	 * 
	 * @param img an image file
	 * @return a BufferedImage
	 */
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		controller.getModel().moveFood(e);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		controller.getModel().grabFood(e);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		controller.getModel().dropFood();
	}

}
