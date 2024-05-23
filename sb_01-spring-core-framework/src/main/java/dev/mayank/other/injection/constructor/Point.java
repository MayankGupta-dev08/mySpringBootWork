package dev.mayank.other.injection.constructor;

@SuppressWarnings("unused")
class Point {
    private int x;
    private int y;

    public Point() {
    }

    /**
     * If String Constructor is not present, then order in which its present in class is important, given that they satisfy the acceptance criteria
     */
    public Point(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
        System.out.println("--> Point(double, double)");
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("--> Point(int, int)");
    }

    /**
     * If the type of argument is not mentioned in the xml arg tag, then it would be considered as a String var
     * First preference is given to the constructor which has String as the arg type
     */
    public Point(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        System.out.println("--> Point(String, String)");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point(x=%d, y=%d)".formatted(getX(), getY());
    }
}