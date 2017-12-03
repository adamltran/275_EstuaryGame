package game;
public class Food extends Position {
    
    private eFood type;
    private eAnimal[] predators;
    
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
