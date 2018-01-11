import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RobotActionsTranslatorTest {

    @Test
    public void translateCommandsWithoutAlgoMovingPointOfView() {
        String[] wrongActionMsg = new String[]{"translateCommandsWithoutAlgoMovingPointOfView() failed. the ", "th (zero-based) action was expected to be ",
                ", but was "};
        String[] differentSizesMsg = new String[]{"translateCommandsWithoutAlgoMovingPointOfView() failed. the translator returned a list in size of ", ", when it was supposed to be in size of "};
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

        compareActions(expectedRobotActions, robotCommandsFromAlgo, differentSizesMsg, wrongActionMsg);

    }

    @Test
    public void translateCommandsWithAlgoMovingPointOfView() {
        String[] wrongActionMsg = new String[]{"translateCommandsWithAlgoMovingPointOfView() failed. the ", "th (zero-based) action was expected to be ", ", but was "};
        String[] differentSizesMsg = new String[]{"translateCommandsWithAlgoMovingPointOfView() failed. the translator returned a list in size of ", ", when it was supposed to be in size of "};

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

        compareActions(expectedRobotActions, robotCommandsFromAlgo, differentSizesMsg, wrongActionMsg);
    }


    private void compareActions(RobotActionsTranslator.RobotSolvingAction[] expectedRobotActions,
                                List<RobotActionsTranslator.RobotSolvingAction> robotCommandsFromAlgo,
                                String[] differentSizesMsg, String[] wrongActionMsg) {

        assertTrue(differentSizesMsg[0] + robotCommandsFromAlgo.size() + differentSizesMsg[1] + expectedRobotActions.length,
                robotCommandsFromAlgo.size() == expectedRobotActions.length);
        for (int i = 0; i < expectedRobotActions.length; i++) {
            assertTrue(wrongActionMsg[0] + i + wrongActionMsg[1] + expectedRobotActions[i] + wrongActionMsg[2] + robotCommandsFromAlgo.get(i),
                    expectedRobotActions[i] == robotCommandsFromAlgo.get(i));
        }
    }

    @Test
    public void getBoolForDirection() {
        String[] msg = new String[]{"getBoolForDirection() failed when initial face is ", ", current face is ", ", and boolean is "};
        // test translator if the initial and current face are the same (i.e need to translate initial to DOWN)
        assertTrue(msg[0] + "UP" + msg[1] + "UP" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.UP, false));
        assertFalse(msg[0] + "UP" + msg[1] + "UP" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.UP, true));

        assertTrue(msg[0] + "DOWN" + msg[1] + "DOWN" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.DOWN, true));
        assertFalse(msg[0] + "DOWN" + msg[1] + "DOWN" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.DOWN, false));

        assertTrue(msg[0] + "LEFT" + msg[1] + "LEFT" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.LEFT, false));
        assertFalse(msg[0] + "LEFT" + msg[1] + "LEFT" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.LEFT, true));

        assertTrue(msg[0] + "RIGHT" + msg[1] + "RIGHT" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.RIGHT, true));
        assertFalse(msg[0] + "RIGHT" + msg[1] + "RIGHT" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.RIGHT, false));


        assertTrue(msg[0] + "BACK" + msg[1] + "BACK" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.BACK, false));
        assertFalse(msg[0] + "BACK" + msg[1] + "BACK" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.BACK, true));


        assertTrue(msg[0] + "FRONT" + msg[1] + "FRONT" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.FRONT, true));
        assertFalse(msg[0] + "FRONT" + msg[1] + "FRONT" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.FRONT, false));


        // test translator if the current face is DOWN the same
        assertTrue(msg[0] + "UP" + msg[1] + "DOWN" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.DOWN, false));
        assertFalse(msg[0] + "UP" + msg[1] + "DOWN" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.DOWN, true));

        assertTrue(msg[0] + "LEFT" + msg[1] + "DOWN" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.DOWN, false));
        assertFalse(msg[0] + "LEFT" + msg[1] + "DOWN" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.DOWN, true));

        assertTrue(msg[0] + "RIGHT" + msg[1] + "DOWN" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.DOWN, true));
        assertFalse(msg[0] + "RIGHT" + msg[1] + "DOWN" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.DOWN, false));


        assertTrue(msg[0] + "BACK" + msg[1] + "DOWN" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.DOWN, false));
        assertFalse(msg[0] + "BACK" + msg[1] + "DOWN" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.DOWN, true));


        assertTrue(msg[0] + "FRONT" + msg[1] + "DOWN" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.DOWN, true));
        assertFalse(msg[0] + "FRONT" + msg[1] + "DOWN" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.DOWN, false));


        // test translator if the initial and current are opposite
        assertTrue(msg[0] + "LEFT" + msg[1] + "RIGHT" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.RIGHT, false));
        assertFalse(msg[0] + "LEFT" + msg[1] + "RIGHT" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.RIGHT, true));

        assertTrue(msg[0] + "BACK" + msg[1] + "FRONT" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.FRONT, false));
        assertFalse(msg[0] + "BACK" + msg[1] + "FRONT" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.FRONT, true));


        assertTrue(msg[0] + "DOWN" + msg[1] + "UP" + msg[2] + "false", RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.UP, false));
        assertFalse(msg[0] + "DOWN" + msg[1] + "UP" + msg[2] + "true", RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.UP, true));

    }

    @Test
    public void virtualUpdateMapDueToFlip() {
        String[] msg = new String[]{"virtualUpdateMapDueToFlip() failed when before virtual flip the key: ", " has value: ", ", and after" +
                " the flip the value is: ", " instead of "};

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

        assertTrue(msg[0] + Face_Enum.UP + msg[1] + prevUP + msg[2] + testMap.get(Face_Enum.UP) + msg[3] + expectedValForUP, testMap.get(Face_Enum.UP) == expectedValForUP);
        assertTrue(msg[0] + Face_Enum.BACK + msg[1] + prevBack + msg[2] + testMap.get(Face_Enum.BACK) + msg[3] + expectedValForBack, testMap.get(Face_Enum.BACK) == expectedValForBack);
        assertTrue(msg[0] + Face_Enum.DOWN + msg[1] + prevDown + msg[2] + testMap.get(Face_Enum.DOWN) + msg[3] + expectedValForDown, testMap.get(Face_Enum.DOWN) == expectedValForDown);
        assertTrue(msg[0] + Face_Enum.FRONT + msg[1] + prevFront + msg[2] + testMap.get(Face_Enum.FRONT) + msg[3] + expectedValForFront, testMap.get(Face_Enum.FRONT) == expectedValForFront);
        assertTrue(msg[0] + Face_Enum.LEFT + msg[1] + prevLeft + msg[2] + testMap.get(Face_Enum.LEFT) + msg[3] + expectedValForLeft, testMap.get(Face_Enum.LEFT) == expectedValForLeft);
        assertTrue(msg[0] + Face_Enum.RIGHT + msg[1] + prevRight + msg[2] + testMap.get(Face_Enum.RIGHT) + msg[3] + expectedValForRight, testMap.get(Face_Enum.RIGHT) == expectedValForRight);
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

        assertTrue(msg[0] + Face_Enum.UP + msg[1] + prevUP + msg[2] + testMap.get(Face_Enum.UP) + msg[3] + expectedValForUP, testMap.get(Face_Enum.UP) == expectedValForUP);
        assertTrue(msg[0] + Face_Enum.BACK + msg[1] + prevBack + msg[2] + testMap.get(Face_Enum.BACK) + msg[3] + expectedValForBack, testMap.get(Face_Enum.BACK) == expectedValForBack);
        assertTrue(msg[0] + Face_Enum.DOWN + msg[1] + prevDown + msg[2] + testMap.get(Face_Enum.DOWN) + msg[3] + expectedValForDown, testMap.get(Face_Enum.DOWN) == expectedValForDown);
        assertTrue(msg[0] + Face_Enum.FRONT + msg[1] + prevFront + msg[2] + testMap.get(Face_Enum.FRONT) + msg[3] + expectedValForFront, testMap.get(Face_Enum.FRONT) == expectedValForFront);
        assertTrue(msg[0] + Face_Enum.LEFT + msg[1] + prevLeft + msg[2] + testMap.get(Face_Enum.LEFT) + msg[3] + expectedValForLeft, testMap.get(Face_Enum.LEFT) == expectedValForLeft);
        assertTrue(msg[0] + Face_Enum.RIGHT + msg[1] + prevRight + msg[2] + testMap.get(Face_Enum.RIGHT) + msg[3] + expectedValForRight, testMap.get(Face_Enum.RIGHT) == expectedValForRight);
        // second flip end


    }

    @Test
    public void updateMapDueToFlip() {
        String[] msg = new String[]{"virtualUpdateMapDueToFlip() failed when before virtual flip the key: ", " has value: ", ", and after" +
                " the flip the value is: ", " instead of "};

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


        assertTrue(msg[0] + Face_Enum.UP + msg[1] + prevUP + msg[2] + initialPhysicalMap.get(Face_Enum.UP) + msg[3] + expectedValForUP, initialPhysicalMap.get(Face_Enum.UP) == expectedValForUP);
        assertTrue(msg[0] + Face_Enum.BACK + msg[1] + prevBack + msg[2] + initialPhysicalMap.get(Face_Enum.BACK) + msg[3] + expectedValForBack, initialPhysicalMap.get(Face_Enum.BACK) == expectedValForBack);
        assertTrue(msg[0] + Face_Enum.DOWN + msg[1] + prevDown + msg[2] + initialPhysicalMap.get(Face_Enum.DOWN) + msg[3] + expectedValForDown, initialPhysicalMap.get(Face_Enum.DOWN) == expectedValForDown);
        assertTrue(msg[0] + Face_Enum.FRONT + msg[1] + prevFront + msg[2] + initialPhysicalMap.get(Face_Enum.FRONT) + msg[3] + expectedValForFront, initialPhysicalMap.get(Face_Enum.FRONT) == expectedValForFront);
        assertTrue(msg[0] + Face_Enum.LEFT + msg[1] + prevLeft + msg[2] + initialPhysicalMap.get(Face_Enum.LEFT) + msg[3] + expectedValForLeft, initialPhysicalMap.get(Face_Enum.LEFT) == expectedValForLeft);
        assertTrue(msg[0] + Face_Enum.RIGHT + msg[1] + prevRight + msg[2] + initialPhysicalMap.get(Face_Enum.RIGHT) + msg[3] + expectedValForRight, initialPhysicalMap.get(Face_Enum.RIGHT) == expectedValForRight);

    }
}

