

import java.util.ArrayList;
import java.util.List;


public class Logic {


    public static List<cmd> mainAlgorithm(Cube cube) {
        List<cmd> result = new ArrayList<>();
        initialize(cube, result);
        stageOne(cube, result);
        stageTwo(cube, result);
        flipForStageThree(cube, result);
        stageThree(cube, result);
        stageFour(cube, result);
        CommandsListOptimizer.optimizeList(result);
        return result;
    }

    static void initialize(Cube cube, List<cmd> actions) {

        if (cube == null) {
            System.out.println("Error: 'initialize' arguments are null");
            return;
        }

        if (cube.getUp().getColor() != Cube.Color.RED) { // getting the red facet to the top
            if (cube.getBack().getColor() == Cube.Color.RED) {
                LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
            } else if (cube.getDown().getColor() == Cube.Color.RED) {
                LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
                LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
            } else if (cube.getFront().getColor() == Cube.Color.RED) {
                LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
                LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
                LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
            } else if (cube.getRight().getColor() == Cube.Color.RED) {
                LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
            } else if (cube.getLeft().getColor() == Cube.Color.RED) {
                LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
            }
        }

        if (cube.getFront().getColor() != Cube.Color.YELLOW) { // getting the yellow facet to the front
            if (cube.getLeft().getColor() == Cube.Color.YELLOW) {
                actions.add(cmd.CMD_RIGHT_ROTATE);
                cube.rotate(true);
            } else if (cube.getRight().getColor() == Cube.Color.YELLOW) {
                actions.add(cmd.CMD_LEFT_ROTATE);
                cube.rotate(false);
            } else if (cube.getBack().getColor() == Cube.Color.YELLOW) {
                actions.add(cmd.CMD_LEFT_ROTATE);
                cube.rotate(false);
                actions.add(cmd.CMD_LEFT_ROTATE);
                cube.rotate(false);
            }
        }
    }
    static void stageOne(Cube cube, List<cmd> actions) {
        if (cube == null || actions == null) {
            System.out.println("Error: 'stageOne' arguments are null");
            return;
        }
        Location redYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.YELLOW);
        LogicUtils.getRedCross(cube, actions, redYellow);
        cube.rotate(true);

        Location redGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.GREEN);
        LogicUtils.getRedCross(cube, actions, redGreen);
        cube.rotate(true);

        Location redWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.WHITE);
        LogicUtils.getRedCross(cube, actions, redWhite);
        cube.rotate(true);

        Location redBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.BLUE);
        LogicUtils.getRedCross(cube, actions, redBlue);
        cube.rotate(true);
    }
    static void stageTwo(Cube cube, List<cmd> actions) {
        if (cube == null || actions == null) {
            System.out.println("Error: 'stageTwo' arguments are null");
            return;
        }
        Location redYellowBlue = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.YELLOW, Cube.Color.BLUE);
        LogicUtils.getRedCorner(cube, actions, redYellowBlue);
        cube.rotate(true);
        Location redGreenYellow = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.YELLOW);
        LogicUtils.getRedCorner(cube, actions, redGreenYellow);
        cube.rotate(true);
        Location redWhiteGreen = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.WHITE, Cube.Color.GREEN);
        LogicUtils.getRedCorner(cube, actions, redWhiteGreen);
        cube.rotate(true);
        Location redBlueWhite = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.WHITE);
        LogicUtils.getRedCorner(cube, actions, redBlueWhite);
        cube.rotate(true);
    }

    static void flipForStageThree(Cube cube, List<cmd> actions) {
        LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
        LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
    }

    static void stageThree(Cube cube, List<cmd> actions) {
        if (cube == null || actions == null) {
            System.out.println("Error: 'stageThree' arguments are null");
            return;
        }
        Location whiteBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.WHITE, Cube.Color.BLUE);
        LogicUtils.getFrontRightEdge(cube, actions, whiteBlue);

        cube.rotate(true);

        Location greenWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.GREEN, Cube.Color.WHITE);
        LogicUtils.getFrontRightEdge(cube, actions, greenWhite);

        cube.rotate(true);

        Location yellowGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.YELLOW, Cube.Color.GREEN);
        LogicUtils.getFrontRightEdge(cube, actions, yellowGreen);

        cube.rotate(true);

        Location blueYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.BLUE, Cube.Color.YELLOW);
        LogicUtils.getFrontRightEdge(cube, actions, blueYellow);

        cube.rotate(true);

    }

    static void stageFour(Cube cube, List<cmd> actions) {
        if (cube.getUp().getGrid()[0][1] == Cube.Color.ORANGE && cube.getUp().getGrid()[2][1] == Cube.Color.ORANGE) {
            if (cube.getUp().getGrid()[1][2] != Cube.Color.ORANGE) {
                LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                LogicUtils.twoCounterInPlace(actions, cube);
                LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
            }
        } else if (cube.getUp().getGrid()[1][0] == Cube.Color.ORANGE && cube.getUp().getGrid()[1][2] == Cube.Color.ORANGE) {
            LogicUtils.twoCounterInPlace(actions, cube);
        } else if (cube.getUp().getGrid()[0][1] == Cube.Color.ORANGE && cube.getUp().getGrid()[1][0] == Cube.Color.ORANGE) {
            LogicUtils.twoNearInPlace(actions, cube);
        } else if (cube.getUp().getGrid()[1][2] == Cube.Color.ORANGE && cube.getUp().getGrid()[2][1] == Cube.Color.ORANGE) {
            LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            LogicUtils.twoNearInPlace(actions, cube);
            LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
        } else if (cube.getUp().getGrid()[1][0] == Cube.Color.ORANGE && cube.getUp().getGrid()[2][1] == Cube.Color.ORANGE) {
            LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
            LogicUtils.twoNearInPlace(actions, cube);
            LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
        } else if (cube.getUp().getGrid()[0][1] == Cube.Color.ORANGE && cube.getUp().getGrid()[1][2] == Cube.Color.ORANGE) {
            LogicUtils.executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
            LogicUtils.twoNearInPlace(actions, cube);
        } else {
            LogicUtils.noneInPlace(actions, cube);

        }
    }

     static class Location {
        int x, y;
        Face_Enum name;
        Face_Enum secondDircetion;
        Face_Enum thirdDirection;

        Location(Face_Enum name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
            secondDircetion = null;
        }

        Location(Face_Enum name, Face_Enum directionOfSecond, Face_Enum directionOfThird) {
            this.name = name;
            this.x = 10;
            this.y = 10;
            this.secondDircetion = directionOfSecond;
            this.thirdDirection = directionOfThird;
        }

        public String toString() {
            if (secondDircetion != null) {
                return ("Location: prime = " + name + ", secondary in: " + secondDircetion + ", third in: " + thirdDirection);
            } else {
                return ("Location: prime = " + name + ", x:" + x + ", y=" + y);
            }
        }
    }
}


