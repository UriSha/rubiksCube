import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class is used for optimization. We optimize using color translation and adjusting duplicated
 * and unnecessary commands
 */

class OptimizeCube {
    /**
     * @param cube - A standard cube
     * @param colors 0 - new red
     *               1 - new green
     *               2 - new yellow
     *               3 - new white
     *               4 - new blue
     *               5 - new orange
     * @return Cube - a converted cube
     * This function translates the colors using a help function the iterates all the possible colors
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
        return OptimizeCube.getNumOfAtomic(result);
    }

    /**
     *
     * @param cube - A standard cube
     * @return cube - with translated colors
     * Since the algorithm solves the cube using the red Cross system, it doesn't check
     * if there's more efficient method. This function helps to translate the red color to any other color, and then we
     * can choose the fastest way to solve the cube
     */
    static Cube getConvertedOptimizedCube(Cube cube) {
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

    /**
     * This function gets the list of commands, removes inverse commands and changes 3 commands of the same direction
     * To 1 command with the opposite direction
     * @param actions - The list of the commands
     */
    static void optimizeList(List<cmd> actions){
        Map<cmd, cmd> contrastCMDs = new HashMap<>();
        contrastCMDs.put(cmd.CMD_LEFT_ROTATE, cmd.CMD_RIGHT_ROTATE);
        contrastCMDs.put(cmd.CMD_RIGHT_ROTATE, cmd.CMD_LEFT_ROTATE);
        contrastCMDs.put(cmd.CMD_UP_TWIST_LEFT, cmd.CMD_UP_TWIST_RIGHT);
        contrastCMDs.put(cmd.CMD_UP_TWIST_RIGHT, cmd.CMD_UP_TWIST_LEFT);
        contrastCMDs.put(cmd.CMD_LEFT_TWIST_FRONTUPWARD, cmd.CMD_LEFT_TWIST_BACKUPWARD);
        contrastCMDs.put(cmd.CMD_LEFT_TWIST_BACKUPWARD, cmd.CMD_LEFT_TWIST_FRONTUPWARD);
        contrastCMDs.put(cmd.CMD_DOWN_TWIST_RIGHT, cmd.CMD_DOWN_TWIST_LEFT);
        contrastCMDs.put(cmd.CMD_DOWN_TWIST_LEFT, cmd.CMD_DOWN_TWIST_RIGHT);
        contrastCMDs.put(cmd.CMD_RIGHT_TWIST_FRONTUPWARD, cmd.CMD_RIGHT_TWIST_BACKUPWARD);
        contrastCMDs.put(cmd.CMD_RIGHT_TWIST_BACKUPWARD, cmd.CMD_RIGHT_TWIST_FRONTUPWARD);
        contrastCMDs.put(cmd.CMD_FRONT_TWIST_CLOCKWISE, cmd.CMD_FRONT_TWIST_C_CLOCKWISE);
        contrastCMDs.put(cmd.CMD_FRONT_TWIST_C_CLOCKWISE, cmd.CMD_FRONT_TWIST_CLOCKWISE);
        contrastCMDs.put(cmd.CMD_BACK_TWIST_CLOCKWISE, cmd.CMD_BACK_TWIST_C_CLOCKWISE);
        contrastCMDs.put(cmd.CMD_BACK_TWIST_C_CLOCKWISE, cmd.CMD_BACK_TWIST_CLOCKWISE);

        for (int i = actions.size() - 2; i >= 0; i--){
            try{
                if(actions.get(i)==cmd.CMD_FLIP){continue;}
                if (actions.get(i) == actions.get(i+1) && actions.get(i) == actions.get(i+2)){
                    actions.set(i, contrastCMDs.get(actions.get(i)));
                    actions.remove(i+2);
                    actions.remove(i+1);
                } else if (actions.get(i+1) == contrastCMDs.get(actions.get(i))){
                    actions.remove(i+1);
                    actions.remove(i);
                }
            } catch (IndexOutOfBoundsException e){}
        }
    }

// This function counts the number of commands , without flips and rotates
    static int getNumOfAtomic(List<cmd> actions){
        int num = 0;
        for (cmd action : actions){
            if (action != cmd.CMD_RIGHT_ROTATE && action != cmd.CMD_LEFT_ROTATE && action != cmd.CMD_FLIP){
                num++;
            }
        }
        return num;
    }
}
