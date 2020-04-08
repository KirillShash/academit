package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.common.CellContent;
import ru.academit.shashkov.minesweeper.common.CellState;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;

import javax.swing.*;
import java.awt.*;

public class FieldConstructor {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;
    private JButton[][] minesButtons;
    @Getter
    private final JPanel playingField;
    private final ListenerCreator listenerCreator;

    FieldConstructor(int rowsNumber, int columnsNumber, ListenerCreator listenerCreator) {
        this.listenerCreator = listenerCreator;
        playingField = new JPanel();
        buildField(rowsNumber, columnsNumber);
    }

    public void buildField(int rowsNumber, int columnsNumber) {
        playingField.setLayout(new GridLayout(rowsNumber, columnsNumber));
        minesButtons = new JButton[rowsNumber][columnsNumber];

        for (int x = 0; x < minesButtons.length; x++) {
            for (int y = 0; y < minesButtons[x].length; y++) {
                JButton button = new JButton(IconsManager.getStateIcon(CellState.CLOSED));
                button.setPressedIcon(IconsManager.getContentIcon(CellContent.EMPTY));
                button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                button.setContentAreaFilled(false);
                button.setPreferredSize(new Dimension(WIDTH, HEIGHT));
                button.addMouseListener(listenerCreator.createListenerForPlayingField(x, y));
                button.setFocusPainted(false);

                playingField.add(button);
                minesButtons[x][y] = button;
            }
        }

        GridBagConstraints cellsConstraints = new GridBagConstraints();

        for (int x = 0; x < minesButtons.length; x++) {
            cellsConstraints.gridy = x;
            for (int y = 0; y < minesButtons[x].length; y++) {
                Body.getBody().add(minesButtons[x][y], cellsConstraints);
            }
        }
    }
}
