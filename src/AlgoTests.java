/**
 * Created by mac_ori on 12/11/2017.
 */
public class AlgoTests {

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

        System.out.println(tempClassForPrint.toStringCube(cube));

//        cube.twistBottomFace(false);
//        cube.flip();
//        cube.twistBackFace(true);
//        cube.twistBackFace(true);
//        cube.twistUpperFace(false);
//        cube.twistBottomFace(false);
//        cube.flip();
//        cube.twistFrontFace(false);
//        cube.flip();
//        cube.twistFrontFace(false);
//        cube.twistRightFace(true);
//        cube.twistUpperFace(false);
//        cube.twistBottomFace(false);
//        cube.twistRightFace(true);
//        cube.twistLeftFace(true);
//        cube.twistUpperFace(false);
//        cube.twistFrontFace(false);
//        cube.twistLeftFace(true);
//        cube.twistUpperFace(false);
//        cube.twistBottomFace(false);
//        cube.twistRightFace(true);
//        cube.twistLeftFace(true);
//        cube.twistBottomFace(false);
//        cube.twistRightFace(true);
//        cube.twistLeftFace(true);
//        cube.twistUpperFace(false);
//        cube.twistBottomFace(false);
//        cube.twistFrontFace(false);
//        cube.flip();
//        cube.twistBackFace(true);
//        cube.twistFrontFace(false);
//        cube.twistLeftFace(true);
//
//
//        System.out.println(tempClassForPrint.toStringCube(cube));
//
//        List<cmd> result = new ArrayList<>();
//        int algoStage = 0;
//        Logic.initialize(cube, result);
//        System.out.println(tempClassForPrint.toStringCube(cube));
//
//        Logic.stageOne(cube, result);
//
//        System.out.println(tempClassForPrint.toStringCube(cube));
//
//        System.out.println(result.toString());

        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.YELLOW)); // UP 2,2 FRONT
        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.YELLOW, Cube.Color.GREEN)); // UP 2,2 Right
        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.YELLOW, Cube.Color.GREEN, Cube.Color.RED)); // RIGHT 0,0 Front

        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.WHITE)); // UP 2,0 FRONT
        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.WHITE, Cube.Color.GREEN)); // UP 2,0 Left
        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.WHITE, Cube.Color.RED, Cube.Color.GREEN)); // Left 0,2 Up

        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.WHITE, Cube.Color.BLUE)); // UP 0,0 Left
        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.WHITE)); // UP 0,0 Back


        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.YELLOW)); // UP 0,2 BACK
        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.RED, Cube.Color.YELLOW, Cube.Color.BLUE)); // UP 0,2 RIGHT


        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.ORANGE, Cube.Color.BLUE, Cube.Color.YELLOW)); // Down 2,2 Back
        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.YELLOW, Cube.Color.ORANGE, Cube.Color.BLUE)); // Right 2,2 Down
        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.BLUE, Cube.Color.ORANGE, Cube.Color.YELLOW)); // Back 2,0 Down

        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.ORANGE, Cube.Color.BLUE, Cube.Color.WHITE)); // DOWN 2,0 Back
        System.out.println(Logic.getLocationOfCorner(cube, Cube.Color.WHITE, Cube.Color.ORANGE, Cube.Color.BLUE)); // Left 2,0 Down


    }

}
