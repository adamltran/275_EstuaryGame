package bogus;

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
	
	public void setCenterScreen(Position position) {
		centerScreen.setX(position.getX());
		centerScreen.setY(position.getY());
	}
	
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

	public long getTimeRemaining() {
		if (difficulty == 0)
			return 45 - (currentTime - startTime)/1000000000;
		else
			return 30 - (currentTime - startTime)/1000000000;
	}
	
	public void moveFood(MouseEvent e){
		if(foodGrabbed){
			food.setX(e.getX());
			food.setY(e.getY());
		}
	}

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
