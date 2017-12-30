import java.util.HashMap;
import java.util.Map;

public class CubePrintUtils {
    public static void main(String[] args) {
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
        System.out.println(cube.toString());
        System.out.println(cube.isValidCube());
        cube.twistUpperFace(true);
//        System.out.println(toStringCube(cube));
        System.out.println(cube.isValidCube());
        cube.twistBottomFace(false);
//        System.out.println(toStringCube(cube));
        System.out.println(cube.isValidCube());
        cube.twistFrontFace(true);
//        System.out.println(toStringCube(cube));
        System.out.println(cube.isValidCube());
        cube.flip();
//        System.out.println(toStringCube(cube));
        System.out.println(cube.isValidCube());
        cube.twistFrontFace(false);
        System.out.println(cube.isValidCube());
        cube.twistFrontFace(false);
        System.out.println(cube.isValidCube());
        cube.twistLeftFace(false);
        System.out.println(cube.isValidCube());
        cube.twistBottomFace(false);
        System.out.println(cube.isValidCube());
        cube.twistBottomFace(false);
        System.out.println(cube.isValidCube());
//        System.out.println(toStringCube(cube));
        cube.flip();
//        System.out.println(toStringCube(cube));
        System.out.println(cube.isValidCube());
        cube.twistRightFace(true);
        System.out.println(cube.isValidCube());
        cube.rotate(true);
        cube.rotate(true);
        cube.rotate(false);
        System.out.println(cube.isValidCube());
        cube.twistRightFace(true);
        System.out.println(cube.isValidCube());
        cube.twistRightFace(false);
        System.out.println(cube.isValidCube());
        cube.twistUpperFace(false);
        System.out.println(cube.isValidCube());
        cube.twistUpperFace(true);
        System.out.println(cube.isValidCube());
        cube.twistUpperFace(true);
        System.out.println(cube.isValidCube());
        cube.twistUpperFace(true);
        System.out.println(cube.isValidCube());


//        System.out.println(Cube.Color.ORANGE - Cube.Color.BLUE);


        Map<Cube.Color[], Integer> subCubes = new HashMap<>();
        subCubes.put(new Cube.Color[]{Cube.Color.BLUE},1);
        subCubes.put(new Cube.Color[]{Cube.Color.BLUE},2);
        int x = 0;

    }


    static char getChar(Cube.Color c) {
        return c.name().charAt(0);
    }

    static void topAndButtomBorders(StringBuilder result) {
        for (int i = 0; i < 10; i++)
            result.append(' ');


        boolean lineOrSpace = true;

        for (int i = 0; i < 6; i++) {
            result.append(lineOrSpace ? '-' : ' ');
            lineOrSpace = !lineOrSpace;
        }
        for (int i = 0; i < 17; i++)
            result.append(' ');

        result.append('\n');
    }

    static void upAndDownFaces(StringBuilder result, Cube.Color[][] grid) {
        boolean charOrSpace = true;

        int row = 0;
        while (row < 3) {

            for (int i = 0; i < 8; i++)
                result.append(' ');
            result.append('|');
            result.append(' ');


            for (int i = 0; i < 6; i++) {
                result.append(charOrSpace ? getChar(grid[row][i / 2]) : ' ');
                charOrSpace = !charOrSpace;
            }
            result.append('|');
            for (int i = 0; i < 17; i++)
                result.append(' ');

            result.append('\n');
            row++;
        }
    }

    static void middleBorders(StringBuilder result) {

        int numOfFaces = 0;
        while (numOfFaces < 4) {

            result.append(' ');
            result.append(' ');


            boolean lineOrSpace = true;

            for (int i = 0; i < 6; i++) {
                result.append(lineOrSpace ? '-' : ' ');
                lineOrSpace = !lineOrSpace;
            }
            numOfFaces++;
        }
        result.append(' ');
        result.append('\n');
    }

    static void fourMiddleFaces(StringBuilder result, Cube.Color[][] left, Cube.Color[][] front, Cube.Color[][] right, Cube.Color[][] back) {
        Cube.Color[][][] faces = new Cube.Color[4][3][3];
        faces[0] = left;
        faces[1] = front;
        faces[2] = right;
        faces[3] = back;

        int row = 0;

        while (row < 3) {
            int numOfCubes = 0;
            while (numOfCubes < 4) {
                result.append('|');
                result.append(' ');


                boolean charOrSpace = true;

                for (int i = 0; i < 6; i++) {
                    result.append(charOrSpace ? getChar(faces[numOfCubes][row][i / 2]) : ' ');
                    charOrSpace = !charOrSpace;
                }
                numOfCubes++;
            }
            result.append('|');
            result.append('\n');
            row++;
        }
    }

}
