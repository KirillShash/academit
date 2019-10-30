package ru.academit.shashkov.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность массива должна быть больше 0!");
        }

        elements = new double[n];
    }

    public Vector(Vector vector) {
        elements = Arrays.copyOf(vector.elements, vector.elements.length);
    }

    public Vector(double[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Размерность массива должна быть больше 0!");
        }

        elements = Arrays.copyOf(arr, arr.length);
    }

    public Vector(int n, double[] arr) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность массива должна быть больше 0!");
        }

        elements = Arrays.copyOf(arr, n);
    }

    public double getElement(int index) {
        if ((index > elements.length - 1) || index < 0) {
            throw new IllegalArgumentException("Вы ввели отсутствующий индекс!");
        }

        return elements[index];
    }

    public void setElement(int index, double element) {
        if ((index > elements.length - 1) || index < 0) {
            throw new IllegalArgumentException("Вы ввели отсутствующий индекс!");
        }

        elements[index] = element;
    }

    public int getSize() {
        return elements.length;
    }

    public Vector getSum(Vector vector) {
        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        } else {
            vector.elements = Arrays.copyOf(vector.elements, elements.length);
        }

        for (int i = 0; i < elements.length; i++) {
            elements[i] += vector.elements[i];
        }

        return this;
    }

    public Vector getDifference(Vector vector) {
        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        } else {
            vector.elements = Arrays.copyOf(vector.elements, elements.length);
        }

        for (int i = 0; i < elements.length; i++) {
            elements[i] -= vector.elements[i];
        }

        return this;
    }

    public Vector getMultiplyByScalar(double scalar) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= scalar;
        }

        return this;
    }

    public Vector getRotate() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= -1;
        }

        return this;
    }

    public double getLength() {
        double length = 0;

        for (int i = 0; i < elements.length - 1; ++i) {
            length += Math.pow(elements[i], 2);
        }

        length = Math.sqrt(length + Math.pow(elements[elements.length - 1], 2));

        return length;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        if (this.elements.length != vector.elements.length) {
            return false;
        }

        for (int i = 0; i < vector.elements.length; i++) {
            if (Math.abs(this.elements[i]) != Math.abs(vector.elements[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int hash = 1;

        hash = hash * prime + elements.length;
        hash = hash * prime + Arrays.hashCode(elements);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector v = new Vector(vector1);

        v.getSum(vector2);

        return v;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector v = new Vector(vector1);

        v.getDifference(vector2);

        return v;
    }

    public static double getMultiply(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.getSize(), vector2.getSize());
        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.elements[i] * vector2.elements[i];
        }

        return result;
    }
}
