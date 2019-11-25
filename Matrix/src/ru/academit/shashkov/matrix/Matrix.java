package ru.academit.shashkov.matrix;

import ru.academit.shashkov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int numberOfRows, int numberOfColumns) {
        if (numberOfRows <= 0 || numberOfColumns <= 0) {
            throw new IllegalArgumentException("Количество строк или столбцов должно быть больше 0!");
        }

        rows = new Vector[numberOfRows];

        for (int i = 0; i < numberOfRows; i++) {
            rows[i] = new Vector(numberOfColumns);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Длина передаваемого массива должна быть больше 0!");
        }

        int maxNumberOfColumns = 0;

        for (double[] e : array) {
            if (e.length > maxNumberOfColumns) {
                maxNumberOfColumns = e.length;
            }
        }

        if (maxNumberOfColumns == 0) {
            throw new IllegalArgumentException("Длина всех вложенных массивов равна 0!");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxNumberOfColumns);

            int currentSize = array[i].length;

            for (int j = 0; j < currentSize; ++j) {
                rows[i].setElement(j, array[i][j]);
            }
        }
    }

    public Matrix(Vector[] vectors) {
        int maxNumberOfColumns = 0;

        for (Vector e : vectors) {
            if (e.getSize() > maxNumberOfColumns) {
                maxNumberOfColumns = e.getSize();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxNumberOfColumns);
            int currentSize = vectors[i].getSize();

            for (int j = 0; j < currentSize; j++) {
                rows[i].setElement(j, vectors[i].getElement(j));
            }
        }
    }

    public int getNumberOfRows() {
        return rows.length;
    }

    public int getNumberOfColumns() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index >= rows.length || index < 0) {
            throw new IndexOutOfBoundsException("Вы ввели отсутствующий индекс!");
        }

        return rows[index];
    }

    public void setRow(Vector vector, int index) {
        if (index >= rows.length || index < 0) {
            throw new IndexOutOfBoundsException("Вы ввели отсутствующий индекс!");
        }

        if (vector.getSize() != rows[index].getSize()) {
            throw new IllegalArgumentException("Допустимая размерность заменяющего вектора = " + this.getNumberOfColumns());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            this.getRow(index).setElement(i, vector.getElement(i));
        }
    }

    public Vector getColumn(int index) {
        if (index >= this.getNumberOfColumns() || index < 0) {
            throw new IndexOutOfBoundsException("Вы ввели отсутствующий индекс!");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setElement(i, rows[i].getElement(index));
        }

        return column;
    }

    public void transpose() {
        Matrix temp = new Matrix(this.getNumberOfColumns(), rows.length);

        for (int i = 0; i < temp.rows.length; i++) {
            temp.rows[i] = new Vector(this.getColumn(i));
        }

        rows = temp.rows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (this.getNumberOfColumns() != rows.length) {
            throw new IllegalArgumentException("Определитель может быть рассчитан только для квадратной матрицы!");
        }

        if (rows.length == 1) {
            return rows[0].getElement(0);
        }

        Matrix minor = new Matrix(this);
        minor.rows = new Vector[rows.length - 1];
        int matrixNumberOfColumns = this.getNumberOfColumns();
        int minorNumberOfColumns = matrixNumberOfColumns - 1;
        double result = 0;

        for (int i = 0; i < matrixNumberOfColumns; ++i) {
            if (rows[0].getElement(i) == 0) {
                continue;
            }

            for (int j = 0; j < minor.rows.length; ++j) {
                minor.rows[j] = new Vector(minorNumberOfColumns);
                for (int k = 0, n = 0; k < minorNumberOfColumns; ++k, ++n) {
                    if (n == i) {
                        --k;
                        continue;
                    }

                    minor.rows[j].setElement(k, rows[j + 1].getElement(n));
                }
            }

            if (i % 2 == 0) {
                result += rows[0].getElement(i) * minor.getDeterminant();
            } else {
                result -= rows[0].getElement(i) * minor.getDeterminant();
            }
        }

        return result;
    }

    public Vector getMultiplicationByVector(Vector vector) {
        if (this.getNumberOfColumns() != vector.getSize()) {
            throw new IllegalArgumentException("Количество строк в переданном векторе-стоблце не равно количеству столбцов в данной матрице!");
        }

        Vector result = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            result.setElement(i, Vector.getMultiply(rows[i], vector));
        }

        return result;
    }

    public void add(Matrix matrix) {
        if (rows.length != matrix.rows.length || this.getNumberOfColumns() != matrix.getNumberOfColumns()) {
            throw new IllegalArgumentException("Переданы матрицы разных размеров!");
        }

        for (int i = 0; i < rows.length; ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (rows.length != matrix.rows.length || this.getNumberOfColumns() != matrix.getNumberOfColumns()) {
            throw new IllegalArgumentException("Переданы матрицы разных размеров!");
        }

        for (int i = 0; i < rows.length; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getAddition(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getNumberOfColumns() != matrix2.getNumberOfColumns()) {
            throw new IllegalArgumentException("Переданы матрицы разных размеров!");
        }

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getSubtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getNumberOfColumns() != matrix2.getNumberOfColumns()) {
            throw new IllegalArgumentException("Переданы матрицы разных размеров!");
        }

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getNumberOfColumns() != matrix2.rows.length) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы не совпадает с количеством строк во второй матрице!");
        }

        int firstMatrixNumberOfColumns = matrix1.getNumberOfColumns();
        int secondMatrixNumberOfColumns = matrix2.getNumberOfColumns();
        Matrix resultMatrix = new Matrix(matrix1.rows.length, secondMatrixNumberOfColumns);

        for (int i = 0; i < resultMatrix.rows.length; ++i) {
            resultMatrix.rows[i] = new Vector(secondMatrixNumberOfColumns);

            for (int j = 0; j < secondMatrixNumberOfColumns; ++j) {
                double result = 0;

                for (int k = 0; k < firstMatrixNumberOfColumns; ++k) {
                    result += matrix1.rows[i].getElement(k) * matrix2.rows[k].getElement(j);
                }

                resultMatrix.rows[i].setElement(j, result);
            }
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        String result = Arrays.toString(this.rows);
        return "{ " + result.substring(1, result.length() - 1) + " }";
    }
}