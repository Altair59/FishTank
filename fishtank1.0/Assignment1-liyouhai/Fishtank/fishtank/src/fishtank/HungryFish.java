package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class HungryFish {

    /** How this fish appears on the screen. */
    public String appearance;

    /** Indicates whether this fish is moving right. */
    boolean goingRight;

    /** This fish's first coordinate. */
    int r;
    /** This fish's second coordinate. */
    int c;
    /** The colour of this fish. */
    private Color colour;


    /**
     * Constructs a new hungry fish.
     */
    public HungryFish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><MEHUNGRY>";
        goingRight = true;
    }


    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
      r = a;
      c = b;
    }



    /**
     * Causes this fish to blow a bubble.
     */
    protected void blowBubble() {
          Bubble b = new Bubble();
          b.setLocation(c, r);
          System.out.println(r + " " + c);

        int rb = r;
        boolean bubbled = false;
        while (rb >= 0 && !bubbled){
            if (FishTank.myLittleFishies[rb][c] == null){
                FishTank.myLittleFishies[rb][c] = b;
                bubbled = true;
            } else{
                rb--;
            }
        }
    }



    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private String reverseAppearance() {
      System.out.println("Turnign around" + this.appearance);
        String reverse = "";
        for (int i=appearance.length()-1; i>=0; i--) {
            switch (appearance.charAt(i)) {
            case ')': reverse += '('; break;
            case '(': reverse += ')'; break;
            case '>': reverse += '<'; break;
            case '<': reverse += '>'; break;
            case '}': reverse += '{'; break;
            case '{': reverse += '}'; break;
            case '[': reverse += ']'; break;
            case ']': reverse += '['; break;
            default: reverse += appearance.charAt(i); break;
            }
        }
        System.out.println("Turned around" + this.appearance);
        appearance = reverse;
        return reverse;
    }


    /**
     * Turns this fish around, causing it to reverse direction.
     */
    protected void turnAround() {
        goingRight = !goingRight;
        if (goingRight) {
            appearance = reverseAppearance();
        } else {
            appearance = reverseAppearance();
        }
    }

    /** The font used to draw instances of this class. */
    static Font FONT = new Font("Monospaced", Font.PLAIN, 10);


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
    public void draw(Graphics g) {
        drawString(g, appearance, r, c);
    }



    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void move() {
        double d = Math.random();
        if (c==5 || c==96) {
            if (c==5){
                c += 1;
            } else{
                c -= 1;
            }
            turnAround(); }
        else // Move one spot to the right or left.
            if (goingRight) {
                c += 1;
            } else {
                c -= 1;
            }
            // If it's elss tahn 10%, turn around
            if (d<0.1){turnAround();}

        // Figure out whether I blow a bubble.
        // If it's elss tahn 10%, blow a bubble.
        d =Math.random();
        if (d < 0.1) { blowBubble(); }
        // Figure out whether to move up or down, or neither.
        d = Math.random();
        if (r == 5){
            r += 1;
        } else if (r >= 43){
            r -= 1;
        } else {        // If it's elss tahn 10%, move up or down.
            if (d < 0.1) {
                // Increment
                r += 1;
            } else if (d < 0.2) {
                // Decrement
                r -= 1;
            }
        }
    }
}
