import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

class CubeUtils {

    private static char getChar(Cube.Color c) {
        return c.name().charAt(0);
    }

    static void topAndBottomBorders(StringBuilder result) {
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

    static Cube initialDoneCube() {
        Cube.Color[][][] cubeValues = new Cube.Color[6][3][3];

        // up
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[0][i][j] = Cube.Color.RED;
            }
        }

        // back
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[1][i][j] = Cube.Color.BLUE;

            }
        }

        // down
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[2][i][j] = Cube.Color.ORANGE;

            }
        }

        // front
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[3][i][j] = Cube.Color.GREEN;
            }
        }

        // right
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[4][i][j] = Cube.Color.YELLOW;
            }
        }

        // left
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubeValues[5][i][j] = Cube.Color.WHITE;
            }
        }

        return new Cube(cubeValues);
    }

    static Cube getRandomCube() {
        Cube cube = initialDoneCube();

        for (int j = 0; j < 100; j++) {
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
        return cube;
    }

    static boolean isValidCube(Cube cube) {
        Map<SubCube, Integer> subCubes = new HashMap<>();
        populateSubCubes(subCubes);

        // Top face
        if (!checkTopFace(cube, subCubes))
            return false;

        // Middle sub-cubes
        if (!checkMiddleSubCubes(cube, subCubes))
            return false;

        // Bottom face
        if (!checkBottomFace(cube, subCubes))
            return false;

        // Check whether the centers have the valid colors
        if (!checkCentersColorValidity(cube))
            return false;

        return true;
    }


    /**
     * Populates the Hashmap with the relevant sub-cubes
     *
     * @param subCubes - the map to be populated
     */
    private static void populateSubCubes(Map<SubCube, Integer> subCubes) {

        // 2's
        subCubes.put(new SubCube(Cube.Color.RED, Cube.Color.GREEN), 0);
        subCubes.put(new SubCube(Cube.Color.RED, Cube.Color.WHITE), 0);
        subCubes.put(new SubCube(Cube.Color.RED, Cube.Color.YELLOW), 0);
        subCubes.put(new SubCube(Cube.Color.RED, Cube.Color.BLUE), 0);

        subCubes.put(new SubCube(Cube.Color.GREEN, Cube.Color.WHITE), 0);
        subCubes.put(new SubCube(Cube.Color.GREEN, Cube.Color.YELLOW), 0);
        subCubes.put(new SubCube(Cube.Color.GREEN, Cube.Color.ORANGE), 0);

        subCubes.put(new SubCube(Cube.Color.WHITE, Cube.Color.BLUE), 0);
        subCubes.put(new SubCube(Cube.Color.WHITE, Cube.Color.ORANGE), 0);

        subCubes.put(new SubCube(Cube.Color.YELLOW, Cube.Color.BLUE), 0);
        subCubes.put(new SubCube(Cube.Color.YELLOW, Cube.Color.ORANGE), 0);

        subCubes.put(new SubCube(Cube.Color.BLUE, Cube.Color.ORANGE), 0);

        // 3's
        subCubes.put(new SubCube(Cube.Color.RED, Cube.Color.GREEN, Cube.Color.WHITE), 0);
        subCubes.put(new SubCube(Cube.Color.RED, Cube.Color.GREEN, Cube.Color.YELLOW), 0);
        subCubes.put(new SubCube(Cube.Color.RED, Cube.Color.WHITE, Cube.Color.BLUE), 0);
        subCubes.put(new SubCube(Cube.Color.RED, Cube.Color.YELLOW, Cube.Color.BLUE), 0);

        subCubes.put(new SubCube(Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.ORANGE), 0);
        subCubes.put(new SubCube(Cube.Color.GREEN, Cube.Color.YELLOW, Cube.Color.ORANGE), 0);

        subCubes.put(new SubCube(Cube.Color.WHITE, Cube.Color.BLUE, Cube.Color.ORANGE), 0);

        subCubes.put(new SubCube(Cube.Color.YELLOW, Cube.Color.BLUE, Cube.Color.ORANGE), 0);
    }

    private static boolean checkTopFace(Cube cube, Map<SubCube, Integer> subCubes) {

        if (!topThreeColorCheck(cube, subCubes))
            return false;
        if (!topTwoColorCheck(cube, subCubes))
            return false;
        return true;
    }

    private static boolean topThreeColorCheck(Cube cube, Map<SubCube, Integer> subCubes) {
        Cube.Color[] threeColors = new Cube.Color[3];
        SubCube subCube;

        // 0,0
        threeColors[0] = cube.getUp().getGridEntry(0, 0);
        threeColors[1] = cube.getBack().getGridEntry(0, 2);
        threeColors[2] = cube.getLeft().getGridEntry(0, 0);
        Arrays.sort(threeColors);
        subCube = new SubCube(threeColors);
        Integer val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 0,2
        threeColors[0] = cube.getUp().getGridEntry(0, 2);
        threeColors[1] = cube.getBack().getGridEntry(0, 0);
        threeColors[2] = cube.getRight().getGridEntry(0, 2);
        Arrays.sort(threeColors);
        subCube = new SubCube(threeColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 2,0
        threeColors[0] = cube.getUp().getGridEntry(2, 0);
        threeColors[1] = cube.getFront().getGridEntry(0, 0);
        threeColors[2] = cube.getLeft().getGridEntry(0, 2);
        Arrays.sort(threeColors);
        subCube = new SubCube(threeColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 2,2
        threeColors[0] = cube.getUp().getGridEntry(2, 2);
        threeColors[1] = cube.getFront().getGridEntry(0, 2);
        threeColors[2] = cube.getRight().getGridEntry(0, 0);
        Arrays.sort(threeColors);
        subCube = new SubCube(threeColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        return true;
    }

    private static boolean topTwoColorCheck(Cube cube, Map<SubCube, Integer> subCubes) {
        Cube.Color[] twoColors = new Cube.Color[2];
        SubCube subCube;

        // 0,1
        twoColors[0] = cube.getUp().getGridEntry(0, 1);
        twoColors[1] = cube.getBack().getGridEntry(0, 1);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        Integer val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 1,0
        twoColors[0] = cube.getUp().getGridEntry(1, 0);
        twoColors[1] = cube.getLeft().getGridEntry(0, 1);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 1,2
        twoColors[0] = cube.getUp().getGridEntry(1, 2);
        twoColors[1] = cube.getRight().getGridEntry(0, 1);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 2,1
        twoColors[0] = cube.getUp().getGridEntry(2, 1);
        twoColors[1] = cube.getFront().getGridEntry(0, 1);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        return true;
    }

    private static boolean checkMiddleSubCubes(Cube cube, Map<SubCube, Integer> subCubes) {
        Cube.Color[] twoColors = new Cube.Color[2];
        SubCube subCube;

        // front-left
        twoColors[0] = cube.getFront().getGridEntry(1, 0);
        twoColors[1] = cube.getLeft().getGridEntry(1, 2);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        Integer val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // front-right
        twoColors[0] = cube.getFront().getGridEntry(1, 2);
        twoColors[1] = cube.getRight().getGridEntry(1, 0);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // back-left
        twoColors[0] = cube.getBack().getGridEntry(1, 0);
        twoColors[1] = cube.getRight().getGridEntry(1, 2);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // back-right
        twoColors[0] = cube.getBack().getGridEntry(1, 2);
        twoColors[1] = cube.getLeft().getGridEntry(1, 0);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        return true;
    }

    private static boolean checkBottomFace(Cube cube, Map<SubCube, Integer> subCubes) {

        if (!bottomThreeColorCheck(cube, subCubes))
            return false;

        if (!bottomTwoColorCheck(cube, subCubes)) {
            return false;
        }

        return true;
    }

    private static boolean bottomThreeColorCheck(Cube cube, Map<SubCube, Integer> subCubes) {
        Cube.Color[] threeColors = new Cube.Color[3];
        SubCube subCube;

        // 0,0
        threeColors[0] = cube.getDown().getGridEntry(0, 0);
        threeColors[1] = cube.getFront().getGridEntry(2, 0);
        threeColors[2] = cube.getLeft().getGridEntry(2, 2);
        Arrays.sort(threeColors);
        subCube = new SubCube(threeColors);
        Integer val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 0,2
        threeColors[0] = cube.getDown().getGridEntry(0, 2);
        threeColors[1] = cube.getFront().getGridEntry(2, 2);
        threeColors[2] = cube.getRight().getGridEntry(2, 0);
        Arrays.sort(threeColors);
        subCube = new SubCube(threeColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 2,0
        threeColors[0] = cube.getDown().getGridEntry(2, 0);
        threeColors[1] = cube.getBack().getGridEntry(2, 2);
        threeColors[2] = cube.getLeft().getGridEntry(2, 0);
        Arrays.sort(threeColors);
        subCube = new SubCube(threeColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 2,2
        threeColors[0] = cube.getDown().getGridEntry(2, 2);
        threeColors[1] = cube.getBack().getGridEntry(2, 0);
        threeColors[2] = cube.getRight().getGridEntry(2, 2);
        Arrays.sort(threeColors);
        subCube = new SubCube(threeColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        return true;
    }

    private static boolean bottomTwoColorCheck(Cube cube, Map<SubCube, Integer> subCubes) {
        Cube.Color[] twoColors = new Cube.Color[2];
        SubCube subCube;

        // 0,1
        twoColors[0] = cube.getDown().getGridEntry(0, 1);
        twoColors[1] = cube.getFront().getGridEntry(2, 1);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        Integer val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 1,0
        twoColors[0] = cube.getDown().getGridEntry(1, 0);
        twoColors[1] = cube.getLeft().getGridEntry(2, 1);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 1,2
        twoColors[0] = cube.getDown().getGridEntry(1, 2);
        twoColors[1] = cube.getRight().getGridEntry(2, 1);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        // 2,1
        twoColors[0] = cube.getDown().getGridEntry(2, 1);
        twoColors[1] = cube.getBack().getGridEntry(2, 1);
        Arrays.sort(twoColors);
        subCube = new SubCube(twoColors);
        val = subCubes.get(subCube);
        if (val == null || val > 0)
            return false;
        subCubes.put(subCube, val + 1);

        return true;
    }

    private static boolean checkCentersColorValidity(Cube cube) {

        Map<Cube.Color, Boolean> colorsCount = new HashMap<>();

        for (Cube.Color color : Cube.Color.values())
            colorsCount.put(color, false);

        if (!checkSpecificCenterColorValidity(cube.getFront().getColor(), cube.getBack().getColor(), colorsCount))
            return false;
        if (!checkSpecificCenterColorValidity(cube.getUp().getColor(), cube.getDown().getColor(), colorsCount))
            return false;
        if (!checkSpecificCenterColorValidity(cube.getRight().getColor(), cube.getLeft().getColor(), colorsCount))
            return false;

        return true;
    }

    private static boolean checkSpecificCenterColorValidity(Cube.Color color1, Cube.Color color2, Map<Cube.Color, Boolean> colorsCount) {
        if (colorsCount.get(color1))
            return false;
        colorsCount.put(color1, true);

        if (colorsCount.get(color2))
            return false;
        colorsCount.put(color2, true);

        switch (color1) {
            case RED:
                if (color2 != Cube.Color.ORANGE)
                    return false;
                break;
            case ORANGE:
                if (color2 != Cube.Color.RED)
                    return false;
                break;
            case GREEN:
                if (color2 != Cube.Color.BLUE)
                    return false;
                break;
            case BLUE:
                if (color2 != Cube.Color.GREEN)
                    return false;
                break;
            case WHITE:
                if (color2 != Cube.Color.YELLOW)
                    return false;
                break;
            case YELLOW:
                if (color2 != Cube.Color.WHITE)
                    return false;
                break;
            case NO_COLOR:
                return false;
            default:
                break;
        }
        return true;
    }
}
