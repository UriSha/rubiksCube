import java.util.List;

/**
 * The class that contains all the help functions to the Logic class
 * Was created in order to give the code more delicate and clean appearance
 */
class LogicUtils {
    /**
     * @param command - ENUM that represents the command type
     * @param actions - List with all the commands
     * @param cube    - Standard cube
     *                This functions adds the desirable command to the commands list and also changes the cube object,
     *                according to the command
     */
    static void executeCMD(AlgorithmCommands command, List<AlgorithmCommands> actions, Cube cube) {
        actions.add(command);
        switch (command) {
            case CMD_LEFT_ROTATE:
                cube.rotate(false);
                break;
            case CMD_RIGHT_ROTATE:
                cube.rotate(true);
                break;
            case CMD_FLIP:
                cube.flip();
                break;
            case CMD_UP_TWIST_LEFT:
                cube.twistUpperFace(false);
                break;
            case CMD_UP_TWIST_RIGHT:
                cube.twistUpperFace(true);
                break;
            case CMD_DOWN_TWIST_LEFT:
                cube.twistBottomFace(false);
                break;
            case CMD_DOWN_TWIST_RIGHT:
                cube.twistBottomFace(true);
                break;
            case CMD_LEFT_TWIST_FRONTUPWARD:
                cube.twistLeftFace(true);
                break;
            case CMD_LEFT_TWIST_BACKUPWARD:
                cube.twistLeftFace(false);
                break;
            case CMD_RIGHT_TWIST_FRONTUPWARD:
                cube.twistRightFace(true);
                break;
            case CMD_RIGHT_TWIST_BACKUPWARD:
                cube.twistRightFace(false);
                break;
            case CMD_FRONT_TWIST_CLOCKWISE:
                cube.twistFrontFace(true);
                break;
            case CMD_FRONT_TWIST_C_CLOCKWISE:
                cube.twistFrontFace(false);
                break;
            case CMD_BACK_TWIST_CLOCKWISE:
                cube.twistBackFace(true);
                break;
            case CMD_BACK_TWIST_C_CLOCKWISE:
                cube.twistBackFace(false);
                break;

        }
    }

    // gets 2 colors of an edge part of the cube and returns  a Location object of the exact location on the cube
    // by searching inside the object
    static Logic.Location getLocationOfEdge(Cube cube, Cube.Color prime, Cube.Color second) {

        if (cube.getUp().getGridEntry(0, 1) == prime && cube.getBack().getGridEntry(0, 1) == second) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.BACK, 0, 1);
        }
        if (cube.getUp().getGridEntry(1, 0) == prime && cube.getLeft().getGridEntry(0, 1) == second) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.LEFT, 1, 0);
        }
        if (cube.getUp().getGridEntry(1, 2) == prime && cube.getRight().getGridEntry(0, 1) == second) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.RIGHT, 1, 2);
        }
        if (cube.getUp().getGridEntry(2, 1) == prime && cube.getFront().getGridEntry(0, 1) == second) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.FRONT, 2, 1);
        }

        if (cube.getFront().getGridEntry(0, 1) == prime && cube.getUp().getGridEntry(2, 1) == second) {
            return new Logic.Location(Face_Enum.FRONT,Face_Enum.UP, 0, 1);
        }
        if (cube.getFront().getGridEntry(1, 0) == prime && cube.getLeft().getGridEntry(1, 2) == second) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.LEFT, 1, 0);
        }
        if (cube.getFront().getGridEntry(1, 2) == prime && cube.getRight().getGridEntry(1, 0) == second) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.RIGHT, 1, 2);
        }
        if (cube.getFront().getGridEntry(2, 1) == prime && cube.getDown().getGridEntry(0, 1) == second) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.DOWN, 2, 1);
        }

        if (cube.getDown().getGridEntry(0, 1) == prime && cube.getFront().getGridEntry(2, 1) == second) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.FRONT, 0, 1);
        }
        if (cube.getDown().getGridEntry(1, 0) == prime && cube.getLeft().getGridEntry(2, 1) == second) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.LEFT, 1, 0);
        }
        if (cube.getDown().getGridEntry(1, 2) == prime && cube.getRight().getGridEntry(2, 1) == second) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.RIGHT, 1, 2);
        }
        if (cube.getDown().getGridEntry(2, 1) == prime && cube.getBack().getGridEntry(2, 1) == second) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.BACK, 2, 1);
        }

        if (cube.getBack().getGridEntry(0, 1) == prime && cube.getUp().getGridEntry(0, 1) == second) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.UP, 0, 1);
        }
        if (cube.getBack().getGridEntry(1, 0) == prime && cube.getRight().getGridEntry(1, 2) == second) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.RIGHT, 1, 0);
        }
        if (cube.getBack().getGridEntry(1, 2) == prime && cube.getLeft().getGridEntry(1, 0) == second) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.LEFT, 1, 2);
        }
        if (cube.getBack().getGridEntry(2, 1) == prime && cube.getDown().getGridEntry(2, 1) == second) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.DOWN, 2, 1);
        }

        if (cube.getLeft().getGridEntry(0, 1) == prime && cube.getUp().getGridEntry(1, 0) == second) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.UP, 0, 1);
        }
        if (cube.getLeft().getGridEntry(1, 0) == prime && cube.getBack().getGridEntry(1, 2) == second) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.BACK, 1, 0);
        }
        if (cube.getLeft().getGridEntry(1, 2) == prime && cube.getFront().getGridEntry(1, 0) == second) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.FRONT, 1, 2);
        }
        if (cube.getLeft().getGridEntry(2, 1) == prime && cube.getDown().getGridEntry(1, 0) == second) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.DOWN, 2, 1);
        }

        if (cube.getRight().getGridEntry(0, 1) == prime && cube.getUp().getGridEntry(1, 2) == second) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.UP, 0, 1);
        }
        if (cube.getRight().getGridEntry(1, 0) == prime && cube.getFront().getGridEntry(1, 2) == second) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.FRONT, 1, 0);
        }
        if (cube.getRight().getGridEntry(1, 2) == prime && cube.getBack().getGridEntry(1, 0) == second) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.BACK, 1, 2);
        }
        if (cube.getRight().getGridEntry(2, 1) == prime && cube.getDown().getGridEntry(1, 2) == second) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.DOWN, 2, 1);
        }

        return null;
    }

    // gets 3 colors of a corner part of the cube and returns  a Location object of the exact location on the cube
    // by searching inside the object
    static Logic.Location getLocationOfCorner(Cube cube, Cube.Color prime, Cube.Color second, Cube.Color third) {

        if (cube.getUp().getGridEntry(0, 0) == prime && cube.getBack().getGridEntry(0, 2) == second && cube.getLeft().getGridEntry(0, 0) == third) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.BACK, Face_Enum.LEFT);
        }
        if (cube.getUp().getGridEntry(0, 0) == prime && cube.getBack().getGridEntry(0, 2) == third && cube.getLeft().getGridEntry(0, 0) == second) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.LEFT, Face_Enum.BACK);
        }
        if (cube.getUp().getGridEntry(0, 2) == prime && cube.getBack().getGridEntry(0, 0) == second && cube.getRight().getGridEntry(0, 2) == third) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.BACK, Face_Enum.RIGHT);
        }
        if (cube.getUp().getGridEntry(0, 2) == prime && cube.getBack().getGridEntry(0, 0) == third && cube.getRight().getGridEntry(0, 2) == second) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.RIGHT, Face_Enum.BACK);
        }
        if (cube.getUp().getGridEntry(2, 0) == prime && cube.getFront().getGridEntry(0, 0) == second && cube.getLeft().getGridEntry(0, 2) == third) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.FRONT, Face_Enum.LEFT);
        }
        if (cube.getUp().getGridEntry(2, 0) == prime && cube.getFront().getGridEntry(0, 0) == third && cube.getLeft().getGridEntry(0, 2) == second) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.LEFT, Face_Enum.FRONT);
        }
        if (cube.getUp().getGridEntry(2, 2) == prime && cube.getFront().getGridEntry(0, 2) == second && cube.getRight().getGridEntry(0, 0) == third) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.FRONT, Face_Enum.RIGHT);
        }
        if (cube.getUp().getGridEntry(2, 2) == prime && cube.getFront().getGridEntry(0, 2) == third && cube.getRight().getGridEntry(0, 0) == second) {
            return new Logic.Location(Face_Enum.UP, Face_Enum.RIGHT, Face_Enum.FRONT);
        }

        if (cube.getFront().getGridEntry(0, 0) == prime && cube.getUp().getGridEntry(2, 0) == second && cube.getLeft().getGridEntry(0, 2) == third) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.UP, Face_Enum.LEFT);
        }
        if (cube.getFront().getGridEntry(0, 0) == prime && cube.getUp().getGridEntry(2, 0) == third && cube.getLeft().getGridEntry(0, 2) == second) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.LEFT, Face_Enum.UP);
        }
        if (cube.getFront().getGridEntry(0, 2) == prime && cube.getUp().getGridEntry(2, 2) == second && cube.getRight().getGridEntry(0, 0) == third) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.UP, Face_Enum.RIGHT);
        }
        if (cube.getFront().getGridEntry(0, 2) == prime && cube.getUp().getGridEntry(2, 2) == third && cube.getRight().getGridEntry(0, 0) == second) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.RIGHT, Face_Enum.UP);
        }
        if (cube.getFront().getGridEntry(2, 0) == prime && cube.getLeft().getGridEntry(2, 2) == second && cube.getDown().getGridEntry(0, 0) == third) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.LEFT, Face_Enum.DOWN);
        }
        if (cube.getFront().getGridEntry(2, 0) == prime && cube.getLeft().getGridEntry(2, 2) == third && cube.getDown().getGridEntry(0, 0) == second) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.DOWN, Face_Enum.LEFT);
        }
        if (cube.getFront().getGridEntry(2, 2) == prime && cube.getRight().getGridEntry(2, 0) == second && cube.getDown().getGridEntry(0, 2) == third) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.RIGHT, Face_Enum.DOWN);
        }
        if (cube.getFront().getGridEntry(2, 2) == prime && cube.getRight().getGridEntry(2, 0) == third && cube.getDown().getGridEntry(0, 2) == second) {
            return new Logic.Location(Face_Enum.FRONT, Face_Enum.DOWN, Face_Enum.RIGHT);
        }


        if (cube.getBack().getGridEntry(0, 0) == prime && cube.getUp().getGridEntry(0, 2) == second && cube.getRight().getGridEntry(0, 2) == third) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.UP, Face_Enum.RIGHT);
        }
        if (cube.getBack().getGridEntry(0, 0) == prime && cube.getUp().getGridEntry(0, 2) == third && cube.getRight().getGridEntry(0, 2) == second) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.RIGHT, Face_Enum.UP);
        }
        if (cube.getBack().getGridEntry(0, 2) == prime && cube.getUp().getGridEntry(0, 0) == second && cube.getLeft().getGridEntry(0, 0) == third) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.UP, Face_Enum.LEFT);
        }
        if (cube.getBack().getGridEntry(0, 2) == prime && cube.getUp().getGridEntry(0, 0) == third && cube.getLeft().getGridEntry(0, 0) == second) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.LEFT, Face_Enum.UP);
        }
        if (cube.getBack().getGridEntry(2, 0) == prime && cube.getDown().getGridEntry(2, 2) == second && cube.getRight().getGridEntry(2, 2) == third) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.DOWN, Face_Enum.RIGHT);
        }
        if (cube.getBack().getGridEntry(2, 0) == prime && cube.getDown().getGridEntry(2, 2) == third && cube.getRight().getGridEntry(2, 2) == second) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.RIGHT, Face_Enum.DOWN);
        }
        if (cube.getBack().getGridEntry(2, 2) == prime && cube.getDown().getGridEntry(2, 0) == second && cube.getLeft().getGridEntry(2, 0) == third) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.DOWN, Face_Enum.LEFT);
        }
        if (cube.getBack().getGridEntry(2, 2) == prime && cube.getDown().getGridEntry(2, 0) == third && cube.getLeft().getGridEntry(2, 0) == second) {
            return new Logic.Location(Face_Enum.BACK, Face_Enum.LEFT, Face_Enum.DOWN);
        }


        if (cube.getDown().getGridEntry(0, 0) == prime && cube.getFront().getGridEntry(2, 0) == second && cube.getLeft().getGridEntry(2, 2) == third) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.FRONT, Face_Enum.LEFT);
        }
        if (cube.getDown().getGridEntry(0, 0) == prime && cube.getFront().getGridEntry(2, 0) == third && cube.getLeft().getGridEntry(2, 2) == second) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.LEFT, Face_Enum.FRONT);
        }
        if (cube.getDown().getGridEntry(0, 2) == prime && cube.getFront().getGridEntry(2, 2) == second && cube.getRight().getGridEntry(2, 0) == third) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.FRONT, Face_Enum.RIGHT);
        }
        if (cube.getDown().getGridEntry(0, 2) == prime && cube.getFront().getGridEntry(2, 2) == third && cube.getRight().getGridEntry(2, 0) == second) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.RIGHT, Face_Enum.FRONT);
        }
        if (cube.getDown().getGridEntry(2, 0) == prime && cube.getBack().getGridEntry(2, 2) == second && cube.getLeft().getGridEntry(2, 0) == third) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.BACK, Face_Enum.LEFT);
        }
        if (cube.getDown().getGridEntry(2, 0) == prime && cube.getBack().getGridEntry(2, 2) == third && cube.getLeft().getGridEntry(2, 0) == second) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.LEFT, Face_Enum.BACK);
        }
        if (cube.getDown().getGridEntry(2, 2) == prime && cube.getBack().getGridEntry(2, 0) == second && cube.getRight().getGridEntry(2, 2) == third) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.BACK, Face_Enum.RIGHT);
        }
        if (cube.getDown().getGridEntry(2, 2) == prime && cube.getBack().getGridEntry(2, 0) == third && cube.getRight().getGridEntry(2, 2) == second) {
            return new Logic.Location(Face_Enum.DOWN, Face_Enum.RIGHT, Face_Enum.BACK);
        }

        if (cube.getRight().getGridEntry(0, 0) == prime && cube.getUp().getGridEntry(2, 2) == second && cube.getFront().getGridEntry(0, 2) == third) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.UP, Face_Enum.FRONT);
        }
        if (cube.getRight().getGridEntry(0, 0) == prime && cube.getUp().getGridEntry(2, 2) == third && cube.getFront().getGridEntry(0, 2) == second) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.FRONT, Face_Enum.UP);
        }
        if (cube.getRight().getGridEntry(0, 2) == prime && cube.getUp().getGridEntry(0, 2) == second && cube.getBack().getGridEntry(0, 0) == third) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.UP, Face_Enum.BACK);
        }
        if (cube.getRight().getGridEntry(0, 2) == prime && cube.getUp().getGridEntry(0, 2) == third && cube.getBack().getGridEntry(0, 0) == second) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.BACK, Face_Enum.UP);
        }
        if (cube.getRight().getGridEntry(2, 0) == prime && cube.getFront().getGridEntry(2, 2) == second && cube.getDown().getGridEntry(0, 2) == third) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.FRONT, Face_Enum.DOWN);
        }
        if (cube.getRight().getGridEntry(2, 0) == prime && cube.getFront().getGridEntry(2, 2) == third && cube.getDown().getGridEntry(0, 2) == second) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.DOWN, Face_Enum.FRONT);
        }
        if (cube.getRight().getGridEntry(2, 2) == prime && cube.getBack().getGridEntry(2, 0) == second && cube.getDown().getGridEntry(2, 2) == third) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.BACK, Face_Enum.DOWN);
        }
        if (cube.getRight().getGridEntry(2, 2) == prime && cube.getBack().getGridEntry(2, 0) == third && cube.getDown().getGridEntry(2, 2) == second) {
            return new Logic.Location(Face_Enum.RIGHT, Face_Enum.DOWN, Face_Enum.BACK);
        }

        if (cube.getLeft().getGridEntry(0, 0) == prime && cube.getUp().getGridEntry(0, 0) == second && cube.getBack().getGridEntry(0, 2) == third) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.UP, Face_Enum.BACK);
        }
        if (cube.getLeft().getGridEntry(0, 0) == prime && cube.getUp().getGridEntry(0, 0) == third && cube.getBack().getGridEntry(0, 2) == second) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.BACK, Face_Enum.UP);
        }
        if (cube.getLeft().getGridEntry(0, 2) == prime && cube.getUp().getGridEntry(2, 0) == second && cube.getFront().getGridEntry(0, 0) == third) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.UP, Face_Enum.FRONT);
        }
        if (cube.getLeft().getGridEntry(0, 2) == prime && cube.getUp().getGridEntry(2, 0) == third && cube.getFront().getGridEntry(0, 0) == second) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.FRONT, Face_Enum.UP);
        }
        if (cube.getLeft().getGridEntry(2, 0) == prime && cube.getBack().getGridEntry(2, 2) == second && cube.getDown().getGridEntry(2, 0) == third) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.BACK, Face_Enum.DOWN);
        }
        if (cube.getLeft().getGridEntry(2, 0) == prime && cube.getBack().getGridEntry(2, 2) == third && cube.getDown().getGridEntry(2, 0) == second) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.DOWN, Face_Enum.BACK);
        }
        if (cube.getLeft().getGridEntry(2, 2) == prime && cube.getFront().getGridEntry(2, 0) == second && cube.getDown().getGridEntry(0, 0) == third) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.FRONT, Face_Enum.DOWN);
        }
        if (cube.getLeft().getGridEntry(2, 2) == prime && cube.getFront().getGridEntry(2, 0) == third && cube.getDown().getGridEntry(0, 0) == second) {
            return new Logic.Location(Face_Enum.LEFT, Face_Enum.DOWN, Face_Enum.FRONT);
        }

        System.out.println("Not such corner exists");
        return null;
    }


    //////////////////////////////// stage seven ////////////////////////////////

    // one of the core actions of stage seven in the algorithm,
    // rotate the right-up-front to be front-right-up and left-up-front to be front-left-up.
    // has fixed actions that is added to the list of commands
    static void coreSevenInside(Cube cube, List<AlgorithmCommands> actions) {
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
    }

    // one of the core actions of stage seven in the algorithm,
    // rotate the right-up-front to be up-front-right and left-up-front to be up-front-left.
    // has fixed actions that is added to the list of commands
    static void coreSevenOutside(Cube cube, List<AlgorithmCommands> actions) {
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
    }

    // help function for stage seven that returns the Face ENUM of the orange side of the given one out of the
    // four corners, according to user input
    static Face_Enum getOrangeFace(Cube cube, int corner) {
        switch (corner) {
            case 0:
                if (cube.getRight().getGrid()[0][0] == Cube.Color.ORANGE) return Face_Enum.RIGHT;
                if (cube.getUp().getGrid()[2][2] == Cube.Color.ORANGE) return Face_Enum.UP;
                if (cube.getFront().getGrid()[0][2] == Cube.Color.ORANGE) return Face_Enum.FRONT;
            case 1:
                if (cube.getRight().getGrid()[0][2] == Cube.Color.ORANGE) return Face_Enum.RIGHT;
                if (cube.getUp().getGrid()[0][2] == Cube.Color.ORANGE) return Face_Enum.UP;
                if (cube.getBack().getGrid()[0][0] == Cube.Color.ORANGE) return Face_Enum.BACK;
            case 2:
                if (cube.getLeft().getGrid()[0][0] == Cube.Color.ORANGE) return Face_Enum.LEFT;
                if (cube.getUp().getGrid()[0][0] == Cube.Color.ORANGE) return Face_Enum.UP;
                if (cube.getBack().getGrid()[0][2] == Cube.Color.ORANGE) return Face_Enum.BACK;
            case 3:
                if (cube.getLeft().getGrid()[0][2] == Cube.Color.ORANGE) return Face_Enum.LEFT;
                if (cube.getUp().getGrid()[2][0] == Cube.Color.ORANGE) return Face_Enum.UP;
                if (cube.getFront().getGrid()[0][0] == Cube.Color.ORANGE) return Face_Enum.FRONT;
        }
        return null;
    }


    // help function for stage seven that helps to locate , given a location object which is on the upper face
    // returns the number of the corner
    // 0 = front-right
    // 0 = back-right
    // 0 = back-left
    // 0 = front-left
    static int getUpperCornerNum(Logic.Location location) {
        Face_Enum prime = location.name;
        Face_Enum sec = location.secondDircetion;
        Face_Enum third = location.thirdDirection;
        if (prime == Face_Enum.FRONT) {
            if ((sec == Face_Enum.UP && third == Face_Enum.RIGHT) || (sec == Face_Enum.RIGHT && third == Face_Enum.UP)) {
                return 0;
            }
            if ((sec == Face_Enum.UP && third == Face_Enum.LEFT) || (sec == Face_Enum.LEFT && third == Face_Enum.UP)) {
                return 3;
            }
        }
        if (prime == Face_Enum.RIGHT) {
            if ((sec == Face_Enum.FRONT && third == Face_Enum.UP) || (sec == Face_Enum.UP && third == Face_Enum.FRONT)) {
                return 0;
            }
            if ((sec == Face_Enum.BACK && third == Face_Enum.UP) || (sec == Face_Enum.UP && third == Face_Enum.BACK)) {
                return 1;
            }
        }
        if (prime == Face_Enum.BACK) {
            if ((sec == Face_Enum.RIGHT && third == Face_Enum.UP) || (sec == Face_Enum.UP && third == Face_Enum.RIGHT)) {
                return 1;
            }
            if ((sec == Face_Enum.LEFT && third == Face_Enum.UP) || (sec == Face_Enum.UP && third == Face_Enum.LEFT)) {
                return 2;
            }
        }
        if (prime == Face_Enum.LEFT) {
            if ((sec == Face_Enum.BACK && third == Face_Enum.UP) || (sec == Face_Enum.UP && third == Face_Enum.BACK)) {
                return 2;
            }
            if ((sec == Face_Enum.FRONT && third == Face_Enum.UP) || (sec == Face_Enum.UP && third == Face_Enum.FRONT)) {
                return 3;
            }
        }
        if (prime == Face_Enum.UP) {
            if ((sec == Face_Enum.FRONT && third == Face_Enum.RIGHT) || (sec == Face_Enum.RIGHT && third == Face_Enum.FRONT)) {
                return 0;
            }
            if ((sec == Face_Enum.BACK && third == Face_Enum.RIGHT) || (sec == Face_Enum.RIGHT && third == Face_Enum.BACK)) {
                return 1;
            }
            if ((sec == Face_Enum.FRONT && third == Face_Enum.LEFT) || (sec == Face_Enum.LEFT && third == Face_Enum.FRONT)) {
                return 3;
            }
            if ((sec == Face_Enum.BACK && third == Face_Enum.LEFT) || (sec == Face_Enum.LEFT && third == Face_Enum.BACK)) {
                return 2;
            }
        }
        return 4;
    }


    //////////////////////////////// stage six ////////////////////////////////


    // one of the core actions of stage six in the algorithm.
    // rotate up-right-back, up-back-left, up-right-front corners counter-clockwise
    // has fixed actions that is added to the list of commands
    static void coreSixLeftToRight(Cube cube, List<AlgorithmCommands> actions) {
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
    }

    // one of the core actions of stage six in the algorithm.
    // rotate up-right-back, up-back-left, up-right-front corners clockwise
    // has fixed actions that is added to the list of commands
    static void coreSixRightToLeft(Cube cube, List<AlgorithmCommands> actions) {
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
    }

    //////////////////////////////// stage five ////////////////////////////////


    // one of the core actions of stage five in the algorithm.
    // rotate up-right, up-back, up-left edges counter-clockwise
    // has fixed actions that is added to the list of commands
    static void coreFiveLeftToRight(Cube cube, List<AlgorithmCommands> actions) {
        LogicUtils.executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
    }

    // one of the core actions of stage five in the algorithm.
    // rotate up-right, up-back, up-left edges clockwise
    // has fixed actions that is added to the list of commands
    static void coreFiveRightToLeft(Cube cube, List<AlgorithmCommands> actions) {
        LogicUtils.executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        LogicUtils.executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
    }

    //////////////////////////////// stage four ////////////////////////////////


    // one of the core actions of stage four in the algorithm, set orange edges from front-up, right-up, back-up, left-up
    //to face up.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    static void noneInPlace(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
    }

    // one of the core actions of stage four in the algorithm, set orange edges from front-up, right-up to face up.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    static void twoNearInPlace(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);

    }

    // one of the core actions of stage four in the algorithm, set orange edges from front-up, back-up to face up.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    static void twoCounterInPlace(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
    }


    //////////////////////////////// stage three ////////////////////////////////

    // Help function for stage three. searches for the location given and brings it to the desired position on the cube
    // which is the front-right edge.
    // Adds the appropriate commands to the list
    static void getFrontRightEdge(Cube cube, List<AlgorithmCommands> actions, Logic.Location edge) {
        switch (edge.name) {
            case UP:
                if (edge.x == 1 && edge.y == 2) {
                    edgeFromTheRight(actions, cube);
                } else if (edge.x == 0 && edge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
                    edgeFromTheRight(actions, cube);
                } else if (edge.x == 2 && edge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    edgeFromTheRight(actions, cube);
                } else if (edge.x == 1 && edge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
                    edgeFromTheRight(actions, cube);
                }
                break;
            case FRONT:
                if (edge.x == 0 && edge.y == 1) {
                    edgeOnTop(actions, cube);
                } else if (edge.x == 1 && edge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_RIGHT_ROTATE, actions, cube);
                    edgeFromTheRight(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_LEFT_ROTATE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
                    edgeFromTheRight(actions, cube);
                }
                break;
            case LEFT:
                if (edge.x == 0 && edge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    edgeOnTop(actions, cube);
                } else if (edge.x == 1 && edge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_LEFT_ROTATE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_LEFT_ROTATE, actions, cube);
                    edgeOnTop(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_RIGHT_ROTATE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_RIGHT_ROTATE, actions, cube);
                    edgeOnTop(actions, cube);
                } else if (edge.x == 1 && edge.y == 2) {
                    executeCMD(AlgorithmCommands.CMD_RIGHT_ROTATE, actions, cube);
                    edgeFromTheRight(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_LEFT_ROTATE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
                    edgeOnTop(actions, cube);
                }
                break;
            case RIGHT:
                if (edge.x == 0 && edge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
                    edgeOnTop(actions, cube);
                } else if (edge.x == 1 && edge.y == 0) {
                    switchEdgeColors(actions, cube);
                } else if (edge.x == 1 && edge.y == 2) {
                    executeCMD(AlgorithmCommands.CMD_LEFT_ROTATE, actions, cube);
                    edgeOnTop(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_RIGHT_ROTATE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    edgeFromTheRight(actions, cube);
                }
                break;
            case BACK:
                if (edge.x == 0 && edge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    edgeOnTop(actions, cube);
                } else if (edge.x == 1 && edge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_LEFT_ROTATE, actions, cube);
                    edgeOnTop(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_RIGHT_ROTATE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    edgeOnTop(actions, cube);
                } else if (edge.x == 1 && edge.y == 2) {
                    executeCMD(AlgorithmCommands.CMD_RIGHT_ROTATE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_RIGHT_ROTATE, actions, cube);
                    edgeOnTop(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_LEFT_ROTATE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_LEFT_ROTATE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    edgeFromTheRight(actions, cube);
                }
        }
    }


    // one of the core actions of stage three in the algorithm, bring an edge from up-right to the
    // front-right side.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void edgeFromTheRight(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
    }

    // one of the core actions of stage three in the algorithm, bring an edge from up-front to the
    // right-front side.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void edgeOnTop(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
    }

    // one of the core actions of stage three in the algorithm, switching sides of the front-right edge.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void switchEdgeColors(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_UP_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);

    }


    //////////////////////////////// stage two ////////////////////////////////


    // Help function for stage two. searches for the location given and brings it to the desired position on the cube
    // which is up-front-right corner.
    // Adds the appropriate commands to the list
    static void getRedCorner(Cube cube, List<AlgorithmCommands> actions, Logic.Location redCorner) {
        if (redCorner.name == null) {
            System.out.println("Error: 'stageTwo' has failed");
            return;
        }
        switch (redCorner.name) {
            case RIGHT:
                if (redCorner.secondDircetion == Face_Enum.FRONT) {
                    if (redCorner.thirdDirection == Face_Enum.DOWN) {
                        rightLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.UP) {
                        System.out.println("Not a logic location for this corner - RFU");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.UP) {
                    if (redCorner.thirdDirection == Face_Enum.FRONT) {
                        rightUpperRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.BACK) {
                        System.out.println("Not a logic location for this corner - RUB");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.BACK) {
                    if (redCorner.thirdDirection == Face_Enum.UP) {
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                        downRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.DOWN) {
                        System.out.println("Not a logic location for this corner - RBD");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.DOWN) {
                    if (redCorner.thirdDirection == Face_Enum.BACK) {
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        frontLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.FRONT) {
                        System.out.println("Not a logic location for this corner - RDF");
                    }
                }
                break;
            case LEFT:
                if (redCorner.secondDircetion == Face_Enum.DOWN) {
                    if (redCorner.thirdDirection == Face_Enum.FRONT) {
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        frontLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.BACK) {
                        System.out.println("Not a logic location for this corner - LDB");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.FRONT) {
                    if (redCorner.thirdDirection == Face_Enum.UP) {
                        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
                        frontLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.DOWN) {
                        System.out.println("Not a logic location for this corner - LFD");

                    }
                } else if (redCorner.secondDircetion == Face_Enum.UP) {
                    if (redCorner.thirdDirection == Face_Enum.BACK) {
                        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                        rightLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.FRONT) {
                        System.out.println("Not a logic location for this corner - LUF");

                    }
                } else if (redCorner.secondDircetion == Face_Enum.BACK) {
                    if (redCorner.thirdDirection == Face_Enum.DOWN) {
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        rightLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.UP) {
                        System.out.println("Not a logic location for this corner - LBU");
                    }
                }
                break;
            case BACK:
                if (redCorner.secondDircetion == Face_Enum.RIGHT) {
                    if (redCorner.thirdDirection == Face_Enum.DOWN) {
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        rightLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.UP) {
                        System.out.println("Not a logic location for this corner - BRU");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.UP) {
                    if (redCorner.thirdDirection == Face_Enum.RIGHT) {
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                        rightLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.LEFT) {
                        System.out.println("Not a logic location for this corner - BUL");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.LEFT) {
                    if (redCorner.thirdDirection == Face_Enum.UP) {
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                        frontLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.DOWN) {

                        System.out.println("Not a logic location for this corner - BLD");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.DOWN) {
                    if (redCorner.thirdDirection == Face_Enum.LEFT) {
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        frontLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.RIGHT) {
                        System.out.println("Not a logic location for this corner - BDR");
                    }
                }
                break;
            case FRONT:
                if (redCorner.secondDircetion == Face_Enum.RIGHT) {
                    if (redCorner.thirdDirection == Face_Enum.UP) {
                        frontUpperRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.DOWN) {
                        System.out.println("Not a logic location for this corner - FRD");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.DOWN) {
                    if (redCorner.thirdDirection == Face_Enum.RIGHT) {
                        frontLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.LEFT) {
                        System.out.println("Not a logic location for this corner - FDL");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.UP) {
                    if (redCorner.thirdDirection == Face_Enum.LEFT) {
                        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
                        downRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.RIGHT) {
                        System.out.println("Not a logic location for this corner - FUR");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.LEFT) {
                    if (redCorner.thirdDirection == Face_Enum.DOWN) {
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        rightLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.UP) {
                        System.out.println("Not a logic location for this corner - FLU");
                    }
                }
                break;
            case DOWN:
                if (redCorner.secondDircetion == Face_Enum.RIGHT) {
                    if (redCorner.thirdDirection == Face_Enum.FRONT) {
                        downRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.BACK) {
                        System.out.println("Not a logic location for this corner - DRB");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.FRONT) {
                    if (redCorner.thirdDirection == Face_Enum.LEFT) {
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        downRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.RIGHT) {
                        System.out.println("Not a logic location for this corner - DFR");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.LEFT) {
                    if (redCorner.thirdDirection == Face_Enum.BACK) {
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        downRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.FRONT) {
                        System.out.println("Not a logic location for this corner - DLF");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.BACK) {
                    if (redCorner.thirdDirection == Face_Enum.RIGHT) {
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        downRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.LEFT) {
                        System.out.println("Not a logic location for this corner - DBL");
                    }
                }
                break;
            case UP:
                if (redCorner.secondDircetion == Face_Enum.RIGHT) {
                    if (redCorner.thirdDirection == Face_Enum.BACK) {
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                        frontLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.FRONT) {
                        System.out.println("Not a logic location for this corner - URF");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.BACK) {
                    if (redCorner.thirdDirection == Face_Enum.LEFT) {
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                        rightLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.RIGHT) {
                        System.out.println("Not a logic location for this corner - UBR");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.LEFT) {
                    if (redCorner.thirdDirection == Face_Enum.FRONT) {
                        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
                        rightLowerRedCorner(actions, cube);
                    } else if (redCorner.thirdDirection == Face_Enum.BACK) {
                        System.out.println("Not a logic location for this corner - ULB");
                    }
                } else if (redCorner.secondDircetion == Face_Enum.FRONT) {
                    if (redCorner.thirdDirection == Face_Enum.LEFT) {
                        System.out.println("Not a logic location for this corner - UFL");
                    }
                }
        }
    }


    // one of the core actions of stage two in the algorithm, bring an corner from right-front-down to the
    // up-front-right side.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void rightLowerRedCorner(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
    }

    // one of the core actions of stage two in the algorithm, bring an corner from front-right-down to the
    // up-right-front side.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void frontLowerRedCorner(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
    }

    // one of the core actions of stage two in the algorithm, bring an corner from down-front-right to the
    // up-right-front side.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void downRedCorner(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
    }

    // one of the core actions of stage two in the algorithm, bring an corner from right-up-front to the up-front-right
    // side.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void rightUpperRedCorner(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
    }

    // one of the core actions of stage two in the algorithm, bring an corner from front-up-right to the up-right-front
    // side.
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void frontUpperRedCorner(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
    }


    //////////////////////////////// stage one ////////////////////////////////

    // Help functions for stage one. Brings the given edge to the UP face in the FRONT side.
    static void getRedCross(Cube cube, List<AlgorithmCommands> actions, Logic.Location redEdge) {

        if (redEdge.name == null) {
            System.out.println("Error: 'stageOne' has failed");
            return;
        }
        switch (redEdge.name) {
            case UP:
                if (redEdge.x == 0 && redEdge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 2) {
                    executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                }//redYellow.x==2&&redYellow.y==1 is the place
                break;
            case FRONT:
                if (redEdge.x == 0 && redEdge.y == 1) {
                    frontRedEdge(actions, cube);

                } else if (redEdge.x == 1 && redEdge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                    frontRedEdge(actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 2) {
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
                    frontRedEdge(actions, cube);
                } else if (redEdge.x == 2 && redEdge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                    frontRedEdge(actions, cube);
                }
                break;
            case DOWN:
                if (redEdge.x == 0 && redEdge.y == 1) {
                    downRedEdge(actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_RIGHT, actions, cube);
                    downRedEdge(actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 2) {
                    executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                    downRedEdge(actions, cube);
                } else if (redEdge.x == 2 && redEdge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
                    downRedEdge(actions, cube);
                }
                break;
            case BACK:
                if (redEdge.x == 0 && redEdge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                    backRedEdge(actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                    backRedEdge(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                } else if (redEdge.x == 2 && redEdge.y == 1) {
                    backRedEdge(actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 2) {
                    executeCMD(AlgorithmCommands.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                    backRedEdge(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                }
                break;
            case LEFT:
                if (redEdge.x == 0 && redEdge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
                    leftRedEdge(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                } else if (redEdge.x == 2 && redEdge.y == 1) {
                    leftRedEdge(actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 2) {
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                }
                break;
            case RIGHT:
                if (redEdge.x == 0 && redEdge.y == 1) {
                    executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 0) {
                    executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
                } else if (redEdge.x == 2 && redEdge.y == 1) {
                    rightRedEdge(actions, cube);
                } else if (redEdge.x == 1 && redEdge.y == 2) {
                    executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
                    rightRedEdge(actions, cube);
                    executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
                }
                break;
        }
    }

    // one of the core actions of stage one in the algorithm, bring an edge  from front-up to the up-front side
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void frontRedEdge(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
    }

    // one of the core actions of stage one in the algorithm, bring an edge from back-down to the up-front side
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void backRedEdge(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
    }

    // one of the core actions of stage one in the algorithm, bring an edge  from left-up to the up-front side
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void leftRedEdge(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
    }

    // one of the core actions of stage one in the algorithm, bring an edge  from right-up to the up-front side
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void rightRedEdge(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
    }

    // one of the core actions of stage one in the algorithm, bring an edge from down-front to the up-front side
    // has fixed actions that is added to the list of commands
    // Adds the appropriate commands to the list
    private static void downRedEdge(List<AlgorithmCommands> actions, Cube cube) {
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(AlgorithmCommands.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
    }


}
