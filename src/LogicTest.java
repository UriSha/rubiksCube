import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    void algorithm() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            Logic.algorithm(cube);
            assertTrue(Logic.isArnonReady(cube), "Error: The main algorithm is not working");
        }
    }


    @Test
    void initialize() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<cmd> actions = new ArrayList<>();
            Logic.initialize(cube, actions);
            String msg = "Initialize test has failed. The center of the %s face is: %s instead of: %s";
            assertTrue(cube.getUp().getGrid()[1][1] == Cube.Color.RED, String.format
                    (msg,"UP", cube.getUp().getGrid()[1][1],"RED"));
            assertTrue(cube.getFront().getGrid()[1][1] == Cube.Color.YELLOW, String.format
                    (msg,"FRONT", cube.getFront().getGrid()[1][1],"YELLOW"));
        }
    }
    @Test
    void stageOne() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<cmd> actions = new ArrayList<>();
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
            assertTrue(redYellow.name == Face_Enum.UP && redYellow.secondDircetion == Face_Enum.FRONT,
                    String.format(msgCross,"RED-YELLOW", redYellow.name, redYellow.secondDircetion,"UP - FRONT"));
            assertTrue(redBlue.name == Face_Enum.UP && redBlue.secondDircetion == Face_Enum.RIGHT,
                    String.format(msgCross,"RED-BLUE", redBlue.name, redBlue.secondDircetion,"UP - RIGHT"));
            assertTrue(redGreen.name == Face_Enum.UP && redGreen.secondDircetion == Face_Enum.LEFT,
                    String.format(msgCross,"RED-GREEN", redGreen.name, redGreen.secondDircetion,"UP - LEFT"));
            assertTrue(redWhite.name == Face_Enum.UP && redWhite.secondDircetion == Face_Enum.BACK,
                    String.format(msgCross,"RED-WHITE", redWhite.name, redWhite.secondDircetion,"UP - BACK"));
        }
    }

    @Test
    void stageTwo() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<cmd> actions = new ArrayList<>();
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
            assertTrue(redYellowBlue.name == Face_Enum.UP && redYellowBlue.secondDircetion == Face_Enum.FRONT
                    && redYellowBlue.thirdDirection == Face_Enum.RIGHT, String.format(msgCorner,"RED-YELLOW-BLUE"));
            assertTrue(redGreenYellow.name == Face_Enum.UP && redGreenYellow.secondDircetion == Face_Enum.LEFT
                    && redGreenYellow.thirdDirection == Face_Enum.FRONT, String.format(msgCorner,"RED-GREEN-YELLOW"));
            assertTrue(redWhiteGreen.name == Face_Enum.UP && redWhiteGreen.secondDircetion == Face_Enum.BACK
                    && redWhiteGreen.thirdDirection == Face_Enum.LEFT, String.format(msgCorner,"RED-WHITE-GREEN"));
            assertTrue(redBlueWhite.name == Face_Enum.UP && redBlueWhite.secondDircetion == Face_Enum.RIGHT
                    && redBlueWhite.thirdDirection == Face_Enum.BACK, String.format(msgCorner,"RED-BLUE-WHITE"));
        }
    }

    @Test
    void stageThree() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<cmd> actions = new ArrayList<>();
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
            assertTrue(whiteBlue.name == Face_Enum.FRONT && whiteBlue.secondDircetion == Face_Enum.RIGHT,
                    String.format(msgEdge,"WHITE-BLUE", whiteBlue.name, whiteBlue.secondDircetion,"FRONT - RIGHT"));
            assertTrue(greenWhite.name == Face_Enum.LEFT && greenWhite.secondDircetion == Face_Enum.FRONT,
                    String.format(msgEdge,"GREEN-WHITE", greenWhite.name, greenWhite.secondDircetion,"LEFT - FRONT"));
            assertTrue(yellowGreen.name == Face_Enum.BACK && yellowGreen.secondDircetion == Face_Enum.LEFT,
                    String.format(msgEdge,"YELLOW-GREEN", yellowGreen.name, yellowGreen.secondDircetion,"BACK - LEFT"));
            assertTrue(blueYellow.name == Face_Enum.RIGHT && blueYellow.secondDircetion == Face_Enum.BACK,
                    String.format(msgEdge,"BLUE-YELLOW", blueYellow.name, blueYellow.secondDircetion,"RIGHT - BACK"));
        }
    }

    @Test
    void stageFour() {
        for (int j = 0; j < 100; j++) {
            Cube cube = CubeUtils.getRandomCube();
            List<cmd> actions = new ArrayList<>();
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
                assertTrue(oranges[i].name == Face_Enum.UP, String.format(errMsg, oranges[i].name));
            }
        }
    }

    @Test
    void stageFive() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<cmd> actions = new ArrayList<>();
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
                assertTrue(cube.getFront().getGrid()[0][1] == oranges[j]
                        , String.format(errMsg, cube.getFront().getGrid()[0][1]));
                cube.rotate(true);
            }
        }
    }

    @Test
    void stageSix() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<cmd> actions = new ArrayList<>();
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
            assertTrue((corner1.name == Face_Enum.UP && corner1.secondDircetion == Face_Enum.FRONT && corner1.thirdDirection == Face_Enum.RIGHT) ||
                    (corner1.name == Face_Enum.RIGHT && corner1.secondDircetion == Face_Enum.UP && corner1.thirdDirection == Face_Enum.FRONT) ||
                    (corner1.name == Face_Enum.FRONT && corner1.secondDircetion == Face_Enum.RIGHT && corner1.thirdDirection == Face_Enum.UP) ||
                    (corner1.name == Face_Enum.UP && corner1.secondDircetion == Face_Enum.RIGHT && corner1.thirdDirection == Face_Enum.FRONT) ||
                    (corner1.name == Face_Enum.FRONT && corner1.secondDircetion == Face_Enum.UP && corner1.thirdDirection == Face_Enum.RIGHT) ||
                    (corner1.name == Face_Enum.RIGHT && corner1.secondDircetion == Face_Enum.FRONT && corner1.thirdDirection == Face_Enum.UP),
                    String.format(errMsg,"0"));
            assertTrue((corner2.name == Face_Enum.UP && corner2.secondDircetion == Face_Enum.FRONT && corner2.thirdDirection == Face_Enum.LEFT) ||
                    (corner2.name == Face_Enum.LEFT && corner2.secondDircetion == Face_Enum.UP && corner2.thirdDirection == Face_Enum.FRONT) ||
                    (corner2.name == Face_Enum.FRONT && corner2.secondDircetion == Face_Enum.LEFT && corner2.thirdDirection == Face_Enum.UP) ||
                    (corner2.name == Face_Enum.UP && corner2.secondDircetion == Face_Enum.LEFT && corner2.thirdDirection == Face_Enum.FRONT) ||
                    (corner2.name == Face_Enum.FRONT && corner2.secondDircetion == Face_Enum.UP && corner2.thirdDirection == Face_Enum.LEFT) ||
                    (corner2.name == Face_Enum.LEFT && corner2.secondDircetion == Face_Enum.FRONT && corner2.thirdDirection == Face_Enum.UP),
                    String.format(errMsg,"3"));
            cube.rotate(true);
            cube.rotate(true);
            Logic.Location corner3 = LogicUtils.getLocationOfCorner(cube, Cube.Color.ORANGE, Cube.Color.YELLOW, Cube.Color.GREEN);
            Logic.Location corner4 = LogicUtils.getLocationOfCorner(cube, Cube.Color.ORANGE, Cube.Color.YELLOW, Cube.Color.BLUE);
            assertNotNull(corner3);
            assertNotNull(corner4);
            assertTrue((corner3.name == Face_Enum.UP && corner3.secondDircetion == Face_Enum.FRONT && corner3.thirdDirection == Face_Enum.RIGHT) ||
                    (corner3.name == Face_Enum.RIGHT && corner3.secondDircetion == Face_Enum.UP && corner3.thirdDirection == Face_Enum.FRONT) ||
                    (corner3.name == Face_Enum.FRONT && corner3.secondDircetion == Face_Enum.RIGHT && corner3.thirdDirection == Face_Enum.UP) ||
                    (corner3.name == Face_Enum.UP && corner3.secondDircetion == Face_Enum.RIGHT && corner3.thirdDirection == Face_Enum.FRONT) ||
                    (corner3.name == Face_Enum.FRONT && corner3.secondDircetion == Face_Enum.UP && corner3.thirdDirection == Face_Enum.RIGHT) ||
                    (corner3.name == Face_Enum.RIGHT && corner3.secondDircetion == Face_Enum.FRONT && corner3.thirdDirection == Face_Enum.UP),
                    String.format(errMsg,"2"));
            assertTrue((corner4.name == Face_Enum.UP && corner4.secondDircetion == Face_Enum.FRONT && corner4.thirdDirection == Face_Enum.LEFT) ||
                    (corner4.name == Face_Enum.LEFT && corner4.secondDircetion == Face_Enum.UP && corner4.thirdDirection == Face_Enum.FRONT) ||
                    (corner4.name == Face_Enum.FRONT && corner4.secondDircetion == Face_Enum.LEFT && corner4.thirdDirection == Face_Enum.UP) ||
                    (corner4.name == Face_Enum.UP && corner4.secondDircetion == Face_Enum.LEFT && corner4.thirdDirection == Face_Enum.FRONT) ||
                    (corner4.name == Face_Enum.FRONT && corner4.secondDircetion == Face_Enum.UP && corner4.thirdDirection == Face_Enum.LEFT) ||
                    (corner4.name == Face_Enum.LEFT && corner4.secondDircetion == Face_Enum.FRONT && corner4.thirdDirection == Face_Enum.UP),
                    String.format(errMsg,"1"));
        }
    }

    @Test
    void stageSeven() {
        for (int i = 0; i < 100; i++) {
            Cube cube = CubeUtils.getRandomCube();
            List<cmd> actions = new ArrayList<>();
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
            assertTrue(Logic.isArnonReady(cube), errMsg);
        }
    }
}