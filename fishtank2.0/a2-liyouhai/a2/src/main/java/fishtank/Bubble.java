package fishtank;
import java.awt.*;

/**
 * A bubble.
 */
public class Bubble extends FishTankEntity {

    /** How this bubble appears on the screen. */
    private String appearance;

    /** The font used to draw instances of this class. */
    private static Font FONT = new Font("Monospaced", Font.PLAIN, 10);

    /** My colour. Ah, the vagaries of British vs. US spelling. */
    private Color colour;

    /** This bubble's first coordinate. */
    private int x;
    /** This bubble's second coordinate. */
    private int y;

    /**
     * Constructs a new bubble at the specified cursor location (x, y).
     */
    public Bubble() {
        // Get a nice-looking grey for the bubble
         colour = Color.gray.darker().darker().darker();
         // start off with . as the appearance
        appearance = ".";
    }

    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
        // set x to b
      x = a;
        // set y to a
      y = b;
    }

    @Override
    int getX() {
        return x;
    }

    @Override
    int getY() {
        return y;
    }

    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param  g  the graphics context in which to draw the string.
     * @param  s  the string to draw.
     * @param  x  the x-coordinate of the string's cursor location.
     * @param  y  the y-coordinate of the string's cursor location.
     */
    void drawString(Graphics g, String s, int x, int y) {
        g.setColor(colour);
        g.setFont(FONT);
        FontMetrics fm = g.getFontMetrics(FONT);
        g.drawString(s, y*fm.charWidth('W'), x*fm.getAscent());
    }


    /**
     * Draws this fish tank item.
     *
     * @param  g  the graphics context in which to draw this item.
     */
    void draw(Graphics g) {
        drawString(g, appearance, y, x);
    }

    public void changeAppearance(){
        double d = Math.random();
        if (d < 0.05) {
            // If the appearance is a ., change it to an o
            if (appearance.equals("."))appearance="o";
                // If the appearance is an o, change it to a O
            else if (appearance.equals("o"))appearance="O";
        }
    }

    /**
     * Causes this item to take its turn in the fish-tank simulation, moving straight up.
     */
    public void floatStraightUp() {
        y--;
        changeAppearance();
    }    /**
     * Causes this item to take its turn in the fish-tank simulation, moving up and left.
     */
    public void floatLeftUp() {
        y--;
        x -= 1;
        changeAppearance();
    }
    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void floatRightUp() {
        y--;
        x += 1;
        changeAppearance();
    }

    public void update() {
        double determine = Math.random();
        if (determine < 0.33) {
            if (!FishTank.checkCollision(x-1,y-1))
                floatLeftUp();
        } else if (determine < 0.66) {
            if (!FishTank.checkCollision(x+1,y-1))
                floatRightUp();
        } else {
            if (!FishTank.checkCollision(x,y-1))
                floatStraightUp();
        }
        if (atBound(x,y))
            delete();
    }
}
