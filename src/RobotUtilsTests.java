import java.util.ArrayList;
import java.util.List;

public class RobotUtilsTests {
    public static void main(String[] args) {
        List<cmd> algorithmCommands = new ArrayList<>();

        List<RobotUtils.RobotSolvingAction> robotCommands;
        algorithmCommands.add(cmd.CMD_FRONT_TWIST_CLOCKWISE);
        algorithmCommands.add(cmd.CMD_FRONT_TWIST_CLOCKWISE);
        algorithmCommands.add(cmd.CMD_RIGHT_TWIST_BACKUPWARD);
        algorithmCommands.add(cmd.CMD_DOWN_TWIST_RIGHT);

        robotCommands = RobotUtils.translateCommandList(algorithmCommands);
        System.out.println(robotCommands);
//        int x = 9;

    }
}
