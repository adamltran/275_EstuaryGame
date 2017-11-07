import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class frame1 extends JFrame{

	
	public static void main(String[] args) {
		
		Controller controller = new Controller(new Model(), new foodPanel());
		(new Thread(controller)).start();
		
		//creating food
		foodPanel food = new foodPanel();
		food.setBackground(Color.blue);
		//creating frame
		JFrame f = new JFrame("Who's Meal is it Anyway?");
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.setLayout(new GridLayout());
		f.add(food);
		//f.add(a);
		//f.add(b);
		f.setVisible(true);
	}
	
}
