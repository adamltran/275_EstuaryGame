
public class Controller implements Runnable {
	
	private Model model;
	private foodPanel view;
	
	public Controller(Model model, foodPanel view) {
		this.model = model;
		this.view = view;
	}
	
	public void updateModel() {
		try {
			while(model.getTimeLeft() > 0)
			{
				Thread.sleep(1000);
				model.decrementTime();
				model.displaySecondsLeft();
				if (view.checkFood())
					model.upScore();
			}
			if (model.getTimeLeft() == 0) {
				model.displayEndOfGame();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void updateView() {
		while(model.getTimeLeft() > 0) {
			if (view.checkFood())
				model.upScore();
		}
	}
	
	public void tick() {
		
	}
	
	public void run() {
		updateModel();
		updateView();
	}

}
