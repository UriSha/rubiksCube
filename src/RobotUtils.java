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
        ROBOT_RIGHT_ROTATE,
        ROBOT_LEFT_ROTATE,
        ROBOT_RIGHT_TWIST,
        ROBOT_LEFT_TWIST
    }

    /**
     *
     */
    public static List<RobotSolvingAction> translateCommandList(List<cmd> algorithmCommands){
        List<RobotSolvingAction> result = new ArrayList<>();

        // currentPositionMap - key : face to twist (from algorithm), value : its current position
        Map<Face_Enum,Face_Enum> currentPositionMap = new HashMap<>();

        // initialize values map
        currentPositionMap.put(Face_Enum.UP,Face_Enum.UP);
        currentPositionMap.put(Face_Enum.LEFT,Face_Enum.LEFT);
        currentPositionMap.put(Face_Enum.FRONT,Face_Enum.FRONT);
        currentPositionMap.put(Face_Enum.BACK,Face_Enum.BACK);
        currentPositionMap.put(Face_Enum.RIGHT,Face_Enum.RIGHT);
        currentPositionMap.put(Face_Enum.DOWN,Face_Enum.DOWN);

        for (cmd command : algorithmCommands)
            fromAlgoCommandToRobotAction(command,result,currentPositionMap);

        return result;

    }

    private static void fromAlgoCommandToRobotAction(cmd command, List<RobotSolvingAction> result, Map<Face_Enum,Face_Enum> currentPositionMap ){
        Face_Enum upLocation;
        Face_Enum leftLocation;
        Face_Enum frontLocation;
        Face_Enum backLocation;
        Face_Enum rightLocation;
        Face_Enum downLocation;

        boolean isRight;

        switch (command){
            case CMD_FLIP:
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(currentPositionMap);
                break;
            case CMD_RIGHT_ROTATE:
                result.add(RobotSolvingAction.ROBOT_RIGHT_ROTATE);
                updateMapDueToRightRotate(currentPositionMap);
                break;
            case CMD_LEFT_ROTATE:
                result.add(RobotSolvingAction.ROBOT_LEFT_ROTATE);
                updateMapDueToLeftRotate(currentPositionMap);
                break;
            case CMD_FRONT_TWIST_CLOCKWISE:
                frontLocation = currentPositionMap.get(Face_Enum.FRONT);
                isRight = getBoolForDirection(Face_Enum.FRONT, frontLocation,true);
                result.addAll(getRobotActionTwist(frontLocation,isRight,currentPositionMap));
                break;
            case CMD_FRONT_TWIST_C_CLOCKWISE:
                frontLocation = currentPositionMap.get(Face_Enum.FRONT);
                isRight = getBoolForDirection(Face_Enum.FRONT, frontLocation,false);
                result.addAll(getRobotActionTwist(frontLocation,isRight,currentPositionMap));
                break;
            case CMD_RIGHT_TWIST_FRONTUPWARD:
                rightLocation = currentPositionMap.get(Face_Enum.RIGHT);
                isRight = getBoolForDirection(Face_Enum.RIGHT, rightLocation,true);
                result.addAll(getRobotActionTwist(rightLocation,isRight,currentPositionMap));
                break;
            case CMD_RIGHT_TWIST_BACKUPWARD:
                rightLocation = currentPositionMap.get(Face_Enum.RIGHT);
                isRight = getBoolForDirection(Face_Enum.RIGHT, rightLocation,false);
                result.addAll(getRobotActionTwist(rightLocation,isRight,currentPositionMap));
                break;
            case CMD_LEFT_TWIST_FRONTUPWARD:
                leftLocation = currentPositionMap.get(Face_Enum.LEFT);
                isRight = getBoolForDirection(Face_Enum.LEFT, leftLocation,true);
                result.addAll(getRobotActionTwist(leftLocation,isRight,currentPositionMap));
                break;
            case CMD_LEFT_TWIST_BACKUPWARD:
                leftLocation = currentPositionMap.get(Face_Enum.LEFT);
                isRight = getBoolForDirection(Face_Enum.LEFT, leftLocation,false);
                result.addAll(getRobotActionTwist(leftLocation,isRight,currentPositionMap));
                break;
            case CMD_UP_TWIST_RIGHT:
                upLocation = currentPositionMap.get(Face_Enum.UP);
                isRight = getBoolForDirection(Face_Enum.UP, upLocation,true);
                result.addAll(getRobotActionTwist(upLocation,isRight,currentPositionMap));
                break;
            case CMD_UP_TWIST_LEFT:
                upLocation = currentPositionMap.get(Face_Enum.UP);
                isRight = getBoolForDirection(Face_Enum.UP, upLocation,false);
                result.addAll(getRobotActionTwist(upLocation,isRight,currentPositionMap));
                break;
            case CMD_DOWN_TWIST_RIGHT:
                downLocation = currentPositionMap.get(Face_Enum.DOWN);
                isRight = getBoolForDirection(Face_Enum.DOWN, downLocation,true);
                result.addAll(getRobotActionTwist(downLocation,isRight,currentPositionMap));
                break;
            case CMD_DOWN_TWIST_LEFT:
                downLocation = currentPositionMap.get(Face_Enum.DOWN);
                isRight = getBoolForDirection(Face_Enum.DOWN, downLocation,false);
                result.addAll(getRobotActionTwist(downLocation,isRight,currentPositionMap));
                break;
            case CMD_BACK_TWIST_CLOCKWISE:
                backLocation = currentPositionMap.get(Face_Enum.BACK);
                isRight = getBoolForDirection(Face_Enum.BACK, backLocation,true);
                result.addAll(getRobotActionTwist(backLocation,isRight,currentPositionMap));
                break;
            case CMD_BACK_TWIST_C_CLOCKWISE:
                backLocation = currentPositionMap.get(Face_Enum.BACK);
                isRight = getBoolForDirection(Face_Enum.BACK, backLocation,false);
                result.addAll(getRobotActionTwist(backLocation,isRight,currentPositionMap));
                break;


            default:
                break;
        }

    }



    /**
     * also updates the faces locations map
     * @param face
     * @param direction
     * @param currentPositionMap
     * @return
     */
    private static List<RobotSolvingAction> getRobotActionTwist(Face_Enum face, boolean direction,Map<Face_Enum,Face_Enum> currentPositionMap){
        List<RobotSolvingAction> result = new ArrayList<>();
        switch (face){
            case UP:
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(currentPositionMap);
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(currentPositionMap);
                break;
            case LEFT:
                result.add(RobotSolvingAction.ROBOT_RIGHT_ROTATE);
                updateMapDueToRightRotate(currentPositionMap);
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(currentPositionMap);
                break;
            case FRONT:
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(currentPositionMap);
                break;
            case BACK:
                result.add(RobotSolvingAction.ROBOT_RIGHT_ROTATE);
                updateMapDueToRightRotate(currentPositionMap);
                result.add(RobotSolvingAction.ROBOT_RIGHT_ROTATE);
                updateMapDueToRightRotate(currentPositionMap);
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(currentPositionMap);
                break;
            case RIGHT:
                result.add(RobotSolvingAction.ROBOT_LEFT_ROTATE);
                updateMapDueToLeftRotate(currentPositionMap);
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(currentPositionMap);
                break;
            case DOWN:
                break;
            default:
                break;
        }
        if (direction)
            result.add(RobotSolvingAction.ROBOT_RIGHT_TWIST);
        else
            result.add(RobotSolvingAction.ROBOT_LEFT_TWIST);
        return result;
    }

    private static void updateMapDueToFlip(Map<Face_Enum,Face_Enum> currentPositionMap){

        Face_Enum faceCurrentlyInFront = getfaceInPosition(currentPositionMap,Face_Enum.FRONT);
        Face_Enum faceCurrentlyInUp = getfaceInPosition(currentPositionMap,Face_Enum.UP);
        Face_Enum faceCurrentlyInBack = getfaceInPosition(currentPositionMap,Face_Enum.BACK);
        Face_Enum faceCurrentlyInDown = getfaceInPosition(currentPositionMap,Face_Enum.DOWN);


        currentPositionMap.put(faceCurrentlyInFront, Face_Enum.DOWN);
        currentPositionMap.put(faceCurrentlyInUp, Face_Enum.FRONT);
        currentPositionMap.put(faceCurrentlyInBack, Face_Enum.UP);
        currentPositionMap.put(faceCurrentlyInDown, Face_Enum.BACK);
    }



    private static void updateMapDueToRightRotate(Map<Face_Enum,Face_Enum> currentPositionMap){

        Face_Enum faceCurrentlyInFront = getfaceInPosition(currentPositionMap,Face_Enum.FRONT);
        Face_Enum faceCurrentlyInLeft = getfaceInPosition(currentPositionMap,Face_Enum.LEFT);
        Face_Enum faceCurrentlyInBack = getfaceInPosition(currentPositionMap,Face_Enum.BACK);
        Face_Enum faceCurrentlyInRight = getfaceInPosition(currentPositionMap,Face_Enum.RIGHT);

        currentPositionMap.put(faceCurrentlyInFront, Face_Enum.RIGHT);
        currentPositionMap.put(faceCurrentlyInLeft, Face_Enum.FRONT);
        currentPositionMap.put(faceCurrentlyInBack, Face_Enum.LEFT);
        currentPositionMap.put(faceCurrentlyInRight, Face_Enum.BACK);
    }

    private static void updateMapDueToLeftRotate(Map<Face_Enum,Face_Enum> currentPositionMap){

        Face_Enum faceCurrentlyInFront = getfaceInPosition(currentPositionMap,Face_Enum.FRONT);
        Face_Enum faceCurrentlyInRight = getfaceInPosition(currentPositionMap,Face_Enum.RIGHT);
        Face_Enum faceCurrentlyInBack = getfaceInPosition(currentPositionMap,Face_Enum.BACK);
        Face_Enum faceCurrentlyInLeft = getfaceInPosition(currentPositionMap,Face_Enum.LEFT);


        currentPositionMap.put(faceCurrentlyInFront, Face_Enum.LEFT);
        currentPositionMap.put(faceCurrentlyInLeft, Face_Enum.BACK);
        currentPositionMap.put(faceCurrentlyInBack, Face_Enum.RIGHT);
        currentPositionMap.put(faceCurrentlyInRight, Face_Enum.FRONT);

    }

    private static Face_Enum getfaceInPosition(Map<Face_Enum,Face_Enum> currentPositionMap, Face_Enum face){
        if (currentPositionMap.get(Face_Enum.FRONT) == face)
            return Face_Enum.FRONT;
        if (currentPositionMap.get(Face_Enum.UP) == face)
            return Face_Enum.UP;
        if (currentPositionMap.get(Face_Enum.LEFT) == face)
            return Face_Enum.LEFT;
        if (currentPositionMap.get(Face_Enum.RIGHT) == face)
            return Face_Enum.RIGHT;
        if (currentPositionMap.get(Face_Enum.DOWN) == face)
            return Face_Enum.DOWN;
        if (currentPositionMap.get(Face_Enum.BACK) == face)
            return Face_Enum.BACK;

        return null;

    }

    private static boolean getBoolForDirection(Face_Enum initial, Face_Enum current, boolean algorithmBool){

        // the original Face (i.e was right and right now is up)
//        if (initial.getValue() == current.getValue())
//            return algorithmBool;
//
//        // they are opposite (i.e was initially UP and now is in DOWN)
//        if (initial.getValue() + current.getValue() == 7)
//            return !algorithmBool;

        boolean result = getBoolOtherFaceIsDown(current, algorithmBool);
        return getBoolOtherFaceIsDown(initial,result);



    }

    private static boolean getBoolOtherFaceIsDown(Face_Enum initial, boolean algorithmBool){
        if (initial == Face_Enum.BACK || initial == Face_Enum.LEFT)
            return !algorithmBool;
        if (initial == Face_Enum.FRONT || initial == Face_Enum.RIGHT)
            return algorithmBool;

        return initial == Face_Enum.DOWN ? algorithmBool : !algorithmBool;
    }




}
