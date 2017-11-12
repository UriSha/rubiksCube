

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
                    backRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==0){
                    executeCMD(cmd.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                    backRedEdge(actions, cube);
                } else if (redEdge.x==2 && redEdge.y==1){
                    executeCMD(cmd.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                    executeCMD(cmd.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
                    backRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==2){
                    executeCMD(cmd.CMD_BACK_TWIST_C_CLOCKWISE, actions, cube);
                    backRedEdge(actions, cube);
                } break;
            case LEFT:
                if(redEdge.x==0 && redEdge.y==1){
                    leftRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==0){
                    executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    leftRedEdge(actions, cube);
                } else if (redEdge.x==2 && redEdge.y==1){
                    executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
                    leftRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==2){
                    executeCMD(cmd.CMD_LEFT_TWIST_FRONTUPWARD, actions, cube);
                    leftRedEdge(actions, cube);
                } break;
            case RIGHT:
                if(redEdge.x==0 && redEdge.y==1){
                    rightRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==0){
                    executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
                    rightRedEdge(actions, cube);
                } else if (redEdge.x==2 && redEdge.y==1){
                    executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
                    executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, actions, cube);
                    rightRedEdge(actions, cube);
                } else if (redEdge.x==1 && redEdge.y==2){
                    executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
                    rightRedEdge(actions, cube);
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
        executeCMD(cmd.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_BACK_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        frontRedEdge(actions, cube);
    }

    private static void leftRedEdge(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        frontRedEdge(actions, cube);
    }

    private static void rightRedEdge(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD, actions, cube);
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE, actions, cube);
        frontRedEdge(actions, cube);
    }

    private static void downRedEdge(List<cmd> actions, Cube cube){
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
    }

    protected static void stageOne(Cube cube, List<cmd> actions) {

        if (cube == null || actions == null) {
            System.out.println("Error: 'stageOne' arguments are null");
            return;
        }
        Location redYellow = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.YELLOW);



        getRedCross(cube, actions,redYellow );
        System.out.println("RedYellow in place");//Debugging
        cube.rotate(true);
        Location redGreen = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.GREEN);

        getRedCross(cube, actions,redGreen );
        System.out.println("RedGreen in place");//Debugging
        cube.rotate(true);
        Location redWhite = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.WHITE);
        getRedCross(cube, actions,redWhite );
        System.out.println("RedWhite in place");//Debugging
        cube.rotate(true);
        Location redBlue = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.BLUE);
        getRedCross(cube, actions,redBlue );
        System.out.println("RedBlue in place");//Debugging
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
                cube.twistLeftFace(true);
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


    protected static void initialize(Cube cube, List<cmd> actions) {

        if (cube == null) {
            System.out.println("Error: 'initialize' arguments are null");
            return;
        }

        if (cube.getUp().getColor() != Cube.Color.RED) { // getting the red facet to the top
            if (cube.getBack().getColor() == Cube.Color.RED) {
                actions.add(cmd.CMD_FLIP);
                cube.flip();
                actions.add(cmd.CMD_FLIP);
                cube.flip();
                actions.add(cmd.CMD_FLIP);
                cube.flip();
            } else if (cube.getDown().getColor() == Cube.Color.RED) {
                actions.add(cmd.CMD_FLIP);
                cube.flip();
                actions.add(cmd.CMD_FLIP);
                cube.flip();
            } else if (cube.getFront().getColor() == Cube.Color.RED) {
                actions.add(cmd.CMD_FLIP);
                cube.flip();
            } else if (cube.getRight().getColor() == Cube.Color.RED) {
                cube.rotate(false);
                actions.add(cmd.CMD_LEFT_ROTATE);
                cube.flip();
                actions.add(cmd.CMD_FLIP);
            } else if (cube.getLeft().getColor() == Cube.Color.RED) {
                cube.rotate(true);
                actions.add(cmd.CMD_RIGHT_ROTATE);
                cube.flip();
                actions.add(cmd.CMD_FLIP);
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
        Location(Face_Enum name, int x, int y){
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }
}


