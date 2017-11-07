import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class foodPanel extends JPanel implements MouseListener, MouseMotionListener{
	private int size = 50;
	private int foodx = 210;
	private int foody = 175;
	
	private int dragFromX = 0;
	private int dragFromY = 0;
	
	private boolean canDrag = false;
	
	public foodPanel() {
	
		setForeground(Color.red);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	public boolean checkFood() {
		if (foodx == 75 && foody == 75)
			return true;
		return false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.fillOval(foodx, foody, size, size);
		g.fillOval(75, 75, size, size);
		g.fillRect(350, 300, size, size);
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(x >= foodx && x <= (foodx + size) && y >= foody && y <= (foody + size)) {
			
			canDrag = true;
			dragFromX = x - foodx;
			dragFromY = y - foody;
		}
		
		else {
			canDrag = false;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if(canDrag) {
			
			//change food position
			foodx = e.getX() - dragFromX;
			foody = e.getY() - dragFromY;
			
			
			//food doesnt drag off screen
			foodx = Math.max(foodx, 0);
			foodx = Math.min(foodx, getWidth() - size);
			
			foody = Math.max(foody, 0);
			foody = Math.min(foody, getHeight() - size);
			
			
			if(foodx <= 80 && foody <= 80) {
				System.out.println("you win");
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