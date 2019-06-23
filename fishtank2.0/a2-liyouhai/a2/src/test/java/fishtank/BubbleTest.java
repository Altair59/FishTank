package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BubbleTest {
    private Bubble bubble;

    @Before
    public void setUp() {
        bubble = new Bubble();
    }

    @Test
    public void floatTest(){
        for (int j=0;j<1000;j++) {
            int up = 0;
            int upLeft = 0;
            int upright = 0;
            for (int i = 0; i < 100000; i++) {
                bubble.setLocation(50, 20);
                int oldX = bubble.getX();
                bubble.update();
                if (bubble.getX() - oldX == 0) {
                    up++;
                } else if (bubble.getX() - oldX == 1) {
                    upright++;
                } else if (bubble.getX() - oldX == -1) {
                    upLeft++;
                }
            }
            assertTrue((upright > 28000 && upright < 38000) && (upLeft > 28000 && upLeft < 38000) &&
                    up + upLeft + upright == 100000);
        }
    }

    @Test
    public void testSelfDestruct(){
        boolean destruct = true;
        for (int i=0;i<1000;i++){
            bubble = new Bubble();
            bubble.setLocation(50,45);
            for (int j=0;j<50;j++){
                if (bubble.exists())
                    bubble.update();
                if (bubble.atBound(bubble.getX(),bubble.getY())){
                    if (bubble.exists()){
                        destruct = false;
                        break;
                    }
                }
            }
            if (!destruct){
                break;
            }
        }
        assertTrue(destruct);
    }

    @Test
    public void testCollision(){
        Fish mockedFish1 = mock(Fish.class);
        when(mockedFish1.getX()).thenReturn(49);
        when(mockedFish1.getY()).thenReturn(19);
        Bubble mockedBubble = mock(Bubble.class);
        when(mockedBubble.getX()).thenReturn(50);
        when(mockedBubble.getY()).thenReturn(19);
        HungryFish mockedHungryFish = mock(HungryFish.class);
        when(mockedHungryFish.getX()).thenReturn(51);
        when(mockedHungryFish.getY()).thenReturn(19);
        for (int i=0;i<100;i++) {
            FishTank.addEntity(49, 19, mockedFish1);
            FishTank.addEntity(50, 19, mockedBubble);
            FishTank.addEntity(51, 19, mockedHungryFish);
            Bubble bubble = new Bubble();
            bubble.setLocation(50, 20);
            bubble.update();
            assertTrue(FishTank.getEntity(49, 19) instanceof Fish && FishTank.getEntity(50, 19)
                    instanceof Bubble && FishTank.getEntity(51, 19) instanceof HungryFish);
            assertTrue(bubble.getX() == 50 && bubble.getY() == 20);
            FishTank.clearAll();
        }
    }
}
