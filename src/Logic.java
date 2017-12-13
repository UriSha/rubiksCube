import java.util.ArrayList;
import java.util.List;

class Logic {

    static List<cmd> mainAlgorithm(Cube cube) {
        List<cmd> result = new ArrayList<>();
        initialize(cube, result);
        stageOne(cube, result);
        stageTwo(cube, result);
        flipForStageThree(cube, result);
        stageThree(cube, result);
        stageFour(cube, result);
        stageFive(cube, result);
        stageSix(cube, result);
        stageSeven(cube, result);
        CommandsListOptimizer.optimizeList(result);
        return result;
    }

    private static void initialize(Cube cube, List<cmd> actions) {

        if (cube == null) {
            System.out.println("Error: 'initialize' arguments arre null");
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
                LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            } else if (cube.getRight().getColor() == Cube.Color.YELLOW) {
                LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
            } else if (cube.getBack().getColor() == Cube.Color.YELLOW) {
                LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
            }
        }
    }

   private static void stageOne(Cube cube, List<cmd> actions) {
        if (cube == null || actions == null) {
            System.out.println("Error: 'stageOne' arguments are null");
            return;
        }
        Location redYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.YELLOW);
        LogicUtils.getRedCross(cube, actions, redYellow);
        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
        Location redGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.GREEN);
        LogicUtils.getRedCross(cube, actions, redGreen);
        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
        Location redWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.WHITE);
        LogicUtils.getRedCross(cube, actions, redWhite);
        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
        Location redBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.BLUE);
        LogicUtils.getRedCross(cube, actions, redBlue);
        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
    }

   private static void stageTwo(Cube cube, List<cmd> actions) {
        if (cube == null || actions == null) {
            System.out.println("Error: 'stageTwo' arguments are null");
            return;
        }
        Location redYellowBlue = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.YELLOW, Cube.Color.BLUE);
        LogicUtils.getRedCorner(cube, actions, redYellowBlue);
        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
        Location redGreenYellow = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.YELLOW);
        LogicUtils.getRedCorner(cube, actions, redGreenYellow);
        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
        Location redWhiteGreen = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.WHITE, Cube.Color.GREEN);
        LogicUtils.getRedCorner(cube, actions, redWhiteGreen);
        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
        Location redBlueWhite = LogicUtils.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.WHITE);
        LogicUtils.getRedCorner(cube, actions, redBlueWhite);
        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
    }

   private static void flipForStageThree(Cube cube, List<cmd> actions) {
        LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
        LogicUtils.executeCMD(cmd.CMD_FLIP, actions, cube);
    }

   private static void stageThree(Cube cube, List<cmd> actions) {
        if (cube == null || actions == null) {
            System.out.println("Error: 'stageThree' arguments are null");
            return;
        }
        Location whiteBlue = LogicUtils.getLocationOfEdge(cube, Cube.Color.WHITE, Cube.Color.BLUE);
        LogicUtils.getFrontRightEdge(cube, actions, whiteBlue);

        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);

        Location greenWhite = LogicUtils.getLocationOfEdge(cube, Cube.Color.GREEN, Cube.Color.WHITE);
        LogicUtils.getFrontRightEdge(cube, actions, greenWhite);

        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);

        Location yellowGreen = LogicUtils.getLocationOfEdge(cube, Cube.Color.YELLOW, Cube.Color.GREEN);
        LogicUtils.getFrontRightEdge(cube, actions, yellowGreen);

        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);

        Location blueYellow = LogicUtils.getLocationOfEdge(cube, Cube.Color.BLUE, Cube.Color.YELLOW);
        LogicUtils.getFrontRightEdge(cube, actions, blueYellow);

        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);

    }

   private static void stageFour(Cube cube, List<cmd> actions) {
        if (cube == null || actions == null) {
            System.out.println("Error: 'stageFour' arguments are null");
            return;
        }
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

   private static void stageFive(Cube cube, List<cmd> actions) {
        if (cube == null || actions == null) {
            System.out.println("Error: 'stageFive' arguments are null");
            return;
        }
        Location whiteOrange = LogicUtils.getLocationOfEdge(cube, Cube.Color.WHITE, Cube.Color.ORANGE);
        if (whiteOrange == null)
            return;
        switch (whiteOrange.name) {
            case RIGHT:
                LogicUtils.executeCMD(cmd.CMD_UP_TWIST_LEFT, actions, cube);
                break;
            case LEFT:
                LogicUtils.executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                break;
            case BACK:
                LogicUtils.executeCMD(cmd.CMD_UP_TWIST_LEFT, actions, cube);
                LogicUtils.executeCMD(cmd.CMD_UP_TWIST_LEFT, actions, cube);
                break;
            default:
                break;
        }
        Location greenOrange = LogicUtils.getLocationOfEdge(cube, Cube.Color.GREEN, Cube.Color.ORANGE);
        if (greenOrange == null)
            return;
        Location yellowOrange = LogicUtils.getLocationOfEdge(cube, Cube.Color.YELLOW, Cube.Color.ORANGE);
        if (yellowOrange == null)
            return;
        Location blueOrange = LogicUtils.getLocationOfEdge(cube, Cube.Color.BLUE, Cube.Color.ORANGE);
        if (blueOrange == null)
            return;
        if (greenOrange.name == Face_Enum.BACK && yellowOrange.name == Face_Enum.RIGHT) {
            LogicUtils.coreFiveLeftToRight(cube, actions);
        } else if (greenOrange.name == Face_Enum.RIGHT && blueOrange.name == Face_Enum.BACK) {
            LogicUtils.coreFiveRightToLeft(cube, actions);
        } else if (greenOrange.name == Face_Enum.BACK && yellowOrange.name == Face_Enum.LEFT) {
            LogicUtils.executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
            LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            LogicUtils.coreFiveRightToLeft(cube, actions);
            LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
        } else if (yellowOrange.name == Face_Enum.RIGHT && blueOrange.name == Face_Enum.BACK) {
            LogicUtils.executeCMD(cmd.CMD_UP_TWIST_LEFT, actions, cube);
            LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
            LogicUtils.coreFiveLeftToRight(cube, actions);
            LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
        } else if (greenOrange.name == Face_Enum.RIGHT && blueOrange.name == Face_Enum.LEFT) {
            LogicUtils.coreFiveLeftToRight(cube, actions);
            LogicUtils.executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
            LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            LogicUtils.coreFiveRightToLeft(cube, actions);
            LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
        }
    }

   private static void stageSix(Cube cube, List<cmd> actions) {
        do {
            int whiteBlue = LogicUtils.getUpperCornerNum(LogicUtils.getLocationOfCorner(cube,
                    Cube.Color.WHITE, Cube.Color.BLUE, Cube.Color.ORANGE));
            int greenWhite = LogicUtils.getUpperCornerNum(LogicUtils.getLocationOfCorner(cube,
                    Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.ORANGE));
            int yellowGreen = LogicUtils.getUpperCornerNum(LogicUtils.getLocationOfCorner(cube,
                    Cube.Color.YELLOW, Cube.Color.GREEN, Cube.Color.ORANGE));
            int blueYellow = LogicUtils.getUpperCornerNum(LogicUtils.getLocationOfCorner(cube,
                    Cube.Color.BLUE, Cube.Color.YELLOW, Cube.Color.ORANGE));
            if (whiteBlue == 0) {
                if (greenWhite == 2) {
                    LogicUtils.coreSixLeftToRight(cube, actions);
                } else if (greenWhite == 1) {
                    LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                    LogicUtils.coreSixRightToLeft(cube, actions);
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                }
                break;
            } else if (greenWhite == 3) {
                if (whiteBlue == 1) {
                    LogicUtils.coreSixRightToLeft(cube, actions);
                } else if (whiteBlue == 2) {
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                    LogicUtils.coreSixLeftToRight(cube, actions);
                    LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                }
                break;
            } else if (yellowGreen == 2) {
                if (whiteBlue == 1) {
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                    LogicUtils.coreSixRightToLeft(cube, actions);
                    LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                } else if (whiteBlue == 3) {
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                    LogicUtils.coreSixLeftToRight(cube, actions);
                    LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                    LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                }
                break;
            } else if (blueYellow == 1) {
                if (whiteBlue == 2) {
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                    LogicUtils.coreSixRightToLeft(cube, actions);
                    LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                    LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                } else if (whiteBlue == 3) {
                    LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                    LogicUtils.coreSixLeftToRight(cube, actions);
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                }
                break;
            } else {
                LogicUtils.coreSixLeftToRight(cube, actions);
            }
        } while (true);
    }


   private static void stageSeven(Cube cube, List<cmd> actions) {
        boolean arnonIsReady = false;
        while (!arnonIsReady) {
            Face_Enum first = LogicUtils.getOrangeFace(cube, 0);
            Face_Enum sec = LogicUtils.getOrangeFace(cube, 1);
            Face_Enum third = LogicUtils.getOrangeFace(cube, 2);
            Face_Enum forth = LogicUtils.getOrangeFace(cube, 3);
            if (first == Face_Enum.UP && forth == Face_Enum.UP) {
                if (third == Face_Enum.UP && sec == Face_Enum.UP) {
                    arnonIsReady = true;
                } else {
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                }
            } else if (first == Face_Enum.UP) {
                if (third != Face_Enum.UP) {
                    LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                } else {
                    if (forth == Face_Enum.LEFT) {
                        LogicUtils.coreSevenOutside(cube, actions);
                        LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                    } else {
                        LogicUtils.coreSevenInside(cube, actions);
                        LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                    }
                }
            } else if (forth == Face_Enum.UP) {
                if (sec != Face_Enum.UP) {
                    LogicUtils.executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                } else {
                    if (first == Face_Enum.RIGHT) {
                        LogicUtils.coreSevenOutside(cube, actions);
                        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                    } else {
                        LogicUtils.coreSevenInside(cube, actions);
                        LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                    }
                }
            } else if (first == Face_Enum.FRONT && forth == Face_Enum.FRONT) {
                LogicUtils.coreSevenInside(cube, actions);
                LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            } else if (first == Face_Enum.RIGHT && forth == Face_Enum.LEFT) {
                LogicUtils.coreSevenOutside(cube, actions);
                LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            } else if (first == Face_Enum.RIGHT) {
                LogicUtils.coreSevenOutside(cube, actions);
                LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            } else if (first == Face_Enum.FRONT) {
                LogicUtils.coreSevenInside(cube, actions);
                LogicUtils.executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
            }
        }
    }

    static boolean isArnonReady(Cube cube) {
        if (cube == null)
            return false;
        Cube.Color colorCheck = cube.getUp().getGrid()[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cube.getUp().getGrid()[i][j] != colorCheck)
                    return false;
            }
        }
        colorCheck = cube.getDown().getGrid()[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cube.getDown().getGrid()[i][j] != colorCheck)
                    return false;
            }
        }
        colorCheck = cube.getRight().getGrid()[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cube.getRight().getGrid()[i][j] != colorCheck)
                    return false;
            }
        }
        colorCheck = cube.getLeft().getGrid()[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cube.getLeft().getGrid()[i][j] != colorCheck)
                    return false;
            }
        }
        colorCheck = cube.getFront().getGrid()[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cube.getFront().getGrid()[i][j] != colorCheck)
                    return false;
            }
        }
        colorCheck = cube.getBack().getGrid()[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cube.getBack().getGrid()[i][j] != colorCheck)
                    return false;
            }
        }
        return true;
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


