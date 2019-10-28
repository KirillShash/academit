package ru.academit.shashkov.shapes.main;

import ru.academit.shashkov.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapesForCalculatingArea = {new Circle(2), new Triangle(0, 0, 1, 1, 3, 5), new Rectangle(15, 22),
                new Square(45), new Rectangle(23, 5), new Triangle(0, 0, 5, 3, 10, 4.5)};
        Shape[] shapesForCalculatingPerimeter = {new Circle(2), new Triangle(0, 0, 1, 1, 3, 5), new Rectangle(15, 22),
                new Square(45), new Rectangle(23, 5), new Triangle(0, 0, 5, 3, 10, 4.5)};

        System.out.println("Фигуры для расчета:");
        for (Shape shape : shapesForCalculatingArea) {
            System.out.print(shape);
        }

        System.out.println();

        Arrays.sort(shapesForCalculatingArea, new ShapeAreaComparator());
        Arrays.sort(shapesForCalculatingPerimeter, new ShapePerimeterComparator());

        StringBuffer line1 = new StringBuffer("Площади в порядке возрастания = ");

        for (Shape shape : shapesForCalculatingArea) {
            line1.append(String.format("%.2f| ", shape.getArea()));
        }
        System.out.println(line1);

        StringBuffer line2 = new StringBuffer("Периметры в порядке возрастания = ");

        for (Shape shape : shapesForCalculatingPerimeter) {
            line2.append(String.format("%.2f| ", shape.getPerimeter()));
        }
        System.out.println(line2);

        System.out.println();

        double maxArea = shapesForCalculatingArea[shapesForCalculatingArea.length - 1].getArea();
        double secondPerimeter = shapesForCalculatingPerimeter[shapesForCalculatingPerimeter.length - 2].getPerimeter();

        System.out.printf("Максимальная площадь = %.2f%n", maxArea);
        System.out.printf("Второй по размеру периметр = %.2f", secondPerimeter);
    }
}
