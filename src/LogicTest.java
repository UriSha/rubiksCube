import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LogicTest {
    /**
     * Test for the full algorithm, after getting a randomized cube - in order to test the optimizing stage
     */
    @Test

    public void algorithm() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            Logic.algorithm(cube);
            assertTrue("Error: The optimizing stage is not working", Logic.isArnonReady(cube));
        }
    }

    /**
     * These tests are for each stage of the algorithm separately. Each test assumes that the previous one was successful
     */


    @Test
    public void initialize() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<AlgorithmCommands> actions = new ArrayList<>();
            Logic.initialize(cube, actions);
            String msg1 = "Initialize test has failed. The center of the";
            String msg2 = "face is: ";
            String msg3 = "instead of: ";
            assertTrue(msg1 + "UP" + msg2 + cube.getUp().getGrid()[1][1] + msg3 + "RED",
                    cube.getUp().getGrid()[1][1] == Cube.Color.RED);
            assertTrue(msg1 + "FRONT" + msg2 + cube.getFront().getGrid()[1][1] + msg3 + "YELLOW",
                    cube.getFront().getGrid()[1][1] == Cube.Color.YELLOW);
        }
    }

    @Test
    public void stageOne() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<AlgorithmCommands> actions = new ArrayList<>();
            Logic.initialize(cube, actions);
            Logic.stageOne(cube, actions);
            String msg1 = "Stage one test has failed. ";
            String msg2 = " edge is located at : ";
            String msg3 = "  instead of : ";
            Logic.Location redYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.YELLOW);
            Logic.Location redBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.BLUE);
            Logic.Location redGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.GREEN);
            Logic.Location redWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.WHITE);
            assertNotNull(redYellow);
            assertNotNull(redBlue);
            assertNotNull(redGreen);
            assertNotNull(redWhite);
            assertTrue(msg1 + "RED-YELLOW" + msg2 + redYellow.name + " - " + redYellow.secondDircetion + msg3 + "UP - FRONT",
                    redYellow.name == Face_Enum.UP && redYellow.secondDircetion == Face_Enum.FRONT);
            assertTrue(msg1 + "RED-BLUE" + msg2 + redBlue.name + " - " + redBlue.secondDircetion + msg3 + "UP - RIGHT",
                    redBlue.name == Face_Enum.UP && redBlue.secondDircetion == Face_Enum.RIGHT);
            assertTrue(msg1 + "RED-GREEN" + msg2 + redGreen.name + " - " + redGreen.secondDircetion + msg3 + "UP - LEFT",
                    redGreen.name == Face_Enum.UP && redGreen.secondDircetion == Face_Enum.LEFT);
            assertTrue(msg1 + "RED-WHITE" + msg2 + redWhite.name + " - " + redWhite.secondDircetion + msg3 + "UP - BACK",
                    redWhite.name == Face_Enum.UP && redWhite.secondDircetion == Face_Enum.BACK);
        }
    }

    @Test
    public void stageTwo() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<AlgorithmCommands> actions = new ArrayList<>();
            Logic.initialize(cube, actions);
            Logic.stageOne(cube, actions);
            Logic.stageTwo(cube, actions);
            String msg1 = "Stage Two has failed. ";
            String msg2 = "corner is not in place. ";
            Logic.Location redYellowBlue = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.YELLOW, Cube.Color.BLUE);
            Logic.Location redGreenYellow = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.YELLOW);
            Logic.Location redWhiteGreen = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.WHITE, Cube.Color.GREEN);
            Logic.Location redBlueWhite = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.WHITE);
            assertNotNull(redYellowBlue);
            assertNotNull(redGreenYellow);
            assertNotNull(redWhiteGreen);
            assertNotNull(redBlueWhite);
            assertTrue(msg1+ "RED-YELLOW-BLUE" +msg2, redYellowBlue.name == Face_Enum.UP
                    && redYellowBlue.secondDircetion == Face_Enum.FRONT && redYellowBlue.thirdDirection == Face_Enum.RIGHT);
            assertTrue(msg1+ "RED-GREEN-YELLOW" +msg2, redGreenYellow.name == Face_Enum.UP
                    && redGreenYellow.secondDircetion == Face_Enum.LEFT && redGreenYellow.thirdDirection == Face_Enum.FRONT);
            assertTrue(msg1+ "RED-WHITE-GREEN" +msg2, redWhiteGreen.name == Face_Enum.UP
                    && redWhiteGreen.secondDircetion == Face_Enum.BACK && redWhiteGreen.thirdDirection == Face_Enum.LEFT);
            assertTrue(msg1+ "RED-BLUE-WHITE" +msg2, redBlueWhite.name == Face_Enum.UP
                    && redBlueWhite.secondDircetion == Face_Enum.RIGHT && redBlueWhite.thirdDirection == Face_Enum.BACK);
        }
    }

    @Test
    public void stageThree() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<AlgorithmCommands> actions = new ArrayList<>();
            Logic.initialize(cube, actions);
            Logic.stageOne(cube, actions);
            Logic.stageTwo(cube, actions);
            Logic.flipForStageThree(cube, actions);
            Logic.stageThree(cube, actions);
            String msg1 = "Stage Three test has failed. ";
            String msg2 = " edge is located at : ";
            String msg3 = " - ";
            String msg4 = "  instead of : ";
            Logic.Location whiteBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.WHITE, Cube.Color.BLUE);
            Logic.Location greenWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.GREEN, Cube.Color.WHITE);
            Logic.Location yellowGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.YELLOW, Cube.Color.GREEN);
            Logic.Location blueYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.BLUE, Cube.Color.YELLOW);
            assertNotNull(whiteBlue);
            assertNotNull(greenWhite);
            assertNotNull(yellowGreen);
            assertNotNull(blueYellow);
            assertTrue(msg1+  "WHITE-BLUE" +msg2+ whiteBlue.name +msg3+ whiteBlue.secondDircetion +msg4+ "FRONT - RIGHT",
                    whiteBlue.name == Face_Enum.FRONT && whiteBlue.secondDircetion == Face_Enum.RIGHT);
            assertTrue(msg1+  "GREEN-WHITE" +msg2+ greenWhite.name +msg3+ greenWhite.secondDircetion +msg4+ "LEFT - FRONT",
                    greenWhite.name == Face_Enum.LEFT && greenWhite.secondDircetion == Face_Enum.FRONT);
            assertTrue(msg1+  "YELLOW-GREEN" +msg2+ yellowGreen.name +msg3+ yellowGreen.secondDircetion +msg4+ "BACK - LEFT",
                    yellowGreen.name == Face_Enum.BACK && yellowGreen.secondDircetion == Face_Enum.LEFT);
            assertTrue(msg1+  "BLUE-YELLOW" +msg2+ blueYellow.name +msg3+ blueYellow.secondDircetion +msg4+ "RIGHT - BACK",
                    blueYellow.name == Face_Enum.RIGHT && blueYellow.secondDircetion == Face_Enum.BACK);
        }
    }

    @Test
    public void stageFour() {
        for (int j = 0; j < 100; j++) {
            Cube cube = CubeUtils.getRandomCube();
            List<AlgorithmCommands> actions = new ArrayList<>();
            Logic.initialize(cube, actions);
            Logic.stageOne(cube, actions);
            Logic.stageTwo(cube, actions);
            Logic.flipForStageThree(cube, actions);
            Logic.stageThree(cube, actions);
            Logic.stageFour(cube, actions);
            Logic.Location orangeBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.ORANGE, Cube.Color.BLUE);
            Logic.Location orangeWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.ORANGE, Cube.Color.WHITE);
            Logic.Location orangeGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.ORANGE, Cube.Color.GREEN);
            Logic.Location orangeYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.ORANGE, Cube.Color.YELLOW);
            Logic.Location[] oranges = {orangeBlue, orangeWhite, orangeGreen, orangeYellow};
            String errMsg = "Stage four test has failed. this orange face should be UP, but instead it's ";
            for (int i = 0; i < 4; i++) {
                assertNotNull(oranges[i]);
                assertTrue(errMsg + oranges[i].name, oranges[i].name == Face_Enum.UP);
            }
        }
    }

    @Test
    public void stageFive() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<AlgorithmCommands> actions = new ArrayList<>();
            Logic.initialize(cube, actions);
            Logic.stageOne(cube, actions);
            Logic.stageTwo(cube, actions);
            Logic.flipForStageThree(cube, actions);
            Logic.stageThree(cube, actions);
            Logic.stageFour(cube, actions);
            Logic.stageFive(cube, actions);
            Cube.Color[] oranges = {Cube.Color.WHITE, Cube.Color.GREEN, Cube.Color.YELLOW, Cube.Color.BLUE};
            String errMsg = "Stage five test has failed. the color that caused the problem : ";
            for (int j = 0; j < 4; j++) {
                assertNotNull(oranges[j]);
                assertTrue(errMsg + cube.getFront().getGrid()[0][1],
                        cube.getFront().getGrid()[0][1] == oranges[j]);
                cube.rotate(true);
            }
        }
    }

    @Test
    public void stageSix() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<AlgorithmCommands> actions = new ArrayList<>();
            Logic.initialize(cube, actions);
            Logic.stageOne(cube, actions);
            Logic.stageTwo(cube, actions);
            Logic.flipForStageThree(cube, actions);
            Logic.stageThree(cube, actions);
            Logic.stageFour(cube, actions);
            Logic.stageFive(cube, actions);
            Logic.stageSix(cube, actions);
            Logic.Location corner1 = LogicUtils.getLocationOfCorner(cube, Cube.Color.ORANGE, Cube.Color.WHITE, Cube.Color.BLUE);
            Logic.Location corner2 = LogicUtils.getLocationOfCorner(cube, Cube.Color.ORANGE, Cube.Color.WHITE, Cube.Color.GREEN);
            String errMsg1 = "Stage six test has failed. corner number ";
            String errMsg2 = "is not in place";
            assertNotNull(corner1);
            assertNotNull(corner2);
            assertTrue(errMsg1 + "0" + errMsg2,
                    (corner1.name == Face_Enum.UP && corner1.secondDircetion == Face_Enum.FRONT && corner1.thirdDirection == Face_Enum.RIGHT) ||
                            (corner1.name == Face_Enum.RIGHT && corner1.secondDircetion == Face_Enum.UP && corner1.thirdDirection == Face_Enum.FRONT) ||
                            (corner1.name == Face_Enum.FRONT && corner1.secondDircetion == Face_Enum.RIGHT && corner1.thirdDirection == Face_Enum.UP) ||
                            (corner1.name == Face_Enum.UP && corner1.secondDircetion == Face_Enum.RIGHT && corner1.thirdDirection == Face_Enum.FRONT) ||
                            (corner1.name == Face_Enum.FRONT && corner1.secondDircetion == Face_Enum.UP && corner1.thirdDirection == Face_Enum.RIGHT) ||
                            (corner1.name == Face_Enum.RIGHT && corner1.secondDircetion == Face_Enum.FRONT && corner1.thirdDirection == Face_Enum.UP));
            assertTrue(errMsg1 + "3"+ errMsg2,
                    (corner2.name == Face_Enum.UP && corner2.secondDircetion == Face_Enum.FRONT && corner2.thirdDirection == Face_Enum.LEFT) ||
                            (corner2.name == Face_Enum.LEFT && corner2.secondDircetion == Face_Enum.UP && corner2.thirdDirection == Face_Enum.FRONT) ||
                            (corner2.name == Face_Enum.FRONT && corner2.secondDircetion == Face_Enum.LEFT && corner2.thirdDirection == Face_Enum.UP) ||
                            (corner2.name == Face_Enum.UP && corner2.secondDircetion == Face_Enum.LEFT && corner2.thirdDirection == Face_Enum.FRONT) ||
                            (corner2.name == Face_Enum.FRONT && corner2.secondDircetion == Face_Enum.UP && corner2.thirdDirection == Face_Enum.LEFT) ||
                            (corner2.name == Face_Enum.LEFT && corner2.secondDircetion == Face_Enum.FRONT && corner2.thirdDirection == Face_Enum.UP));
            cube.rotate(true);
            cube.rotate(true);
            Logic.Location corner3 = LogicUtils.getLocationOfCorner(cube, Cube.Color.ORANGE, Cube.Color.YELLOW, Cube.Color.GREEN);
            Logic.Location corner4 = LogicUtils.getLocationOfCorner(cube, Cube.Color.ORANGE, Cube.Color.YELLOW, Cube.Color.BLUE);
            assertNotNull(corner3);
            assertNotNull(corner4);
            assertTrue(errMsg1 + "2" + errMsg2,
                    (corner3.name == Face_Enum.UP && corner3.secondDircetion == Face_Enum.FRONT && corner3.thirdDirection == Face_Enum.RIGHT) ||
                            (corner3.name == Face_Enum.RIGHT && corner3.secondDircetion == Face_Enum.UP && corner3.thirdDirection == Face_Enum.FRONT) ||
                            (corner3.name == Face_Enum.FRONT && corner3.secondDircetion == Face_Enum.RIGHT && corner3.thirdDirection == Face_Enum.UP) ||
                            (corner3.name == Face_Enum.UP && corner3.secondDircetion == Face_Enum.RIGHT && corner3.thirdDirection == Face_Enum.FRONT) ||
                            (corner3.name == Face_Enum.FRONT && corner3.secondDircetion == Face_Enum.UP && corner3.thirdDirection == Face_Enum.RIGHT) ||
                            (corner3.name == Face_Enum.RIGHT && corner3.secondDircetion == Face_Enum.FRONT && corner3.thirdDirection == Face_Enum.UP));
            assertTrue(errMsg1 + "1" + errMsg2, (corner4.name == Face_Enum.UP && corner4.secondDircetion == Face_Enum.FRONT && corner4.thirdDirection == Face_Enum.LEFT) ||
                    (corner4.name == Face_Enum.LEFT && corner4.secondDircetion == Face_Enum.UP && corner4.thirdDirection == Face_Enum.FRONT) ||
                    (corner4.name == Face_Enum.FRONT && corner4.secondDircetion == Face_Enum.LEFT && corner4.thirdDirection == Face_Enum.UP) ||
                    (corner4.name == Face_Enum.UP && corner4.secondDircetion == Face_Enum.LEFT && corner4.thirdDirection == Face_Enum.FRONT) ||
                    (corner4.name == Face_Enum.FRONT && corner4.secondDircetion == Face_Enum.UP && corner4.thirdDirection == Face_Enum.LEFT) ||
                    (corner4.name == Face_Enum.LEFT && corner4.secondDircetion == Face_Enum.FRONT && corner4.thirdDirection == Face_Enum.UP));
        }
    }

    @Test
    public void stageSeven() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<AlgorithmCommands> actions = new ArrayList<>();
            Logic.initialize(cube, actions);
            Logic.stageOne(cube, actions);
            Logic.stageTwo(cube, actions);
            Logic.flipForStageThree(cube, actions);
            Logic.stageThree(cube, actions);
            Logic.stageFour(cube, actions);
            Logic.stageFive(cube, actions);
            Logic.stageSix(cube, actions);
            Logic.stageSeven(cube, actions);
            String errMsg = "Stage seven test has failed";
            assertTrue(errMsg, Logic.isArnonReady(cube));
        }
    }

}