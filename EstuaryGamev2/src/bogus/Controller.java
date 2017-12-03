package bogus;

import javax.swing.JFrame;

public class Controller implements Runnable{

	public static void main(String[] args) {
		Controller controller = new Controller();
		(new Thread(controller)).start();
		
		
		//creating frame
		JFrame f = new JFrame("Who's Meal is it Anyway?");
		f.setSize(500, 500);
		f.add(controller.view);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private Model model;
	private View view;
	
	public Controller() {
		super();
		this.model = new Model();
		this.view = new View(this);
	}

	private void tick(){
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
