package junitTests;

import org.junit.Test;

import game.Food;
import game.eFood;

import static org.junit.Assert.*;

public class FoodTest {
	
	@Test
	public void testFoodConstructor() {
		int i = 0;
		Food[] foods = {new Food(eFood.CLAM),
						new Food(eFood.ALGAE),
						new Food(eFood.CRAB),
						new Food(eFood.DEADFISH)
					   };
		for (int j=0; j<foods.length; j++) {
			i++;
		}
		
		assertEquals(4, i);
	}
	
	@Test
	public void getTypeTest() {
		Food food = new Food(eFood.ALGAE);
		assertEquals(eFood.ALGAE, food.getType());
	}

}
