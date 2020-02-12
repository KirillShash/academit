package ru.academit.shashkov.minesweeper.view;

import javax.swing.*;
import java.awt.*;

public class FieldConstructor {
    public static void getField(int columns, int rows) {
        JButton[][] cells = new JButton[columns][rows];

        ImageIcon icon1 = new ImageIcon("C:\\Users\\Kirill\\IdeaProjects\\academit\\Minesweeper\\resources\\icons\\0.png");

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new JButton(icon1);
                cells[i][j].setBorder(null);
                cells[i][j].setFocusPainted(false);
                cells[i][j].setContentAreaFilled(false);
            }
        }

        GridBagConstraints bagConstraints = new GridBagConstraints();

        for (int i = 0; i < cells.length; i++) {
            bagConstraints.gridx = 1 + i;
            for (int j = 0; j < cells[i].length; j++) {
                Display.getPanel().add(cells[i][j], bagConstraints);
            }
        }
    }
}
