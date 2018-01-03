import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    void mainAlgorithm() {
    }

    @Test
    void algorithm() {
    }
    @Test
    void initialize(){
        Cube cube=getRandomCube();
        List<cmd> actions=new ArrayList<>();
        Logic.initialize(cube,actions);
        String msgUP = "Initialize test has failed. The center of the UP face is: %s instead of: RED";
        String msgFRONT = "Initialize test has failed. The center of the Front face is: %s instead of: YELLOW";
        assertTrue(cube.getUp().getGrid()[1][1]==Cube.Color.RED,String.format(msgUP,cube.getUp().getGrid()[1][1]));
        assertTrue(cube.getFront().getGrid()[1][1]==Cube.Color.YELLOW,String.format(msgUP,cube.getFront().getGrid()[1][1]));
    }
    @Test
    void stageOne(){
        Cube cube=getRandomCube();
        List<cmd> actions=new ArrayList<>();
        Logic.initialize(cube,actions);
        Logic.stageOne(cube,actions);
        String msgCross1="Stage one test has failed. RED-YELLOW edge is located at : %s - %s  instead of : UP - FRONT";
        String msgCross2="Stage one test has failed. RED-BLUE is located at : %s - %s  instead of : UP - RIGHT";
        String msgCross3="Stage one test has failed. RED-GREEN is located at : %s - %s  instead of : UP - LEFT";
        String msgCross4="Stage one test has failed. RED-WHITE is located at : %s - %s  instead of : UP - BACK";
        Logic.Location redYellow=LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.YELLOW);
        Logic.Location redBlue=LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.BLUE);
        Logic.Location redGreen=LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.GREEN);
        Logic.Location redWhite=LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.WHITE);
        assertTrue(redYellow.name==Face_Enum.UP && redYellow.secondDircetion==Face_Enum.FRONT,
                String.format(msgCross1,redYellow.name,redYellow.secondDircetion));
        assertTrue(redBlue.name==Face_Enum.UP && redBlue.secondDircetion==Face_Enum.RIGHT,
                String.format(msgCross2,redBlue.name,redBlue.secondDircetion));
        assertTrue(redGreen.name==Face_Enum.UP && redGreen.secondDircetion==Face_Enum.LEFT,
                String.format(msgCross3,redGreen.name,redGreen.secondDircetion));
        assertTrue(redWhite.name==Face_Enum.UP && redWhite.secondDircetion==Face_Enum.BACK,
                String.format(msgCross4,redWhite.name,redWhite.secondDircetion));
    }
    @Test
    void stageTwo(){
        Cube cube=getRandomCube();
        List<cmd> actions=new ArrayList<>();
        Logic.initialize(cube,actions);
        Logic.stageOne(cube,actions);
        Logic.stageTwo(cube,actions);
        String msgCorner="Stage two test has failed. ";
        


    }
    private Cube getRandomCube(){
        Cube.Color[][][] cubeValues = new Cube.Color[6][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[0][i][j] = Cube.Color.RED;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[1][i][j] = Cube.Color.BLUE;

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[2][i][j] = Cube.Color.ORANGE;

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[3][i][j] = Cube.Color.GREEN;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[4][i][j] = Cube.Color.YELLOW;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[5][i][j] = Cube.Color.WHITE;
            }
        }
        Cube cube = new Cube(cubeValues);
        for (int j = 0; j < 100; j++) {
                int x = ThreadLocalRandom.current().nextInt(0, 8);
                boolean y = ThreadLocalRandom.current().nextBoolean();
                switch (x) {
                    case 0:
                        cube.twistUpperFace(y);
                        break;
                    case 1:
                        cube.twistFrontFace(y);
                        break;
                    case 2:
                        cube.twistBottomFace(y);
                        break;
                    case 3:
                        cube.twistRightFace(y);
                        break;
                    case 4:
                        cube.twistLeftFace(y);
                        break;
                    case 5:
                        cube.twistBackFace(y);
                    case 6:
                        cube.flip();
                    case 7:
                        cube.rotate(y);
                        break;
                }

            }
            return cube;
        }
}