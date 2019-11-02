package ru.academit.shashkov.vector.main;

import ru.academit.shashkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(6);
        Vector vector2 = new Vector(vector1);
        Vector vector3 = new Vector(new double[]{1, 2, 3, 4, 5});
        Vector vector4 = new Vector(7, new double[]{1, 2, 3, 4, 5});
        Vector vector5 = new Vector(new double[]{-3, -1, -5, -7, -4});
        Vector vector6 = new Vector(new double[]{3, 1, 5, 7, 4});
        Vector vector7 = new Vector(new double[]{-3, -1, -5, -7, -4});

        System.out.printf("Векторы для теста функций:%n№1 = %s%n№2 = %s%n№3 = %s%n№4 = %s%n№5 = %s%n№6 = %s%n№7 = %s%n", vector1, vector2, vector3, vector4, vector5, vector6, vector7);

        System.out.println("Поиск элемента по индексу 4 из вектора №3: " + vector3.getElement(4));

        vector3.setElement(4, 4.5);
        System.out.println("Этот же элемент после замены: " + vector3.getElement(4));

        System.out.println("Размерность вектора №4 = " + vector4.getSize());

        System.out.println("Нестатическое сложение вектора №1 и вектора №3 = " + vector1.addVector(vector3));

        System.out.println("Нестатическое вычитание вектора №4 из вектора №3 = " + vector4.subtractVector(vector3));

        System.out.println("Разворот вектора № 3 = " + vector3.rotateVector());

        System.out.println("Умножение на скаляр 5 вектора №3 = " + vector3.multiplyByScalar(5));

        System.out.println("Длина вектора №5 = " + vector5.getLength());

        System.out.println("Равны ли векторы №5 и №6 = " + vector5.equals(vector6));
        System.out.println("Равны ли векторы №5 и №7 = " + vector5.equals(vector7));

        System.out.println("Статическое сложение вектора №5 и вектора №6 = " + Vector.getSum(vector5, vector6));

        System.out.println("Статическое вычитание вектора №5 из вектора №6 = " + Vector.getDifference(vector5, vector6));

        System.out.println("Скалярное произведение векторов №5 и №6 = " + Vector.getMultiply(vector5, vector6));
    }
}
