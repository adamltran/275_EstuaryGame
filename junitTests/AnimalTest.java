package junitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import game.Animal;
import game.Food;
import game.eAnimal;
import game.eFood;

public class AnimalTest {
	
	@Test
	public void AnimalConstructor() {
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal(eAnimal.HARBORSEAL));
		assertEquals(1, animals.size());
	}
	
	@Test
	public void feedTest() {
		Animal animal = new Animal(eAnimal.BLUECRAB);
		Food food = new Food(eFood.DEADFISH);
		assertTrue(animal.feed(food.getType()));
	}
	
	@Test
	public void moveTest() {
		Animal a1 = new Animal(eAnimal.BLUECRAB);
		Animal a2 = new Animal(eAnimal.HARBORSEAL);
		Animal a3 = new Animal(eAnimal.HORSESHOECRAB);
		Animal a4 = new Animal(eAnimal.STINGRAY);
		Animal a5 = new Animal(eAnimal.STRIPEDBASS);
		
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(a1);
		animals.add(a2);
		animals.add(a3);
		animals.add(a4);
		animals.add(a5);
		
		Iterator iter = animals.iterator();
		
		while(iter.hasNext()) {
			Animal a = (Animal) iter.next();
			a.setX(400);
			a.setY(300);
			a.move(1);
			a.move(0);
		}
		
		assertNotEquals(a1.getX(), a3.getX());
		assertNotEquals(a1.getY(), a5.getY());
	}
	

}
