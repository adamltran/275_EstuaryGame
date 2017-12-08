package game;
/**
 * This enum represents all animals, and their diets, that will be represented in this game.
 */
public enum eAnimal {
    HORSESHOECRAB(new eFood[]{
            eFood.WORM,
            eFood.CLAM,
            eFood.ALGAE
    }),
    HARBORSEAL(new eFood[]{
            eFood.FISH,
            eFood.CRAB,
            eFood.SQUIGGLYWORMLOOKINFISHTHATSTICKSOUTTATHESAND
    }), 
    STINGRAY(new eFood[]{
            eFood.SHRIMP,
            eFood.CRAB,
            eFood.CLAM
    }), 
    BLUECRAB(new eFood[]{
            eFood.SNAIL,
            eFood.DEADFISH,
            eFood.CLAM
    }), 
    STRIPEDBASS(new eFood[]{
            eFood.WORM,
            eFood.FLY,
            eFood.SQUIGGLYWORMLOOKINFISHTHATSTICKSOUTTATHESAND
    });

    /**
     * This holds all {@code eFood} that the given animal will eat.
     */
    public final eFood[] diet;
    private eAnimal(eFood[] fl){
        diet = fl;
    }    
}
