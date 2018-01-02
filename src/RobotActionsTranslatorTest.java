import static org.junit.jupiter.api.Assertions.*;

class RobotActionsTranslatorTest {
    @org.junit.jupiter.api.Test
    void translateCommandList() {
    }

    @org.junit.jupiter.api.Test
    void getBoolForDirection() {
        String msg = "translator failed when initial face is %s, current face is %s, and boolean is %s";
        // test translator if the initial and current face are the same (i.e need to translate initial to DOWN)
        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.UP, false),String.format(msg,"UP","UP","false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.UP, true),String.format(msg,"UP","UP","true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.DOWN, true),String.format(msg,"DOWN","DOWN","true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.DOWN, false),String.format(msg,"DOWN","DOWN","false"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.LEFT, false),String.format(msg,"LEFT","LEFT","false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.LEFT, true),String.format(msg,"LEFT","LEFT","true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.RIGHT, true),String.format(msg,"RIGHT","RIGHT","true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.RIGHT, false),String.format(msg,"RIGHT","RIGHT","false"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.BACK, false),String.format(msg,"BACK","BACK","false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.BACK, true),String.format(msg,"BACK","BACK","true"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.FRONT, true),String.format(msg,"FRONT","FRONT","true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.FRONT, false),String.format(msg,"FRONT","FRONT","false"));


        // test translator if the current face is DOWN the same
        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.DOWN, false),String.format(msg,"UP","DOWN","false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.DOWN, true),String.format(msg,"UP","DOWN","true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.DOWN, false),String.format(msg,"LEFT","DOWN","false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.DOWN, true),String.format(msg,"LEFT","DOWN","true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.DOWN, true),String.format(msg,"RIGHT","DOWN","true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.RIGHT, Face_Enum.DOWN, false),String.format(msg,"RIGHT","DOWN","false"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.DOWN, false),String.format(msg,"BACK","DOWN","false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.DOWN, true),String.format(msg,"BACK","DOWN","true"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.DOWN, true),String.format(msg,"FRONT","DOWN","true"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.FRONT, Face_Enum.DOWN, false),String.format(msg,"FRONT","DOWN","false"));


        // test translator if the initial and current are opposite
        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.RIGHT, false),String.format(msg,"LEFT","RIGHT","false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.LEFT, Face_Enum.RIGHT, true),String.format(msg,"LEFT","RIGHT","true"));

        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.FRONT, false),String.format(msg,"BACK","FRONT","false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.BACK, Face_Enum.FRONT, true),String.format(msg,"BACK","FRONT","true"));


        assertTrue(RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.UP, false),String.format(msg,"DOWN","UP","false"));
        assertFalse(RobotActionsTranslator.getBoolForDirection(Face_Enum.DOWN, Face_Enum.UP, true),String.format(msg,"DOWN","UP","true"));

    }

}