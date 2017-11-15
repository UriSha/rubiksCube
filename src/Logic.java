

import java.util.ArrayList;
import java.util.List;




public class Logic {


    public List<cmd> mainAlgorithm(Cube cube) {
        List<cmd> result = new ArrayList<>();
        int algoStage = 0;
        initialize(cube, result);
        stageOne(cube, result);

        return result;
    }




    private static void getRedCorner(Cube cube, List<cmd> actions, Location redCorner){
        if(redCorner.name==null)
        {
            System.out.println("Error: 'stageOne' has failed");
            return ;
        }
        switch (redCorner.name){
            case RIGHT:
                if (redCorner.secondDircetion == Face_Enum.FRONT) {
                    if (redCorner.thirdDirection == Face_Enum.DOWN) {rightLowerRedCorner(actions, cube);}
                    else if(redCorner.thirdDirection == Face_Enum.UP){
                        System.out.println("Not a logic location for this corner");
                    }
                }

        }
    }

    private static void rightLowerRedCorner(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
    }

    private static void frontLowerRedCorner(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
    }

    private static void downRedCorner(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
    }

    private static void rightUpperRedCorner(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
    }

    private static void frontUpperRedCorner(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
    }





























    private static void getRedCross(Cube cube, List<cmd> actions, Location redEdge){


        if(redEdge.name==null)
        {
            System.out.println("Error: 'stageOne' has failed");
            return ;
        }
        switch(redEdge.name){
            case UP:
                if (redEdge.x == 0 && redEdge.y == 1) {
                    executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                    executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(cmd.CMD_FRONT_TWIST_C_CLOCKWISE, actions, cube);
                    executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
                } else if (redEdge.x==1 && redEdge.y==2) {
                    executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
                    executeCMD(cmd.CMD_UP_TWIST_LEFT, actions, cube);
                    executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
                } else if (redEdge.x==1 && redEdge.y==0){
                    executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(cmd.CMD_UP_TWIST_LEFT, actions, cube);
                    executeCMD(cmd.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
                    executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                }//redYellow.x==2&&redYellow.y==1 is the place
                break;
            case FRONT:
                if (redEdge.x == 0 && redEdge.y == 1){
                    frontRedEdge(actions,cube);

                }
                else if(redEdge.x==1 && redEdge.y==0){
                    executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
                    frontRedEdge(actions,cube);
                }
                else if(redEdge.x==1 && redEdge.y==2){
                    executeCMD(cmd.CMD_FRONT_TWIST_C_CLOCKWISE,actions,cube);
                    frontRedEdge(actions,cube);
                }
                else if(redEdge.x==2 && redEdge.y==1){
                    executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
                    executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
                    frontRedEdge(actions,cube);
                } break;
            case DOWN:
                if(redEdge.x==0 && redEdge.y==1){
                    downRedEdge(actions,cube);
                }
                else if(redEdge.x==1 && redEdge.y==0){
                    executeCMD(cmd.CMD_DOWN_TWIST_RIGHT,actions,cube);
                    downRedEdge(actions,cube);
                }else if(redEdge.x==1 && redEdge.y==2){
                    executeCMD(cmd.CMD_DOWN_TWIST_LEFT,actions,cube);
                    downRedEdge(actions,cube);
                } else if (redEdge.x==2 && redEdge.y==1){
                    executeCMD(cmd.CMD_DOWN_TWIST_LEFT,actions,cube);
                    executeCMD(cmd.CMD_DOWN_TWIST_LEFT,actions,cube);
                    downRedEdge(actions,cube);
                } break;
            case BACK:
                if(redEdge.x==0 && redEdge.y==1){
                    executeCMD(cmd.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                    executeCMD(cmd.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                    backRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==0){
                    executeCMD(cmd.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                    backRedEdge(actions, cube);
                    executeCMD(cmd.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                } else if (redEdge.x==2 && redEdge.y==1){
                    backRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==2){
                    executeCMD(cmd.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                    backRedEdge(actions, cube);
                    executeCMD(cmd.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                } break;
            case LEFT:
                if(redEdge.x==0 && redEdge.y==1){
                    executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    leftRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==0){
                    executeCMD(cmd.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
                    leftRedEdge(actions, cube);
                    executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                } else if (redEdge.x==2 && redEdge.y==1){
                    leftRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==2){
                    executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    leftRedEdge(actions, cube);
                    executeCMD(cmd.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
                } break;
            case RIGHT:
                if(redEdge.x==0 && redEdge.y==1){
                    executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
                    executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
                    rightRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==0){
                    executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
                    rightRedEdge(actions, cube);
                    executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
                } else if (redEdge.x==2 && redEdge.y==1){
                    rightRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==2){
                    executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
                    rightRedEdge(actions, cube);
                    executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
                } break;
        }
    }

    private static void frontRedEdge(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD,actions,cube);
        executeCMD(cmd.CMD_LEFT_TWIST_FRONTUPWARD,actions,cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
        executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD,actions,cube);
        executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD,actions,cube);
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT,actions,cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
    }

    private static void backRedEdge(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        frontRedEdge(actions, cube);
    }

    private static void leftRedEdge(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        frontRedEdge(actions, cube);
    }

    private static void rightRedEdge(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        frontRedEdge(actions, cube);
    }

    private static void downRedEdge(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
    }

    private static void stageOne(Cube cube, List<cmd> actions) {

        if (cube == null || actions == null) {
            System.out.println("Error: 'stageOne' arguments are null");
            return;
        }
        Location redYellow = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.YELLOW);



        getRedCross(cube, actions,redYellow );
        System.out.println("RedYellow in place");//Debugging
        System.out.println(tempClassForPrint.toStringCube(cube));

        cube.rotate(true);
        Location redGreen = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.GREEN);

        getRedCross(cube, actions,redGreen );
        System.out.println("RedGreen in place");//Debugging
        System.out.println(tempClassForPrint.toStringCube(cube));

        cube.rotate(true);

        Location redWhite = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.WHITE);
        getRedCross(cube, actions,redWhite );
        System.out.println("RedWhite in place");//Debugging
        System.out.println(tempClassForPrint.toStringCube(cube));

        cube.rotate(true);

        Location redBlue = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.BLUE);
        getRedCross(cube, actions,redBlue );
        System.out.println("RedBlue in place");//Debugging
        System.out.println(tempClassForPrint.toStringCube(cube));

        cube.rotate(true);


    }

    private static void executeCMD(cmd command,List<cmd> actions, Cube cube){
        actions.add(command);
        switch (command){
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




    private static Location getLocationOfEdge(Cube cube, Cube.Color prime, Cube.Color second){

        if (cube.getUp().getGridEntry(0,1) == prime && cube.getBack().getGridEntry(0,1) == second){ return new Location(Face_Enum.UP, 0, 1); }
        if (cube.getUp().getGridEntry(1,0) == prime && cube.getLeft().getGridEntry(0,1) == second){ return new Location(Face_Enum.UP, 1, 0); }
        if (cube.getUp().getGridEntry(1,2) == prime && cube.getRight().getGridEntry(0,1) == second){ return new Location(Face_Enum.UP, 1, 2); }
        if (cube.getUp().getGridEntry(2,1) == prime && cube.getFront().getGridEntry(0,1) == second){ return new Location(Face_Enum.UP, 2, 1); }

        if (cube.getFront().getGridEntry(0,1) == prime && cube.getUp().getGridEntry(2,1) == second){ return new Location(Face_Enum.FRONT, 0, 1); }
        if (cube.getFront().getGridEntry(1,0) == prime && cube.getLeft().getGridEntry(1,2) == second){ return new Location(Face_Enum.FRONT, 1, 0); }
        if (cube.getFront().getGridEntry(1,2) == prime && cube.getRight().getGridEntry(1,0) == second){ return new Location(Face_Enum.FRONT, 1, 2); }
        if (cube.getFront().getGridEntry(2,1) == prime && cube.getDown().getGridEntry(0,1) == second){ return new Location(Face_Enum.FRONT, 2, 1); }

        if (cube.getDown().getGridEntry(0,1) == prime && cube.getFront().getGridEntry(2,1) == second){ return new Location(Face_Enum.DOWN, 0, 1); }
        if (cube.getDown().getGridEntry(1,0) == prime && cube.getLeft().getGridEntry(2,1) == second){ return new Location(Face_Enum.DOWN, 1, 0); }
        if (cube.getDown().getGridEntry(1,2) == prime && cube.getRight().getGridEntry(2,1) == second){ return new Location(Face_Enum.DOWN, 1, 2); }
        if (cube.getDown().getGridEntry(2,1) == prime && cube.getBack().getGridEntry(2,1) == second){ return new Location(Face_Enum.DOWN, 2, 1); }

        if (cube.getBack().getGridEntry(0,1) == prime && cube.getUp().getGridEntry(0,1) == second){ return new Location(Face_Enum.BACK, 0, 1); }
        if (cube.getBack().getGridEntry(1,0) == prime && cube.getRight().getGridEntry(1,2) == second){ return new Location(Face_Enum.BACK, 1, 0); }
        if (cube.getBack().getGridEntry(1,2) == prime && cube.getLeft().getGridEntry(1,0) == second){ return new Location(Face_Enum.BACK, 1, 2); }
        if (cube.getBack().getGridEntry(2,1) == prime && cube.getDown().getGridEntry(2,1) == second){ return new Location(Face_Enum.BACK, 2, 1); }

        if (cube.getLeft().getGridEntry(0,1) == prime && cube.getUp().getGridEntry(1,0) == second){ return new Location(Face_Enum.LEFT, 0, 1); }
        if (cube.getLeft().getGridEntry(1,0) == prime && cube.getBack().getGridEntry(1,2) == second){ return new Location(Face_Enum.LEFT, 1, 0); }
        if (cube.getLeft().getGridEntry(1,2) == prime && cube.getFront().getGridEntry(1,0) == second){ return new Location(Face_Enum.LEFT, 1, 2); }
        if (cube.getLeft().getGridEntry(2,1) == prime && cube.getDown().getGridEntry(1,0) == second){ return new Location(Face_Enum.LEFT, 2, 1); }

        if (cube.getRight().getGridEntry(0,1) == prime && cube.getUp().getGridEntry(1,2) == second){ return new Location(Face_Enum.RIGHT, 0, 1); }
        if (cube.getRight().getGridEntry(1,0) == prime && cube.getFront().getGridEntry(1,2) == second){ return new Location(Face_Enum.RIGHT, 1, 0); }
        if (cube.getRight().getGridEntry(1,2) == prime && cube.getBack().getGridEntry(1,0) == second){ return new Location(Face_Enum.RIGHT, 1, 2); }
        if (cube.getRight().getGridEntry(2,1) == prime && cube.getDown().getGridEntry(1,2) == second){ return new Location(Face_Enum.RIGHT, 2, 1); }

        return null;
    }



    public static Location getLocationOfCorner(Cube cube, Cube.Color prime, Cube.Color second, Cube.Color third){

        if (cube.getUp().getGridEntry(0,0) == prime && cube.getBack().getGridEntry(0,2) == second && cube.getLeft().getGridEntry(0,0) == third) {
            return new Location(Face_Enum.UP, Face_Enum.BACK, Face_Enum.LEFT); }
        if (cube.getUp().getGridEntry(0,0) == prime && cube.getBack().getGridEntry(0,2) == third && cube.getLeft().getGridEntry(0,0) == second) {
            return new Location(Face_Enum.UP, Face_Enum.LEFT, Face_Enum.BACK); }
        if (cube.getUp().getGridEntry(0,2) == prime && cube.getBack().getGridEntry(0,0) == second && cube.getRight().getGridEntry(0,2) == third) {
            return new Location(Face_Enum.UP, Face_Enum.BACK, Face_Enum.RIGHT); }
        if (cube.getUp().getGridEntry(0,2) == prime && cube.getBack().getGridEntry(0,0) == third && cube.getRight().getGridEntry(0,2) == second) {
            return new Location(Face_Enum.UP, Face_Enum.RIGHT, Face_Enum.BACK); }
        if (cube.getUp().getGridEntry(2,0) == prime && cube.getFront().getGridEntry(0,0) == second && cube.getLeft().getGridEntry(0,2) == third) {
            return new Location(Face_Enum.UP, Face_Enum.FRONT, Face_Enum.LEFT); }
        if (cube.getUp().getGridEntry(2,0) == prime && cube.getFront().getGridEntry(0,0) == third && cube.getLeft().getGridEntry(0,2) == second) {
            return new Location(Face_Enum.UP, Face_Enum.LEFT, Face_Enum.FRONT); }
        if (cube.getUp().getGridEntry(2,2) == prime && cube.getFront().getGridEntry(0,2) == second && cube.getRight().getGridEntry(0,0) == third) {
            return new Location(Face_Enum.UP, Face_Enum.FRONT, Face_Enum.RIGHT); }
        if (cube.getUp().getGridEntry(2,2) == prime && cube.getFront().getGridEntry(0,2) == third && cube.getRight().getGridEntry(0,0) == second) {
            return new Location(Face_Enum.UP, Face_Enum.RIGHT, Face_Enum.FRONT); }

        if (cube.getFront().getGridEntry(0,0) == prime && cube.getUp().getGridEntry(2,0) == second && cube.getLeft().getGridEntry(0,2) == third) {
            return new Location(Face_Enum.FRONT, Face_Enum.UP, Face_Enum.LEFT); }
        if (cube.getFront().getGridEntry(0,0) == prime && cube.getUp().getGridEntry(2,0) == third && cube.getLeft().getGridEntry(0,2) == second) {
            return new Location(Face_Enum.FRONT, Face_Enum.LEFT, Face_Enum.UP); }
        if (cube.getFront().getGridEntry(0,2) == prime && cube.getUp().getGridEntry(2,2) == second && cube.getRight().getGridEntry(0,0) == third) {
            return new Location(Face_Enum.FRONT, Face_Enum.UP, Face_Enum.RIGHT); }
        if (cube.getFront().getGridEntry(0,2) == prime && cube.getUp().getGridEntry(2,2) == third && cube.getRight().getGridEntry(0,0) == second) {
            return new Location(Face_Enum.FRONT, Face_Enum.RIGHT, Face_Enum.UP); }
        if (cube.getFront().getGridEntry(2,0) == prime && cube.getLeft().getGridEntry(2,2) == second && cube.getDown().getGridEntry(0,0) == third) {
            return new Location(Face_Enum.FRONT, Face_Enum.LEFT, Face_Enum.DOWN); }
        if (cube.getFront().getGridEntry(2,0) == prime && cube.getLeft().getGridEntry(2,2) == third && cube.getDown().getGridEntry(0,0) == second) {
            return new Location(Face_Enum.FRONT, Face_Enum.DOWN, Face_Enum.LEFT); }
        if (cube.getFront().getGridEntry(2,2) == prime && cube.getRight().getGridEntry(2,0) == second && cube.getDown().getGridEntry(0,2) == third) {
            return new Location(Face_Enum.FRONT, Face_Enum.RIGHT, Face_Enum.DOWN); }
        if (cube.getFront().getGridEntry(2,2) == prime && cube.getRight().getGridEntry(2,0) == third && cube.getDown().getGridEntry(0,2) == second) {
            return new Location(Face_Enum.FRONT, Face_Enum.DOWN, Face_Enum.RIGHT); }


        if (cube.getBack().getGridEntry(0,0) == prime && cube.getUp().getGridEntry(0,2) == second && cube.getRight().getGridEntry(0,2) == third) {
            return new Location(Face_Enum.BACK, Face_Enum.UP, Face_Enum.RIGHT); }
        if (cube.getBack().getGridEntry(0,0) == prime && cube.getUp().getGridEntry(0,2) == third && cube.getRight().getGridEntry(0,2) == second) {
            return new Location(Face_Enum.BACK, Face_Enum.RIGHT, Face_Enum.UP); }
        if (cube.getBack().getGridEntry(0,2) == prime && cube.getUp().getGridEntry(0,0) == second && cube.getLeft().getGridEntry(0,0) == third) {
            return new Location(Face_Enum.BACK, Face_Enum.UP, Face_Enum.LEFT); }
        if (cube.getBack().getGridEntry(0,2) == prime && cube.getUp().getGridEntry(0,0) == third && cube.getLeft().getGridEntry(0,0) == second) {
            return new Location(Face_Enum.BACK, Face_Enum.LEFT, Face_Enum.UP); }
        if (cube.getBack().getGridEntry(2,0) == prime && cube.getDown().getGridEntry(2,2) == second && cube.getRight().getGridEntry(2,2) == third) {
            return new Location(Face_Enum.BACK, Face_Enum.DOWN, Face_Enum.RIGHT); }
        if (cube.getBack().getGridEntry(2,0) == prime && cube.getDown().getGridEntry(2,2) == third && cube.getRight().getGridEntry(2,2) == second) {
            return new Location(Face_Enum.BACK, Face_Enum.RIGHT, Face_Enum.DOWN); }
        if (cube.getBack().getGridEntry(2,2) == prime && cube.getDown().getGridEntry(2,0) == second && cube.getLeft().getGridEntry(2,0) == third) {
            return new Location(Face_Enum.BACK, Face_Enum.DOWN, Face_Enum.LEFT); }
        if (cube.getBack().getGridEntry(2,2) == prime && cube.getDown().getGridEntry(2,0) == third && cube.getLeft().getGridEntry(2,0) == second) {
            return new Location(Face_Enum.BACK, Face_Enum.LEFT, Face_Enum.DOWN); }


        if (cube.getDown().getGridEntry(0,0) == prime && cube.getFront().getGridEntry(2,0) == second && cube.getLeft().getGridEntry(2,2) == third) {
            return new Location(Face_Enum.DOWN, Face_Enum.FRONT, Face_Enum.LEFT); }
        if (cube.getDown().getGridEntry(0,0) == prime && cube.getFront().getGridEntry(2,0) == third && cube.getLeft().getGridEntry(2,2) == second) {
            return new Location(Face_Enum.DOWN, Face_Enum.LEFT, Face_Enum.FRONT); }
        if (cube.getDown().getGridEntry(0,2) == prime && cube.getFront().getGridEntry(2,2) == second && cube.getRight().getGridEntry(2,0) == third) {
            return new Location(Face_Enum.DOWN, Face_Enum.FRONT, Face_Enum.RIGHT); }
        if (cube.getDown().getGridEntry(0,2) == prime && cube.getFront().getGridEntry(2,2) == third && cube.getRight().getGridEntry(2,0) == second) {
            return new Location(Face_Enum.DOWN, Face_Enum.RIGHT, Face_Enum.FRONT); }
        if (cube.getDown().getGridEntry(2,0) == prime && cube.getBack().getGridEntry(2,2) == second && cube.getLeft().getGridEntry(2,0) == third) {
            return new Location(Face_Enum.DOWN, Face_Enum.BACK, Face_Enum.LEFT); }
        if (cube.getDown().getGridEntry(2,0) == prime && cube.getBack().getGridEntry(2,2) == third && cube.getLeft().getGridEntry(2,0) == second) {
            return new Location(Face_Enum.DOWN, Face_Enum.LEFT, Face_Enum.BACK); }
        if (cube.getDown().getGridEntry(2,2) == prime && cube.getBack().getGridEntry(2,0) == second && cube.getRight().getGridEntry(2,2) == third) {
            return new Location(Face_Enum.DOWN, Face_Enum.BACK, Face_Enum.RIGHT); }
        if (cube.getDown().getGridEntry(2,2) == prime && cube.getBack().getGridEntry(2,0) == third && cube.getRight().getGridEntry(2,2) == second) {
            return new Location(Face_Enum.DOWN, Face_Enum.RIGHT, Face_Enum.BACK); }

        if (cube.getRight().getGridEntry(0,0) == prime && cube.getUp().getGridEntry(2,2) == second && cube.getFront().getGridEntry(0,2) == third) {
            return new Location(Face_Enum.RIGHT, Face_Enum.UP, Face_Enum.FRONT); }
        if (cube.getRight().getGridEntry(0,0) == prime && cube.getUp().getGridEntry(2,2) == third && cube.getFront().getGridEntry(0,2) == second) {
            return new Location(Face_Enum.RIGHT, Face_Enum.FRONT, Face_Enum.UP); }
        if (cube.getRight().getGridEntry(0,2) == prime && cube.getUp().getGridEntry(0,2) == second && cube.getBack().getGridEntry(0,0) == third) {
            return new Location(Face_Enum.RIGHT, Face_Enum.UP, Face_Enum.BACK); }
        if (cube.getRight().getGridEntry(0,2) == prime && cube.getUp().getGridEntry(0,2) == third && cube.getBack().getGridEntry(0,0) == second) {
            return new Location(Face_Enum.RIGHT, Face_Enum.BACK, Face_Enum.UP); }
        if (cube.getRight().getGridEntry(2,0) == prime && cube.getFront().getGridEntry(2,2) == second && cube.getDown().getGridEntry(0,2) == third) {
            return new Location(Face_Enum.RIGHT, Face_Enum.FRONT, Face_Enum.DOWN); }
        if (cube.getRight().getGridEntry(2,0) == prime && cube.getFront().getGridEntry(2,2) == third && cube.getDown().getGridEntry(0,2) == second) {
            return new Location(Face_Enum.RIGHT, Face_Enum.DOWN, Face_Enum.FRONT); }
        if (cube.getRight().getGridEntry(2,2) == prime && cube.getBack().getGridEntry(2,0) == second && cube.getDown().getGridEntry(2,2) == third) {
            return new Location(Face_Enum.RIGHT, Face_Enum.BACK, Face_Enum.DOWN); }
        if (cube.getRight().getGridEntry(2,2) == prime && cube.getBack().getGridEntry(2,0) == third && cube.getDown().getGridEntry(2,2) == second) {
            return new Location(Face_Enum.RIGHT, Face_Enum.DOWN, Face_Enum.BACK); }


        if (cube.getLeft().getGridEntry(0,0) == prime && cube.getUp().getGridEntry(0,0) == second && cube.getBack().getGridEntry(0,2) == third) {
            return new Location(Face_Enum.LEFT, Face_Enum.UP, Face_Enum.BACK); }
        if (cube.getLeft().getGridEntry(0,0) == prime && cube.getUp().getGridEntry(0,0) == third && cube.getBack().getGridEntry(0,2) == second) {
            return new Location(Face_Enum.LEFT, Face_Enum.BACK, Face_Enum.UP); }
        if (cube.getLeft().getGridEntry(0,2) == prime && cube.getUp().getGridEntry(2,0) == second && cube.getFront().getGridEntry(0,0) == third) {
            return new Location(Face_Enum.LEFT, Face_Enum.UP, Face_Enum.FRONT); }
        if (cube.getLeft().getGridEntry(0,2) == prime && cube.getUp().getGridEntry(2,0) == third && cube.getFront().getGridEntry(0,0) == second) {
            return new Location(Face_Enum.LEFT, Face_Enum.FRONT, Face_Enum.UP); }
        if (cube.getLeft().getGridEntry(2,0) == prime && cube.getBack().getGridEntry(2,2) == second && cube.getDown().getGridEntry(2,0) == third) {
            return new Location(Face_Enum.LEFT, Face_Enum.BACK, Face_Enum.DOWN); }
        if (cube.getLeft().getGridEntry(2,0) == prime && cube.getBack().getGridEntry(2,2) == third && cube.getDown().getGridEntry(2,0) == second) {
            return new Location(Face_Enum.LEFT, Face_Enum.DOWN, Face_Enum.BACK); }
        if (cube.getLeft().getGridEntry(2,2) == prime && cube.getFront().getGridEntry(2,0) == second && cube.getDown().getGridEntry(0,0) == third) {
            return new Location(Face_Enum.LEFT, Face_Enum.FRONT, Face_Enum.DOWN); }
        if (cube.getLeft().getGridEntry(2,2) == prime && cube.getFront().getGridEntry(2,0) == third && cube.getDown().getGridEntry(0,0) == second) {
            return new Location(Face_Enum.LEFT, Face_Enum.DOWN, Face_Enum.FRONT); }

        System.out.println("Not such corner exists");
        return null;
    }






    protected static void initialize(Cube cube, List<cmd> actions) {

        if (cube == null) {
            System.out.println("Error: 'initialize' arguments are null");
            return;
        }

        if (cube.getUp().getColor() != Cube.Color.RED) { // getting the red facet to the top
            if (cube.getBack().getColor() == Cube.Color.RED) {
                executeCMD(cmd.CMD_FLIP, actions, cube);
            } else if (cube.getDown().getColor() == Cube.Color.RED) {
                executeCMD(cmd.CMD_FLIP, actions, cube);
                executeCMD(cmd.CMD_FLIP, actions, cube);
            } else if (cube.getFront().getColor() == Cube.Color.RED) {
                executeCMD(cmd.CMD_FLIP, actions, cube);
                executeCMD(cmd.CMD_FLIP, actions, cube);
                executeCMD(cmd.CMD_FLIP, actions, cube);
            } else if (cube.getRight().getColor() == Cube.Color.RED) {
                executeCMD(cmd.CMD_LEFT_ROTATE, actions, cube);
                executeCMD(cmd.CMD_FLIP, actions, cube);
            } else if (cube.getLeft().getColor() == Cube.Color.RED) {
                executeCMD(cmd.CMD_RIGHT_ROTATE, actions, cube);
                executeCMD(cmd.CMD_FLIP, actions, cube);
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

    private static class Location {
        int x,y;
        Face_Enum name;
        Face_Enum secondDircetion;
        Face_Enum thirdDirection;
        Location(Face_Enum name, int x, int y){
            this.name = name;
            this.x = x;
            this.y = y;
            secondDircetion = null;
        }
        Location(Face_Enum name, Face_Enum directionOfSecond, Face_Enum directionOfThird){
            this.name = name;
            this.x = 10;
            this.y = 10;
            this.secondDircetion = directionOfSecond;
            this.thirdDirection = directionOfThird;
        }

        public String toString(){
            if(secondDircetion != null) {
                return ("Location: prime = " + name + ", secondary in: " + secondDircetion + ", third in: " + thirdDirection);
            }
            else{
                return ("Location: prime = " + name + ", x:" + x + ", y=" + y);
            }
        }
    }
}


