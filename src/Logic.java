import java.util.ArrayList;
import java.util.List;



public class Logic{

    public List<Action> mainAlgorithm(Cube cube){
        List<Action> result = new ArrayList<>();
        int algoStage = 0;
        initialize(cube, result);
        stageZero(cube, result);



        return result;
    }

    private static void stageZero(Cube cube, List<Action> actions) {
        if (cube == null || actions == null){
            System.out.println("Error: 'stageZero' arguments are null");
            return;
        }
        if (cube.getFront().getGrid()[1][0] == Cube.Color.RED){
            actions.add(Action.LEFT_TWIST_FRONTUPWARD);
            cube.twistLeftface
        }
    }

    public static void initialize(Cube cube, List<Action> actions){

        if (cube == null || actions == null){
            System.out.println("Error: 'initialize' arguments are null");
            return;
        }

        if (cube.getUp().getColor() != Cube.Color.RED){ // getting the red facet to the top
            if (cube.getBack().getColor() == Cube.Color.RED){
                actions.add(Action.FLIP);
                cube.flip();
                actions.add(Action.FLIP);
                cube.flip();
                actions.add(Action.FLIP);
                cube.flip();
            } else if (cube.getDown().getColor() == Cube.Color.RED){
                actions.add(Action.FLIP);
                cube.flip();
                actions.add(Action.FLIP);
                cube.flip();
            } else if (cube.getFront().getColor() == Cube.Color.RED){
                actions.add(Action.FLIP);
                cube.flip();
            } else if (cube.getRight().getColor() == Cube.Color.RED) {
                cube.rotate(false);
                actions.add(Action.LEFT_ROTATE);
                cube.flip();
                actions.add(Action.FLIP);
            } else if (cube.getLeft().getColor() == Cube.Color.RED) {
                cube.rotate(true);
                actions.add(Action.RIGHT_ROTATE);
                cube.flip();
                actions.add(Action.FLIP);
            }
        }

        if (cube.getFront().getColor() != Cube.Color.YELLOW){
            if (cube.getLeft().getColor() == Cube.Color.YELLOW){
                actions.add(Action.RIGHT_ROTATE);
                cube.rotate(true);
            } else if (cube.getRight().getColor() == Cube.Color.YELLOW){
                actions.add(Action.LEFT_ROTATE);
                cube.rotate(false);
            } else if (cube.getBack().getColor() == Cube.Color.YELLOW){
                actions.add(Action.LEFT_ROTATE);
                cube.rotate(false);
                actions.add(Action.LEFT_ROTATE);
                cube.rotate(false);
            }
    }
}
