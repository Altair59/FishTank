package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SeaweedTest {
    private Seaweed seaweed;
    private Fish fish;

    @Before
    public void setUp() {
        seaweed = new Seaweed(10);
        fish = new Fish();
    }
    @Test
    public void testLeastLength(){
        boolean leastLength = true;
        for (int i =0;i<1000;i++){
            seaweed = new Seaweed(10);
            FishTank.addEntity(50,20, seaweed);
            FishTank.addEntity(49,19,fish);
            FishTank.addEntity(49,20,new Fish());
            FishTank.addEntity(48,20,new Fish());
            FishTank.addEntity(48,19,new Fish());
            FishTank.addEntity(48,18,new Fish());
            FishTank.addEntity(49,18,new Fish());
            fish.setGoingRight(true);
            fish.update();
            if (seaweed.getLenght() < 1){
                leastLength = false;
                break;
            }
            FishTank.clearAll();
        }
        assertTrue(leastLength);
    }



    @Test
    public void testEaten(){
        boolean getEaten = true;
        int eatMid = 0;
        for (int i=0;i<1000;i++){
            seaweed = new Seaweed(10);
            FishTank.addEntity(50,20,seaweed);
            FishTank.addEntity(51,15,fish);
            fish.setGoingRight(false);
            fish.update();
            if (seaweed.getLenght() != 4 && seaweed.getLenght() != 5 && seaweed.getLenght() != 6){
                getEaten = false;
                break;
            }
            if (seaweed.getLenght() == 5){
                eatMid++;
            }
            FishTank.clearAll();
        }
        assertTrue(getEaten && (eatMid > 850 && eatMid < 950));
    }

    @Test
    public void testGrow() {
        boolean grew = true;
        for (int j = 0; j < 1000; j++) {
            seaweed = new Seaweed(10);
            FishTank.addEntity(50, 20, seaweed);
            FishTank.addEntity(51, 18, fish);
            fish.setGoingRight(false);
            fish.update();
            fish.setGoingRight(false);
            fish.update();
            for (int i = 1; i <= 2000; i++) {
                int oldLength = seaweed.getLenght();
                seaweed.update();
                if (oldLength == seaweed.l0) {
                    if (seaweed.getLenght() != oldLength) {
                        grew = false;
                        break;
                    }
                } else {
                    if (i%200==0){
                        if (seaweed.getLenght() - oldLength != 1) {
                            grew = false;
                            break;
                        }
                    }
                    if (seaweed.getLenght() - oldLength == 1){
                        if ( i%200!=0){
                            grew = false;
                            break;
                        }
                    }
                }
            }
            FishTank.clearAll();
            if (!grew)
                break;
        }
        FishTank.clearAll();
        assertTrue(grew);
    }
}
