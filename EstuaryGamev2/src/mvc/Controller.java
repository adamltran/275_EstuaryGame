package mvc;

import javax.swing.JFrame;

import game.Tutorial;

public class Controller implements Runnable{

	public static void main(String[] args) {
		
		Controller controller = new Controller();
		(new Thread(controller)).start();
		
	}
	
	private Model model;
	private View view;
	private Start menu;
	boolean end;
	
	/**
	 * Creates a new instance of {@code Controller}.
	 */
	public Controller() {
		super();
		this.view = new View(this, false, false);
		this.model = new Model(true, 0);
		menu = new Start(this);
	}
	
	/**
	 * Sets the field {@code menu} to a new instance of {@code Start}.
	 */
	public void displayMenuScreen() {
		menu = new Start(this);
	}
	
	/**
	 * Prepares and presents the game to be displayed, setting the fields {@code view} and {@code model} to new instances of 
	 * {@code View} and {@code Model}, respectively.
	 * 
	 * @param tutorial a boolean representing whether the newly created game view is a tutorial or not
	 * @param difficulty an int representing the difficulty level
	 */
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
	
	/**
	 * Prepares and presents the tutorial.
	 */
	public void displayTutorialScreen() {
		this.model = new Model(true, 0);
		JFrame f = new JFrame("Whose Prey is it Anyway? (Tutorial)");
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.add(new Tutorial(this));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	/**
	 * Prepares and presents the end screen.
	 */
	public void displayEndScreen() {
		this.model = new Model(true, 0);
		JFrame f = new JFrame("Whose Prey is it Anyway? (Tutorial)");
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.add(new Tutorial(this));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}

	/**
	 * Steps the {@code model} forward and repaints the {@code view}.
	 */
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

	public Start getMenu() {
		return menu;
	}
	
	
	
	
	

}
