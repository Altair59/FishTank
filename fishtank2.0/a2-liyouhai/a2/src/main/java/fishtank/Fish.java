package fishtank;

import java.awt.*;
/**
 * A fish.
 */
public class Fish extends FishTankEntity {

    /** How this fish appears on the screen. */
    String appearance;

    /** Indicates whether this fish is moving right. */
    boolean goingRight;

    /** This fish's first coordinate. */
    private int r;
    /** This fish's second coordinate. */
    private int c;
    /** The colour of this fish. */
    Color colour;
    private double determine;

    /**
     * Constructs a new fish.
     */
    public Fish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><>";
        goingRight = true;
    }



    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
        r = b;
        c = a;
    }

    int getX() {
        return c;
    }

    int getY() {
        return r;
    }


    /**
     * Causes this fish to blow a bubble.
     */
    protected void blowBubble() {
		  Bubble b = new Bubble();
		  for (int i = c-1;i<=c+1;i++){
		      if (!FishTank.checkCollision(i,r-1)){
                  FishTank.addEntity(i, r-1, b);
                  break;
              }
          }
    }



    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    public String reverseAppearance() {
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

        return reverse;
    }



    /**
     * Turns this fish around, causing it to reverse direction.
     */
    public void turnAround() {
        goingRight = !goingRight;
        if (goingRight) {
            appearance = reverseAppearance();
        } else {
            appearance = reverseAppearance();
        }
    }

    public void setGoingRight(boolean goingRight) {
        if (this.goingRight != goingRight) {
            this.goingRight = !this.goingRight;
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

    private void eatSeaweed(){
        for (int i=r+1;i<=47;i++){
            FishTankEntity e = FishTank.getEntity(c,i);
            if (e instanceof Seaweed){
                if (r >= e.getY() - ((Seaweed) e).getLenght()){
                    ((Seaweed) e).getEaten(r);
                }
            }
        }
    }

    private void updateLocation(){
        if (goingRight){
            if (atBound(c+1,r)){
                turnAround();
                if (atBound(c-1,r)){
                    determine = Math.random();
                    if (determine<0.05 && !atBound(c,r-1) && !FishTank.checkCollision(c,r-1)){
                        r -= 1;
                    } else if (determine < 0.1 && !atBound(c, r + 1) && !FishTank.checkCollision(c, r + 1)) {
                        r += 1;
                    }
                } else {
                    determine = Math.random();
                    if (determine<0.05 && !atBound(c-1,c-1) && !FishTank.checkCollision(c-1,r-1)){
                        c -= 1;
                        r -= 1;
                    } else if (determine<0.1 && !atBound(c-1,r+1) && !FishTank.checkCollision(c-1,r+1)){
                        c -= 1;
                        r += 1;
                    } else if (!FishTank.checkCollision(c-1,r)){
                        c -= 1;
                    } else {
                        determine = Math.random();
                        if (determine<0.05 && !atBound(c,r-1) && !FishTank.checkCollision(c,r-1)){
                            r -= 1;
                        } else if (determine < 0.1 && !atBound(c, r + 1) && !FishTank.checkCollision(c, r + 1)) {
                            r += 1;
                        }
                    }
                }
            } else {
                determine = Math.random();
                if (determine<0.05 && !atBound(c+1,r-1) && !FishTank.checkCollision(c+1,r-1)){
                    c += 1;
                    r -= 1;
                } else if (determine<0.1 && !atBound(c+1,r+1) && !FishTank.checkCollision(c+1,r+1)){
                    c += 1;
                    r += 1;
                } else if (!FishTank.checkCollision(c+1,r)){
                    c += 1;
                } else{
                    turnAround();
                    if (atBound(c-1,r)){
                        determine = Math.random();
                        if (determine<0.05 && !atBound(c,r-1) && !FishTank.checkCollision(c,r-1)){
                            r -= 1;
                        } else if (determine < 0.1 && !atBound(c, r + 1) && !FishTank.checkCollision(c, r + 1)) {
                            r += 1;
                        }
                    } else {
                        determine = Math.random();
                        if (determine<0.05 && !atBound(c-1,c-1) && !FishTank.checkCollision(c-1,r-1)){
                            c -= 1;
                            r -= 1;
                        } else if (determine<0.1 && !atBound(c-1,r+1) && !FishTank.checkCollision(c-1,r+1)){
                            c -= 1;
                            r += 1;
                        } else if (!FishTank.checkCollision(c-1,r)){
                            c -= 1;
                        } else {
                            determine = Math.random();
                            if (determine<0.05 && !atBound(c,r-1) && !FishTank.checkCollision(c,r-1)){
                                r -= 1;
                            } else if (determine < 0.1 && !atBound(c, r + 1) && !FishTank.checkCollision(c, r + 1)) {
                                r += 1;
                            }
                        }
                    }
                }
            }
        } else {
            if (atBound(c-1,r)){
                turnAround();
                if (atBound(c+1,r)){
                    determine = Math.random();
                    if (determine<0.05 && !atBound(c,r-1) && !FishTank.checkCollision(c,r-1)){
                        r -= 1;
                    } else if (determine < 0.1 && !atBound(c, r + 1) && !FishTank.checkCollision(c, r + 1)) {
                        r += 1;
                    }
                } else {
                    determine = Math.random();
                    if (determine<0.05 && !atBound(c+1,c-1) && !FishTank.checkCollision(c+1,r-1)){
                        c += 1;
                        r -= 1;
                    } else if (determine<0.1 && !atBound(c+1,r+1) && !FishTank.checkCollision(c+1,r+1)){
                        c += 1;
                        r += 1;
                    } else if (!FishTank.checkCollision(c+1,r)){
                        c += 1;
                    } else {
                        determine = Math.random();
                        if (determine<0.05 && !atBound(c,r-1) && !FishTank.checkCollision(c,r-1)){
                            r -= 1;
                        } else if (determine < 0.1 && !atBound(c, r + 1) && !FishTank.checkCollision(c, r + 1)) {
                            r += 1;
                        }
                    }
                }
            } else {
                determine = Math.random();
                if (determine<0.05 && !atBound(c-1,r-1) && !FishTank.checkCollision(c-1,r-1)){
                    c -= 1;
                    r -= 1;
                } else if (determine<0.1 && !atBound(c-1,r+1) && !FishTank.checkCollision(c-1,r+1)){
                    c -= 1;
                    r += 1;
                } else if (!FishTank.checkCollision(c-1,r)){
                    c -= 1;
                } else{
                    turnAround();
                    if (atBound(c+1,r)){
                        determine = Math.random();
                        if (determine<0.05 && !atBound(c,r-1) && !FishTank.checkCollision(c,r-1)){
                            r -= 1;
                        } else if (determine < 0.1 && !atBound(c, r + 1) && !FishTank.checkCollision(c, r + 1)) {
                            r += 1;
                        }
                    } else {
                        determine = Math.random();
                        if (determine<0.05 && !atBound(c+1,c-1) && !FishTank.checkCollision(c+1,r-1)){
                            c += 1;
                            r -= 1;
                        } else if (determine<0.1 && !atBound(c+1,r+1) && !FishTank.checkCollision(c+1,r+1)){
                            c += 1;
                            r += 1;
                        } else if (!FishTank.checkCollision(c+1,r)){
                            c += 1;
                        } else {
                            determine = Math.random();
                            if (determine<0.05 && !atBound(c,r-1) && !FishTank.checkCollision(c,r-1)){
                                r -= 1;
                            } else if (determine < 0.1 && !atBound(c, r + 1) && !FishTank.checkCollision(c, r + 1)) {
                                r += 1;
                            }
                        }
                    }
                }
            }
        }
    }


    public void update() {
        updateLocation();

        determine = Math.random();
        if (determine<0.1){
            blowBubble();
        }

        determine = Math.random();
        if (determine < 0.1) { turnAround(); }

        eatSeaweed();
    }
    }
