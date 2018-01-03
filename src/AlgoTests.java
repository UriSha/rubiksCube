import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// This class checks the validity of the algorithm
public class AlgoTests {
    /**
     *
     * @param cube - A standard cube
     * @param numOfTests - number of tests we want to run
     * @param numOfCommands - number of commands we want to manipulate the cube
     * @param commands - for checking the average of commands number
     * @return True if all the tests of the algorithm succeeded, False otherwise
     * Use randomization and maniulate the cube. runs the algorithm and check if the cube is solved.
     * Also updates the integer list and then we can check for average number of commands to solve each cube test
     */
     private static boolean testAlgorithm(Cube cube, int numOfTests, int numOfCommands, Integer[] commands) {

         for (int i = 0; i < numOfTests; i++) {
             for (int j = 0; j < numOfCommands; j++) {
                 int x = ThreadLocalRandom.current().nextInt(0, 8);
                 boolean y = ThreadLocalRandom.current().nextBoolean();
                 switch (x) {
                     case 0:
                         cube.twistUpperFace(y);
                         break;
                     case 1:
                         cube.twistFrontFace(y);
                         break;
                     case 2:
                         cube.twistBottomFace(y);
                         break;
                     case 3:
                         cube.twistRightFace(y);
                         break;
                     case 4:
                         cube.twistLeftFace(y);
                         break;
                     case 5:
                         cube.twistBackFace(y);
                     case 6:
                         cube.flip();
                     case 7:
                         cube.rotate(y);
                         break;
                 }

             }

             List<cmd> result = Logic.mainAlgorithm(cube);


             commands[i] = OptimizeCube.getNumOfAtomic(result);
         }
         return true;
     }
// Main test -  initialize solved cube ,gets two integers that represents number of tests the user wants to run
// and number of commands the user wants to manipulate the cube
    public static void main(String[] args){
        Cube.Color[][][] cubeValues = new Cube.Color[6][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[0][i][j] = Cube.Color.RED;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[1][i][j] = Cube.Color.BLUE;

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[2][i][j] = Cube.Color.ORANGE;

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[3][i][j] = Cube.Color.GREEN;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[4][i][j] = Cube.Color.YELLOW;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[5][i][j] = Cube.Color.WHITE;
            }
        }

        Cube cube = new Cube(cubeValues);
        System.out.println(cube);
        Integer[] commands=new Integer[300];
        System.out.println(testAlgorithm(cube,Integer.parseInt(args[1]),Integer.parseInt(args[2]),commands));
        int sum = 0;
        for (int d : commands) sum += d;
        System.out.println(sum/(double)commands.length);
        }
}
