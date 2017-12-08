package game;

import java.util.Random;

/**
 *    The {@code Animal} class holds data and methods used for identifying animals and 
 *    determining if the user fed the animal the correct food.
 */
public class Animal extends Position{
    private eAnimal type;
    private Random r;
    private int randX;
    private int randY;
    private boolean direction;
    
    /**
     * Constructs a new animal with the given type.
     * @param type type of animal.
     */
    public Animal(eAnimal type){
        this.type = type;
        r = new Random();
        resetRandomPosition();
    }
    
    /**
     * Returns true if the food being fed to {@code this} animal is part of its diet and false
     * if it is not.
     * 
     * @param food the food being fed to the animal
     * @return true if {@code food} is an element of {@code diet}, else false.
     */
    public boolean feed(eFood food){
        for(eFood diet:type.diet){
            if(diet.equals(food)){
                return true;
            }
        }
        return false;
    }
    
    public boolean getDirection() {
    	return direction;
    }
    
    public void setDirection(boolean newDirection) {
    	direction = newDirection;
    }
    
    /**
     * Randomly sets the position that the animal will stay near.
     */
    private void resetRandomPosition() {
    	randX = r.nextInt((1650 - 150) + 1) + 150;
    	randY = r.nextInt((850 - 150) + 1) + 150;
    }
    
    /**
     * Moves the animal by a set speed based on the difficulty away from the random position.
     * @param difficulty the difficulty of the game
     */
    public void move(int difficulty) {
    	double speed = (difficulty == 0) ? 2.3 : 2.7;
    	
    	Position randLoc = new Position();
    	randLoc.setX(randX);
    	randLoc.setY(randY);
    	double distanceFromRandLoc = this.eclideanDistanceTo(randLoc);
    	
    	if (distanceFromRandLoc > 5) {
    		double distanceFromRandLocX = (randLoc.getX() - this.getX());
    		double distanceFromRandLocY = (randLoc.getY() - this.getY());
    		double a = Math.atan2(distanceFromRandLocY, distanceFromRandLocX);
    		double dx = Math.cos(a) * speed;
    		double dy = Math.sin(a) * speed;
    		
    		this.setX(this.getX() + dx);
    		this.setY(this.getY() + dy);
    		
    		if (dx > 0) {
    			setDirection(true);
    		}
    		else {
    			setDirection(false);
    		}
    	}
    	else {
    		resetRandomPosition();
    	}
    }
    
    public eAnimal getType() {
		return type;
	}
}
