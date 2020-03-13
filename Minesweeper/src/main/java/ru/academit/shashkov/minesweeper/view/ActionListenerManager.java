package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.common.DifficultyType;
import ru.academit.shashkov.minesweeper.view.menu.Menu;

import javax.swing.*;

public class ActionListenerManager {
    @Getter
    private static final ActionMap actionMap = new ActionMap();

    public static void startBeginnerMode() {
        Menu.getBeginnerMode().addActionListener(actionEvent -> {
            Body.restartBody();
            FieldConstructor.buildField(DifficultyType.BEGINNER_MODE);
            MinesweeperView.setFrameSizeForDifficult(DifficultyType.BEGINNER_MODE);
            Body.updateBody();
        });
    }

    public static void startIntermediateMode() {
        Menu.getIntermediateMode().addActionListener(actionEvent -> {
            Body.restartBody();
            FieldConstructor.buildField(DifficultyType.INTERMEDIATE_MODE);
            MinesweeperView.setFrameSizeForDifficult(DifficultyType.INTERMEDIATE_MODE);
            Body.updateBody();
        });
    }

    public static void startExpertMode() {
        Menu.getExpertMode().addActionListener(actionEvent -> {
            Body.restartBody();
            FieldConstructor.buildField(DifficultyType.EXPERT_MODE);
            MinesweeperView.setFrameSizeForDifficult(DifficultyType.EXPERT_MODE);
            Body.updateBody();
        });
    }

    public static void exit() {
        Menu.getExit().addActionListener(actionEvent -> System.exit(0));
    }
}
