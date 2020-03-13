package ru.academit.shashkov.minesweeper.view.menu;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.view.ActionListenerManager;

import javax.swing.*;

public class Menu {
    @Getter
    private static final JMenuItem beginnerMode;
    @Getter
    private static final JMenuItem intermediateMode;
    @Getter
    private static final JMenuItem expertMode;
    @Getter
    private static final JMenuItem customMode;
    @Getter
    private static final JMenuItem beginnerModeHighScore;
    @Getter
    private static final JMenuItem intermediateModeHighScore;
    @Getter
    private static final JMenuItem expertModeHighScore;
    @Getter
    private static final JMenuItem exit;

    static {
        beginnerMode = new JMenuItem("Beginner");
        intermediateMode = new JMenuItem("Intermediate");
        expertMode = new JMenuItem("Expert");
        customMode = new JMenuItem("Custom Mode");
        beginnerModeHighScore = new JMenuItem("Beginner");
        intermediateModeHighScore = new JMenuItem("Intermediate");
        expertModeHighScore = new JMenuItem("Expert");
        exit = new JMenuItem("Exit");
    }

    public static JMenuBar addMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        JMenu newGame = new JMenu("New game");
        JMenu highScoreTable = new JMenu("High score table");

        newGame.add(beginnerMode);
        newGame.add(intermediateMode);
        newGame.add(expertMode);
        newGame.add(customMode);

        highScoreTable.add(beginnerModeHighScore);
        highScoreTable.add(intermediateModeHighScore);
        highScoreTable.add(expertModeHighScore);

        JMenuItem[] items = {beginnerMode, intermediateMode, expertMode, customMode, beginnerModeHighScore, intermediateModeHighScore, expertModeHighScore, exit};

        for (JMenuItem e : items) {
            e.setFocusPainted(false);
            e.setContentAreaFilled(false);
        }

        menu.add(newGame);
        menu.add(highScoreTable);
        menu.add(exit);
        menuBar.add(menu);

        ActionListenerManager.startBeginnerMode();
        ActionListenerManager.startExpertMode();
        ActionListenerManager.startIntermediateMode();
        ActionListenerManager.exit();

        return menuBar;
    }
}
