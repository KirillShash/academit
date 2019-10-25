package ru.academit.shashkov.shapes;

public class Rectangle implements Shape {
    private double verticalSideLength;
    private double horizontalSideLength;

    public Rectangle(double verticalSideLength, double horizontalSideLength) {
        this.verticalSideLength = verticalSideLength;
        this.horizontalSideLength = horizontalSideLength;
    }

    public double getVerticalSideLength() {
        return verticalSideLength;
    }

    public void setVerticalSideLength(double verticalSideLength) {
        this.verticalSideLength = verticalSideLength;
    }

    public double getHorizontalSideLength() {
        return horizontalSideLength;
    }

    public void setHorizontalSideLength(double horizontalSideLength) {
        this.horizontalSideLength = horizontalSideLength;
    }

    @Override
    public double getWidth() {
        return horizontalSideLength;
    }

    @Override
    public double getHeight() {
        return verticalSideLength;
    }

    @Override
    public double getArea() {
        return horizontalSideLength * verticalSideLength;
    }

    @Override
    public double getPerimeter() {
        return (horizontalSideLength + verticalSideLength) * 2;
    }

    @Override
    public String toString() {
        return "Rectangle";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Rectangle r = (Rectangle) o;

        return horizontalSideLength == r.horizontalSideLength && verticalSideLength == r.verticalSideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + (int) verticalSideLength;
        hash = prime * hash + (int) horizontalSideLength;

        return hash;
    }
}
