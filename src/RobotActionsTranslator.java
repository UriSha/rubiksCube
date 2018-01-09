import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RobotActionsTranslator {

    /**
     * used in RobotUtils to determine which action the robot should do
     */
    public enum RobotSolvingAction {
        ROBOT_FLIP,
        ROBOT_RIGHT_ROTATE,
        ROBOT_LEFT_ROTATE,
        ROBOT_RIGHT_TWIST,
        ROBOT_LEFT_TWIST
    }

    /**
     * @param algorithmCommands - a list of commands received as the output of the algorithm to translate to robot actions
     * @return a list of action the robot should do in order to solve the cube
     */
    static List<RobotSolvingAction> translateCommandList(List<AlgorithmCommands> algorithmCommands) {
        List<RobotSolvingAction> result = new ArrayList<>();

        // initialPhysicalMap - key : face to twist (from algorithm), value : its current position
        Map<Face_Enum, Face_Enum> initialPhysicalMap = new HashMap<>();

        // initialize map values
        initialPhysicalMap.put(Face_Enum.UP, Face_Enum.UP);
        initialPhysicalMap.put(Face_Enum.LEFT, Face_Enum.LEFT);
        initialPhysicalMap.put(Face_Enum.FRONT, Face_Enum.FRONT);
        initialPhysicalMap.put(Face_Enum.BACK, Face_Enum.BACK);
        initialPhysicalMap.put(Face_Enum.RIGHT, Face_Enum.RIGHT);
        initialPhysicalMap.put(Face_Enum.DOWN, Face_Enum.DOWN);

        // virtualInitialMap - key :
        Map<Face_Enum, Face_Enum> virtualInitialMap = new HashMap<>();

        // initialize map values
        virtualInitialMap.put(Face_Enum.UP, Face_Enum.UP);
        virtualInitialMap.put(Face_Enum.LEFT, Face_Enum.LEFT);
        virtualInitialMap.put(Face_Enum.FRONT, Face_Enum.FRONT);
        virtualInitialMap.put(Face_Enum.BACK, Face_Enum.BACK);
        virtualInitialMap.put(Face_Enum.RIGHT, Face_Enum.RIGHT);
        virtualInitialMap.put(Face_Enum.DOWN, Face_Enum.DOWN);


        for (AlgorithmCommands command : algorithmCommands)
            fromAlgoCommandToRobotAction(command, result, initialPhysicalMap, virtualInitialMap);

        return result;

    }

    /**
     * translate an algorithm command to robot action using memory of the physical cube state (i.e initialPhysicalMap and virtualInitialMap)
     */
    private static void fromAlgoCommandToRobotAction(AlgorithmCommands command, List<RobotSolvingAction> result, Map<Face_Enum, Face_Enum> initialPhysicalMap, Map<Face_Enum, Face_Enum> virtualInitialMap) {
        Face_Enum upLocation;
        Face_Enum leftLocation;
        Face_Enum frontLocation;
        Face_Enum backLocation;
        Face_Enum rightLocation;
        Face_Enum downLocation;

        boolean isRight;

        switch (command) {
            case CMD_FLIP:
//                result.addAll(translateFlipToActualCMD(initialPhysicalMap, virtualCubeState));
                virtualUpdateMapDueToFlip(virtualInitialMap);

                break;
            case CMD_RIGHT_ROTATE:
//                result.addAll(translateRightRotateToActualCMD(initialPhysicalMap, virtualCubeState));
                virtualUpdateMapDueToRightRotate(virtualInitialMap);
                break;
            case CMD_LEFT_ROTATE:
//                result.addAll(translateLefttRotateToActualCMD(initialPhysicalMap, virtualCubeState));
                virtualUpdateMapDueToLeftRotate(virtualInitialMap);
                break;
            case CMD_FRONT_TWIST_CLOCKWISE:
                frontLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.FRONT));
                if (frontLocation == Face_Enum.FRONT)
                    isRight = true;
                else
                    isRight = getBoolForDirection(Face_Enum.FRONT, frontLocation, true);
                result.addAll(getRobotActionTwist(frontLocation, isRight, initialPhysicalMap));
                break;
            case CMD_FRONT_TWIST_C_CLOCKWISE:
                frontLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.FRONT));
                if (frontLocation == Face_Enum.FRONT)
                    isRight = false;
                else
                    isRight = getBoolForDirection(Face_Enum.FRONT, frontLocation, false);
                result.addAll(getRobotActionTwist(frontLocation, isRight, initialPhysicalMap));
                break;
            case CMD_RIGHT_TWIST_FRONTUPWARD:
                rightLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.RIGHT));
                if (rightLocation == Face_Enum.RIGHT)
                    isRight = true;
                else
                    isRight = getBoolForDirection(Face_Enum.RIGHT, rightLocation, true);
                result.addAll(getRobotActionTwist(rightLocation, isRight, initialPhysicalMap));
                break;
            case CMD_RIGHT_TWIST_BACKUPWARD:
                rightLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.RIGHT));
                if (rightLocation == Face_Enum.RIGHT)
                    isRight = false;
                else
                    isRight = getBoolForDirection(Face_Enum.RIGHT, rightLocation, false);
                result.addAll(getRobotActionTwist(rightLocation, isRight, initialPhysicalMap));
                break;
            case CMD_LEFT_TWIST_FRONTUPWARD:
                leftLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.LEFT));
                if (leftLocation == Face_Enum.LEFT)
                    isRight = true;
                else
                    isRight = getBoolForDirection(Face_Enum.LEFT, leftLocation, true);
                result.addAll(getRobotActionTwist(leftLocation, isRight, initialPhysicalMap));
                break;
            case CMD_LEFT_TWIST_BACKUPWARD:
                leftLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.LEFT));
                if (leftLocation == Face_Enum.LEFT)
                    isRight = false;
                else
                    isRight = getBoolForDirection(Face_Enum.LEFT, leftLocation, false);
                result.addAll(getRobotActionTwist(leftLocation, isRight, initialPhysicalMap));
                break;
            case CMD_UP_TWIST_RIGHT:
                upLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.UP));
                if (upLocation == Face_Enum.UP)
                    isRight = true;
                else
                    isRight = getBoolForDirection(Face_Enum.UP, upLocation, true);
                result.addAll(getRobotActionTwist(upLocation, isRight, initialPhysicalMap));
                break;
            case CMD_UP_TWIST_LEFT:
                upLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.UP));
                if (upLocation == Face_Enum.UP)
                    isRight = false;
                else
                    isRight = getBoolForDirection(Face_Enum.UP, upLocation, false);
                result.addAll(getRobotActionTwist(upLocation, isRight, initialPhysicalMap));
                break;
            case CMD_DOWN_TWIST_RIGHT:
                downLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.DOWN));
                if (downLocation == Face_Enum.DOWN)
                    isRight = true;
                else
                    isRight = getBoolForDirection(Face_Enum.DOWN, downLocation, true);
                result.addAll(getRobotActionTwist(downLocation, isRight, initialPhysicalMap));
                break;
            case CMD_DOWN_TWIST_LEFT:
                downLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.DOWN));
                if (downLocation == Face_Enum.DOWN)
                    isRight = false;
                else
                    isRight = getBoolForDirection(Face_Enum.DOWN, downLocation, false);
                result.addAll(getRobotActionTwist(downLocation, isRight, initialPhysicalMap));
                break;
            case CMD_BACK_TWIST_CLOCKWISE:
                backLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.BACK));
                if (backLocation == Face_Enum.BACK)
                    isRight = true;
                else
                    isRight = getBoolForDirection(Face_Enum.BACK, backLocation, true);
                result.addAll(getRobotActionTwist(backLocation, isRight, initialPhysicalMap));
                break;
            case CMD_BACK_TWIST_C_CLOCKWISE:
                backLocation = initialPhysicalMap.get(virtualInitialMap.get(Face_Enum.BACK));
                if (backLocation == Face_Enum.BACK)
                    isRight = false;
                else
                    isRight = getBoolForDirection(Face_Enum.BACK, backLocation, false);
                result.addAll(getRobotActionTwist(backLocation, isRight, initialPhysicalMap));
                break;


            default:
                break;
        }

    }


    /**
     * helper for fromAlgoCommandToRobotAction()
     * adds the robot actions when the next algorithm action is twist of a face
     *
     */
    private static List<RobotSolvingAction> getRobotActionTwist(Face_Enum face, boolean direction, Map<Face_Enum, Face_Enum> initialPhysicalMap) {
        List<RobotSolvingAction> result = new ArrayList<>();
        switch (face) {
            case UP:
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(initialPhysicalMap);
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(initialPhysicalMap);

                break;
            case LEFT:
                result.add(RobotSolvingAction.ROBOT_RIGHT_ROTATE);
                updateMapDueToRightRotate(initialPhysicalMap);
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(initialPhysicalMap);
                break;
            case FRONT:
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(initialPhysicalMap);
                break;
            case BACK:
                result.add(RobotSolvingAction.ROBOT_RIGHT_ROTATE);
                updateMapDueToRightRotate(initialPhysicalMap);
                result.add(RobotSolvingAction.ROBOT_RIGHT_ROTATE);
                updateMapDueToRightRotate(initialPhysicalMap);
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(initialPhysicalMap);
                break;
            case RIGHT:
                result.add(RobotSolvingAction.ROBOT_LEFT_ROTATE);
                updateMapDueToLeftRotate(initialPhysicalMap);
                result.add(RobotSolvingAction.ROBOT_FLIP);
                updateMapDueToFlip(initialPhysicalMap);
                break;
            case DOWN:
                break;
            default:
                break;
        }
        if (getBoolOtherFaceIsDown(face, direction))
            result.add(RobotSolvingAction.ROBOT_RIGHT_TWIST);
        else
            result.add(RobotSolvingAction.ROBOT_LEFT_TWIST);
        return result;
    }

    /**
     * updates the mapping between the virtual face (i.e the current view point of the algorithm) to the initial faces due to virtual flip
     * for example before any virtual (algirothm) actions the map is {up:up, back:back, down:down, front:front, left:left, right:right}
     * and after the first flip {up:back, back:down, down:front, front:up, left:left, right:right}
     */
    static void virtualUpdateMapDueToFlip(Map<Face_Enum, Face_Enum> virtualInitialMap) {
        Face_Enum temp = virtualInitialMap.get(Face_Enum.UP);

        virtualInitialMap.put(Face_Enum.UP, virtualInitialMap.get(Face_Enum.BACK));
        virtualInitialMap.put(Face_Enum.BACK, virtualInitialMap.get(Face_Enum.DOWN));
        virtualInitialMap.put(Face_Enum.DOWN, virtualInitialMap.get(Face_Enum.FRONT));
        virtualInitialMap.put(Face_Enum.FRONT, temp);
    }
    /**
     * updates the mapping between the virtual face (i.e the current view point of the algorithm) to the initial faces due to virtual left rotate
     */
    private static void virtualUpdateMapDueToLeftRotate(Map<Face_Enum, Face_Enum> virtualInitialMap) {
        Face_Enum temp = virtualInitialMap.get(Face_Enum.FRONT);

        virtualInitialMap.put(Face_Enum.FRONT, virtualInitialMap.get(Face_Enum.RIGHT));
        virtualInitialMap.put(Face_Enum.RIGHT, virtualInitialMap.get(Face_Enum.BACK));
        virtualInitialMap.put(Face_Enum.BACK, virtualInitialMap.get(Face_Enum.LEFT));
        virtualInitialMap.put(Face_Enum.LEFT, temp);
    }
    /**
     * updates the mapping between the virtual face (i.e the current view point of the algorithm) to the initial faces due to virtual right rotate
     */
    private static void virtualUpdateMapDueToRightRotate(Map<Face_Enum, Face_Enum> virtualInitialMap) {
        Face_Enum temp = virtualInitialMap.get(Face_Enum.FRONT);

        virtualInitialMap.put(Face_Enum.FRONT, virtualInitialMap.get(Face_Enum.LEFT));
        virtualInitialMap.put(Face_Enum.LEFT, virtualInitialMap.get(Face_Enum.BACK));
        virtualInitialMap.put(Face_Enum.BACK, virtualInitialMap.get(Face_Enum.RIGHT));
        virtualInitialMap.put(Face_Enum.RIGHT, temp);
    }
    /**
     * updates the mapping between the initial face to the physical faces due to physical flip
     * for example before any physical actions the map is {up:up, back:back, down:down, front:front, left:left, right:right}
     * and after the first robot flip {up:down, back:up, down:back, front:down, left:left, right:right}
     */
    static void updateMapDueToFlip(Map<Face_Enum, Face_Enum> initialPhysicalMap) {

        Face_Enum faceCurrentlyInFront = getfaceInPosition(initialPhysicalMap, Face_Enum.FRONT);
        Face_Enum faceCurrentlyInUp = getfaceInPosition(initialPhysicalMap, Face_Enum.UP);
        Face_Enum faceCurrentlyInBack = getfaceInPosition(initialPhysicalMap, Face_Enum.BACK);
        Face_Enum faceCurrentlyInDown = getfaceInPosition(initialPhysicalMap, Face_Enum.DOWN);


        initialPhysicalMap.put(faceCurrentlyInFront, Face_Enum.DOWN);
        initialPhysicalMap.put(faceCurrentlyInUp, Face_Enum.FRONT);
        initialPhysicalMap.put(faceCurrentlyInBack, Face_Enum.UP);
        initialPhysicalMap.put(faceCurrentlyInDown, Face_Enum.BACK);
    }

    /**
     * updates the mapping between the initial face to the physical faces due to physical right rotate
     */
    private static void updateMapDueToRightRotate(Map<Face_Enum, Face_Enum> initialPhysicalMap) {

        Face_Enum faceCurrentlyInFront = getfaceInPosition(initialPhysicalMap, Face_Enum.FRONT);
        Face_Enum faceCurrentlyInLeft = getfaceInPosition(initialPhysicalMap, Face_Enum.LEFT);
        Face_Enum faceCurrentlyInBack = getfaceInPosition(initialPhysicalMap, Face_Enum.BACK);
        Face_Enum faceCurrentlyInRight = getfaceInPosition(initialPhysicalMap, Face_Enum.RIGHT);

        initialPhysicalMap.put(faceCurrentlyInFront, Face_Enum.RIGHT);
        initialPhysicalMap.put(faceCurrentlyInLeft, Face_Enum.FRONT);
        initialPhysicalMap.put(faceCurrentlyInBack, Face_Enum.LEFT);
        initialPhysicalMap.put(faceCurrentlyInRight, Face_Enum.BACK);
    }
    /**
     * updates the mapping between the initial face to the physical faces due to physical left rotate
     */
    private static void updateMapDueToLeftRotate(Map<Face_Enum, Face_Enum> initialPhysicalMap) {

        Face_Enum faceCurrentlyInFront = getfaceInPosition(initialPhysicalMap, Face_Enum.FRONT);
        Face_Enum faceCurrentlyInRight = getfaceInPosition(initialPhysicalMap, Face_Enum.RIGHT);
        Face_Enum faceCurrentlyInBack = getfaceInPosition(initialPhysicalMap, Face_Enum.BACK);
        Face_Enum faceCurrentlyInLeft = getfaceInPosition(initialPhysicalMap, Face_Enum.LEFT);


        initialPhysicalMap.put(faceCurrentlyInFront, Face_Enum.LEFT);
        initialPhysicalMap.put(faceCurrentlyInLeft, Face_Enum.BACK);
        initialPhysicalMap.put(faceCurrentlyInBack, Face_Enum.RIGHT);
        initialPhysicalMap.put(faceCurrentlyInRight, Face_Enum.FRONT);

    }

    /**
     * returns the Face_Enum that is the key that has the value of face in initialPhysicalMap
     */
    private static Face_Enum getfaceInPosition(Map<Face_Enum, Face_Enum> initialPhysicalMap, Face_Enum face) {
        if (initialPhysicalMap.get(Face_Enum.FRONT) == face)
            return Face_Enum.FRONT;
        if (initialPhysicalMap.get(Face_Enum.UP) == face)
            return Face_Enum.UP;
        if (initialPhysicalMap.get(Face_Enum.LEFT) == face)
            return Face_Enum.LEFT;
        if (initialPhysicalMap.get(Face_Enum.RIGHT) == face)
            return Face_Enum.RIGHT;
        if (initialPhysicalMap.get(Face_Enum.DOWN) == face)
            return Face_Enum.DOWN;
        if (initialPhysicalMap.get(Face_Enum.BACK) == face)
            return Face_Enum.BACK;

        return null;

    }

    /**
     * @param initial - the initial physical position of the face that needs to be twisted
     * @param current - the current physical position of the face that needs to be twisted
     * @param algorithmBool - the original boolen of the twist (i.e true for right and false for left, true for up and false for down)
     * @return boolean that implies the orientation of the physical twist that will be coherent with the algorithm
     */
    static boolean getBoolForDirection(Face_Enum initial, Face_Enum current, boolean algorithmBool) {

        // the original Face (i.e was right and right now is up)
//        if (initial.getValue() == current.getValue())
//            return algorithmBool;
//
//        // they are opposite (i.e was initially UP and now is in DOWN)
//        if (initial.getValue() + current.getValue() == 7)
//            return !algorithmBool;

        boolean result = getBoolOtherFaceIsDown(current, algorithmBool);
        if (initial == current)
            return result;
        return getBoolOtherFaceIsDown(initial, result);


    }

    /**
     * helper for getBoolForDirection()
     * translates boolean from a location of a face to the down location
     */
    private static boolean getBoolOtherFaceIsDown(Face_Enum initial, boolean algorithmBool) {
        if (initial == Face_Enum.BACK || initial == Face_Enum.LEFT)
            return !algorithmBool;
        if (initial == Face_Enum.FRONT || initial == Face_Enum.RIGHT)
            return algorithmBool;

        return initial == Face_Enum.DOWN ? algorithmBool : !algorithmBool;
    }

//    private static List<RobotSolvingAction> updateFlipToActualCMD(Map<Face_Enum,Face_Enum> initialPhysicalMap) {
//
//    	return null;
//    }
//    
//    private static List<RobotSolvingAction> translateRightRotateToActualCMD(Map<Face_Enum,Face_Enum> initialPhysicalMap) {
//    	
//    	return null;
//    }
//    
//    private static List<RobotSolvingAction> translateLefttRotateToActualCMD(Map<Face_Enum,Face_Enum> initialPhysicalMap) {
//    	
//    	return null;
//    }

}
