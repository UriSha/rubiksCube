import java.util.concurrent.ThreadLocalRandom;

 class CubeUtils {

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

        Cube cube = new Cube(cubeValues);
        
        return cube;
	}
    static Cube getRandomCube() {
        Cube cube=initialDoneCube();

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
}
