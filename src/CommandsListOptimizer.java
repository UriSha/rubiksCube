import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CommandsListOptimizer {

    protected void optimizeList(ArrayList<cmd> actions){
        Map<cmd, cmd> contrastCMDs = new HashMap<>();
        contrastCMDs.put(cmd.CMD_LEFT_ROTATE, cmd.CMD_RIGHT_ROTATE);
        contrastCMDs.put(cmd.CMD_RIGHT_ROTATE, cmd.CMD_LEFT_ROTATE);
        contrastCMDs.put(cmd.CMD_UP_TWIST_LEFT, cmd.CMD_UP_TWIST_RIGHT);
        contrastCMDs.put(cmd.CMD_UP_TWIST_RIGHT, cmd.CMD_UP_TWIST_LEFT);
        contrastCMDs.put(cmd.CMD_LEFT_TWIST_FRONTUPWARD, cmd.CMD_LEFT_TWIST_BACKUPWARD);
        contrastCMDs.put(cmd.CMD_LEFT_TWIST_BACKUPWARD, cmd.CMD_LEFT_TWIST_FRONTUPWARD);
        contrastCMDs.put(cmd.CMD_DOWN_TWIST_RIGHT, cmd.CMD_DOWN_TWIST_LEFT);
        contrastCMDs.put(cmd.CMD_DOWN_TWIST_LEFT, cmd.CMD_DOWN_TWIST_RIGHT);
        contrastCMDs.put(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, cmd.CMD_RIGHT_TWIST_BACKUPWARD);
        contrastCMDs.put(cmd.CMD_RIGHT_TWIST_BACKUPWARD, cmd.CMD_RIGHT_TWIST_FRONTUPWARD);
        contrastCMDs.put(cmd.CMD_FRONT_TWIST_CLOCKWISE, cmd.CMD_FRONT_TWIST_C_CLOCKWISE);
        contrastCMDs.put(cmd.CMD_FRONT_TWIST_C_CLOCKWISE, cmd.CMD_FRONT_TWIST_CLOCKWISE);
        contrastCMDs.put(cmd.CMD_BACK_TWIST_CLOCKWISE, cmd.CMD_BACK_TWIST_C_CLOCKWISE);
        contrastCMDs.put(cmd.CMD_BACK_TWIST_C_CLOCKWISE, cmd.CMD_BACK_TWIST_CLOCKWISE);

        for (int i = actions.size() - 2; i >= 0; i--){
            try{
                if (actions.get(i) == actions.get(i+1) && actions.get(i) == actions.get(i+2)){
                    actions.set(i, contrastCMDs.get(actions.get(i)));
                    actions.remove(i+2);
                    actions.remove(i+1);
                } else if (actions.get(i+1) == contrastCMDs.get(actions.get(i))){
                    actions.remove(i+1);
                    actions.remove(i);
                }
            } catch (ArrayIndexOutOfBoundsException e){
                break;
            }
        }
    }
}