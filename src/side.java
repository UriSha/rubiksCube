
public class side{
    public enum Direction{
        FRONT,
        BACK,
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
    Direction dir;
    int centerColor;
    int[][] facet;

    public side(int direction, int[][] facet){
        this.facet = facet;
        this.centerColor = facet[1][1];
        switch (direction){
            case 0: this.dir =Direction.UP;
            case 1: this.dir =Direction.FRONT;
            case 2: this.dir =Direction.DOWN;
            case 3: this.dir =Direction.BACK;
            case 4: this.dir =Direction.RIGHT;
            case 5: this.dir =Direction.LEFT;
        }
    }

    public static void main(String[] args) {
        System.out.println("hello world");
    }
}


