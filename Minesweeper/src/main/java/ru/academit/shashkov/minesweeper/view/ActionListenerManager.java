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
            Display.restart();
            FieldConstructor.buildField(9, 9);
            Display.updatePanel();
        });
    }

    public static void startIntermediateMode() {
        Menu.getIntermediateMode().addActionListener(actionEvent -> {
            Display.restart();
            FieldConstructor.buildField(16, 16);
            Display.setFrameSizeForDifficult(DifficultyType.INTERMEDIATE_MOD);
            Display.updatePanel();
        });
    }

    public static void startExpertMode() {
        Menu.getExpertMode().addActionListener(actionEvent -> {
            Display.restart();
            FieldConstructor.buildField(16, 30);
            Display.setFrameSizeForDifficult(DifficultyType.EXPERT_MOD);
            Display.updatePanel();
        });
    }

    public static void exit() {
        Menu.getExit().addActionListener(actionEvent -> System.exit(0));
    }
}
