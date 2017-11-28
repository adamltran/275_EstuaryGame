/**
 *    The {@code Animal} class holds data and methods used for identifying animals and 
 *    determining if the user fed the animal the correct food.
 */
public class Animal extends Position implements Updatable{
    private eAnimal type;
    
    /**
     * Constructs a new animal with the given type.
     * @param type type of animal.
     */
    public Animal(eAnimal type){
        this.type = type;
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
    
    @Override
    public void update(double time) {
    	
    }

//    @Override
//    public Node render(double scale) {
//        Node body = new Rectangle(scale*1,scale*1,Color.RED);
//        return body;
//    }
    
    
}
