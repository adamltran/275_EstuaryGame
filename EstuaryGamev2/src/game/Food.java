package game;
public class Food extends Position {
    
    private eFood type;
    private eAnimal[] predators;
    
    /**
     * Creates a new instance of {@code food} with the given type.
     * @param type the type of food
     */
    public Food(eFood type) {
        this.type = type;
    }

    // Getters
public eFood getType() {
        return type;
    }
    
    public eAnimal[] getPredators()
    {
        return predators;
    }

}
