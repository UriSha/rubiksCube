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
            String msg = "Initialize test has failed. The center of the %s face is: %s instead of: %s";
            assertTrue(String.format(msg,"UP", cube.getUp().getGrid()[1][1],"RED"),
                    cube.getUp().getGrid()[1][1] == Cube.Color.RED);
            assertTrue( String.format(msg,"FRONT", cube.getFront().getGrid()[1][1],"YELLOW"),
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
            String msgCross = "Stage one test has failed. %s edge is located at : %s - %s  instead of : %s";
            Logic.Location redYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.YELLOW);
            Logic.Location redBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.BLUE);
            Logic.Location redGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.GREEN);
            Logic.Location redWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.WHITE);
            assertNotNull(redYellow);
            assertNotNull(redBlue);
            assertNotNull(redGreen);
            assertNotNull(redWhite);
            assertTrue(String.format(msgCross,"RED-YELLOW", redYellow.name, redYellow.secondDircetion,"UP - FRONT"),
                    redYellow.name == Face_Enum.UP && redYellow.secondDircetion == Face_Enum.FRONT);
            assertTrue(String.format(msgCross,"RED-BLUE", redBlue.name, redBlue.secondDircetion,"UP - RIGHT"),
                    redBlue.name == Face_Enum.UP && redBlue.secondDircetion == Face_Enum.RIGHT);
            assertTrue(String.format(msgCross,"RED-GREEN", redGreen.name, redGreen.secondDircetion,"UP - LEFT"),
                    redGreen.name == Face_Enum.UP && redGreen.secondDircetion == Face_Enum.LEFT);
            assertTrue(String.format(msgCross,"RED-WHITE", redWhite.name, redWhite.secondDircetion,"UP - BACK"),
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
            String msgCorner = "Stage Two has failed. %s  corner is not in place";
            Logic.Location redYellowBlue = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.YELLOW, Cube.Color.BLUE);
            Logic.Location redGreenYellow = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.YELLOW);
            Logic.Location redWhiteGreen = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.WHITE, Cube.Color.GREEN);
            Logic.Location redBlueWhite = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.WHITE);
            assertNotNull(redYellowBlue);
            assertNotNull(redGreenYellow);
            assertNotNull(redWhiteGreen);
            assertNotNull(redBlueWhite);
            assertTrue( String.format(msgCorner,"RED-YELLOW-BLUE"), redYellowBlue.name == Face_Enum.UP
                    && redYellowBlue.secondDircetion == Face_Enum.FRONT && redYellowBlue.thirdDirection == Face_Enum.RIGHT);
            assertTrue(String.format(msgCorner,"RED-GREEN-YELLOW"),redGreenYellow.name == Face_Enum.UP
                    && redGreenYellow.secondDircetion == Face_Enum.LEFT && redGreenYellow.thirdDirection == Face_Enum.FRONT);
            assertTrue( String.format(msgCorner,"RED-WHITE-GREEN"),redWhiteGreen.name == Face_Enum.UP
                    && redWhiteGreen.secondDircetion == Face_Enum.BACK && redWhiteGreen.thirdDirection == Face_Enum.LEFT);
            assertTrue(String.format(msgCorner,"RED-BLUE-WHITE"),redBlueWhite.name == Face_Enum.UP
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
            String msgEdge = "Stage Three test has failed. %s edge is located at : %s - %s  instead of : %s";
            Logic.Location whiteBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.WHITE, Cube.Color.BLUE);
            Logic.Location greenWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.GREEN, Cube.Color.WHITE);
            Logic.Location yellowGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.YELLOW, Cube.Color.GREEN);
            Logic.Location blueYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.BLUE, Cube.Color.YELLOW);
            assertNotNull(whiteBlue);
            assertNotNull(greenWhite);
            assertNotNull(yellowGreen);
            assertNotNull(blueYellow);
            assertTrue(String.format(msgEdge,"WHITE-BLUE", whiteBlue.name, whiteBlue.secondDircetion,"FRONT - RIGHT"),
                    whiteBlue.name == Face_Enum.FRONT && whiteBlue.secondDircetion == Face_Enum.RIGHT);
            assertTrue(String.format(msgEdge,"GREEN-WHITE", greenWhite.name, greenWhite.secondDircetion,"LEFT - FRONT"),
                    greenWhite.name == Face_Enum.LEFT && greenWhite.secondDircetion == Face_Enum.FRONT);
            assertTrue(String.format(msgEdge,"YELLOW-GREEN", yellowGreen.name, yellowGreen.secondDircetion,"BACK - LEFT"),
                    yellowGreen.name == Face_Enum.BACK && yellowGreen.secondDircetion == Face_Enum.LEFT);
            assertTrue(String.format(msgEdge,"BLUE-YELLOW", blueYellow.name, blueYellow.secondDircetion,"RIGHT - BACK"),
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
            String errMsg = "Stage four test has failed. this orange face should be UP, but instead it's %s";
            for (int i = 0; i < 4; i++) {
                assertNotNull(oranges[i]);
                assertTrue( String.format(errMsg, oranges[i].name),oranges[i].name == Face_Enum.UP);
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
            String errMsg = "Stage five test has failed. the color that caused the problem : %s";
            for (int j = 0;j < 4; j++) {
                assertNotNull(oranges[j]);
                assertTrue(String.format(errMsg, cube.getFront().getGrid()[0][1]),
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
            String errMsg = "Stage six test has failed. corner number %s is not in place";
            assertNotNull(corner1);
            assertNotNull(corner2);
            assertTrue(String.format(errMsg,"0"),
                    (corner1.name == Face_Enum.UP && corner1.secondDircetion == Face_Enum.FRONT && corner1.thirdDirection == Face_Enum.RIGHT) ||
                            (corner1.name == Face_Enum.RIGHT && corner1.secondDircetion == Face_Enum.UP && corner1.thirdDirection == Face_Enum.FRONT) ||
                            (corner1.name == Face_Enum.FRONT && corner1.secondDircetion == Face_Enum.RIGHT && corner1.thirdDirection == Face_Enum.UP) ||
                            (corner1.name == Face_Enum.UP && corner1.secondDircetion == Face_Enum.RIGHT && corner1.thirdDirection == Face_Enum.FRONT) ||
                            (corner1.name == Face_Enum.FRONT && corner1.secondDircetion == Face_Enum.UP && corner1.thirdDirection == Face_Enum.RIGHT) ||
                            (corner1.name == Face_Enum.RIGHT && corner1.secondDircetion == Face_Enum.FRONT && corner1.thirdDirection == Face_Enum.UP));
            assertTrue( String.format(errMsg,"3"),
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
            assertTrue(String.format(errMsg,"2"),
                    (corner3.name == Face_Enum.UP && corner3.secondDircetion == Face_Enum.FRONT && corner3.thirdDirection == Face_Enum.RIGHT) ||
                            (corner3.name == Face_Enum.RIGHT && corner3.secondDircetion == Face_Enum.UP && corner3.thirdDirection == Face_Enum.FRONT) ||
                            (corner3.name == Face_Enum.FRONT && corner3.secondDircetion == Face_Enum.RIGHT && corner3.thirdDirection == Face_Enum.UP) ||
                            (corner3.name == Face_Enum.UP && corner3.secondDircetion == Face_Enum.RIGHT && corner3.thirdDirection == Face_Enum.FRONT) ||
                            (corner3.name == Face_Enum.FRONT && corner3.secondDircetion == Face_Enum.UP && corner3.thirdDirection == Face_Enum.RIGHT) ||
                            (corner3.name == Face_Enum.RIGHT && corner3.secondDircetion == Face_Enum.FRONT && corner3.thirdDirection == Face_Enum.UP) );
            assertTrue(String.format(errMsg,"1"),(corner4.name == Face_Enum.UP && corner4.secondDircetion == Face_Enum.FRONT && corner4.thirdDirection == Face_Enum.LEFT) ||
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
            assertTrue( errMsg,Logic.isArnonReady(cube));
        }
    }

}