package bogus;

import javax.swing.JFrame;

import game.Tutorial;

public class Controller implements Runnable{

	public static void main(String[] args) {
		
		Controller controller = new Controller();
		(new Thread(controller)).start();
		
	}
	
	private Model model;
	private View view;
	private Start menu;boolean end;
	
	public Controller() {
		super();
		this.view = new View(this, false, false);
		this.model = new Model(true, 0);
		menu = new Start(this);
	}
	
	public void displayMenuScreen() {
		menu = new Start(this);
	}
	
	public void displayMainGameScreen(boolean tutorial, int difficulty) {
		String diff = (difficulty == 0) ? "(Easy)" : "(Hard)";
		
		view = new View(this, tutorial, end);
		JFrame f = new JFrame("Whose Prey is it Anyway? " + diff);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.add(this.view);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.model = new Model(tutorial, difficulty);
		f.setVisible(true);
	}
	
	public void displayTutorialScreen() {
		this.model = new Model(true, 0);
		JFrame f = new JFrame("Whose Prey is it Anyway? (Tutorial)");
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.add(new Tutorial(this));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	public void displayEndScreen() {
		this.model = new Model(true, 0);
		JFrame f = new JFrame("Whose Prey is it Anyway? (Tutorial)");
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.add(new Tutorial(this));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}

	private void tick() {
		model.update();
		view.repaint();
	}
	
	@Override
	public void run() {
		try {
			while(!model.isOver())
			{
				Thread.sleep(1000/60);
				tick();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Model getModel() {
		return model;
	}

	public View getView() {
		return view;
	}
	
	
	
	

}
