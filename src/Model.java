import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Model {
	
	private eAnimal[] animals = { eAnimal.BLUECRAB,
							      eAnimal.HARBORSEAL,
							      eAnimal.HORSESHOECRAB,
							      eAnimal.STINGRAY,
							      eAnimal.STRIPEDBASS };
	private List<eAnimal> onScreenAnimals;
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
		(new Model()).addAnimal();
	}
	
	public void addAnimal() {
		
		
	}
	
	public void writeScoreToFile(String name) {
		try {
			FileOutputStream fileOut = new FileOutputStream("Score/scores.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeChars(name + ": " + score);
			objectOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readScoresFromFile() {
		try {
			FileInputStream fileIn = new FileInputStream("Score/scores.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			String scores = objectIn.readLine();
			objectIn.close();
			System.out.println(scores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
