public class Pair{
    public int x;
    public int y;

    public Pair(int xValue, int yValue) {
        this.x = xValue;
        this.y = yValue;
    }

    public boolean equals(Pair a) {
        if (x == a.x && y == a.y) {
            return true;
        }
        return false;
    }
}