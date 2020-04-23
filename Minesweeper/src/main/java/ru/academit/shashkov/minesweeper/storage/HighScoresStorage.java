package ru.academit.shashkov.minesweeper.storage;

import ru.academit.shashkov.minesweeper.common.Score;

import java.io.IOException;
import java.util.Map;

public interface HighScoresStorage {
    Object loadHighScoresFile() throws IOException, ClassNotFoundException;

    void updateHighScoresFile(Map<String, Score> highScores);
}
