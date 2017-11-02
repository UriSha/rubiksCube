/** Represents the cube's state
 *
 *  front - the cube's front face, looking from the arm's point of reference
 *  all other faces are according to the above point of reference
 */

public class newCube {
    private final int dimension = 3;

    // Directions point of reference: Robot's arm is front
    private Face front;
    private Face back;
    private Face up;
    private Face down;
    private Face right;
    private Face left;

    public newCube(Face front, Face back, Face up, Face down, Face right, Face left) {
        this.front = front;
        this.back = back;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    public enum Color {
        RED,
        WHITE,
        GREEN,
        BLUE,
        ORANGE,
        YELLOW
    }

    public Face getFront() {
        return front;
    }

    public void setFront(Face front) {
        this.front = front;
    }

    public Face getBack() {
        return back;
    }

    public void setBack(Face back) {
        this.back = back;
    }

    public Face getUp() {
        return up;
    }

    public void setUp(Face up) {
        this.up = up;
    }

    public Face getDown() {
        return down;
    }

    public void setDown(Face down) {
        this.down = down;
    }

    public Face getRight() {
        return right;
    }

    public void setRight(Face right) {
        this.right = right;
    }

    public Face getLeft() {
        return left;
    }

    public void setLeft(Face left) {
        this.left = left;
    }


    /** Rotates the cube to the right- front face turns to be right face, etc. */
    public void rightRotate() {
        Face tmp = front;
        front = left;
        left = back;
        back = right;
        right = tmp;
        up.counterClockwiseFixInnerValues();
        down.clockwiseFixInnerValues();
    }

    /** Rotates the cube to the left- front face turns to be left face, etc. */
    public void leftRotate() {
        Face tmp = front;
        front = right;
        right = back;
        back = left;
        left = tmp;
        up.clockwiseFixInnerValues();
        down.counterClockwiseFixInnerValues();
    }

    /** Flip the cube on its front- front face turns to be bottom face, etc. */
    public void flip() {
        Face tmp = front;
        front = up;
        up = back;
        back = down;
        down = tmp;
        left.clockwiseFixInnerValues();
        right.counterClockwiseFixInnerValues();
    }

    /** Twists bottom face to the right- front face bottom row turns to be right face bottom row, etc. */
    public void rightTwistBottomFace() {
        Color[] tmp = front.grid[dimension-1];
        front.grid[dimension-1] = left.grid[dimension-1];
        left.grid[dimension-1] = back.grid[dimension-1];
        back.grid[dimension-1] = right.grid[dimension-1];
        right.grid[dimension-1] = tmp;
        down.clockwiseFixInnerValues();
    }

    /** Twists bottom face to the left- front face bottom row turns to be left face bottom row, etc. */
    public void leftTwistBottomFace() {
        Color[] tmp = front.grid[dimension-1];
        front.grid[dimension-1] = right.grid[dimension-1];
        right.grid[dimension-1] = back.grid[dimension-1];
        back.grid[dimension-1] = left.grid[dimension-1];
        left.grid[dimension-1] = tmp;
        down.counterClockwiseFixInnerValues();
    }



    /** Represents a cube's face
     *
     *  grid - a matrix represents the face state.
     *  color - the color of the central tile of the face
     */
    public class Face {

        private final Color[][] grid = new Color[dimension][dimension];
        private Color color;

        public Face() {

        }

        public Color[][] getGrid() {
            return grid;
        }

        /** Sets an entire row of the grid */
        public void setGridRow(Color[] row, int rowIndex){
            if (rowIndex < 0 || rowIndex >= dimension){
                System.out.println("Invalid row!");
                return;
            }
            if (row.length != dimension){
                System.out.println("Invalid row array!");
                return;
            }
            grid[rowIndex][0] = row[0];
            grid[rowIndex][1] = row[1];
            grid[rowIndex][2] = row[2];
        }


        /** Sets an entire column of the grid */
        public void setGridColumn(Color[] col, int colIndex){
            if (colIndex < 0 || colIndex >= dimension){
                System.out.println("Invalid column!");
                return;
            }
            if (col.length != dimension){
                System.out.println("Invalid column array!");
                return;
            }
            grid[0][colIndex] = col[0];
            grid[1][colIndex] = col[1];
            grid[2][colIndex] = col[2];
        }


        /** Sets a specific entry of the grid */
        public void setGridEntry(Color color, int row, int column){
            if (row < 0 || row >= dimension){
                System.out.println("Invalid row!");
                return;
            }
            if (column < 0 || column >= dimension){
                System.out.println("Invalid column!");
                return;
            }
            grid[row][column] = color;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        /** Turns the face's whole grid clockwise */
        public void clockwiseFixInnerValues() {

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

        /** Turns the face's whole grid counter clockwise */
        public void counterClockwiseFixInnerValues() {

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
    }
}
