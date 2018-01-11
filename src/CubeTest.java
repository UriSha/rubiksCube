import org.junit.Test;

import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class CubeTest {


    @Test
    public void isValidCube() {
        Cube cube = CubeUtils.initialDoneCube();
        changeOneTileCheckValidity(cube);

        for (int i = 0; i < 100; i++) {
            cube = CubeUtils.getRandomCube();
            changeOneTileCheckValidity(cube);
            changeSeveralTilesCheckValidity(cube);
        }
    }


    private void changeOneTileCheckValidity(Cube cube) {
        String[] msg =new String[]{"isValidCube() failed. returns " ," instead of ", " on the following cube: \n"};
        boolean isValid;
        Cube.Color initialColor;
        Cube.Color[][] grid;

        isValid = cube.isValidCube();
        assertTrue(msg[0]+isValid+msg[1]+!isValid +msg[2]+cube, isValid);
        Cube.Face[] faces = cube.getFaces();

        for (int k = 0; k < 6; k++) {
            grid = faces[k].getGrid();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    initialColor = grid[i][j];
                    for (Cube.Color color : Cube.Color.values()) {
                        if (color != initialColor) {
                            grid[i][j] = color;
                            isValid = cube.isValidCube();
                            assertFalse(msg[0]+isValid+msg[1]+!isValid +msg[2]+cube, isValid);
                            grid[i][j] = initialColor;
                        }
                    }
                }
            }
        }
    }

    private void changeSeveralTilesCheckValidity(Cube cube) {
        String[] msg =new String[]{"isValidCube() failed. returns "," instead of "," on the following cube: \n"};
        boolean isValid;
        Cube.Face[] faces = cube.getFaces();

        Random rand = new Random();

        int faceIndex = rand.nextInt(6);
        Cube.Color[][] grid = faces[faceIndex].getGrid();

        int i = rand.nextInt(3);
        int j = rand.nextInt(3);
        Cube.Color initialColor = grid[i][j];

        for (Cube.Color color : Cube.Color.values()) {
            if (color != initialColor) {
                grid[i][j] = color;

                int prevI = i, prevJ = j, prevFace = faceIndex;
                while(prevI==i && prevJ==j && prevFace == faceIndex){
                    i = rand.nextInt(3);
                    j = rand.nextInt(3);
                    faceIndex = rand.nextInt(6);
                }
                grid = faces[faceIndex].getGrid();
                Cube.Color secondColor = grid[i][j];

                for (Cube.Color color2 : Cube.Color.values()) {
                    if (color2 != secondColor && initialColor != color2 ) {
                        grid[i][j] = color2;
                        isValid = cube.isValidCube();
                        assertFalse(msg[0]+isValid+msg[1]+!isValid+msg[2]+cube, isValid);

                    }
                }

            }
        }
    }

    @Test
    public void cubeActions() {
        String msg ="cubeActions() failed. made cube actions on an initial cube and the result turned out to be illegal \n";
        Cube cube;
        for (int i = 0; i < 100; i++) {
            cube = CubeUtils.getRandomCube();
            assertTrue(msg+cube, cube.isValidCube());
        }


    }


}