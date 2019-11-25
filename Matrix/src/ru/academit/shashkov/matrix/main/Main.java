package ru.academit.shashkov.matrix.main;

import ru.academit.shashkov.matrix.Matrix;
import ru.academit.shashkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 3, 4}), new Vector(new double[]{4, 3, 2, 1, -1}), new Vector(new double[]{1, 2, 3, 4})});
        Matrix matrix2 = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 3}), new Vector(new double[]{5, 6, 7}), new Vector(new double[]{8, 9, 11})});

        System.out.println(matrix1);
        System.out.println(matrix2);

        System.out.println("Rows = " + matrix1.getNumberOfRows());
        System.out.println("Columns = " + matrix1.getNumberOfColumns());

        System.out.println(matrix1.getRow(0));

        Vector v = new Vector(new double[]{5, 5, 5, 5, 5});
        matrix1.setRow(v, 2);
        System.out.println(matrix1.getRow(2));

        System.out.println(matrix1.getColumn(4));

        System.out.println("Матрица до транспонирования: " + matrix1);
        matrix1.transpose();
        System.out.println("Матрица после транспонирования: " + matrix1);

        matrix1.multiplyByScalar(-1);
        System.out.println(matrix1);

        System.out.println(matrix2.getDeterminant());
        matrix2.transpose();
        System.out.println(matrix2.getDeterminant());

        System.out.println(matrix1);
        System.out.println(matrix2);

        Vector v2 = new Vector(new double[]{5, 5, 5});
        System.out.println(matrix2.getMultiplicationByVector(v2));

        matrix2.add(matrix2);
        System.out.println(matrix2);

        matrix2.subtract(matrix2);
        System.out.println(matrix2);

        System.out.println(Matrix.getAddition(matrix1, matrix1));
        System.out.println(Matrix.getSubtraction(matrix1, matrix1));

        Matrix matrix3 = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 3}), new Vector(new double[]{4, 3, 2}), new Vector(new double[]{1, 2, 3})});
        System.out.println(Matrix.getMultiplication(matrix3, matrix3));
    }
}
