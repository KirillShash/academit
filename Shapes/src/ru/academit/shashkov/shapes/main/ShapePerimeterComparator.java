package ru.academit.shashkov.shapes.main;

import ru.academit.shashkov.shapes.Shape;

import java.util.Comparator;

public class ShapePerimeterComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getPerimeter(),o2.getPerimeter());
    }
}
