package bogus;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import game.Animal;
import game.Food;
import game.Position;
import game.eAnimal;
import game.eFood;

public class Model {
	private boolean over;
	private ArrayList<Animal> animals;

	private Food food;
	private boolean foodGrabbed;
	private int numberOfStrikes;
	private int score;
	private long startTime;
	private long currentTime;

	private Random r;

	public Model(){
		r = new Random();
		over = false;
		foodGrabbed = false;
		animals = new ArrayList<Animal>();
		makeNewAnimal();
		makeNewAnimal();
		makeNewFood();
		startTime = System.nanoTime();
	}

	private void makeNewFood(){
		food = new Food(animals.get(r.nextInt(animals.size())).getType().diet[r.nextInt(3)]){{
			setX(225);
			setY(225);
		}};
	}

	private void makeNewAnimal(){
		switch(r.nextInt(5)){
		case 0:
			animals.add(new Animal(eAnimal.BLUECRAB){{
				setX(425);
				setY(375);
			}});
			break;
		case 1:
			animals.add(new Animal(eAnimal.HARBORSEAL){{
				setX(425);
				setY(145);
			}});
			break;
		case 2:
			animals.add(new Animal(eAnimal.HORSESHOECRAB){{
				setX(85);
				setY(375);
			}});
			break;
		case 3:
			animals.add(new Animal(eAnimal.STINGRAY){{
				setX(250);
				setY(145);
			}});
			break;
		case 4:
			animals.add(new Animal(eAnimal.STRIPEDBASS){{
				setX(85);
				setY(145);
			}});
			
			break;
		default:
			animals.add(new Animal(eAnimal.STRIPEDBASS){{
				setX(85);
				setY(145);
			}});
			break;
		}
	}

	public void update() {
		currentTime = System.nanoTime();
		over = (getTimeRemaining()<=0||numberOfStrikes>=5);
	}

	public long getTimeRemaining() {
		return 60 - (currentTime - startTime)/1000000000;
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
			if (food.chebyshevDistanceTo(a) < 75) {
				if(a.feed(food.getType())) {
					i.remove();
					makeNewFood();
					score += 100;
				}
				else {
					numberOfStrikes++;
				}
			}
		}
		while(animals.size()<2){
			makeNewAnimal();
		}
		
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
