import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Cube cube=CubeUtils.getRandomCube();
        List<cmd> actions=new ArrayList<>();
        Logic.initialize(cube,actions);
        String msgUP = "Initialize test has failed. The center of the UP face is: %s instead of: RED";
        String msgFRONT = "Initialize test has failed. The center of the Front face is: %s instead of: YELLOW";
        assertTrue(cube.getUp().getGrid()[1][1]==Cube.Color.RED,String.format(msgUP,cube.getUp().getGrid()[1][1]));
        assertTrue(cube.getFront().getGrid()[1][1]==Cube.Color.YELLOW,String.format(msgFRONT,cube.getFront().getGrid()[1][1]));
    }
    @Test
    void stageOne(){
        Cube cube=CubeUtils.getRandomCube();
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
        assertNotNull(redYellow);
        assertNotNull(redBlue);
        assertNotNull(redGreen);
        assertNotNull(redWhite);
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
        Cube cube=CubeUtils.getRandomCube();
        List<cmd> actions=new ArrayList<>();
        Logic.initialize(cube,actions);
        Logic.stageOne(cube,actions);
        Logic.stageTwo(cube,actions);
        String msgCorner1="Stage Two has failed. RED-YELLOW-BLUE corner is not in place";
        String msgCorner2="Stage Two has failed. RED-GREEN-YELLOW corner is not in place";
        String msgCorner3="Stage Two has failed. RED-WHITE-GREEN corner is not in place";
        String msgCorner4="Stage Two has failed. RED-BLUE-WHITE corner is not in place";
        Logic.Location redYellowBlue = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.YELLOW, Cube.Color.BLUE);
        Logic.Location redGreenYellow = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.YELLOW);
        Logic.Location redWhiteGreen = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.WHITE, Cube.Color.GREEN);
        Logic.Location redBlueWhite = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.WHITE);
        assertNotNull(redYellowBlue);
        assertNotNull(redGreenYellow);
        assertNotNull(redWhiteGreen);
        assertNotNull(redBlueWhite);
        assertTrue(redYellowBlue.name==Face_Enum.UP&&redYellowBlue.secondDircetion==Face_Enum.FRONT
                &&redYellowBlue.thirdDirection==Face_Enum.RIGHT,msgCorner1);
        assertTrue(redGreenYellow.name==Face_Enum.UP&&redGreenYellow.secondDircetion==Face_Enum.LEFT
                &&redGreenYellow.thirdDirection==Face_Enum.FRONT,msgCorner2);
        assertTrue(redWhiteGreen.name==Face_Enum.UP&&redWhiteGreen.secondDircetion==Face_Enum.BACK
                &&redWhiteGreen.thirdDirection==Face_Enum.LEFT,msgCorner3);
        assertTrue(redBlueWhite.name==Face_Enum.UP&&redBlueWhite.secondDircetion==Face_Enum.RIGHT
                &&redBlueWhite.thirdDirection==Face_Enum.BACK,msgCorner4);
    }
    @Test
    void stageThree(){
        Cube cube=CubeUtils.getRandomCube();
        List<cmd> actions=new ArrayList<>();
        Logic.initialize(cube,actions);
        Logic.stageOne(cube,actions);
        Logic.stageTwo(cube,actions);
        Logic.flipForStageThree(cube,actions);
        Logic.stageThree(cube,actions);
        String msgEdge1="Stage Three test has failed. WHITE-BLUE edge is located at : %s - %s  instead of : FRONT - RIGHT";
        String msgEdge2="Stage Three test has failed. GREEN-WHITE edge is located at : %s - %s  instead of : LEFT - FRONT";
        String msgEdge3="Stage Three test has failed. YELLOW-GREEN edge is located at : %s - %s  instead of : BACK - LEFT";
        String msgEdge4="Stage Three test has failed. BLUE-YELLOW edge is located at : %s - %s  instead of : RIGHT - BACK";
        Logic.Location whiteBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.WHITE, Cube.Color.BLUE);
        Logic.Location greenWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.GREEN, Cube.Color.WHITE);
        Logic.Location yellowGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.YELLOW, Cube.Color.GREEN);
        Logic.Location blueYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.BLUE, Cube.Color.YELLOW);
        assertNotNull(whiteBlue);
        assertNotNull(greenWhite);
        assertNotNull(yellowGreen);
        assertNotNull(blueYellow);
        assertTrue(whiteBlue.name==Face_Enum.FRONT && whiteBlue.secondDircetion==Face_Enum.RIGHT,
                String.format(msgEdge1,whiteBlue.name,whiteBlue.secondDircetion));
        assertTrue(greenWhite.name==Face_Enum.LEFT && greenWhite.secondDircetion==Face_Enum.FRONT,
                String.format(msgEdge2,greenWhite.name,greenWhite.secondDircetion));
        assertTrue(yellowGreen.name==Face_Enum.BACK && yellowGreen.secondDircetion==Face_Enum.LEFT,
                String.format(msgEdge3,yellowGreen.name,yellowGreen.secondDircetion));
        assertTrue(blueYellow.name==Face_Enum.RIGHT && blueYellow.secondDircetion==Face_Enum.BACK,
                String.format(msgEdge4,blueYellow.name,blueYellow.secondDircetion));
    }
    @Test
    void stageFour(){
        Cube cube=CubeUtils.getRandomCube();
        List<cmd> actions=new ArrayList<>();
        Logic.initialize(cube,actions);
        Logic.stageOne(cube,actions);
        Logic.stageTwo(cube,actions);
        Logic.flipForStageThree(cube,actions);
        Logic.stageThree(cube,actions);
        Logic.stageFour(cube,actions);
        Logic.Location orangeBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.ORANGE, Cube.Color.BLUE);
        Logic.Location orangeWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.ORANGE, Cube.Color.WHITE);
        Logic.Location orangeGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.ORANGE, Cube.Color.GREEN);
        Logic.Location orangeYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.ORANGE, Cube.Color.YELLOW);
        Logic.Location[] oranges={orangeBlue,orangeWhite,orangeGreen,orangeYellow};
        String errMsg="Stage four test has failed. this orange face should be UP, but instead it's %s";
        for(int i=0;i<4;i++){
            assertNotNull(oranges[i]);
            assertTrue(oranges[i].name==Face_Enum.UP,String.format(errMsg,oranges[i].name));
        }
    }
    void stageFive(){
        Cube cube=CubeUtils.getRandomCube();
        List<cmd> actions=new ArrayList<>();
        Logic.initialize(cube,actions);
        Logic.stageOne(cube,actions);
        Logic.stageTwo(cube,actions);
        Logic.flipForStageThree(cube,actions);
        Logic.stageThree(cube,actions);
        Logic.stageFour(cube,actions);
        Logic.stageFive(cube,actions);

    }
}