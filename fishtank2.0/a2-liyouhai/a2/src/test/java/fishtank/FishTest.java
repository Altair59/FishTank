package fishtank;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class FishTest {

    /* Note: FishTest is in the package FishTank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be AutoGrading your tests, so make sure
       to follow this naming convention!
     */
    private Fish fish;

    @Before
    public void setUp() {
        fish = new Fish();
    }

    @Test
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
      // refactored &
        //fixed the starter code.
        boolean inRange = true;
        for (int c=0;c<100;c++) {
            int bubbleMade = 0;
            for (int i = 0; i < 1000; i++) {
                fish.setLocation(50, 20);
                fish.update();
                if (FishTank.getEntity(fish.getX() - 1, fish.getY() - 1) instanceof Bubble) {
                    bubbleMade++;
                    FishTank.clearAll();
                } else if (FishTank.getEntity(fish.getX(), fish.getY() - 1) instanceof Bubble) {
                    bubbleMade++;
                    FishTank.clearAll();
                } else if (FishTank.getEntity(fish.getX() + 1, fish.getY() - 1) instanceof Bubble) {
                    bubbleMade++;
                    FishTank.clearAll();
                }
            }
            FishTank.clearAll();
            if (!(bubbleMade > 50 && bubbleMade < 150)){
                inRange = false;
                break;
            }
        }
        assertTrue(inRange);
    }

    @Test
    public void testSetGoingRightTrue(){
        boolean rightDirection = true;
        fish.setLocation(2,20);
        for (int i=0;i<200;i++){
            fish.setGoingRight(true);
            int oldC = fish.getX();
            fish.update();
            FishTank.clearAll();
            if (fish.atBound(fish.getX()+1,fish.getY())){
                break;
            }
            if (fish.getX() <= oldC){
                rightDirection = false;
                break;
            }
        }
        assertTrue(rightDirection);
    }

    @Test
    public void testSetGoingRightFalse(){
        boolean rightDirection = true;
        fish.setLocation(103,20);
        for (int i=0;i<200;i++) {
            fish.setGoingRight(false);
            int oldC = fish.getX();
            fish.update();
            FishTank.clearAll();
            if (fish.atBound(fish.getX() - 1, fish.getY())) {
                break;
            }
            if (fish.getX() >= oldC) {
                rightDirection = false;
                break;
            }
        }
        assertTrue(rightDirection);
    }

    @Test
    public void testTurnAround() {
        boolean inRange = true;
        for (int c=0;c<100;c++) {
            int turned = 0;
            for (int i = 0; i < 1000; i++) {
                fish.setLocation(50, 20);
                boolean oldDirection = fish.goingRight;
                fish.update();
                FishTank.clearAll();
                if (oldDirection != fish.goingRight) {
                    turned++;
                }
            }
            if (!(turned > 50 && turned < 150))
                inRange  = false;
        }
        assertTrue(inRange);
    }

    @Test
    public void testUpDown() {
        boolean inRange = true;
        for (int c=0;c<100;c++) {
            int UpDown = 0;
            for (int i = 0; i < 1000; i++) {
                fish.setLocation(50, 20);
                int oldY = fish.getY();
                fish.update();
                FishTank.clearAll();
                if (oldY != fish.getY()) {
                    UpDown++;
                }
            }
            if (!(UpDown > 50 && UpDown < 150))
                inRange  = false;
        }
        assertTrue(inRange);
    }

    @Test
    public void testRightBound(){
        for (int i = 0;i<1000;i++) {
            fish.setLocation(103, 20);
            fish.setGoingRight(true);
            fish.update();
            FishTank.clearAll();
            assertNotEquals(104, fish.getX());
        }
    }

    @Test
    public void testLeftBound(){
        for (int i=0;i<1000;i++) {
            fish.setLocation(2, 20);
            fish.setGoingRight(false);
            fish.update();
            FishTank.clearAll();
            assertNotEquals(1, fish.getX());
        }
    }

    @Test
    public void testUpperBound(){
        boolean inRange = true;
        for (int i=0;i<1000;i++){
            fish.setLocation(50,2);
            fish.update();
            FishTank.clearAll();
            if (fish.getY() == 1){
                inRange = false;
                break;
            }
        }
        assertTrue(inRange);
    }

    @Test
    public void testLowerBound(){
        boolean inRange = true;
        for (int i=0;i<1000;i++){
            fish.setLocation(50,45);
            fish.update();
            FishTank.clearAll();
            if (fish.getY() == 46){
                inRange = false;
                break;
            }
        }
        assertTrue(inRange);
    }

    @Test
    public void testCollision(){
        Bubble mockedBubble = mock(Bubble.class);
        when(mockedBubble.getX()).thenReturn(10);
        when(mockedBubble.getY()).thenReturn(11);
        HungryFish mockedHungryFish = mock(HungryFish.class);
        when(mockedHungryFish.getX()).thenReturn(8);
        when(mockedHungryFish.getY()).thenReturn(11);
        for (int i=0;i<100;i++) {
            FishTank.addEntity(10, 11, mockedBubble);
            FishTank.addEntity(8,11,mockedHungryFish);
            Fish fish = new Fish();
            fish.setLocation(9, 11);
            fish.setGoingRight(true);
            fish.update();
            assertTrue(FishTank.getEntity(10, 11) instanceof Bubble && FishTank.getEntity(8,11)
            instanceof HungryFish);
            FishTank.clearAll();
        }
    }
}
