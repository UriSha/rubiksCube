import java.util.HashMap;
import java.util.List;
import java.util.Map;

class optimizerToRed {

    /**
     * @param cube
     * @param colors 0 - new red
     *               1 - new green
     *               2 - new yellow
     *               3 - new white
     *               4 - new blue
     *               5 - new orange
     * @return Cube - a converted cube
     */
    private static Cube convertCube(Cube cube, Cube.Color[] colors) {

        Map<Cube.Color, Cube.Color> convertor = new HashMap<>();
        convertor.put(Cube.Color.RED, colors[0]);
        convertor.put(Cube.Color.GREEN, colors[1]);
        convertor.put(Cube.Color.YELLOW, colors[2]);
        convertor.put(Cube.Color.WHITE, colors[3]);
        convertor.put(Cube.Color.BLUE, colors[4]);
        convertor.put(Cube.Color.ORANGE, colors[5]);

        Cube.Face[] curFaces = cube.getFaces();
        Cube.Color[][][] newGrids = new Cube.Color[curFaces.length][Cube.getDim()][Cube.getDim()];

        for (int k = 0; k < curFaces.length; k++) {
            Cube.Color[][] tmpGrid = curFaces[k].getGrid();
            for (int i = 0; i < Cube.getDim(); i++) {
                for (int j = 0; j < Cube.getDim(); j++) {
                    newGrids[k][i][j] = convertor.get(tmpGrid[i][j]);
                }
            }
        }
        return new Cube(newGrids);
    }

    private static int scoringFunction(Cube cube) {
        List<cmd> result = Logic.algorithm(cube);
        return CommandsListOptimizer.getNumOfAtomic(result);
    }


    /**
     * @return an optimized converted cube
     * @param: a cube
     */
    static Cube getCovertedOptimizedCube(Cube cube) {
        // normal - red id red
        int bestScore = scoringFunction(Cube.cloneCube(cube));
        Cube.Color[] res = new Cube.Color[]{Cube.Color.RED, Cube.Color.GREEN, Cube.Color.YELLOW,
                Cube.Color.WHITE, Cube.Color.BLUE, Cube.Color.ORANGE};

        //  yellow is red

        Cube.Color[] tmp = new Cube.Color[]{Cube.Color.YELLOW, Cube.Color.GREEN, Cube.Color.ORANGE,
                Cube.Color.RED, Cube.Color.BLUE, Cube.Color.WHITE};
        int tmpScore = scoringFunction(convertCube(cube, tmp));
        if (tmpScore < bestScore) {
            bestScore = tmpScore;
            res = tmp;
        }

        //  orange is up
        tmp = new Cube.Color[]{Cube.Color.ORANGE, Cube.Color.GREEN, Cube.Color.WHITE,
                Cube.Color.YELLOW, Cube.Color.BLUE, Cube.Color.RED};
        tmpScore = scoringFunction(convertCube(cube, tmp));
        if (tmpScore < bestScore) {
            bestScore = tmpScore;
            res = tmp;
        }

        //  white is up
        tmp = new Cube.Color[]{Cube.Color.WHITE, Cube.Color.GREEN, Cube.Color.RED,
                Cube.Color.ORANGE, Cube.Color.BLUE, Cube.Color.YELLOW};
        tmpScore = scoringFunction(convertCube(cube, tmp));
        if (tmpScore < bestScore) {
            bestScore = tmpScore;
            res = tmp;
        }

        //  blue is up
        tmp = new Cube.Color[]{Cube.Color.BLUE, Cube.Color.RED, Cube.Color.YELLOW,
                Cube.Color.WHITE, Cube.Color.ORANGE, Cube.Color.GREEN};
        tmpScore = scoringFunction(convertCube(cube, tmp));
        if (tmpScore < bestScore) {
            bestScore = tmpScore;
            res = tmp;
        }

        //  green is up
        tmp = new Cube.Color[]{Cube.Color.GREEN, Cube.Color.ORANGE, Cube.Color.YELLOW,
                Cube.Color.WHITE, Cube.Color.RED, Cube.Color.BLUE};
        tmpScore = scoringFunction(convertCube(cube, tmp));
        if (tmpScore < bestScore) {
            res = tmp;
        }

        return convertCube(cube, res);

    }

}
