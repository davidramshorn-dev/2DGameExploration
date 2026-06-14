package world;

public class Point {
    public final int col, row;

    public Point(int col, int row) {
        this.col = col;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return col == p.col && row == p.row;
    }

    @Override
    public int hashCode() {
        return 31 * col + row;
    }
}

