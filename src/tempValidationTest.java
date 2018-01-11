public class tempValidationTest {
    public static void main(String[] args) {

        Cube.Color[][][] cubeValues = new Cube.Color[6][3][3];

        // up
        Cube.Color[] cubeValues1 = new Cube.Color[]{Cube.Color.GREEN,Cube.Color.BLUE,Cube.Color.YELLOW};
        Cube.Color[] cubeValues2 = new Cube.Color[]{Cube.Color.ORANGE,Cube.Color.RED,Cube.Color.RED};
        Cube.Color[] cubeValues3 = new Cube.Color[]{Cube.Color.ORANGE,Cube.Color.BLUE,Cube.Color.RED};

        for (int j = 0; j < 3; j++) {
            cubeValues[0][0][j] = cubeValues1[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[0][1][j] = cubeValues2[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[0][2][j] = cubeValues3[j];
        }

        // back
        cubeValues1 = new Cube.Color[]{Cube.Color.ORANGE,Cube.Color.YELLOW,Cube.Color.RED};
        cubeValues2 = new Cube.Color[]{Cube.Color.ORANGE,Cube.Color.WHITE,Cube.Color.GREEN};
        cubeValues3 = new Cube.Color[]{Cube.Color.YELLOW,Cube.Color.ORANGE,Cube.Color.GREEN};

        for (int j = 0; j < 3; j++) {
            cubeValues[1][0][j] = cubeValues1[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[1][1][j] = cubeValues2[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[1][2][j] = cubeValues3[j];
        }

        //down
        cubeValues1 = new Cube.Color[]{Cube.Color.BLUE,Cube.Color.WHITE,Cube.Color.BLUE};
        cubeValues2 = new Cube.Color[]{Cube.Color.RED,Cube.Color.ORANGE,Cube.Color.ORANGE};
        cubeValues3 = new Cube.Color[]{Cube.Color.WHITE,Cube.Color.YELLOW,Cube.Color.GREEN};

        for (int j = 0; j < 3; j++) {
            cubeValues[2][0][j] = cubeValues1[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[2][1][j] = cubeValues2[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[2][2][j] = cubeValues3[j];
        }

        //front
        cubeValues1 = new Cube.Color[]{Cube.Color.BLUE,Cube.Color.WHITE,Cube.Color.BLUE};
        cubeValues2 = new Cube.Color[]{Cube.Color.RED,Cube.Color.YELLOW,Cube.Color.YELLOW};
        cubeValues3 = new Cube.Color[]{Cube.Color.YELLOW,Cube.Color.GREEN,Cube.Color.YELLOW};

        for (int j = 0; j < 3; j++) {
            cubeValues[3][0][j] = cubeValues1[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[3][1][j] = cubeValues2[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[3][2][j] = cubeValues3[j];
        }


        //right
        cubeValues1 = new Cube.Color[]{Cube.Color.WHITE,Cube.Color.WHITE,Cube.Color.GREEN};
        cubeValues2 = new Cube.Color[]{Cube.Color.GREEN,Cube.Color.RED,Cube.Color.BLUE};
        cubeValues3 = new Cube.Color[]{Cube.Color.RED,Cube.Color.WHITE,Cube.Color.RED};

        for (int j = 0; j < 3; j++) {
            cubeValues[4][0][j] = cubeValues1[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[4][1][j] = cubeValues2[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[4][2][j] = cubeValues3[j];
        }

        //left
        cubeValues1 = new Cube.Color[]{Cube.Color.WHITE,Cube.Color.GREEN,Cube.Color.WHITE};
        cubeValues2 = new Cube.Color[]{Cube.Color.RED,Cube.Color.ORANGE,Cube.Color.BLUE};
        cubeValues3 = new Cube.Color[]{Cube.Color.ORANGE,Cube.Color.YELLOW,Cube.Color.ORANGE};

        for (int j = 0; j < 3; j++) {
            cubeValues[5][0][j] = cubeValues1[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[5][1][j] = cubeValues2[j];
        }

        for (int j = 0; j < 3; j++) {
            cubeValues[5][2][j] = cubeValues3[j];
        }


        Cube cube = new Cube(cubeValues);
        System.out.println(cube);
        System.out.println(cube.isValidCube());

    }
}
