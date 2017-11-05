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

public class cube1 {
    static final int numOfFacets = 6;
    static final int dim = 3;
    static int stage;
    side[] cubeRep;

    public cube1(int[][][] theCube) {
        cubeRep = new side[numOfFacets];
        stage = 0;
        for (int i = 0; i < numOfFacets; i++) {
            cubeRep[i] = new side(i, theCube[i]);
        }
    }


    public boolean isValidCube(cube1 theCube) {
        int[] colorCounter = new int[numOfFacets];
        int[] centerColorsCounter = {-1,-1,-1,-1,-1,-1};
        for (int i = 0; i < cubeRep.length; i++) {
            for (int j = 0; j < dim; j++) {
                for (int k = 0; k < dim; k++) {
                    colorCounter[theCube.cubeRep[i].facet[j][k]]++;
                    if (k == 1 && j == 1){
                        centerColorsCounter[i] = theCube.cubeRep[i].facet[j][k];
                    }
                }
            }
        }
        for (int i = 0; i < colorCounter.length; i++){
            if (colorCounter[i] != dim*dim){ return false; } // each color appears 9 times
            if (centerColorsCounter[i] < 0){ return false; } // each center has different color
        }
        /**
         * //Checking that the centers are organized correctly
         * case 0 means it's red, thus it needs to be in front of orange
         * case 1 means it's blue, this it needs to be in front of green
         * etc
         */
        for(int i=0;i<2;i++) {
            switch (centerColorsCounter[i]) {
                case 0:
                    if (centerColorsCounter[i+2] != 2)
                        return false;
                    break;
                case 1:
                    if (centerColorsCounter[i+2] != 3)
                        return false;
                    break;
                case 2:
                    if (centerColorsCounter[i+2] != 0)
                        return false;
                    break;
                case 3:
                    if (centerColorsCounter[i+2] != 1)
                        return false;
                    break;
                case 4:
                    if (centerColorsCounter[i+2] != 5)
                        return false;
                    break;
                case 5:
                    if (centerColorsCounter[i+2] != 4)
                        return false;
                    break;
                default:
                    return false;
            }
        }
        switch (centerColorsCounter[4]) {
            case 0:
                if (centerColorsCounter[5] != 2)
                    return false;
                break;
            case 1:
                if (centerColorsCounter[5] != 3)
                    return false;
                break;
            case 2:
                if (centerColorsCounter[5] != 0)
                    return false;
                break;
            case 3:
                if (centerColorsCounter[5] != 1)
                    return false;
                break;
            case 4:
                if (centerColorsCounter[5] != 5)
                    return false;
                break;
            case 5:
                if (centerColorsCounter[5] != 4)
                    return false;
                break;
            default:
                return false;

        }
        return true;
    }

}


