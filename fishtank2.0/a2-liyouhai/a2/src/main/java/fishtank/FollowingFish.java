package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class FollowingFish extends FishTankEntity{

    /** How this fish appears on the screen. */
    private String appearance;

    /** Indicates whether this fish is moving right. */
    private boolean goingRight;

    /** This fish's first coordinate. */
    private int r;
    /** This fish's second coordinate. */
    private int c;
    /** The colour of this fish. */
    private Color colour;
    private boolean connected;
    private boolean distanceOfTwo;

    /** The entity that our fish is following */
    private Fish de;

    /**
     * Constructs a new hungry fish.
     */
    public FollowingFish(Fish f) {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><FOLLOW>";
        goingRight = true;
        de = f;
    }

    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
      r = b;
      c = a;
      distanceOfTwo = distanceTwo();
      connected = verticallyTwo();
    }

    int getX() {
        return c;
    }

    int getY() {
        return r;
    }


    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    public String reverseAppearance() {
      System.out.println("Turning around" + this.appearance);
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
        return reverse;
    }

    private boolean verticallyTwo(){
        return (Math.abs(de.getX() - c) == 0 && Math.abs(de.getY() - r) == 2);
    }

    private boolean distanceTwo(){
        return (Math.abs(de.getX() - c)==1 &&  Math.abs(de.getY() - r) == 1);
    }
    /**
     * Turns this fish to fc
     */
    public void turnToFace() {
        if(de.getX() < this.getX() && goingRight) {
            goingRight = false;
            appearance = reverseAppearance();
        } else if(de.getX() > this.getX() && !goingRight) {
            goingRight = true;
            appearance = reverseAppearance();
        }
    }

    /** The font used to draw instances of this class. */
    private static Font FONT = new Font("Monospaced", Font.PLAIN, 10);


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
        drawString(g, appearance, r, c);
    }

    private void catching(){
        if (de.getY() - r > 2) {
            if (de.getX() > c) {
                if (!FishTank.checkCollision(c + 1, r + 1)) {
                    c++;
                    r++;
                } else if (!FishTank.checkCollision(c + 1, r)) {
                    c++;
                } else if (!FishTank.checkCollision(c, r + 1)) {
                    r++;
                }
            } else if (de.getX() == c) {
                if (!FishTank.checkCollision(c, r + 1)) {
                    r++;
                }
            } else {
                if (!FishTank.checkCollision(c - 1, r + 1)) {
                    c--;
                    r++;
                } else if (!FishTank.checkCollision(c - 1, r)) {
                    c--;
                } else if (!FishTank.checkCollision(c, r + 1)) {
                    r++;
                }
            }
        } else if (de.getY() - r == 2 || de.getY() - r == -2) {
            if (de.getX() > c) {
                if (!FishTank.checkCollision(c + 1, r)) {
                    c++;
                }
            } else if (de.getX() < c) {
                if (!FishTank.checkCollision(c - 1, r)) {
                    c--;
                }
            }
        } else if (de.getY() - r == 1 || de.getY() - r == 0) {
            if (de.getX() > c) {
                if (!FishTank.checkCollision(c + 1, r - 1) && !atBound(c + 1, r - 1)) {
                    c++;
                    r--;
                } else if (!FishTank.checkCollision(c + 1, r) && !atBound(c + 1, r)) {
                    c++;
                } else if (!FishTank.checkCollision(c, r - 1) && !atBound(c, r - 1)) {
                    r--;
                }
            } else if (de.getX() == c) {
                if (!FishTank.checkCollision(c, r - 1) && !atBound(c, r - 1)) {
                    r--;
                }
            } else {
                if (!FishTank.checkCollision(c - 1, r - 1) && !atBound(c - 1, r - 1)) {
                    c--;
                    r--;
                } else if (!FishTank.checkCollision(c - 1, r) && !atBound(c - 1, r)) {
                    c--;
                } else if (!FishTank.checkCollision(c, r - 1) && !atBound(c, r - 1)) {
                    r--;
                }
            }
        } else if (de.getY() - r == -1) {
            if (de.getX() > c) {
                if (!FishTank.checkCollision(c + 1, r + 1) && !atBound(c + 1, r + 1)) {
                    c++;
                    r++;
                } else if (!FishTank.checkCollision(c + 1, r) && !atBound(c + 1, r)) {
                    c++;
                } else if (!FishTank.checkCollision(c, r + 1) && !atBound(c, r + 1)) {
                    r++;
                }
            } else if (de.getX() == c) {
                if (!FishTank.checkCollision(c, r + 1) && !atBound(c, r + 1)) {
                    r++;
                }
            } else {
                if (!FishTank.checkCollision(c - 1, r + 1) && !atBound(c - 1, r + 1)) {
                    c--;
                    r++;
                } else if (!FishTank.checkCollision(c - 1, r) && !atBound(c - 1, r)) {
                    c--;
                } else if (!FishTank.checkCollision(c, r + 1) && !atBound(c, r + 1)) {
                    r++;
                }
            }
        } else {
            if (de.getX() > c) {
                if (!FishTank.checkCollision(c + 1, r - 1)) {
                    c++;
                    r--;
                } else if (!FishTank.checkCollision(c + 1, r)) {
                    c++;
                } else if (!FishTank.checkCollision(c, r - 1)) {
                    r--;
                }
            } else if (de.getX() == c) {
                if (!FishTank.checkCollision(c, r - 1)) {
                    r--;
                }
            } else {
                if (!FishTank.checkCollision(c - 1, r - 1)) {
                    c--;
                    r--;
                } else if (!FishTank.checkCollision(c - 1, r)) {
                    c--;
                } else if (!FishTank.checkCollision(c, r - 1)) {
                    r--;
                }
            }
        }
        distanceOfTwo = distanceTwo();
        connected = verticallyTwo();
    }

    private void stayVerticallyTwo(){
        int deltaC;
        int deltaR;
        if (de.getY() < r){
            deltaR = de.getY() + 2 - r;
        } else {
            deltaR = de.getY() - 2 - r;
        }
        deltaC = de.getX() - c;

        if (atBound(c+deltaC,r+deltaR)){
            c++;
            c--;
            r++;
            r--;
        } else if (FishTank.checkCollision(c+deltaC,r+deltaR)){
            if (!FishTank.checkCollision(c+deltaC,r) && !atBound(c+deltaC,r)){
                c += deltaC;
            } else if (!FishTank.checkCollision(c,r+deltaR) && !atBound(c,r+deltaR)){
                r += deltaR;
            }
        } else {
            c += deltaC;
            r += deltaR;
        }
    }

    private void stayDistanceTwo(){
        if (connected){
            stayVerticallyTwo();
        } else {
            if (Math.abs(de.getY() - r) == 2){
                if (de.getX() == c){
                    c++;
                    c--;
                    r++;
                    r--;
                } else {
                    if (de.getY()<r && de.getX()>c){
                        c++;
                        r--;
                    } else if (de.getY()<r && de.getX()<c){
                        c--;
                        r--;
                    } else if (de.getY()>r && de.getX()>c){
                        c++;
                        r++;
                    } else if (de.getY()>r && de.getX()<c){
                        c--;
                        r++;
                    }
                }
            } else if (de.getY() == r){
                if (r==45 && de.getX()>c){
                    c++;
                    r--;
                } else if (r==45 && de.getX()<c){
                    c--;
                    r--;
                } else if (r==2 && de.getX()>c){
                    c++;
                    r++;
                } else if (r==2 && de.getX()<c){
                    c--;
                    r++;
                }
            } else {
                if (de.getX()==c){
                    if (!atBound(c+1,r) && !FishTank.checkCollision(c+1,r)){
                        c++;
                    } else if (!atBound(c-1,r) && !FishTank.checkCollision(c-1,r)){
                        c--;
                    }
                } else if (Math.abs(de.getX() - c)==2){
                    if (de.getX()<c){
                        c--;
                    } else {
                        c++;
                    }
                } else{
                    c++;
                    c--;
                    r++;
                    r--;
                }
            }
        }
        distanceOfTwo = distanceTwo();
        connected = verticallyTwo();
    }
    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
        turnToFace();
        if (!distanceOfTwo && !connected) {
            catching();
        } else {
            stayDistanceTwo();
        }
    }
}


