package ru.academit.shashkov.vector;

import java.util.Arrays;
import java.util.StringTokenizer;

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

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность массива должна быть больше 0!");
        }

        elements = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность массива должна быть больше 0!");
        }

        elements = Arrays.copyOf(array, n);
    }

    public double getElement(int index) {
        if ((index >= elements.length) || index < 0) {
            throw new IndexOutOfBoundsException("Вы ввели отсутствующий индекс!");
        }

        return elements[index];
    }

    public void setElement(int index, double element) {
        if ((index >= elements.length) || index < 0) {
            throw new IndexOutOfBoundsException("Вы ввели отсутствующий индекс!");
        }

        elements[index] = element;
    }

    public int getSize() {
        return elements.length;
    }

    public Vector addVector(Vector vector) {
        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] += vector.elements[i];
        }

        return this;
    }

    public Vector subtractVector(Vector vector) {
        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] -= vector.elements[i];
        }

        return this;
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= scalar;
        }

        return this;
    }

    public Vector rotateVector() {
        multiplyByScalar(-1);

        return this;
    }

    public double getLength() {
        double length = 0;

        for (double element : elements) {
            length = length + Math.pow(element, 2);
        }

        return Math.sqrt(length);
    }

    @Override
    public String toString() {
        String result = Arrays.toString(elements);

        return "{" + result.substring(1, result.length() - 1) + "}";
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

        if (elements.length != vector.elements.length) {
            return false;
        }

        for (int i = 0; i < elements.length; ++i) {
            if (elements[i] != vector.elements[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int hash = 1;

        hash = hash * prime + Arrays.hashCode(elements);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector v = new Vector(vector1);

        v.addVector(vector2);

        return v;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector v = new Vector(vector1);

        v.subtractVector(vector2);

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
