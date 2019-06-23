package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class
FollowingFishTest {
    private Fish followee;
    private FollowingFish follower;

    @Before
    public void setUp() {
        followee = mock(Fish.class);
        //note: this is also why we use getters and setters so much in Java,
        //we wouldn't be able to mock the field itself if it were used instead
        //of the getter.
        when(followee.getX()).thenReturn(50);
        //This syntax is introduced by a library called mockito.
        //You can use it however you want, and it will be installed when we
        //run the grader.
        //See: http://www.vogella.com/tutorials/Mockito/article.html from 4 onwards
        when(followee.getY()).thenReturn(20);

        follower = new FollowingFish(followee);
    }

    @Test
    public void testApproachesFromBottomRight(){
        for (int i =0;i<100;i++) {
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(50);
            when(followee.getY()).thenReturn(20);
            follower = new FollowingFish(followee);
            follower.setLocation(70, 35);
            for (int j = 0; j < 100; j++) {
                follower.update();
            }
            int verticalDist = Math.abs(follower.getY() - followee.getY());
            int horizontalDist = Math.abs(follower.getX() - followee.getX());
            //Follower should be exactly 2 units below followee.
            assertEquals(2, verticalDist);
            assertEquals(0, horizontalDist);
        }
    }

    @Test
    public void testApproachesFromBottomLeft() {
        for (int j=0;j<100;j++) {
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(50);
            when(followee.getY()).thenReturn(20);
            follower = new FollowingFish(followee);
            follower.setLocation(30, 35);
            for (int i = 0; i < 20; i++) {
                follower.update();
            }
            int verticalDist = Math.abs(follower.getY() - followee.getY());
            int horizontalDist = Math.abs(follower.getX() - followee.getX());
            //Follower should be exactly 2 units below followee.
            assertEquals(2, verticalDist);
            assertEquals(0, horizontalDist);
        }
    }

    @Test
    public void testApproachesFromTopRight() {
        for (int j=0;j<100;j++) {
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(50);
            when(followee.getY()).thenReturn(20);
            follower = new FollowingFish(followee);
            follower.setLocation(60, 5);
            for (int i = 0; i < 15; i++) {
                follower.update();
            }
            int verticalDist = Math.abs(follower.getY() - followee.getY());
            int horizontalDist = Math.abs(follower.getX() - followee.getX());
            //Follower should be exactly 2 units below followee.
            assertEquals(2, verticalDist);
            assertEquals(0, horizontalDist);
        }
    }

    @Test
    public void testApproachesFromTopLeft() {
        for (int j=0;j<100;j++) {
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(50);
            when(followee.getY()).thenReturn(20);
            follower = new FollowingFish(followee);
            follower.setLocation(40, 5);
            for (int i = 0; i < 15; i++) {
                follower.update();
            }
            int verticalDist = Math.abs(follower.getY() - followee.getY());
            int horizontalDist = Math.abs(follower.getX() - followee.getX());
            //Follower should be exactly 2 units below followee.
            assertEquals(2, verticalDist);
            assertEquals(0, horizontalDist);
        }
    }

    @Test
    public void testStayDistanceTwoNormal() {
        for (int i = 0; i < 1000; i++) {
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(50);
            when(followee.getY()).thenReturn(20);
            follower = new FollowingFish(followee);
            follower.setLocation(50, 18);
            followee.update();
            when(followee.getX()).thenReturn(49);
            when(followee.getY()).thenReturn(20);
            follower.update();
            int verticalDistance = Math.abs(followee.getY() - follower.getY());
            int horizontalDistance = Math.abs(followee.getX() - follower.getX());
            assertTrue(verticalDistance == 2 && horizontalDistance == 0);
        }
    }

    @Test
    public void testStayDistanceTwoCorneredTop() {
        for (int i = 0; i < 1000; i++) {
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(50);
            when(followee.getY()).thenReturn(4);
            follower = new FollowingFish(followee);
            follower.setLocation(50, 2);
            followee.update();
            when(followee.getX()).thenReturn(49);
            when(followee.getY()).thenReturn(3);
            follower.update();
            int verticalDistance = Math.abs(followee.getY() - follower.getY());
            int horizontalDistance = Math.abs(followee.getX() - follower.getX());
            assertTrue(verticalDistance == 1 && horizontalDistance == 1);
        }
    }

    @Test
    public void testStayDistanceTwoCorneredBottom() {
        for (int i = 0; i < 1000; i++) {
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(50);
            when(followee.getY()).thenReturn(43);
            follower = new FollowingFish(followee);
            follower.setLocation(50, 45);
            followee.update();
            when(followee.getX()).thenReturn(49);
            when(followee.getY()).thenReturn(44);
            follower.update();
            int verticalDistance = Math.abs(followee.getY() - follower.getY());
            int horizontalDistance = Math.abs(followee.getX() - follower.getX());
            assertTrue(verticalDistance == 1 && horizontalDistance == 1);
        }
    }

    @Test
    public void testRightBound(){
        for (int i=0;i<1000;i++) {
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(103);
            when(followee.getY()).thenReturn(20);
            follower = new FollowingFish(followee);
            follower.setLocation(103, 18);
            followee.setGoingRight(true);
            followee.update();
            FishTank.clearAll();
            when(followee.getX()).thenReturn(103);
            when(followee.getY()).thenReturn(21);
            follower.update();
            assertNotEquals(104, follower.getX());
        }
    }

    @Test
    public void testLeftBound(){
        for (int i=0;i<1000;i++) {
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(2);
            when(followee.getY()).thenReturn(20);
            follower = new FollowingFish(followee);
            follower.setLocation(2, 18);
            followee.setGoingRight(false);
            followee.update();
            FishTank.clearAll();
            when(followee.getX()).thenReturn(2);
            when(followee.getY()).thenReturn(21);
            follower.update();
            assertNotEquals(1, follower.getX());
        }
    }

    @Test
    public void testUpperBound(){
        boolean inRange = true;
        for (int i=0;i<1000;i++){
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(50);
            when(followee.getY()).thenReturn(4);
            follower = new FollowingFish(followee);
            follower.setLocation(50,2);
            followee.update();
            FishTank.clearAll();
            when(followee.getX()).thenReturn(49);
            when(followee.getY()).thenReturn(3);
            follower.update();
            if (follower.getY() == 1){
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
            followee = mock(Fish.class);
            when(followee.getX()).thenReturn(50);
            when(followee.getY()).thenReturn(43);
            follower = new FollowingFish(followee);
            follower.setLocation(50,45);
            followee.update();
            FishTank.clearAll();
            when(followee.getX()).thenReturn(49);
            when(followee.getY()).thenReturn(44);
            follower.update();
            if (follower.getY() == 46){
                inRange = false;
                break;
            }
        }
        assertTrue(inRange);
    }

    @Test
    public void testCollision(){
        followee = mock(Fish.class);
        when(followee.getX()).thenReturn(50);
        when(followee.getY()).thenReturn(20);
        Bubble mockedBubble = mock(Bubble.class);
        when(mockedBubble.getX()).thenReturn(51);
        when(mockedBubble.getY()).thenReturn(18);
        for (int i=0;i<100;i++) {
            FishTank.addEntity(51, 18, mockedBubble);
            follower = new FollowingFish(followee);
            follower.setLocation(50, 18);
            followee.update();
            when(followee.getX()).thenReturn(51);
            when(followee.getY()).thenReturn(20);
            follower.update();
            assertTrue(FishTank.getEntity(51, 18) instanceof Bubble);
            FishTank.clearAll();
        }
    }
}
