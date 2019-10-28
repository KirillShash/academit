package ru.academit.shashkov.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double firstSideLength = getSideLength(x1, y1, x2, y2);
        double secondSideLength = getSideLength(x2, y2, x3, y3);
        double thirdSideLength = getSideLength(x1, y1, x3, y3);
        double halfPerimeter = this.getPerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - firstSideLength) * (halfPerimeter - secondSideLength) * (halfPerimeter - thirdSideLength));
    }

    @Override
    public double getPerimeter() {
        double firstSideLength = getSideLength(x1, y1, x2, y2);
        double secondSideLength = getSideLength(x2, y2, x3, y3);
        double thirdSideLength = getSideLength(x1, y1, x3, y3);
        return firstSideLength + secondSideLength + thirdSideLength;
    }

    @Override
    public String toString() {
        return String.format("Треугольник c координатами вершин x1 = %.1f| x2 = %.1f| x3 = %.1f| y1 = %.1f| y2 = %.1f| y3 = %.1f. " +
                "Площадь = %.1f. Периметр = %.1f.%n", x1, x2, x3, y1, y2, y3, getArea(), getPerimeter());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Triangle t = (Triangle) o;

        return t.x1 == x1 && t.x2 == x2 && t.x3 == x3 && t.y1 == y1 && t.y2 == y2 && t.y3 == y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}
