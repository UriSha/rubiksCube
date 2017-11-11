


public class SubCube {

    private Cube.Color[] colors;

    public SubCube(Cube.Color ... colors){
        this.colors = colors.clone();
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof SubCube))
            return false;
        SubCube otherSubCube = (SubCube)other;
        int n = colors.length;
        if (otherSubCube.colors.length != n) {
            return false;
        }
        for (int i =0 ; i<n ; i++){
            if (colors[i].getValue() != otherSubCube.colors[i].getValue())
                return false;
        }
        return true;

    }

    @Override
    public int hashCode(){
        int res = 0;
        for (Cube.Color col : colors)
            res = 10*res + col.getValue();
        return res;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Cube.Color col : colors)
            sb.append(col.getValue());
        return sb.toString();
    }

}
