package ru.academit.shashkov.minesweeper.model;

import ru.academit.shashkov.minesweeper.common.DifficultyType;
import ru.academit.shashkov.minesweeper.view.FieldConstructor;

public class GameManager implements Model {

    public void startPlay() {
        FieldConstructor.buildField(DifficultyType.BEGINNER_MODE);
    }
}
