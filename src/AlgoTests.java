import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//import java.util.List;
//import java.util.concurrent.ThreadLocalRandom;
//
//// This class checks the validity of the algorithm
public class AlgoTests {
    //    /**
//     *
//     * @param cube - A standard cube
//     * @param numOfTests - number of tests we want to run
//     * @param numOfCommands - number of commands we want to manipulate the cube
//     * @param commands - for checking the average of commands number
//     * @return True if all the tests of the algorithm succeeded, False otherwise
//     * Use randomization and maniulate the cube. runs the algorithm and check if the cube is solved.
//     * Also updates the integer list and then we can check for average number of commands to solve each cube test
//     */
//     private static boolean testAlgorithm(Cube cube, int numOfTests, int numOfCommands, Integer[] commands) {
//
//         for (int i = 0; i < numOfTests; i++) {
//             for (int j = 0; j < numOfCommands; j++) {
//                 int x = ThreadLocalRandom.current().nextInt(0, 8);
//                 boolean y = ThreadLocalRandom.current().nextBoolean();
//                 switch (x) {
//                     case 0:
//                         cube.twistUpperFace(y);
//                         break;
//                     case 1:
//                         cube.twistFrontFace(y);
//                         break;
//                     case 2:
//                         cube.twistBottomFace(y);
//                         break;
//                     case 3:
//                         cube.twistRightFace(y);
//                         break;
//                     case 4:
//                         cube.twistLeftFace(y);
//                         break;
//                     case 5:
//                         cube.twistBackFace(y);
//                     case 6:
//                         cube.flip();
//                     case 7:
//                         cube.rotate(y);
//                         break;
//                 }
//
//             }
//
//             List<cmd> result = Logic.mainAlgorithm(cube);
//
//
//             commands[i] = OptimizeCube.getNumOfAtomic(result);
//         }
//         return true;
//     }
//// Main test -  initialize solved cube ,gets two integers that represents number of tests the user wants to run
//// and number of commands the user wants to manipulate the cube

    public static void main(String[] args) {
        Cube cube=CubeUtils.getRandomCube();
        List<cmd> actions = new ArrayList<>();
        System.out.println(cube);
        Logic.initialize(cube, actions);
        System.out.println(cube);
        Logic.stageOne(cube, actions);
        System.out.println(cube);
        Logic.stageTwo(cube, actions);
        Logic.flipForStageThree(cube, actions);
        Logic.stageThree(cube, actions);
        System.out.println(cube);
        Logic.stageFour(cube, actions);
        System.out.println(cube);
//        System.out.println(cube);
//        Integer[] commands=new Integer[300];
//        System.out.println(testAlgorithm(cube,Integer.parseInt(args[0]),Integer.parseInt(args[1]),commands));
//        int sum = 0;
////        for (int d : commands) sum += d;
////        System.out.println(sum/(double)commands.length);
//        Logic.Location redYellow=LogicUtils.getLocationOfEdge(cube,Cube.Color.RED, Cube.Color.YELLOW);
//        System.out.println(redYellow.secondDircetion);
    }
}
