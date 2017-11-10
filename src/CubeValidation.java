import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CubeValidation {

    public static boolean isValidCube(Cube cube){
        Map<Cube.Color[], Integer> subCubes = new HashMap<>();
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
        if(!checkCentersColorValidity(cube))
            return false;

        return true;
    }

    /**
     * Populates the Hashmap with the relevant sub-cubes
     *
     * @param subCubes - the map to be populated
     */
    private static void populateSubCubes(Map<Cube.Color[], Integer> subCubes){
        // 1's
//        subCubes.put(new Cube.Color[]{Cube.Color.RED},0);
//        subCubes.put(new Cube.Color[]{Cube.Color.GREEN},0);
//        subCubes.put(new Cube.Color[]{Cube.Color.WHITE},0);
//        subCubes.put(new Cube.Color[]{Cube.Color.YELLOW},0);
//        subCubes.put(new Cube.Color[]{Cube.Color.BLUE},0);
//        subCubes.put(new Cube.Color[]{Cube.Color.ORANGE},0);

        // 2's
        subCubes.put(new Cube.Color[]{Cube.Color.RED, Cube.Color.GREEN},0);
        subCubes.put(new Cube.Color[]{Cube.Color.RED, Cube.Color.WHITE},0);
        subCubes.put(new Cube.Color[]{Cube.Color.RED, Cube.Color.YELLOW},0);
        subCubes.put(new Cube.Color[]{Cube.Color.RED, Cube.Color.BLUE},0);

        subCubes.put(new Cube.Color[]{Cube.Color.GREEN, Cube.Color.WHITE},0);
        subCubes.put(new Cube.Color[]{Cube.Color.GREEN, Cube.Color.YELLOW},0);
        subCubes.put(new Cube.Color[]{Cube.Color.GREEN, Cube.Color.ORANGE},0);

        subCubes.put(new Cube.Color[]{Cube.Color.WHITE, Cube.Color.BLUE},0);
        subCubes.put(new Cube.Color[]{Cube.Color.WHITE, Cube.Color.ORANGE},0);

        subCubes.put(new Cube.Color[]{Cube.Color.YELLOW, Cube.Color.BLUE},0);
        subCubes.put(new Cube.Color[]{Cube.Color.YELLOW, Cube.Color.ORANGE},0);

        subCubes.put(new Cube.Color[]{Cube.Color.BLUE, Cube.Color.ORANGE},0);

        // 3's
        subCubes.put(new Cube.Color[]{Cube.Color.RED, Cube.Color.GREEN, Cube.Color.WHITE},0);
        subCubes.put(new Cube.Color[]{Cube.Color.RED, Cube.Color.GREEN, Cube.Color.YELLOW},0);
        subCubes.put(new Cube.Color[]{Cube.Color.RED, Cube.Color.WHITE, Cube.Color.BLUE},0);
        subCubes.put(new Cube.Color[]{Cube.Color.RED, Cube.Color.YELLOW, Cube.Color.BLUE},0);

        subCubes.put(new Cube.Color[]{Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.ORANGE},0);
        subCubes.put(new Cube.Color[]{Cube.Color.GREEN, Cube.Color.YELLOW, Cube.Color.ORANGE},0);

        subCubes.put(new Cube.Color[]{Cube.Color.WHITE, Cube.Color.BLUE, Cube.Color.ORANGE},0);

        subCubes.put(new Cube.Color[]{Cube.Color.YELLOW, Cube.Color.BLUE, Cube.Color.ORANGE},0);
    }

    private static boolean checkTopFace(Cube cube, Map<Cube.Color[], Integer> subCubes){

        if(!topThreeColorCheck(cube, subCubes))
            return false;
        if(!topTwoColorCheck(cube, subCubes))
            return false;



        return true;
    }

    private static boolean topThreeColorCheck(Cube cube, Map<Cube.Color[], Integer> subCubes){
        Cube.Color[] threeColors = new Cube.Color[3];

        // 0,0
        threeColors[0] = cube.getUp().getGridEntry(0,0);
        threeColors[1] = cube.getBack().getGridEntry(0,2);
        threeColors[2] = cube.getLeft().getGridEntry(0,0);
        Arrays.sort(threeColors);
        Integer val = subCubes.get(threeColors);
        if (val == null || val == 1)
            return false;

        // 0,2
        threeColors[0] = cube.getUp().getGridEntry(0,2);
        threeColors[1] = cube.getBack().getGridEntry(0,0);
        threeColors[2] = cube.getRight().getGridEntry(0,2);
        Arrays.sort(threeColors);
        val = subCubes.get(threeColors);
        if (val == null || val == 1)
            return false;

        // 2,0
        threeColors[0] = cube.getUp().getGridEntry(2,0);
        threeColors[1] = cube.getFront().getGridEntry(0,0);
        threeColors[2] = cube.getLeft().getGridEntry(2,0);
        Arrays.sort(threeColors);
        val = subCubes.get(threeColors);
        if (val == null || val == 1)
            return false;

        // 2,2
        threeColors[0] = cube.getUp().getGridEntry(0,0);
        threeColors[1] = cube.getFront().getGridEntry(0,0);
        threeColors[2] = cube.getRight().getGridEntry(0,0);
        Arrays.sort(threeColors);
        val = subCubes.get(threeColors);
        if (val == null || val == 1)
            return false;

        return true;
    }

    private static boolean topTwoColorCheck(Cube cube, Map<Cube.Color[], Integer> subCubes){
        Cube.Color[] twoColors = new Cube.Color[2];

        // 0,1
        twoColors[0] = cube.getUp().getGridEntry(0,1);
        twoColors[1] = cube.getBack().getGridEntry(0,1);
        Arrays.sort(twoColors);
        Integer val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        // 1,0
        twoColors[0] = cube.getUp().getGridEntry(1,0);
        twoColors[1] = cube.getLeft().getGridEntry(0,1);
        Arrays.sort(twoColors);
        val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        // 1,2
        twoColors[0] = cube.getUp().getGridEntry(1,2);
        twoColors[1] = cube.getRight().getGridEntry(0,1);
        Arrays.sort(twoColors);
        val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        // 2,1
        twoColors[0] = cube.getUp().getGridEntry(2,1);
        twoColors[1] = cube.getFront().getGridEntry(0,1);
        Arrays.sort(twoColors);
        val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        return true;
    }

    private static boolean checkMiddleSubCubes(Cube cube, Map<Cube.Color[], Integer> subCubes){
        Cube.Color[] twoColors = new Cube.Color[2];

        // front-left
        twoColors[0] = cube.getFront().getGridEntry(1,0);
        twoColors[1] = cube.getLeft().getGridEntry(1,2);
        Arrays.sort(twoColors);
        Integer val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        // front-right
        twoColors[0] = cube.getFront().getGridEntry(1,2);
        twoColors[1] = cube.getRight().getGridEntry(1,0);
        Arrays.sort(twoColors);
        val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        // back-left
        twoColors[0] = cube.getBack().getGridEntry(1,0);
        twoColors[1] = cube.getLeft().getGridEntry(1,2);
        Arrays.sort(twoColors);
        val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        // back-right
        twoColors[0] = cube.getBack().getGridEntry(1,2);
        twoColors[1] = cube.getRight().getGridEntry(1,0);
        Arrays.sort(twoColors);
        val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        return true;
    }

    private static boolean checkBottomFace(Cube cube, Map<Cube.Color[], Integer> subCubes){

        if(!bottomThreeColorCheck(cube, subCubes))
            return false;

        if(!bottomTwoColorCheck(cube, subCubes)) {
            return false;
        }

        return true;
    }

    private static boolean bottomThreeColorCheck(Cube cube, Map<Cube.Color[], Integer> subCubes){
        Cube.Color[] threeColors = new Cube.Color[3];

        // 0,0
        threeColors[0] = cube.getDown().getGridEntry(0,0);
        threeColors[1] = cube.getFront().getGridEntry(2,0);
        threeColors[2] = cube.getLeft().getGridEntry(2,2);
        Arrays.sort(threeColors);
        Integer val = subCubes.get(threeColors);
        if (val == null || val == 1)
            return false;

        // 0,2
        threeColors[0] = cube.getDown().getGridEntry(0,2);
        threeColors[1] = cube.getFront().getGridEntry(2,2);
        threeColors[2] = cube.getRight().getGridEntry(2,0);
        Arrays.sort(threeColors);
        val = subCubes.get(threeColors);
        if (val == null || val == 1)
            return false;

        // 2,0
        threeColors[0] = cube.getDown().getGridEntry(2,0);
        threeColors[1] = cube.getBack().getGridEntry(2,2);
        threeColors[2] = cube.getLeft().getGridEntry(2,0);
        Arrays.sort(threeColors);
        val = subCubes.get(threeColors);
        if (val == null || val == 1)
            return false;

        // 2,2
        threeColors[0] = cube.getDown().getGridEntry(2,2);
        threeColors[1] = cube.getBack().getGridEntry(0,2);
        threeColors[2] = cube.getRight().getGridEntry(2,2);
        Arrays.sort(threeColors);
        val = subCubes.get(threeColors);
        if (val == null || val == 1)
            return false;

        return true;
    }

    private static boolean bottomTwoColorCheck(Cube cube, Map<Cube.Color[], Integer> subCubes){
        Cube.Color[] twoColors = new Cube.Color[2];

        // 0,1
        twoColors[0] = cube.getDown().getGridEntry(0,1);
        twoColors[1] = cube.getFront().getGridEntry(2,1);
        Arrays.sort(twoColors);
        Integer val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        // 1,0
        twoColors[0] = cube.getDown().getGridEntry(1,0);
        twoColors[1] = cube.getLeft().getGridEntry(2,1);
        Arrays.sort(twoColors);
        val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        // 1,2
        twoColors[0] = cube.getDown().getGridEntry(1,2);
        twoColors[1] = cube.getRight().getGridEntry(2,1);
        Arrays.sort(twoColors);
        val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        // 2,1
        twoColors[0] = cube.getDown().getGridEntry(2,1);
        twoColors[1] = cube.getBack().getGridEntry(2,1);
        Arrays.sort(twoColors);
        val = subCubes.get(twoColors);
        if (val == null || val == 1)
            return false;

        return true;
    }

    private static boolean checkCentersColorValidity(Cube cube){
        switch(cube.getFront().getColor()){
            case RED:
                if (cube.getBack().getColor() != Cube.Color.ORANGE)
                    return false;
                break;
            case ORANGE:
                if (cube.getBack().getColor() != Cube.Color.RED)
                    return false;
                break;
            case GREEN:
                if (cube.getBack().getColor() != Cube.Color.BLUE)
                    return false;
                break;
            case BLUE:
                if (cube.getBack().getColor() != Cube.Color.GREEN)
                    return false;
                break;
            case WHITE:
                if (cube.getBack().getColor() != Cube.Color.YELLOW)
                    return false;
                break;
            case YELLOW:
                if (cube.getBack().getColor() != Cube.Color.WHITE)
                    return false;
                break;
            default:
                break;
        }

        switch(cube.getUp().getColor()){
            case RED:
                if (cube.getDown().getColor() != Cube.Color.ORANGE)
                    return false;
                break;
            case ORANGE:
                if (cube.getDown().getColor() != Cube.Color.RED)
                    return false;
                break;
            case GREEN:
                if (cube.getDown().getColor() != Cube.Color.BLUE)
                    return false;
                break;
            case BLUE:
                if (cube.getDown().getColor() != Cube.Color.GREEN)
                    return false;
                break;
            case WHITE:
                if (cube.getDown().getColor() != Cube.Color.YELLOW)
                    return false;
                break;
            case YELLOW:
                if (cube.getDown().getColor() != Cube.Color.WHITE)
                    return false;
                break;
            default:
                break;
        }

        switch(cube.getRight().getColor()){
            case RED:
                if (cube.getRight().getColor() != Cube.Color.ORANGE)
                    return false;
                break;
            case ORANGE:
                if (cube.getRight().getColor() != Cube.Color.RED)
                    return false;
                break;
            case GREEN:
                if (cube.getRight().getColor() != Cube.Color.BLUE)
                    return false;
                break;
            case BLUE:
                if (cube.getRight().getColor() != Cube.Color.GREEN)
                    return false;
                break;
            case WHITE:
                if (cube.getRight().getColor() != Cube.Color.YELLOW)
                    return false;
                break;
            case YELLOW:
                if (cube.getRight().getColor() != Cube.Color.WHITE)
                    return false;
                break;
            default:
                break;
        }
        return true;
    }
}