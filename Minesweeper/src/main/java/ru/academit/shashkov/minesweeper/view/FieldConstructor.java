package ru.academit.shashkov.minesweeper.view;

import ru.academit.shashkov.minesweeper.common.CellContent;
import ru.academit.shashkov.minesweeper.common.CellState;
import ru.academit.shashkov.minesweeper.common.DifficultyType;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;
import ru.academit.shashkov.minesweeper.view.menu.CustomModeMenu;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class FieldConstructor {
    private static final Map<DifficultyType, Integer[]> minefieldSize;

    static {
        minefieldSize = new EnumMap<>(DifficultyType.class);
        minefieldSize.put(DifficultyType.BEGINNER_MODE, new Integer[]{9, 9});
        minefieldSize.put(DifficultyType.INTERMEDIATE_MODE, new Integer[]{16, 16});
        minefieldSize.put(DifficultyType.EXPERT_MODE, new Integer[]{16, 30});
        minefieldSize.put(DifficultyType.CUSTOM_MODE, new Integer[]{CustomModeMenu.getRowsCount(), CustomModeMenu.getColumnsCount()});
    }

    public static void buildField(DifficultyType difficultyType) {
        int rowsNumbers = minefieldSize.get(difficultyType)[0];
        int columnsNumbers = minefieldSize.get(difficultyType)[1];

        JButton[][] cells = new JButton[rowsNumbers][columnsNumbers];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new JButton(IconsManager.getStateIcon(CellState.CLOSED));
                cells[i][j].setPressedIcon(IconsManager.getContentIcon(CellContent.EMPTY));
                cells[i][j].setBorder(null);
                cells[i][j].setFocusPainted(false);
            }
        }

        GridBagConstraints cellsConstraints = new GridBagConstraints();

        for (int i = 0; i < cells.length; i++) {
            cellsConstraints.gridy = i;
            for (int j = 0; j < cells[i].length; j++) {
                Body.getBody().add(cells[i][j], cellsConstraints);
            }
        }
    }
}
