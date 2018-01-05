import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RobotActionsTranslatorTest {
    @org.junit.jupiter.api.Test
    void translateCommandsWithoutAlgoMovingPointOfView() {
        String wrongActionMsg = "translateCommandsWithoutAlgoMovingPointOfView() failed. the %dth (zero-based) action was expected to be %s, but was %s";
        String differentSizesMsg = "translateCommandsWithoutAlgoMovingPointOfView() failed. the translator returned a list in size of %d, when it was supposed to be in size of %d";

        List<AlgorithmCommands> algorithmCommands = new ArrayList<>();
        algorithmCommands.add(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT);
        algorithmCommands.add(AlgorithmCommands.CMD_UP_TWIST_LEFT);
        algorithmCommands.add(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD);
        algorithmCommands.add(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD);

        List<RobotActionsTranslator.RobotSolvingAction> robotCommandsFromAlgo;
        robotCommandsFromAlgo = RobotActionsTranslator.translateCommandList(algorithmCommands);

        RobotActionsTranslator.RobotSolvingAction[] expectedRobotActions = new RobotActionsTranslator.RobotSolvingAction[10];
        expectedRobotActions[0] = RobotActionsTranslator.RobotSolvingAction.ROBOT_RIGHT_TWIST;
        expectedRobotActions[1] = RobotActionsTranslator.RobotSolvingAction.ROBOT_FLIP;
        expectedRobotActions[2] = RobotActionsTranslator.RobotSolvingAction.ROBOT_FLIP;
        expectedRobotActions[3] = RobotActionsTranslator.RobotSolvingAction.ROBOT_RIGHT_TWIST;
        expectedRobotActions[4] = RobotActionsTranslator.RobotSolvingAction.ROBOT_RIGHT_ROTATE;
        expectedRobotActions[5] = RobotActionsTranslator.RobotSolvingAction.ROBOT_FLIP;
        expectedRobotActions[6] = RobotActionsTranslator.RobotSolvingAction.ROBOT_RIGHT_TWIST;
        expectedRobotActions[7] = RobotActionsTranslator.RobotSolvingAction.ROBOT_FLIP;
        expectedRobotActions[8] = RobotActionsTranslator.RobotSolvingAction.ROBOT_FLIP;
        expectedRobotActions[9] = RobotActionsTranslator.RobotSolvingAction.ROBOT_RIGHT_TWIST;

        compareActions(expectedRobotActions,robotCommandsFromAlgo,differentSizesMsg,wrongActionMsg);

    }
    @org.junit.jupiter.api.Test
    void translateCommandsWithAlgoMovingPointOfView() {
        String wrongActionMsg = "translateCommandsWithAlgoMovingPointOfView() failed. the %dth (zero-based) action was expected to be %s, but was %s";
        String differentSizesMsg = "translateCommandsWithAlgoMovingPointOfView() failed. the translator returned a list in size of %d, when it was supposed to be in size of %d";

        List<AlgorithmCommands> algorithmCommands = new ArrayList<>();
        algorithmCommands.add(AlgorithmCommands.CMD_FLIP);
        algorithmCommands.add(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE);
        algorithmCommands.add(AlgorithmCommands.CMD_LEFT_ROTATE);
        algorithmCommands.add(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE);


        List<RobotActionsTranslator.RobotSolvingAction> robotCommandsFromAlgo = RobotActionsTranslator.translateCommandList(algorithmCommands);

        RobotActionsTranslator.RobotSolvingAction[] expectedRobotActions = new RobotActionsTranslator.RobotSolvingAction[6];
        expectedRobotActions[0] = RobotActionsTranslator.RobotSolvingAction.ROBOT_FLIP;
        expectedRobotActions[1] = RobotActionsTranslator.RobotSolvingAction.ROBOT_FLIP;
        expectedRobotActions[2] = RobotActionsTranslator.RobotSolvingAction.ROBOT_RIGHT_TWIST;
        expectedRobotActions[3] = RobotActionsTranslator.RobotSolvingAction.ROBOT_RIGHT_ROTATE;
        expectedRobotActions[4] = RobotActionsTranslator.RobotSolvingAction.ROBOT_FLIP;
        expectedRobotActions[5] = RobotActionsTranslator.RobotSolvingAction.ROBOT_RIGHT_TWIST;

        compareActions(expectedRobotActions,robotCommandsFromAlgo,differentSizesMsg,wrongActionMsg);
    }


    private void compareActions(RobotActionsTranslator.RobotSolvingAction[] expectedRobotActions,
                                List<RobotActionsTranslator.RobotSolvingAction> robotCommandsFromAlgo,
                                String differentSizesMsg,String wrongActionMsg){

        assertTrue (robotCommandsFromAlgo.size() == expectedRobotActions.length ,
                String.format(differentSizesMsg,robotCommandsFromAlgo.size(),expectedRobotActions.length));
        for (int i=0; i<expectedRobotActions.length;i++){
            assertTrue(expectedRobotActions[i]==robotCommandsFromAlgo.get(i),
                    String.format(wrongActionMsg,i,expectedRobotActions[i],robotCommandsFromAlgo.get(i)));
        }
    }

    @org.junit.jupiter.api.Test
    void getBoolForDirection() {
        String msg = "getBoolForDirection() failed when initial face is %s, current face is %s, and boolean is %s";
        // test translator if the initial and current face are the same (i.e need to translate initial to DOWN)
        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.UP, false), String.format(msg, "UP", "UP", "false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.UP, true), String.format(msg, "UP", "UP", "true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.DOWN, true), String.format(msg, "DOWN", "DOWN", "true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.DOWN, false), String.format(msg, "DOWN", "DOWN", "false"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.LEFT, false), String.format(msg, "LEFT", "LEFT", "false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.LEFT, true), String.format(msg, "LEFT", "LEFT", "true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.RIGHT, true), String.format(msg, "RIGHT", "RIGHT", "true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.RIGHT, false), String.format(msg, "RIGHT", "RIGHT", "false"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.BACK, false), String.format(msg, "BACK", "BACK", "false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.BACK, true), String.format(msg, "BACK", "BACK", "true"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.FRONT, true), String.format(msg, "FRONT", "FRONT", "true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.FRONT, false), String.format(msg, "FRONT", "FRONT", "false"));


        // test translator if the current face is DOWN the same
        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.DOWN, false), String.format(msg, "UP", "DOWN", "false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.DOWN, true), String.format(msg, "UP", "DOWN", "true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.DOWN, false), String.format(msg, "LEFT", "DOWN", "false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.DOWN, true), String.format(msg, "LEFT", "DOWN", "true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.DOWN, true), String.format(msg, "RIGHT", "DOWN", "true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.DOWN, false), String.format(msg, "RIGHT", "DOWN", "false"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.DOWN, false), String.format(msg, "BACK", "DOWN", "false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.DOWN, true), String.format(msg, "BACK", "DOWN", "true"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.DOWN, true), String.format(msg, "FRONT", "DOWN", "true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.DOWN, false), String.format(msg, "FRONT", "DOWN", "false"));


        // test translator if the initial and current are opposite
        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.RIGHT, false), String.format(msg, "LEFT", "RIGHT", "false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.RIGHT, true), String.format(msg, "LEFT", "RIGHT", "true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.FRONT, false), String.format(msg, "BACK", "FRONT", "false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.FRONT, true), String.format(msg, "BACK", "FRONT", "true"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.UP, false), String.format(msg, "DOWN", "UP", "false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.UP, true), String.format(msg, "DOWN", "UP", "true"));

    }

    @org.junit.jupiter.api.Test
    void virtualUpdateMapDueToFlip() {

        String msg = "virtualUpdateMapDueToFlip() failed when before virtual flip the key: %s has value: %s, and after" +
                " the flip the value is: %s instead of %s";

        // init first test map
        Map<Face_Enum, Face_Enum> testMap = new HashMap<>();

        testMap.put(Face_Enum.UP, Face_Enum.UP);
        testMap.put(Face_Enum.BACK, Face_Enum.BACK);
        testMap.put(Face_Enum.DOWN, Face_Enum.DOWN);
        testMap.put(Face_Enum.RIGHT, Face_Enum.RIGHT);
        testMap.put(Face_Enum.LEFT, Face_Enum.LEFT);
        testMap.put(Face_Enum.FRONT, Face_Enum.FRONT);
        // init test map end


        // first flip
        Face_Enum prevUP = Face_Enum.UP;
        Face_Enum prevBack = Face_Enum.BACK;
        Face_Enum prevDown = Face_Enum.DOWN;
        Face_Enum prevFront = Face_Enum.FRONT;
        Face_Enum prevLeft = Face_Enum.LEFT;
        Face_Enum prevRight = Face_Enum.RIGHT;

        RobotActionsTranslator.virtualUpdateMapDueToFlip(testMap);

        Face_Enum expectedValForUP = Face_Enum.BACK;
        Face_Enum expectedValForBack = Face_Enum.DOWN;
        Face_Enum expectedValForDown = Face_Enum.FRONT;
        Face_Enum expectedValForFront = Face_Enum.UP;
        Face_Enum expectedValForLeft = Face_Enum.LEFT;
        Face_Enum expectedValForRight = Face_Enum.RIGHT;

        assertTrue(testMap.get(Face_Enum.UP) == expectedValForUP, String.format(msg, Face_Enum.UP, prevUP, testMap.get(Face_Enum.UP), expectedValForUP));
        assertTrue(testMap.get(Face_Enum.BACK) == expectedValForBack, String.format(msg, Face_Enum.BACK, prevBack, testMap.get(Face_Enum.BACK), expectedValForBack));
        assertTrue(testMap.get(Face_Enum.DOWN) == expectedValForDown, String.format(msg, Face_Enum.DOWN, prevDown, testMap.get(Face_Enum.DOWN), expectedValForDown));
        assertTrue(testMap.get(Face_Enum.FRONT) == expectedValForFront, String.format(msg, Face_Enum.FRONT, prevFront, testMap.get(Face_Enum.FRONT), expectedValForFront));
        assertTrue(testMap.get(Face_Enum.LEFT) == expectedValForLeft, String.format(msg, Face_Enum.LEFT, prevLeft, testMap.get(Face_Enum.LEFT), expectedValForLeft));
        assertTrue(testMap.get(Face_Enum.RIGHT) == expectedValForRight, String.format(msg, Face_Enum.RIGHT, prevRight, testMap.get(Face_Enum.RIGHT), expectedValForRight));
        // first flip end


        // second flip
        prevUP = testMap.get(Face_Enum.UP);
        prevBack = testMap.get(Face_Enum.BACK);
        prevDown = testMap.get(Face_Enum.DOWN);
        prevFront = testMap.get(Face_Enum.FRONT);
        prevLeft = testMap.get(Face_Enum.LEFT);
        prevRight = testMap.get(Face_Enum.RIGHT);

        RobotActionsTranslator.virtualUpdateMapDueToFlip(testMap);

        expectedValForUP = Face_Enum.DOWN;
        expectedValForBack = Face_Enum.FRONT;
        expectedValForDown = Face_Enum.UP;
        expectedValForFront = Face_Enum.BACK;
        expectedValForLeft = Face_Enum.LEFT;
        expectedValForRight = Face_Enum.RIGHT;

        assertTrue(testMap.get(Face_Enum.UP) == expectedValForUP, String.format(msg, Face_Enum.UP, prevUP, testMap.get(Face_Enum.UP), expectedValForUP));
        assertTrue(testMap.get(Face_Enum.BACK) == expectedValForBack, String.format(msg, Face_Enum.BACK, prevBack, testMap.get(Face_Enum.BACK), expectedValForBack));
        assertTrue(testMap.get(Face_Enum.DOWN) == expectedValForDown, String.format(msg, Face_Enum.DOWN, prevDown, testMap.get(Face_Enum.DOWN), expectedValForDown));
        assertTrue(testMap.get(Face_Enum.FRONT) == expectedValForFront, String.format(msg, Face_Enum.FRONT, prevFront, testMap.get(Face_Enum.FRONT), expectedValForFront));
        assertTrue(testMap.get(Face_Enum.LEFT) == expectedValForLeft, String.format(msg, Face_Enum.LEFT, prevLeft, testMap.get(Face_Enum.LEFT), expectedValForLeft));
        assertTrue(testMap.get(Face_Enum.RIGHT) == expectedValForRight, String.format(msg, Face_Enum.RIGHT, prevRight, testMap.get(Face_Enum.RIGHT), expectedValForRight));
        // second flip end

    }
    @org.junit.jupiter.api.Test
    void updateMapDueToFlip(){
        String msg = "updateMapDueToFlip() failed when before flip the key: %s has value: %s, and after" +
                " the flip the value is: %s instead of %s";

        // init first test map
        Map<Face_Enum, Face_Enum> initialPhysicalMap = new HashMap<>();

        initialPhysicalMap.put(Face_Enum.UP, Face_Enum.UP);
        initialPhysicalMap.put(Face_Enum.BACK, Face_Enum.BACK);
        initialPhysicalMap.put(Face_Enum.DOWN, Face_Enum.DOWN);
        initialPhysicalMap.put(Face_Enum.RIGHT, Face_Enum.RIGHT);
        initialPhysicalMap.put(Face_Enum.LEFT, Face_Enum.LEFT);
        initialPhysicalMap.put(Face_Enum.FRONT, Face_Enum.FRONT);
        // init test map end


        // first flip
        Face_Enum prevUP = Face_Enum.UP;
        Face_Enum prevBack = Face_Enum.BACK;
        Face_Enum prevDown = Face_Enum.DOWN;
        Face_Enum prevFront = Face_Enum.FRONT;
        Face_Enum prevLeft = Face_Enum.LEFT;
        Face_Enum prevRight = Face_Enum.RIGHT;

        RobotActionsTranslator.updateMapDueToFlip(initialPhysicalMap);

        Face_Enum expectedValForUP = Face_Enum.FRONT;
        Face_Enum expectedValForBack = Face_Enum.UP;
        Face_Enum expectedValForDown = Face_Enum.BACK;
        Face_Enum expectedValForFront = Face_Enum.DOWN;
        Face_Enum expectedValForLeft = Face_Enum.LEFT;
        Face_Enum expectedValForRight = Face_Enum.RIGHT;

        assertTrue(initialPhysicalMap.get(Face_Enum.UP) == expectedValForUP, String.format(msg, Face_Enum.UP, prevUP, initialPhysicalMap.get(Face_Enum.UP), expectedValForUP));
        assertTrue(initialPhysicalMap.get(Face_Enum.BACK) == expectedValForBack, String.format(msg, Face_Enum.BACK, prevBack, initialPhysicalMap.get(Face_Enum.BACK), expectedValForBack));
        assertTrue(initialPhysicalMap.get(Face_Enum.DOWN) == expectedValForDown, String.format(msg, Face_Enum.DOWN, prevDown, initialPhysicalMap.get(Face_Enum.DOWN), expectedValForDown));
        assertTrue(initialPhysicalMap.get(Face_Enum.FRONT) == expectedValForFront, String.format(msg, Face_Enum.FRONT, prevFront, initialPhysicalMap.get(Face_Enum.FRONT), expectedValForFront));
        assertTrue(initialPhysicalMap.get(Face_Enum.LEFT) == expectedValForLeft, String.format(msg, Face_Enum.LEFT, prevLeft, initialPhysicalMap.get(Face_Enum.LEFT), expectedValForLeft));
        assertTrue(initialPhysicalMap.get(Face_Enum.RIGHT) == expectedValForRight, String.format(msg, Face_Enum.RIGHT, prevRight, initialPhysicalMap.get(Face_Enum.RIGHT), expectedValForRight));
        // first flip end

    }


}