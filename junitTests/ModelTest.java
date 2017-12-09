package junitTests;

import org.junit.Test;
import static org.junit.Assert.*;

import game.Animal;
import game.Food;
import game.Position;
import game.eAnimal;
import game.eFood;
import mvc.Model;

import static org.junit.Assert.*;

public class ModelTest {
	
	@Test
	public void modelConstructorTest() {
		Model m = new Model(false, 1);
		Model n = new Model(false, 0);
		assertEquals(2, m.getAnimals().size() - n.getAnimals().size());
	}
	
	@Test
	public void updateTest() {
		Model m = new Model(false, 1);
		Animal a = m.getAnimals().get(0);
		Position p = new Position();
		Position q = new Position();
		p.setX(a.getX());
		p.setY(a.getY());
		
		m.update();
		
		q.setX(a.getX());
		p.setY(a.getY());
		
		assertNotEquals(0, p.getX() - q.getX());
	}
	
	@Test
	public void checkFoodTest() {
		int i = 0;
		eFood[] foods = {eFood.WORM, eFood.CLAM, eFood.SHRIMP, eFood.SNAIL,
						 eFood.CRAB, eFood.ALGAE, eFood.FISH, eFood.DEADFISH,
						 eFood.FLY, eFood.SQUIGGLYWORMLOOKINFISHTHATSTICKSOUTTATHESAND};
		Model m = new Model(false, 1);
		Animal a = new Animal(eAnimal.BLUECRAB);
		Food food = m.getFood();
		m.getAnimals().set(0, a);
		while(!a.feed(food.getType())) {
			food = new Food(foods[i]);
			i++;
		}
		a.setX(5);
		a.setY(10);
		food.setX(5);
		food.setY(10);
		m.checkFood();
	}

}
