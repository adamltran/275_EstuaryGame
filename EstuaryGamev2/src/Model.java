import java.util.Map;

public class Model {
	
	private Map<Animal, Position> animals;
	private int score; 
	private int time;
	private int strikes;
	private int timesFed;
	
	public Model() {
		score = 0;
		time = 10;
		strikes = 0;
		timesFed = 0;
		System.out.println("Starting...");
		System.out.println("Current Score: " + score);
	}
	
	public void changePosition(Animal animal) {
		
	}
	
	public void incrementStrikes() {
		strikes++;
	}
	
	public boolean checkFood(Animal animal, eFood food) {
		if (animal.feed(food)) {
			this.upScore();
			this.incrementTimesFed();
		}
		return true;
	}
	
	public void incrementTimesFed() {
		timesFed++;
	}
	
	public void upScore() {
		System.out.println("New Score: " + (score + 1));
		score++;
	}
	
	public void displaySecondsLeft() {
		System.out.println("Time left: " + time);
	}
	
	public void decrementTime() {
		time--;
	}
	
	public int getTimeLeft() {
		return time;
	}
	
	public void displayEndOfGame() {
		System.out.println("Game over. Final Score: " + score);
	}
	
	public static void main(String[] args) {
		Controller controller = new Controller(new Model(), new foodPanel());
		(new Thread(controller)).start();
	}

}
