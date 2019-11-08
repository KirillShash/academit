package ru.academit.shashkov.matrix;

public class Matrix {
    public Matrix(int n, int m){
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Количество строк или столбцов должно быть больше 0!");
        }
    }

    public Matrix(Matrix matrix){
        double newMatrix;
    }
}
