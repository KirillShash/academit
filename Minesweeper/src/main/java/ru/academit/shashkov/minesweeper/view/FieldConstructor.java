package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.common.CellState;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;

import javax.swing.*;
import java.awt.*;

public class FieldConstructor {
    @Getter
    private static JButton[][] cells;

    public static void buildField(int rows, int columns) {
        cells = new JButton[columns][rows];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new JButton(IconsManager.getStateIcon(CellState.CLOSED));
                cells[i][j].setBorder(null);
                cells[i][j].setFocusPainted(false);
                cells[i][j].setContentAreaFilled(false);
            }
        }

        GridBagConstraints bagConstraints = new GridBagConstraints();

        for (int i = 0; i < cells.length; i++) {
            bagConstraints.gridx = i;
            for (int j = 0; j < cells[i].length; j++) {
                Display.getPanel().add(cells[i][j], bagConstraints);
            }
        }
    }
}
