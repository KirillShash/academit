package ru.academit.shashkov.minesweeper.controller;

import ru.academit.shashkov.minesweeper.common.DifficultyMode;
import ru.academit.shashkov.minesweeper.common.Score;
import ru.academit.shashkov.minesweeper.common.StandardDifficultyModes;

import java.util.Map;

public class Controller {
    public DifficultyMode getDifficultyMode() {
        return null;
    }


    public void openCell(int x, int y) {
    }

    public void openNotFlaggedNeighbours(int x, int y) {
    }

    public void setFlag(int x, int y) {

    }

    public void restartGame() {

    }

    public void restartGameWithNewDifficulty(StandardDifficultyModes mode) {

    }

    public void showCustomSettings() {

    }

    public void exitGame() {

    }

    public Map<String, Score> getHighScores() {
        return null;
    }

    public void resetHighScores() {

    }

    public void writeNewHighScore(String fieldForName) {

    }

    public void processCustomSettings(String textRowsNumber, String textColumnsNumber, String textMinesNumber) {

    }
}
