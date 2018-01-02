import java.util.ArrayList;
import java.util.List;


public class RobotActionsTranslatorTests {
    public static void main(String[] args) {
        translatorTest();
//        testGetBoolForDirection();
    }




    public static void translatorTest(){

        List<cmd> algorithmCommands = new ArrayList<>();

        List<RobotActionsTranslator.RobotSolvingAction> robotCommands;
        algorithmCommands.add(cmd.CMD_DOWN_TWIST_RIGHT);
        algorithmCommands.add(cmd.CMD_UP_TWIST_LEFT);
        algorithmCommands.add(cmd.CMD_LEFT_TWIST_BACKUPWARD);
        algorithmCommands.add(cmd.CMD_RIGHT_TWIST_FRONTUPWARD);

        robotCommands = RobotActionsTranslator.translateCommandList(algorithmCommands);
        System.out.println(robotCommands);
    }

    public static boolean testGetBoolForDirection() throws AssertionError{
//        System.out.println(RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.UP, false));
//        assert RobotActionsTranslator.getBoolForDirection(Face_Enum.UP, Face_Enum.UP, false): "hii";
//        assert false;
        return true;

    }



}
