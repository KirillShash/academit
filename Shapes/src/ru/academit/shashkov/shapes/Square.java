package ru.academit.shashkov.shapes;

public class Square implements Shape {
    private double sideLength;
    private static final int NUMBER_OF_SIDES = 4;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * NUMBER_OF_SIDES;
    }

    @Override
    public String toString() {
        return String.format("Квадрат с длиной стороны = %.1f. Площадь = %.1f. Периметр = %.1f.%n", sideLength, getArea(), getPerimeter());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Square s = (Square) o;

        return s.sideLength == sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }
}

