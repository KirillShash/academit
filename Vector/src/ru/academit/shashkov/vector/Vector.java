package ru.academit.shashkov.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность массива должна быть больше 0!");
        }

        this.elements = new double[n];
    }

    public Vector(Vector vector) {
        this.elements = vector.elements;
    }

    public Vector(double[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Размерность массива должна быть больше 0!");
        }

        this.elements = Arrays.copyOf(arr, arr.length);
    }

    public Vector(int n, double[] arr) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность массива должна быть больше 0!");
        }

        this.elements = Arrays.copyOf(arr, n);
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

}
