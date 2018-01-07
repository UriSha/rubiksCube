import org.junit.Test;

import static org.junit.Assert.*;

public class CubeTest {


    @Test
    public void isValidCube() {
        Cube cube = CubeUtils.initialDoneCube();
        changeOneTileCheckValidity(cube);

        for (int i = 0; i < 50; i++) {
            cube = CubeUtils.getRandomCube();
            changeOneTileCheckValidity(cube);
        }
    }


    private void changeOneTileCheckValidity(Cube cube) {
        String msg = "isValidCube() failed. returns %s instead of %s on the following cube: \n%s";
        boolean isValid;
        Cube.Color initialColor;
        Cube.Color[][] grid;

        isValid = cube.isValidCube();
        assertTrue(String.format(msg, isValid, !isValid, cube), isValid);
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
                            assertFalse(String.format(msg, isValid, !isValid, cube), isValid);
                            grid[i][j] = initialColor;
                        }
                    }
                }
            }
        }
    }

    @Test
    public void cubeActions() {
        String msg = "cubeActions() failed. made cube actions on an initial cube and the result turd out to be illegal \n%s";
        Cube cube;
        for (int i = 0; i < 100; i++) {
            cube = CubeUtils.getRandomCube();
            assertTrue(String.format(msg, cube), cube.isValidCube());
        }


    }


}