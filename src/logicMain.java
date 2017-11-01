import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac_ori on 01/11/2017.
 *
 */

public class logicMain {

    public enum Job {LEFT_ROTATE, RIGHT_ROTATE, FLIP, UP_TWIST, DOWN_TWIST, LEFT_TWIST, RIGHT_TWIST, FRONT_TWIST, BACK_TWIST}

    public static List<action> actionsList= new ArrayList<action>();
    public static cube theCube;



    public class action{
        Job move;
        int times;

        public action(Job move , int times){
            this.move = move;
            this.times = times;
        }
    }

    public ArrayList<action> getActionsList (){
        return (ArrayList<action>) actionsList;
    }


    /**
     * Initializing the cube so the red is up and the blue is in the front
     */
    public void initializeCube() {
        for (int i = 0; i < theCube.cubeRep.length; i++) {// finding the red facet
            if (theCube.cubeRep[i].centerColor == 0){
                switch (theCube.cubeRep[i].dir){ // getting the red facet to the top
                    case UP:
                        break;
                    case BACK:
                        actionsList.add(new action(Job.FLIP, 1));
                        break;
                    case DOWN:
                        actionsList.add(new action(Job.FLIP, 2));
                        break;
                    case FRONT:
                        actionsList.add(new action(Job.FLIP, 3));
                        break;
                    case LEFT:
                        actionsList.add(new action(Job.RIGHT_ROTATE, 1));
                        actionsList.add(new action(Job.FLIP, 1));
                        side tmp = theCube.cubeRep[0];
                        theCube.cubeRep[0] = theCube.cubeRep[5];
                        theCube.cubeRep[5] = theCube.cubeRep[1];
                        theCube.cubeRep[1] = tmp;

                        theCube.cubeRep[1] = tmp;
                        break;
//                    break;
                    case RIGHT:
                        actionsList.add(new action(Job.LEFT_ROTATE, 1));
                        actionsList.add(new action(Job.FLIP, 1));
                        break;
                }
                break;
            }
        }

    }

    public void flipCube(){
        side tmpFacet;
        int tmp;
        // changing places of the facets
        tmpFacet = theCube.cubeRep[0];
        theCube.cubeRep[0] = theCube.cubeRep[3];
        theCube.cubeRep[0].dir = side.Direction.UP;
        theCube.cubeRep[3] = theCube.cubeRep[2];
        theCube.cubeRep[3].dir = side.Direction.BACK;
        theCube.cubeRep[2] = theCube.cubeRep[1];
        theCube.cubeRep[2].dir = side.Direction.DOWN;
        theCube.cubeRep[1] = tmpFacet;
        theCube.cubeRep[1].dir = side.Direction.FRONT;
        //changing places of the small facelets

        //right side
        tmp = theCube.cubeRep[4].facet[0][0];
        theCube.cubeRep[4].facet[0][0] = theCube.cubeRep[4].facet[0][2];
        theCube.cubeRep[4].facet[0][2] = theCube.cubeRep[4].facet[2][2];
        theCube.cubeRep[4].facet[2][2] = theCube.cubeRep[4].facet[2][0];
        theCube.cubeRep[4].facet[2][0] = tmp;
        tmp = theCube.cubeRep[4].facet[0][1];
        theCube.cubeRep[4].facet[0][1] = theCube.cubeRep[4].facet[1][2];
        theCube.cubeRep[4].facet[1][2] = theCube.cubeRep[4].facet[2][1];
        theCube.cubeRep[4].facet[2][1] = theCube.cubeRep[4].facet[1][0];
        theCube.cubeRep[4].facet[1][0] = tmp;

        //left side
        tmp = theCube.cubeRep[5].facet[0][0];
        theCube.cubeRep[5].facet[0][0] = theCube.cubeRep[5].facet[2][0];
        theCube.cubeRep[5].facet[2][0] = theCube.cubeRep[5].facet[2][2];
        theCube.cubeRep[5].facet[2][2] = theCube.cubeRep[5].facet[0][2];
        theCube.cubeRep[5].facet[0][2] = tmp;
        tmp = theCube.cubeRep[5].facet[0][1];
        theCube.cubeRep[5].facet[0][1] = theCube.cubeRep[5].facet[1][0];
        theCube.cubeRep[5].facet[1][0] = theCube.cubeRep[5].facet[2][1];
        theCube.cubeRep[5].facet[2][1] = theCube.cubeRep[5].facet[1][2];
        theCube.cubeRep[5].facet[1][2] = tmp;

        //front adjusted as the up it was
        //back adjusted as the down it was

    }
}
