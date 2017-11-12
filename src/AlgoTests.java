import java.util.ArrayList;
import java.util.List;

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

        cube.twistBottomFace(false);
        cube.flip();
        cube.twistBackFace(true);
        cube.twistFrontFace(false);
        cube.twistFrontFace(false);
        cube.twistRightFace(true);
        cube.twistLeftFace(true);
        cube.twistUpperFace(false);
        cube.twistBottomFace(false);
        cube.twistBackFace(true);
        cube.twistFrontFace(false);
        cube.twistFrontFace(false);
        cube.twistRightFace(true);
        cube.twistLeftFace(true);
        cube.twistUpperFace(false);
        cube.twistRightFace(true);
        cube.twistLeftFace(true);
        cube.twistLeftFace(true);
        cube.flip();
        cube.twistUpperFace(false);
        cube.twistRightFace(true);

        System.out.println(tempClassForPrint.toStringCube(cube));

        List<cmd> result = new ArrayList<>();
        int algoStage = 0;
        Logic.initialize(cube, result);
        System.out.println(tempClassForPrint.toStringCube(cube));

        Logic.stageOne(cube, result);

        System.out.println(tempClassForPrint.toStringCube(cube));

        System.out.println(result.toString());

    }

}
