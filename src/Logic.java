

import java.util.ArrayList;
import java.util.List;


public class Logic {

    enum facet{
        UP,
        DOWN,
        LEFT,
        RIGHT,
        FRONT,
        BACK
    }

    public List<cmd> mainAlgorithm(Cube cube) {
        List<cmd> result = new ArrayList<>();
        int algoStage = 0;
        initialize(cube, result);
        stageOne(cube, result);

        return result;
    }

    private static void stageOne(Cube cube, List<cmd> actions) {

        if (cube == null || actions == null) {
            System.out.println("Error: 'stageZero' arguments are null");
            return;
        }

        location redYellow = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.YELLOW);
        switch(redYellow.name){
            case UP:
                if (redYellow.x == 0 && redYellow.y == 1){
                    cube.twistUpperFace(true);
                    cube.twistUpperFace(true);
                    actions.add(cmd.UP_TWIST_RIGHT);
                    actions.add(cmd.UP_TWIST_RIGHT);
                } else if (redYellow.x == 1 && redYellow.y == 0){
                    cube.twistUpperFace(true);
                    actions.add(cmd.UP_TWIST_RIGHT);
                } else if (redYellow.x == 1 && redYellow.y == 2){
                    cube.twistUpperFace(false);
                    actions.add(cmd.UP_TWIST_LEFT);
                }
                break;
            case FRONT:
                if (redYellow.x == 0 && redYellow.y == 1){
                    actions.add(cmd.RIGHT_TWIST_FRONTUPWARD);
                    actions.add(cmd.LEFT_TWIST_FRONTUPWARD);
                    actions.add(cmd.DOWN_TWIST_RIGHT);
                    actions.add(cmd.RIGHT_TWIST_BACKUPWARD);
                    actions.add(cmd.LEFT_TWIST_BACKUPWARD);
                    actions.add(cmd.DOWN_TWIST_LEFT);
                    actions.add(cmd.FRONT_TWIST_CLOCKWISE);
                    actions.add(cmd.FRONT_TWIST_CLOCKWISE);
                    cube.twistRightFace(true);
                    cube.twistLeftFace(true);
                    cube.twistBottomFace(true);
                }
        }





        location redWhite = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.WHITE);
        location redBlue = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.BLUE);
        location redGreen = getLocationOfEdge(cube, Cube.Color.RED, Cube.Color.GREEN);


    }




    private static location getLocationOfEdge(Cube cube, Cube.Color prime, Cube.Color second){

        if (cube.getUp().getGridEntry(0,1) == prime && cube.getBack().getGridEntry(0,1) == second){ return new location(facet.UP, 0, 1); }
        if (cube.getUp().getGridEntry(1,0) == prime && cube.getLeft().getGridEntry(0,1) == second){ return new location(facet.UP, 1, 0); }
        if (cube.getUp().getGridEntry(1,2) == prime && cube.getRight().getGridEntry(0,1) == second){ return new location(facet.UP, 1, 2); }
        if (cube.getUp().getGridEntry(2,1) == prime && cube.getFront().getGridEntry(0,1) == second){ return new location(facet.UP, 2, 1); }

        if (cube.getFront().getGridEntry(0,1) == prime && cube.getUp().getGridEntry(2,1) == second){ return new location(facet.FRONT, 0, 1); }
        if (cube.getFront().getGridEntry(1,0) == prime && cube.getLeft().getGridEntry(1,2) == second){ return new location(facet.FRONT, 1, 0); }
        if (cube.getFront().getGridEntry(1,2) == prime && cube.getRight().getGridEntry(1,0) == second){ return new location(facet.FRONT, 1, 2); }
        if (cube.getFront().getGridEntry(2,1) == prime && cube.getDown().getGridEntry(0,1) == second){ return new location(facet.FRONT, 2, 1); }

        if (cube.getDown().getGridEntry(0,1) == prime && cube.getFront().getGridEntry(2,1) == second){ return new location(facet.DOWN, 0, 1); }
        if (cube.getDown().getGridEntry(1,0) == prime && cube.getLeft().getGridEntry(2,1) == second){ return new location(facet.DOWN, 1, 0); }
        if (cube.getDown().getGridEntry(1,2) == prime && cube.getRight().getGridEntry(2,1) == second){ return new location(facet.DOWN, 1, 2); }
        if (cube.getDown().getGridEntry(2,1) == prime && cube.getBack().getGridEntry(2,1) == second){ return new location(facet.DOWN, 2, 1); }

        if (cube.getBack().getGridEntry(0,1) == prime && cube.getUp().getGridEntry(0,1) == second){ return new location(facet.BACK, 0, 1); }
        if (cube.getBack().getGridEntry(1,0) == prime && cube.getRight().getGridEntry(1,2) == second){ return new location(facet.BACK, 1, 0); }
        if (cube.getBack().getGridEntry(1,2) == prime && cube.getLeft().getGridEntry(1,0) == second){ return new location(facet.BACK, 1, 2); }
        if (cube.getBack().getGridEntry(2,1) == prime && cube.getDown().getGridEntry(2,1) == second){ return new location(facet.BACK, 2, 1); }

        if (cube.getLeft().getGridEntry(0,1) == prime && cube.getUp().getGridEntry(1,0) == second){ return new location(facet.LEFT, 0, 1); }
        if (cube.getLeft().getGridEntry(1,0) == prime && cube.getBack().getGridEntry(1,2) == second){ return new location(facet.LEFT, 1, 0); }
        if (cube.getLeft().getGridEntry(1,2) == prime && cube.getFront().getGridEntry(1,0) == second){ return new location(facet.LEFT, 1, 2); }
        if (cube.getLeft().getGridEntry(2,1) == prime && cube.getDown().getGridEntry(1,0) == second){ return new location(facet.LEFT, 2, 1); }

        if (cube.getRight().getGridEntry(0,1) == prime && cube.getUp().getGridEntry(1,2) == second){ return new location(facet.RIGHT, 0, 1); }
        if (cube.getRight().getGridEntry(1,0) == prime && cube.getFront().getGridEntry(1,2) == second){ return new location(facet.RIGHT, 1, 0); }
        if (cube.getRight().getGridEntry(1,2) == prime && cube.getBack().getGridEntry(1,0) == second){ return new location(facet.RIGHT, 1, 2); }
        if (cube.getRight().getGridEntry(2,1) == prime && cube.getDown().getGridEntry(1,2) == second){ return new location(facet.RIGHT, 2, 1); }

        return null;
    }


    private static void initialize(Cube cube, List<cmd> actions) {

        if (cube == null || actions == null) {
            System.out.println("Error: 'initialize' arguments are null");
            return;
        }

        if (cube.getUp().getColor() != Cube.Color.RED) { // getting the red facet to the top
            if (cube.getBack().getColor() == Cube.Color.RED) {
                actions.add(cmd.FLIP);
                cube.flip();
                actions.add(cmd.FLIP);
                cube.flip();
                actions.add(cmd.FLIP);
                cube.flip();
            } else if (cube.getDown().getColor() == Cube.Color.RED) {
                actions.add(cmd.FLIP);
                cube.flip();
                actions.add(cmd.FLIP);
                cube.flip();
            } else if (cube.getFront().getColor() == Cube.Color.RED) {
                actions.add(cmd.FLIP);
                cube.flip();
            } else if (cube.getRight().getColor() == Cube.Color.RED) {
                cube.rotate(false);
                actions.add(cmd.LEFT_ROTATE);
                cube.flip();
                actions.add(cmd.FLIP);
            } else if (cube.getLeft().getColor() == Cube.Color.RED) {
                cube.rotate(true);
                actions.add(cmd.RIGHT_ROTATE);
                cube.flip();
                actions.add(cmd.FLIP);
            }
        }

        if (cube.getFront().getColor() != Cube.Color.YELLOW) {
            if (cube.getLeft().getColor() == Cube.Color.YELLOW) {
                actions.add(cmd.RIGHT_ROTATE);
                cube.rotate(true);
            } else if (cube.getRight().getColor() == Cube.Color.YELLOW) {
                actions.add(cmd.LEFT_ROTATE);
                cube.rotate(false);
            } else if (cube.getBack().getColor() == Cube.Color.YELLOW) {
                actions.add(cmd.LEFT_ROTATE);
                cube.rotate(false);
                actions.add(cmd.LEFT_ROTATE);
                cube.rotate(false);
            }
        }
    }

    private static class location {
        int x,y;
        facet name;
        public location(facet name, int x, int y){
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }

}


