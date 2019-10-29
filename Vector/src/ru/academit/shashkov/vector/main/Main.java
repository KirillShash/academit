package ru.academit.shashkov.vector.main;

import ru.academit.shashkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] x = {1, 2, 3, 4};
        Vector v = new Vector(x);
        System.out.println(v);
        double[] y = {1, 2, 3,4,5,6,7};
        Vector ve = new Vector(8, y);
        System.out.println(ve);
        Vector vector = new Vector(y);
        System.out.println(vector);
    }
}
