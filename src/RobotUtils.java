import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotUtils {

    /**
     *
     */
    public enum RobotSolvingAction {
        ROBOT_FLIP,
        ROBOT_RIGHT_R,
        ROBOT_LEFT_R
    }

    /**
     *
     */
    public List<RobotSolvingAction> translateCommandList(List<cmd> initialCommands, List<cmd> algorithmCommands){
        List<RobotSolvingAction> result = new ArrayList<>();

        for (cmd command : initialCommands){
            switch (command){
                case CMD_FLIP:
                    result.add(RobotSolvingAction.ROBOT_FLIP);
                    break;
                case CMD_RIGHT_ROTATE:
                    result.add(RobotSolvingAction.ROBOT_RIGHT_R);
                    break;
                case CMD_LEFT_ROTATE:
                    result.add(RobotSolvingAction.ROBOT_LEFT_R);
                    break;
                default:
                    break;
            }
        }

        // currentPosition - key : face to twist (from algorithm), value : its current position
//        Map<Cube.Face,Cube.Face> currentPosition = new HashMap<>();

//        currentPosition.put()
        return result;

    }


}
