
public enum Face_Enum {
    UP(1),
    LEFT(2),
    FRONT(3),
    BACK(4),
    RIGHT(5),
    DOWN(6);


    private final int value;
    Face_Enum(int val){
        this.value = val;
    }

    public int getValue() {
        return value;
    }
}
