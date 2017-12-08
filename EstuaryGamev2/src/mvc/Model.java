package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.Timer;

import game.Animal;
import game.Food;
import game.Position;
import game.eAnimal;

public class Model {

	private boolean over;
	private ArrayList<Animal> animals;
	private ArrayList<eAnimal> typesOnScreen;
	private Food food;
	private boolean foodGrabbed;
	int numberOfStrikes;
	private int score;
	private long startTime;
	private long currentTime;
	private Timer centerFoodTimer;
	private Position centerScreen;
	private ActionListener sendFoodToCenterListener;
	private boolean tutorial;
	private int difficulty;

	private Random r1;
	private Random r2;
	private Random x;
	private Random y;

	/**
	 * Creates a new instance of {@code Model} with the given tutorial flag and difficulty value.
	 * 
	 * @param tutorial a boolean representing whether the newly created model represents a tutorial or not
	 * @param difficulty an int representing the difficulty level
	 */
	public Model(boolean tutorial, int difficulty) {

		r1 = new Random();
		r2 = new Random();
		x = new Random();
		y = new Random();
		this.tutorial = tutorial;
		this.difficulty = difficulty;
		over = false;
		foodGrabbed = false;
		animals = new ArrayList<Animal>();
		typesOnScreen = new ArrayList<eAnimal>();
		centerScreen = new Position();
		centerScreen.setX(900);
		centerScreen.setY(500);
		makeNewAnimal();
		makeNewAnimal();
		if (difficulty == 1) {
			makeNewAnimal();
			makeNewAnimal();

		}
		makeNewFood();
		startTime = System.nanoTime();
		sendFoodToCenterListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendFoodToCenter();
			}
		};

		centerFoodTimer = new Timer(1, sendFoodToCenterListener);
	}

	/**
	 * Sets the X and Y components representing the center of the screen to that of the given position.
	 * 
	 * @param position the desired position
	 */
	public void setCenterScreen(Position position) {
		centerScreen.setX(position.getX());
		centerScreen.setY(position.getY());
	}

	/**
	 * Replaces the current food with a new instance of {@code food} whose type is selected from the diet of
	 * one of the {@code Animal} currently part of the game.
	 */
	private void makeNewFood(){

		int animalIndex = 0;

		if (animals.size() > 0) {
			animalIndex = r1.nextInt(animals.size());
			while(animalIndex == animals.size()) {
				animalIndex = r1.nextInt(animals.size());
			}
		}
		else {
			//			makeNewAnimal();
			return;
		}
		int typeIndex = r2.nextInt(2);
		food = new Food(animals.get(animalIndex).getType().diet[typeIndex]){{
			setX(centerScreen.getX());
			setY(centerScreen.getY());
		}};
	}

	/**
	 * Adds a new {@code Animal} to the game, ensuring that that animal is not already there.
	 */
	private void makeNewAnimal(){

		int xCoord = x.nextInt((1600 - 200) + 1) + 200;
		int yCoord = y.nextInt((800 - 200) + 1) + 200;

		switch(r1.nextInt(5)){
		case 0:
			if (!typesOnScreen.contains(eAnimal.BLUECRAB))
			{
				typesOnScreen.add(eAnimal.BLUECRAB);
				animals.add(new Animal(eAnimal.BLUECRAB){{
					setX(xCoord);
					setY(yCoord);
				}});
			}
			else
				makeNewAnimal();
			break;
		case 1:
			if (!typesOnScreen.contains(eAnimal.HARBORSEAL))
			{
				typesOnScreen.add(eAnimal.HARBORSEAL);
				animals.add(new Animal(eAnimal.HARBORSEAL){{
					setX(xCoord);
					setY(yCoord);
				}});
			}
			else
				makeNewAnimal();
			break;
		case 2:
			if (!typesOnScreen.contains(eAnimal.HORSESHOECRAB))
			{
				typesOnScreen.add(eAnimal.HORSESHOECRAB);
				animals.add(new Animal(eAnimal.HORSESHOECRAB){{
					setX(xCoord);
					setY(yCoord);
				}});
			}
			else
				makeNewAnimal();
			break;
		case 3:
			if (!typesOnScreen.contains(eAnimal.STINGRAY))
			{
				typesOnScreen.add(eAnimal.STINGRAY);
				animals.add(new Animal(eAnimal.STINGRAY){{
					setX(xCoord);
					setY(yCoord);
				}});
			}
			else
				makeNewAnimal();
			break;
		case 4:
			if (!typesOnScreen.contains(eAnimal.STRIPEDBASS))
			{
				typesOnScreen.add(eAnimal.STRIPEDBASS);
				animals.add(new Animal(eAnimal.STRIPEDBASS){{
					setX(xCoord);
					setY(yCoord);
				}});
			}
			else
				makeNewAnimal();

			break;
		default:
			if (!typesOnScreen.contains(eAnimal.STRIPEDBASS))
			{
				typesOnScreen.add(eAnimal.STRIPEDBASS);
				animals.add(new Animal(eAnimal.STRIPEDBASS){{
					setX(xCoord);
					setY(yCoord);
				}});
			}
			else
				makeNewAnimal();
			break;
		}
	}

	/**
	 * Updates the model moving the animals, keeping track of the time and the number of strikes, and determining the game over condition.
	 */
	public void update() {
		for (Animal a : animals) {
			a.move(difficulty);
		}
		currentTime = System.nanoTime();
		if (!tutorial){
			if (difficulty == 0) {
				over = (getTimeRemaining()<=0||numberOfStrikes>=5);
			}
			else if (difficulty == 1) {
				over = (getTimeRemaining()<=0||numberOfStrikes>=5);
			}
		}
		else {
			if (numberOfStrikes >= 5)
				numberOfStrikes = 0;
		}

	}

	/**
	 * Calculates the time remaining and returns it.
	 * 
	 * @return the time remaining in seconds
	 */
	public long getTimeRemaining() {
		if (difficulty == 0)
			return 45 - (currentTime - startTime)/1000000000;
		else
			return 30 - (currentTime - startTime)/1000000000;
	}

	/**
	 * Sets the food's X and Y components to that of the MouseEvent.
	 * 
	 * @param e a MouseEvent representing the mouse position 
	 */
	public void moveFood(MouseEvent e){
		if(foodGrabbed){
			food.setX(e.getX());
			food.setY(e.getY());
		}
	}

	/**
	 * Determines if a given {@code MouseEvent} is close enough to the food to constitute a grab.
	 * 
	 * @param e a MouseEvent representing the mouse position 
	 */
	public void grabFood(MouseEvent e){
		Position event = new Position();
		event.setX(e.getX());
		event.setY(e.getY());
		if(food.chebyshevDistanceTo(event)<75){
			foodGrabbed = true;
			food.setX(e.getX());
			food.setY(e.getY());
		}
	}

	/**
	 * Handles food-animal interaction, feeding the animals and adding new ones as needed.
	 */
	private void checkFood() {
		Iterator<Animal> i = animals.iterator();
		while (i.hasNext()) {
			Animal a = i.next();
			if (food.chebyshevDistanceTo(a) < 120) {
				if(a.feed(food.getType())) {
					typesOnScreen.remove(a.getType());
					i.remove();

					score += 100;
				}
				else {
					numberOfStrikes++;
					centerFoodTimer.start();
				}
			}
		}
		int maxAnimals = (difficulty == 0) ? 2 : 4;
		boolean madenew = false;
		while(animals.size()<maxAnimals){
			makeNewAnimal();
			madenew = true;
		}
		if(madenew){
			makeNewFood();
		}
	}

	/**
	 * Moves the food toward the center of the screen (used to animate this event).
	 */
	public void sendFoodToCenter() {

		double distanceFromCenter = food.eclideanDistanceTo(centerScreen);

		if (distanceFromCenter > 5) {
			double distanceFromCenterX = (centerScreen.getX() - food.getX());
			double distanceFromCenterY = (centerScreen.getY() - food.getY());
			double a = Math.atan2(distanceFromCenterY, distanceFromCenterX);
			double dx = Math.cos(a) * 10;
			double dy = Math.sin(a) * 10;

			food.setX(food.getX() + dx);
			food.setY(food.getY() + dy);
		}
		else
			centerFoodTimer.stop();

	}

	/**
	 * Drops the food and calls {@code checkFood()}.
	 */
	public void dropFood(){
		checkFood();
		foodGrabbed = false;
	}

	public boolean isOver() {
		return over;
	}

	public Food getFood() {
		return food;
	}

	public ArrayList<Animal> getAnimals() {
		return animals;
	}

	public int getNumberOfStrikes() {
		return numberOfStrikes;
	}

	public int getScore() {
		return score;
	}



}
