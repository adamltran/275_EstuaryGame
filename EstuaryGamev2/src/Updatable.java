/**
 * This interface enables any object which implements it to be able to be stepped forward
 * a finite amount of time. 
 */
public interface Updatable{
    /**
     * Updates the object based on the given amount of time in a way defined by the implementor.
     * @param time an amount of time, in seconds.
     */
    public void update(double time);
}
	