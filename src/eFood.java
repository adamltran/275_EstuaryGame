public enum eFood {
    WORM(new eAnimal[] {
            eAnimal.STRIPEDBASS, 
            eAnimal.HORSESHOECRAB
    }),
    CLAM(new eAnimal[] {
            eAnimal.STRIPEDBASS, 
            eAnimal.HORSESHOECRAB, 
            eAnimal.BLUECRAB, 
            eAnimal.STINGRAY
    }),
    SNAIL(new eAnimal[] {
            eAnimal.BLUECRAB
    }),
    CRAB(new eAnimal[] {
            eAnimal.HARBORSEAL, 
            eAnimal.STINGRAY
    }),
    ALGAE(new eAnimal[] {
            eAnimal.STINGRAY
    }),
    FISH(new eAnimal[] { 
            eAnimal.HARBORSEAL
    }),
    DEADFISH(new eAnimal[] {
            eAnimal.BLUECRAB
    }),
    SHRIMP(new eAnimal[] {
            eAnimal.STINGRAY
    }),
    FLY(new eAnimal[] {
            eAnimal.STRIPEDBASS
    }),
    SQUIGGLYWORMLOOKINFISHTHATSTICKSOUTTATHESAND(new eAnimal[] {
            eAnimal.STRIPEDBASS,
            eAnimal.HARBORSEAL
    });

    public final eAnimal[] predators;

    private eFood(eAnimal[] p) {
        predators = p;
    }
}
