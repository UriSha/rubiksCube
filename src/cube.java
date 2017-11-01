/**
 * cube[0] = up
 * cube[1] = front
 * cube[2] = down
 * cube[3] = back
 * cube[4] = right
 * cube[5] = left
 *
 * cube[i][0] = upper part of the i facet (UP for front - back - left - right, BACK for up, FRONT for down)
 *
 *
 * 0-red
 * 1-blue
 * 2-orange
 * 3-green
 * 4-yellow
 * 5-white
 */

public class cube {
    static final int numOfFacets = 6;
    static final int dim = 3;
    static int stage;
    side[] cubeRep;

    public cube(int[][][] theCube) {
        cubeRep = new side[numOfFacets];
        stage = 0;
        for (int i = 0; i < numOfFacets; i++) {
            cubeRep[i] = new side(i, theCube[i]);
        }
    }


    public boolean isValidCube(cube theCube) {
        int[] colorCounter = new int[numOfFacets];
        int[] centerColorsCounter = {-1,-1,-1,-1,-1,-1};
        for (int i = 0; i < cubeRep.length; i++) {
            for (int j = 0; j < dim; j++) {
                for (int k = 0; k < dim; k++) {
                    colorCounter[theCube.cubeRep[i].facet[j][k]]++;
                    if (k == 1 && j == 1){
                        centerColorsCounter[theCube.cubeRep[i].facet[j][k]] = i;
                    }
                }
            }
        }
        for (int i = 0; i < colorCounter.length; i++){
            if (colorCounter[i] != dim*dim){ return false; } // each color appears 9 times
            if (centerColorsCounter[i] < 0){ return false; } // each center has different color
        }



        return true;
    }
}


