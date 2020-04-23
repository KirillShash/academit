package ru.academit.shashkov.minesweeper.model;

import lombok.experimental.UtilityClass;

@UtilityClass
class CustomSettingsUtils {
    private final int MIN_ROWS_NUMBER = 9;
    private final int MAX_ROWS_NUMBER = 24;

    private final int MIN_COLUMNS_NUMBER = 9;
    private final int MAX_COLUMNS_NUMBER = 30;

    private final int MIN_MINES_NUMBER = 10;

    int fitRowsNumber(int rowsNumber) {
        return fitUserInputNumber(rowsNumber, MIN_ROWS_NUMBER, MAX_ROWS_NUMBER);
    }

    int fitColumnsNumber(int columnsNumber) {
        return fitUserInputNumber(columnsNumber, MIN_COLUMNS_NUMBER, MAX_COLUMNS_NUMBER);
    }

    private int fitUserInputNumber(int userInputNumber, int minNumber, int maxNumber) {
        if (userInputNumber < minNumber) {
            return minNumber;
        }

        if (userInputNumber > maxNumber) {
            return maxNumber;
        }

        return userInputNumber;
    }

    int fitMinesNumber(int validatedRowsNumber, int validatedColumnNumbers, int validatedMinesNumber) {
        if (validatedMinesNumber < MIN_MINES_NUMBER) {
            return MIN_MINES_NUMBER;
        }

        int maxMinesNumber = countMaxMinesNumber(validatedRowsNumber, validatedColumnNumbers);

        if (validatedMinesNumber > maxMinesNumber) {
            return maxMinesNumber;
        }

        return validatedMinesNumber;
    }

    private int countMaxMinesNumber(int rowsNumber, int columnNumbers) {
        return (rowsNumber - 1) * (columnNumbers - 1);
    }
}
