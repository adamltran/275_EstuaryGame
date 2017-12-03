package bogus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.Animal;

public class View extends JPanel implements MouseListener, MouseMotionListener{
	
	private Controller controller; 

	private BufferedImage topLeft = createImage(new File("Animals/Fish_east_1.png"));
	private BufferedImage botRight = createImage(new File("Animals/Sonny stand west.png"));
	private BufferedImage foodPic = createImage(new File("Animals/apple_core.png"));
	private BufferedImage backg = createImage(new File("Background/underwater.png"));
	private BufferedImage strike = createImage(new File("Background/red_x.png"));
	private JLabel lblTimeLeft;
	private JLabel lblScore;
	
	private BufferedImage[] animals = new BufferedImage[]{
			createImage(new File("Eaters/blueCrab.png")),
			createImage(new File("Eaters/Harbor-Seal-2.png")),
			createImage(new File("Eaters/horseshoe.png")),
			createImage(new File("Eaters/Stingray1_0.png")),
			createImage(new File("Eaters/stripedbass.png")),
			};
	private BufferedImage[] foods = new BufferedImage[]{
			createImage(new File("Food/algae.png")),
			createImage(new File("Food/clam.png")),
			createImage(new File("Food/crab.png")),
			createImage(new File("Food/deadfish.png")),
			createImage(new File("Food/fish.png")),
			createImage(new File("Food/fly.png")),
			createImage(new File("Food/pikefish.png")),
			createImage(new File("Food/shrimp.png")),
			createImage(new File("Food/snail.png")),
			createImage(new File("Food/worm.png")),
			};
	
	public View(Controller controller) {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.controller = controller;
		lblTimeLeft = new JLabel("Time Left: ");
        add(lblTimeLeft);
        
        lblScore = new JLabel("Score: " );
        add(lblScore);
	}
	
	


	private BufferedImage createImage(File img){
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
		lblTimeLeft.setText("Time Left: " + m.getTimeRemaining());
		lblScore.setText("Score: " + m.getScore());

		super.paintComponent(g);
		
		g.drawImage(backg, 0, 0, this);
		for(Animal a:m.getAnimals()){
			BufferedImage i;
			switch(a.getType()){
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
			g.drawImage(i, (int)a.getX()-50, (int)a.getY()-50,100,100, this);
			
			BufferedImage foodi;
			switch(m.getFood().getType()){
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
			g.drawImage(foodi, (int)m.getFood().getX()-75/2, (int)m.getFood().getY()-75/2,75,75, this);
			
		}
		for(int i = 0; i < controller.getModel().getNumberOfStrikes(); i++) {
			g.drawImage(strike, 55*i+100, 20, this);
		}
	}
	
	
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

	

}
