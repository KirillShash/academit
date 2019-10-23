package ru.academit.shashkov.shapes.main;

import ru.academit.shashkov.shapes.*;

import java.util.Arrays;

public class Main {
    private static Circle c1 = new Circle(2);
    private static Triangle t1 = new Triangle(0, 0, 1, 1, 3, 5);
    private static Rectangle r1 = new Rectangle(15, 22);
    private static Square s1 = new Square(45);
    private static Rectangle r2 = new Rectangle(23, 5);
    private static Triangle t2 = new Triangle(0, 0, 5, 3, 10, 4.5);

    public static void main(String[] args) {
        Shape[] shapesForCalculatingArea = {c1, t1, r1, s1, r2};
        Shape[] shapesForCalculatingPerimeter = {c1, t1, r1, s1, r2, t2};

        Arrays.sort(shapesForCalculatingArea, new ShapeAreaComparator());
        Arrays.sort(shapesForCalculatingPerimeter, new ShapePerimeterComparator());

        double maxArea = shapesForCalculatingArea[shapesForCalculatingArea.length - 1].getArea();
        double secondPerimeter = shapesForCalculatingPerimeter[shapesForCalculatingPerimeter.length - 2].getPerimeter();

        System.out.println("Max area = " + maxArea);
        System.out.println("2nd perimeter = " + secondPerimeter);
    }
}
