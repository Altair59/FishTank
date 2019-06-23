package fishtank;
import java.awt.*;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * Seaweed.
 */
public class Seaweed extends FishTankEntity {
    /** The font used to draw instances of this class. */
    private static Font FONT = new Font("Monospaced", Font.PLAIN, 10);


    /** The number of weed segments. */
    private int l;

    /** This bubble's first coordinate. */
    private int x;
    /** This bubble's second coordinate. */
    private int y;


    /** Indicates whether the bottom segment is leaning right. */
    private boolean leanRight;
    int l0;
    private int CD;
    /** My colour. Ah,the vagaries of British vs. US spelling. */
    private Color colour;


    /**
     * Constructs a new seaweed item at the specified cursor
     * location (x,y),l segments tall.
     * @param  l  the number of segments this seaweed is tall.
     */
    public Seaweed(int l) {
        this.l  =l;
        colour  =Color.green.darker().darker();
        this.l0=l;
    }

    public int getLenght() {
        return l;
    }

    public void getEaten(int cutPoint){
        this.l = y - cutPoint;
    }

    private boolean countCD(){
        CD++;
        return (CD % 200==0);
    }

    private void grow(){
        if (getLenght() < l0){
            this.l++;
        }
    }

    /**
     * Draws this fish tank item.  Looks lovely waving in the current, doesn't
     * it?
     *
     * @param  g  the graphics context in which to draw this item.
     */
    void draw(Graphics g) {

        // WWhich way does the first segment lean?
        boolean lR=leanRight;

        for (int i=0;i<l;i++) {
            if (i%2==0)
                if (lR)
                    drawString(g,"/",x,(-i+y));
                else{
                    drawString(g,"\\",x,(-i+y));
                }
            if (i%2==1)
                if (lR)
                    drawString(g,"\\",x,(-i+y));
                else{
                    drawString(g, "/", x, (-i + y));
                }
        }}
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
        g.drawString(s, x*fm.charWidth('W'), y*fm.getAscent());
    }



    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a,int b) {
        this.x  =a;
        this.y  =b;
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
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
        leanRight  =!leanRight;
        if (countCD())
            grow();
    }
}
