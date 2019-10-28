package ru.academit.shashkov.range.main;

import ru.academit.shashkov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Начало первого интервала:");
        double from1 = scanner.nextDouble();

        System.out.println("Конец первого интервала:");
        double to1 = scanner.nextDouble();

        System.out.println("Начало второго интервала:");
        double from2 = scanner.nextDouble();

        System.out.println("Конец второго интервала:");
        double to2 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        System.out.printf("Первый интервал: %s%nВторой интервал: %s%n", range1, range2);

        System.out.println("Число для проверки вхождения в интервалы:");
        double number = scanner.nextDouble();

        System.out.printf("Входит ли число в первый интервал - %b%n", range1.isInside(number));
        System.out.printf("Входит ли число во второй интервал - %b%n", range2.isInside(number));

        double length1 = range1.getLength();
        double length2 = range2.getLength();

        System.out.printf("Длина первого интервала = %.2f%n", length1);
        System.out.printf("Длина второго интервала = %.2f%n", length2);

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Пересечений интервалов нет.");
        } else {
            System.out.printf("Интервал пересечения - %s%n", intersection);
        }

        Range[] union = range1.getUnion(range2);

        if (union.length > 1) {
            System.out.printf("Результатом объединения являются: %s и %s%n", union[0], union[1]);
        } else {
            System.out.printf("Результатом объединения явлется: %s%n", union[0]);
        }

        Range[] difference = range1.getDifference(range2);

        if (difference.length > 1) {
            System.out.printf("Результатом разности являются: %s и %s%n", difference[0], difference[1]);
        } else if (difference.length == 0) {
            System.out.println("Разность данных интервалов - пустой интервал.");
        } else {
            System.out.printf("Результатом разности явлется: %s", difference[0]);
        }
    }
}
