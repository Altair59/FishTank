package fishtank;
import java.awt.*;

/**
 * In Java, an "abstract class" is just a class that doesn't implement
 * some of its methods.
 *
 * In CSC148, you've seen things like this before, where every method in a class
 * simply raised a NotImplementedError. Those are also called abstract classes,
 * and fulfill a similar purpose (try replacing a usage of FishTankEntity with
 * Object in FishTank.java and see if you can understand why it doesn't work.)
 */
public abstract class   FishTankEntity {

    private boolean exists = true;
    public boolean visited = false;

    abstract void update();
    abstract void draw(Graphics g);
    abstract void setLocation(int x, int y);

    void delete() {
        exists = false;
    }

    boolean exists() {
        return this.exists;
    }

    abstract int getX();
    abstract int getY();

    public boolean atBound(int c, int r){
        return (c <= 1 || c >= 104 || r <= 1 || r >= 46);
    }
}
