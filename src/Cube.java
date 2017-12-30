
/**
 * Represents the cube's state
 * <p>
 * front - the cube's front face, looking from the arm's point of reference
 * all other faces are according to the above point of reference
 */

class Cube {

    private final static int dim = 3;

    // Directions point of reference: Robot's arm is front
    private Face front;
    private Face back;
    private Face up;
    private Face down;
    private Face right;
    private Face left;


    /**
     * Creates the initial state of the cube
     *
     * cube[0] = up
     * cube[1] = back
     * cube[2] = down
     * cube[3] = front
     * cube[4] = right
     * cube[5] = left
     *
     * @param theCube - the 3D representation of the cube
     */
    Cube(Color[][][] theCube) {
        this.up = new Face(theCube[0]);
        this.back = new Face(theCube[1]);
        this.down = new Face(theCube[2]);
        this.front = new Face(theCube[3]);
        this.right = new Face(theCube[4]);
        this.left = new Face(theCube[5]);
    }

    /**
     * Creates a clone of a cube
     *
     * @param: cube - the 3D representation of the cube
     *
     * @return: a clone of the cube
     */
    static Cube cloneCube(Cube cube){
        Cube.Color[][][] cloneGrids = new Cube.Color[cube.getFaces().length][getDim()][getDim()];
        Face[] faces = cube.getFaces();
        for (int k = 0; k < faces.length; k++) {
            for (int i = 0; i < getDim(); i++) {
                for (int j = 0; j < getDim(); j++) {
                    cloneGrids[k][i][j] = faces[k].getGrid()[i][j];
                }
            }
        }
        return new Cube(cloneGrids);
    }

    enum Color {
        RED(1),
        GREEN(2),
        WHITE(3),
        YELLOW(4),
        BLUE(5),
        ORANGE(6),
        NO_COLOR(7);

        private final int value;
        Color(int val){
            this.value = val;
        }

        public int getValue(){
            return value;
        }
    }

    static int getDim() {
        return dim;
    }

    Face getFront() {
        return front;
    }

    public void setFront(Face front) {
        this.front = front;
    }

    Face getBack() {
        return back;
    }

    void setBack(Face back) {
        this.back = back;
    }

    Face getUp() {
        return up;
    }

    void setUp(Face up) {
        this.up = up;
    }

    Face getDown() {
        return down;
    }

    public void setDown(Face down) {
        this.down = down;
    }

    Face getRight() {
        return right;
    }

    public void setRight(Face right) {
        this.right = right;
    }

    Face getLeft() {
        return left;
    }

    public void setLeft(Face left) {
        this.left = left;
    }

    /**
     * 0 = up
     * 1 = back
     * 2 = down
     * 3 = front
     * 4 = right
     * 5 = left
     * @return array of faces
     */
    Face[] getFaces(){
        Face[] faces = new Face[6];
        faces[0] = up;
        faces[1] = back;
        faces[2] = down;
        faces[3] = front;
        faces[4] = right;
        faces[5] = left;
        return faces;
    }
    /**
     * Rotates the cube 90 degrees
     *
     * @param right - indicates whether the rotation is to the right or to the left
     */
    void rotate(boolean right) {
        if (right)
            rightRotate();
        else
            leftRotate();
    }


    /**
     * Rotates the cube to the right- front face turns to be right face, etc.
     */
    private void rightRotate() {
        Face tmp = front;
        front = left;
        left = back;
        back = right;
        right = tmp;

        up.counterClockwiseFixInnerValues();
        down.clockwiseFixInnerValues();

    }

    /**
     * Rotates the cube to the left- front face turns to be left face, etc.
     */
    private void leftRotate() {
        Face tmp = front;
        front = right;
        right = back;
        back = left;
        left = tmp;

        up.clockwiseFixInnerValues();
        down.counterClockwiseFixInnerValues();
    }

    /**
     * Flip the cube on its front- front face turns to be bottom face, etc.
     */
    void flip() {
        Face tmp = front;
        front = up;
        up = back;
        back = down;
        down = tmp;

        left.clockwiseFixInnerValues();
        right.counterClockwiseFixInnerValues();

        back.upsideDownFixInnerValues();
        up.upsideDownFixInnerValues();
    }

    /**
     * Twists the left face according to the value of frontUpward
     *
     * @param frontUpward == true -> twists from the front face to the upper face(before flipping)
     */
    void twistRightFace(boolean frontUpward) {
        twistSideFace(true, frontUpward);
    }

    /**
     * Twists the left face according to the value of frontUpward
     *
     * @param frontUpward == true -> twists from the front face to the upper face(before flipping)
     */
    void twistLeftFace(boolean frontUpward) {
        twistSideFace(false, frontUpward);
    }


    /**
     * Twists the front face according to the value of clockwise
     *
     * @param clockwise == true -> twists the front face clockwise. Otherwise twists it counter-clockwise
     */
    void twistFrontFace(boolean clockwise) {
        rotate(true);
        twistSideFace(true, clockwise);
        rotate(false);
    }

    /**
     * Twists the back face according to the value of clockwise(from the front point of view)
     *
     * @param clockwise == true -> twists the back face clockwise(from the front point of view). Otherwise twists it counter-clockwise
     */
    void twistBackFace(boolean clockwise) {
        rotate(false);
        twistSideFace(true, !clockwise);
        rotate(true);
    }

    /**
     * Twists the bottom face according to the value of right
     *
     * @param isRightTwist == true -> twists from the front face to the right face
     */
    void twistBottomFace(boolean isRightTwist) {
        flip();
        twistBackFace(!isRightTwist);
        flip();
        flip();
        flip();
    }

    /**
     * Twists the upper face according to the value of right
     *
     * @param isRightTwist == true -> twists from the front face to the right face(before flipping)
     */
    void twistUpperFace(boolean isRightTwist) {
        flip();
        twistFrontFace(!isRightTwist);
        flip();
        flip();
        flip();

    }


    private void twistSideFace(boolean isRightFace, boolean isTwistUpwards) {
        int frontSideCol = isRightFace ? 2 : 0;
        int backSideCol = isRightFace ? 0 : 2;

        Color[] tmp = front.getGridColumn(frontSideCol);
        Color[] reversedTmp;

        if (isTwistUpwards) {
            front.setGridColumn(down.getGridColumn(frontSideCol), frontSideCol);

            reversedTmp = back.getGridColumn(backSideCol);
            reverseThreeElementsArray(reversedTmp);
            down.setGridColumn(reversedTmp, frontSideCol);

            reversedTmp = up.getGridColumn(frontSideCol);
            reverseThreeElementsArray(reversedTmp);
            back.setGridColumn(reversedTmp,backSideCol);

            up.setGridColumn(tmp, frontSideCol);

            if (isRightFace)
                right.clockwiseFixInnerValues();
            else
                left.counterClockwiseFixInnerValues();
        }
        else {
            front.setGridColumn(up.getGridColumn(frontSideCol), frontSideCol);

            reversedTmp = back.getGridColumn(backSideCol);
            reverseThreeElementsArray(reversedTmp);
            up.setGridColumn(reversedTmp, frontSideCol);

            reversedTmp = down.getGridColumn(frontSideCol);
            reverseThreeElementsArray(reversedTmp);
            back.setGridColumn(reversedTmp,backSideCol);

            down.setGridColumn(tmp, frontSideCol);

            if (isRightFace)
                right.counterClockwiseFixInnerValues();
            else
                left.clockwiseFixInnerValues();
        }
    }

    private static void reverseThreeElementsArray(Color[] array){
        Color tmp = array[0];
        array[0] = array[2];
        array[2] = tmp;
    }

    boolean isValidCube() {
        return CubeValidation.isValidCube(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        CubePrintUtils.topAndButtomBorders(result);

        CubePrintUtils.upAndDownFaces(result, this.getUp().getGrid());

        CubePrintUtils.middleBorders(result);

        CubePrintUtils.fourMiddleFaces(result, this.getLeft().getGrid(), this.getFront().getGrid(), this.getRight().getGrid(), this.getBack().getGrid());

        CubePrintUtils.middleBorders(result);

        CubePrintUtils.upAndDownFaces(result, this.getDown().getGrid());

        CubePrintUtils.topAndButtomBorders(result);

        return result.toString();
    }

    /**
     * Represents a cube's face
     *
     * grid - a matrix represents the face state.
     * color - the color of the central tile of the face
     */
    static class Face {

        private Color[][] grid = new Color[dim][dim];
        private Color color;

        Face(Color[][] grid) {
            this.grid = grid;
            this.color = grid[1][1];
        }

        Color[][] getGrid() {
            return grid;
        }


        /**
         * Returns the specified row of the grid
         *
         * @param rowIndex - the index of the wanted row
         */
        public Color[] getGridRow(int rowIndex) {
            if (rowIndex < 0 || rowIndex >= dim) {
                System.out.println("Invalid row!");
                return null;
            }
            return grid[rowIndex].clone();
        }


        /**
         * Sets an entire row of the grid
         *
         * @param row - an array containing the wanted values for the row
         * @param rowIndex - the index of the row we want to change
         */
        public void setGridRow(Color[] row, int rowIndex) {
            if (rowIndex < 0 || rowIndex >= dim) {
                System.out.println("Invalid row!");
                return;
            }
            if (row.length != dim) {
                System.out.println("Invalid row array!");
                return;
            }
            grid[rowIndex][0] = row[0];
            grid[rowIndex][1] = row[1];
            grid[rowIndex][2] = row[2];
        }


        /**
         * Returns the specified column of the grid
         *
         * @param colIndex - the index of the wanted column
         */
        Color[] getGridColumn(int colIndex) {
            if (colIndex < 0 || colIndex >= dim) {
                System.out.println("Invalid column!");
                return null;
            }
            Color[] res = new Color[dim];
            res[0] = grid[0][colIndex];
            res[1] = grid[1][colIndex];
            res[2] = grid[2][colIndex];
            return res;
        }


        /**
         * Sets an entire column of the grid
         *
         * @param col - an array containing the wanted values for the column
         * @param colIndex - the index of the column we want to change
         */
        void setGridColumn(Color[] col, int colIndex) {
            if (colIndex < 0 || colIndex >= dim) {
                System.out.println("Invalid column!");
                return;
            }
            if (col.length != dim) {
                System.out.println("Invalid column array!");
                return;
            }
            grid[0][colIndex] = col[0];
            grid[1][colIndex] = col[1];
            grid[2][colIndex] = col[2];
        }


        /**
         * Returns a specific entry of the grid
         */
        Color getGridEntry(int row, int column) {
            if (row < 0 || row >= dim) {
                System.out.println("Invalid row!");
                return null;
            }
            if (column < 0 || column >= dim) {
                System.out.println("Invalid column!");
                return null;
            }
            return grid[row][column];
        }

        /**
         * Sets a specific entry of the grid
         */
        public void setGridEntry(Color color, int row, int column) {
            if (row < 0 || row >= dim) {
                System.out.println("Invalid row!");
                return;
            }
            if (column < 0 || column >= dim) {
                System.out.println("Invalid column!");
                return;
            }
            grid[row][column] = color;
        }

        Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        /**
         * Turns the face's whole grid clockwise
         */
        void clockwiseFixInnerValues() {

            // corners update
            Color temp = grid[0][0];
            grid[0][0] = grid[2][0];
            grid[2][0] = grid[2][2];
            grid[2][2] = grid[0][2];
            grid[0][2] = temp;

            // edges update
            temp = grid[0][1];
            grid[0][1] = grid[1][0];
            grid[1][0] = grid[2][1];
            grid[2][1] = grid[1][2];
            grid[1][2] = temp;
        }

        /**
         * Turns the face's whole grid counter clockwise
         */
        void counterClockwiseFixInnerValues() {

            // corners update
            Color temp = grid[0][0];
            grid[0][0] = grid[0][2];
            grid[0][2] = grid[2][2];
            grid[2][2] = grid[2][0];
            grid[2][0] = temp;

            // edges update
            temp = grid[0][1];
            grid[0][1] = grid[1][2];
            grid[1][2] = grid[2][1];
            grid[2][1] = grid[1][0];
            grid[1][0] = temp;

        }

        /**
         * Turns the face's whole grid upside-down
         */
        void upsideDownFixInnerValues() {

            this.clockwiseFixInnerValues();
            this.clockwiseFixInnerValues();
        }
    }
}