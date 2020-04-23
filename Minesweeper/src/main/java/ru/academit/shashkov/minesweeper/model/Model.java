package ru.academit.shashkov.minesweeper.model;

import ru.academit.shashkov.minesweeper.common.DifficultyMode;
import ru.academit.shashkov.minesweeper.common.MinesweeperView;
import ru.academit.shashkov.minesweeper.common.Score;
import ru.academit.shashkov.minesweeper.common.StandardDifficultyModes;

import java.util.Map;

public interface Model {
    void prepareGame();

    void startGame(int x, int y);

    void openCell(int x, int y);

    void restartGameWithNewDifficulty(StandardDifficultyModes newDifficultyMode);

    void restartGame();

    void restartGameWithCustomSettings(int numberOfRows, int numberOfColumns, int numberOfMines);

    void setFlag(int x, int y);

    void setMinesweeperView(MinesweeperView minesweeperView);

    void initMinesweeperView();

    void openNotFlaggedNeighbours(int x, int y);

    Map<String, Score> getHighScores();

    void writeNewHighScore(String text);

    void resetHighScores();

    void exitGame();

    void setTime(long timeSpent);

    DifficultyMode getCurrentDifficultyMode();
}
