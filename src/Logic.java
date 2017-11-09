import java.util.ArrayList;
import java.util.List;
import Cube.*;




/**
 * Created by mac_ori on 09/11/2017.
 */
public class Logic{

    public List<Action> mainAlgorithm(Cube cube){
        List<Action> result = new ArrayList<>();
        initialize(cube, result);



        return result;
    }

    public static void initialize(Cube cube, List<Action> actions){

        if (cube.getUp().getColor() != Cube.Color.RED){
            if (cube.getBack().getColor() != Cube.Color.RED){
                actions.add(Action.FLIP);
                cube.flip()
            }
        }



        //        for (int i = 0; i < theCube.cubeRep.length; i++) {// finding the red facet
//            if (theCube.cubeRep[i].centerColor == 0){
//                switch (theCube.cubeRep[i].dir){ // getting the red facet to the top
//                    case UP:
//                        break;
//                    case BACK:
//                        actionsList.add(new action(Job.FLIP, 1));
//                        break;
//                    case DOWN:
//                        actionsList.add(new action(Job.FLIP, 2));
//                        break;
//                    case FRONT:
//                        actionsList.add(new action(Job.FLIP, 3));
//                        break;
//                    case LEFT:
//                        actionsList.add(new action(Job.RIGHT_ROTATE, 1));
//                        actionsList.add(new action(Job.FLIP, 1));
//                        side tmp = theCube.cubeRep[0];
//                        theCube.cubeRep[0] = theCube.cubeRep[5];
//                        theCube.cubeRep[5] = theCube.cubeRep[1];
//                        theCube.cubeRep[1] = tmp;
//
//                        theCube.cubeRep[1] = tmp;
//                        break;
////                    break;
//                    case RIGHT:
//                        actionsList.add(new action(Job.LEFT_ROTATE, 1));
//                        actionsList.add(new action(Job.FLIP, 1));
//                        break;
//                }
//                break;
//            }
//        }
//
//    }
    }
}
