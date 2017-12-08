package game;
public class Position{
    /**
     * The x component of {@code this} two dimensional position.
     */
    private double x;
    /**
     * The y component of {@code this} two dimensional position.
     */
    private double y;
    
    
    //Distance Methods
    /**
     * Returns the <i>Euclidean</i> distance from {@code this} to {@code p}.
     * @param p a {@code Position}.
     * @return the distance between {@code this} and {@code p}.
     */
    public double eclideanDistanceTo(Position p){
        return euclideanDistance(this,p);
    }
    
    /**
     * Returns the <i>Euclidean</i> distance between two {@code Position}.
     * @param p1 a {@code Position}.
     * @param p2 a {@code Position}.
     * @return the distance between {@code p1} and {@code p2}.
     */
    public static double euclideanDistance(Position p1, Position p2) {
        return Math.sqrt(((p2.x-p1.x)*(p2.x-p1.x)) + ((p2.y-p1.y)*(p2.y-p1.y)));
    }
    
    /**
     * Returns the <i>Manhattan</i> distance from {@code this} to {@code p}.
     * @param p a {@code Position}.
     * @return the distance between {@code this} and {@code p}.
     */
    public double manhattanDistanceTo(Position p){
        return manhattanDistance(this,p);
    }
    /**
     * Returns the <i>Manhattan</i> distance between two {@code Position}.
     * @param p1 a {@code Position}.
     * @param p2 a {@code Position}.
     * @return the distance between {@code p1} and {@code p2}.
     */
    public static double manhattanDistance(Position p1, Position p2) {
        return (p2.x - p1.x) + (p2.y - p2.x);
    }
    
    /**
     * Returns the <i>Chebyshev</i> distance from {@code this} to {@code p}.
     * @param p a {@code Position}.
     * @return the distance between {@code this} and {@code p}.
     */
    public double chebyshevDistanceTo(Position p){
        return chebyshevDistance(this,p);
    }
    /**
     * Returns the <i>Chebyshev</i> distance between two {@code Position}.
     * @param p1 a {@code Position}.
     * @param p2 a {@code Position}.
     * @return the distance between {@code p1} and {@code p2}.
     */
    public static double chebyshevDistance(Position p1, Position p2) {
        return Math.max(Math.abs(p2.x - p1.x), Math.abs(p2.y - p1.y));
    }
    
    //Getters and Setters
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }    
}
