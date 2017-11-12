

import java.util.ArrayList;
import java.util.List;




public class Logic {

//    public enum facet{
//        UP,
//        DOWN,
//        LEFT,
//        RIGHT,
//        FRONT,
//        BACK
//    }

    public List<cmd> mainAlgorithm(Cube cube) {
        List<cmd> result = new ArrayList<>();
        int algoStage = 0;
        stageOne(cube, result);

        return result;
    }

    private static void frontRedYellow(List<cmd> actions,Cube cube){
        executeCMD(cmd.CMD_RIGHT_TWIST_FRONTUPWARD,actions,cube);
        executeCMD(cmd.CMD_LEFT_TWIST_FRONTUPWARD,actions,cube);
        executeCMD(cmd.CMD_DOWN_TWIST_RIGHT,actions,cube);
        executeCMD(cmd.CMD_RIGHT_TWIST_BACKUPWARD,actions,cube);
        executeCMD(cmd.CMD_LEFT_TWIST_BACKUPWARD,actions,cube);
        executeCMD(cmd.CMD_DOWN_TWIST_LEFT,actions,cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);

    }
    private static void downRedYellow(List<cmd> actions,Cube cube){
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
        executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
    }

    private static void stageOne(Cube cube, List<cmd> actions) {

        if (cube == null || actions == null) {
            System.out.println("Error: 'stageOne' arguments are null");
            return;
        }

        Location redYellow = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.YELLOW);
        if(redYellow.name==null)
        {
            System.out.println("Error: 'stageOne' has failed");
            return ;
        }
        switch(redYellow.name){
            case UP:
                if (redYellow.x == 0 && redYellow.y == 1){
                    executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                    executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                } else if (redYellow.x == 1 && redYellow.y == 0){
                    executeCMD(cmd.CMD_UP_TWIST_RIGHT, actions, cube);
                } else if (redYellow.x == 1 && redYellow.y == 2){
                    executeCMD(cmd.CMD_UP_TWIST_LEFT, actions, cube);
                }
                break;
            case FRONT:
                if (redYellow.x == 0 && redYellow.y == 1){
                    frontRedYellow(actions,cube);

                }
                else if(redYellow.x==1 && redYellow.y==0){
                    executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
                    frontRedYellow(actions,cube);
                }
                else if(redYellow.x==1 && redYellow.y==2){
                    executeCMD(cmd.CMD_FRONT_TWIST_C_CLOCKWISE,actions,cube);
                    frontRedYellow(actions,cube);
                }
                else if(redYellow.x==2 && redYellow.y==1){
                    executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
                    executeCMD(cmd.CMD_FRONT_TWIST_CLOCKWISE,actions,cube);
                    frontRedYellow(actions,cube);
                }
            case DOWN:
                if(redYellow.x==0 && redYellow.y==1){
                    downRedYellow(actions,cube);
                }
                else if(redYellow.x==1 && redYellow.y==0){
                    executeCMD(cmd.CMD_DOWN_TWIST_RIGHT,actions,cube);
                    downRedYellow(actions,cube);
                }else if(redYellow.x==1 && redYellow.y==2){
                    executeCMD(cmd.CMD_DOWN_TWIST_LEFT,actions,cube);
                    downRedYellow(actions,cube);
                }
        }
        Location redWhite = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.WHITE);
        Location redBlue = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.BLUE);
        Location redGreen = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.GREEN);



    }

    public static void executeCMD(cmd command,List<cmd> actions, Cube cube){
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


    private static List<cmd> initialize(Cube cube) {
        List<cmd> actions = new ArrayList<>();

        if (cube == null) {
            System.out.println("Error: 'initialize' arguments are null");
            return null;
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

        if (cube.getFront().getColor() != Cube.Color.YELLOW) {
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

        return actions;
    }

    private static class Location {
        int x,y;
        Face_Enum name;
        public Location(Face_Enum name, int x, int y){
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }
}


