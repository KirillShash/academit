package ru.academit.shashkov.range.main;
import ru.academit.shashkov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(3, 10);
        Range range2 = new Range(5, 15);
        double length = range1.getLength();

        System.out.println(length + System.lineSeparator() + range2.isInside(7));
    }
}
