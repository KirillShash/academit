package ru.academit.shashkov.minesweeper.view;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.academit.shashkov.minesweeper.common.StandardDifficultyModes;
import ru.academit.shashkov.minesweeper.controller.Controller;
import ru.academit.shashkov.minesweeper.controller.MenuCommands;
import ru.academit.shashkov.minesweeper.view.menu.Menu;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Slf4j
@AllArgsConstructor
public class ListenerCreator {
    private final Controller controller;

    MouseListener createListenerForPlayingField(int x, int y) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        controller.openCell(x, y);
                        break;
                    case MouseEvent.BUTTON2:
                        controller.openNotFlaggedNeighbours(x, y);
                        break;
                    case MouseEvent.BUTTON3:
                        controller.setFlag(x, y);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    ActionListener createListenerForRestartButton() {
        return e -> controller.restartGame();
    }

    public ActionListener createListenerForMenuButton() {
        return e -> {
            JMenuItem button = (JMenuItem) e.getSource();

            switch (MenuCommands.valueOf(button.getActionCommand())) {
                case RESTART:
                    controller.restartGame();
                    break;

                case SWITCH_TO_BEGINNER_MODE:
                    controller.restartGameWithNewDifficulty(StandardDifficultyModes.BEGINNER);
                    break;

                case SWITCH_TO_INTERMEDIATE_MODE:
                    controller.restartGameWithNewDifficulty(StandardDifficultyModes.INTERMEDIATE);
                    break;

                case SWITCH_TO_EXPERT_MODE:
                    controller.restartGameWithNewDifficulty(StandardDifficultyModes.EXPERT);
                    break;

                case SWITCH_TO_CUSTOM_MODE:
                    swingView.showCustomSettings(controller.getDifficultyMode());
                    break;

                case HIGH_SCORES:
                    swingView.showHighScores(controller.getHighScores());
                    break;

                case EXIT:
                    controller.exitGame();
                    break;

                default:
                    break;
            }
        };
    }

    public ActionListener createListenerForResetHighScoresButton() {
        return e -> controller.resetHighScores();
    }

    public ActionListener createListenerForRecordsManNameApply(JTextField fieldForName) {
        return e -> controller.writeNewHighScore(fieldForName.getText());
    }

    public ActionListener createListenerForCustomSettings
            (JTextField textRowsNumber, JTextField textColumnsNumber, JTextField textMinesNumber) {
        return e -> controller.processCustomSettings
                (textRowsNumber.getText(), textColumnsNumber.getText(), textMinesNumber.getText());
    }

    public static void exit() {
        Menu.getExit().addActionListener(actionEvent -> System.exit(0));
    }
}
