/**
 *
 *
 *
 */
public class CubeUtils {

//    public enum Job {LEFT_ROTATE, RIGHT_ROTATE, FLIP, UP_TWIST, DOWN_TWIST, LEFT_TWIST, RIGHT_TWIST, FRONT_TWIST, BACK_TWIST}

    /** Indicates the action to be done */
    public enum Action {
        LEFT_ROTATE,
        RIGHT_ROTATE,
        FLIP,
        UP_TWIST,
        DOWN_TWIST,
        LEFT_TWIST,
        RIGHT_TWIST,
        FRONT_TWIST,
        BACK_TWIST
    }


    /** Twists the left face according to the value of frontUpward
     *
     *  @param frontUpward == true -> twists from the front face to the upper face(before flipping)
     */
    public static void twistRightFace(Cube cube, boolean frontUpward){
        cube.leftRotate();
        cube.flip();

        if (frontUpward)
            cube.rightTwistBottomFace();
        else
            cube.leftTwistBottomFace();

    }

    /** Twists the left face according to the value of frontUpward
     *
     *  @param frontUpward == true -> twists from the front face to the upper face(before flipping)
     */
    public static void twistLeftFace(Cube cube, boolean frontUpward){
        cube.rightRotate();
        cube.flip();

        if (!frontUpward)
            cube.rightTwistBottomFace();
        else
            cube.leftTwistBottomFace();

    }

    /** Twists the front face according to the value of clockwise
     *
     * @param clockwise == true -> twists the front face clockwise. Otherwise twists it counter-clockwise
     */
    public static void twistFrontFace(Cube cube, boolean clockwise){
        cube.flip();

        if (clockwise)
            cube.rightTwistBottomFace();
        else
            cube.leftTwistBottomFace();

    }

    /** Twists the back face according to the value of clockwise(before flipping)
     *
     * @param clockwise == true -> twists the back face clockwise(before flipping). Otherwise twists it counter-clockwise
     */
    public static void twistBackFace(Cube cube, boolean clockwise){
        cube.flip();
        cube.flip();
        cube.flip();

        if (!clockwise)
            cube.rightTwistBottomFace();
        else
            cube.leftTwistBottomFace();

    }

    /** Twists the bottom face according to the value of right
     *
     *  @param right == true -> twists from the front face to the right face
     */
    public static void twistBottomFace(Cube cube, boolean right){

        if (right)
            cube.rightTwistBottomFace();
        else
            cube.leftTwistBottomFace();

    }

    /** Twists the upper face according to the value of right
     *
     *  @param right == true -> twists from the front face to the right face(before flipping)
     */
    public static void twistUpperFace(Cube cube, boolean right){
        cube.flip();
        cube.flip();

        if (!right)
            cube.rightTwistBottomFace();
        else
            cube.leftTwistBottomFace();

    }


}
