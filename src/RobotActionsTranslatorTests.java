import java.util.ArrayList;
import java.util.List;

public class RobotActionsTranslatorTests {
    public static void main(String[] args) {
        List<cmd> algorithmCommands = new ArrayList<>();

        List<RobotActionsTranslator.RobotSolvingAction> robotCommands;
//        algorithmCommands.add(cmd.CMD_FRONT_TWIST_CLOCKWISE);
//        algorithmCommands.add(cmd.CMD_FRONT_TWIST_CLOCKWISE);
//        algorithmCommands.add(cmd.CMD_RIGHT_TWIST_BACKUPWARD);
//        algorithmCommands.add(cmd.CMD_DOWN_TWIST_RIGHT);

        algorithmCommands.add(cmd.CMD_DOWN_TWIST_RIGHT);
        algorithmCommands.add(cmd.CMD_UP_TWIST_LEFT);
        algorithmCommands.add(cmd.CMD_LEFT_TWIST_BACKUPWARD);
        algorithmCommands.add(cmd.CMD_RIGHT_TWIST_FRONTUPWARD);

        robotCommands = RobotActionsTranslator.translateCommandList(algorithmCommands);
        System.out.println(robotCommands);
//        int x = 9;

    }
}
