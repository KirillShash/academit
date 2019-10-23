package ru.academit.shashkov.shapes.main;

import ru.academit.shashkov.shapes.Circle;
import ru.academit.shashkov.shapes.Rectangle;
import ru.academit.shashkov.shapes.Shape;
import ru.academit.shashkov.shapes.Triangle;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        double max = 0;

        Shape[] shapesArea = {new Circle(2), new Triangle(0, 0, 1, 1, 3, 5), new Rectangle(15, 22)};
        Shape[] shapesPerimeter = {new Circle(2), new Triangle(0, 0, 1, 1, 3, 5), new Rectangle(15, 22)};
        Comparator <Shape> maxArea = new ShapeAreaComparator();
        Comparator <Shape> maxPerimeter = new ShapePerimeterComparator();

        Arrays.sort(shapesArea, maxArea);
        Arrays.sort(shapesPerimeter, maxPerimeter);

        for (Shape e : shapesArea) {
            System.out.println(e.getArea());
        }

        for (Shape e : shapesPerimeter) {
            System.out.println(e);
        }
    }
}
