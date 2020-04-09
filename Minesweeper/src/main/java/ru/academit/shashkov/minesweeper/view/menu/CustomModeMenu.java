package ru.academit.shashkov.minesweeper.view.menu;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.view.Body;
import ru.academit.shashkov.minesweeper.view.MinesweeperSwingView;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Map;

public class CustomModeMenu extends JFrame {
    private static JDialog dialog;
    private static JTextField rows;
    private static JTextField columns;
    private static JTextField mines;
    @Getter
    private static int rowsCount;
    @Getter
    private static int columnsCount;
    @Getter
    private static int minesCount;
    @Getter
    private static JButton acceptButton;
    @Getter
    private static JButton cancelButton;

    static {
        rows = new JTextField(3);
        columns = new JTextField(3);
        mines = new JTextField(3);
        acceptButton = new JButton("Accept");
        cancelButton = new JButton("Cancel");
    }

    public void addCustomMenu() {
        Menu.getCustomMode().addActionListener(actionEvent -> {
            JDialog dialog = createDialog();
            dialog.setVisible(true);
        });
        addActionListener();
    }

    private JDialog createDialog() {
        dialog = new JDialog(this, "Custom mode", true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 200);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setIconImage(IconsManager.getGameIcon().getImage());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel rowsLabel = new JLabel("Rows");
        JLabel columnsLabel = new JLabel("Columns");
        JLabel minesLabel = new JLabel("Mines");

        constraints.weighty = 1;

        panel.add(rowsLabel, constraints);
        panel.add(rows, constraints);

        constraints.gridy = 1;
        panel.add(columnsLabel, constraints);
        panel.add(columns, constraints);

        constraints.gridy = 2;
        panel.add(minesLabel, constraints);
        panel.add(mines, constraints);

        constraints.gridy = 3;
        panel.add(acceptButton, constraints);

        constraints.gridx = 1;
        panel.add(cancelButton, constraints);

        dialog.add(panel);

        return dialog;
    }

    private void addActionListener() {
        CustomModeMenu.getAcceptButton().addActionListener(actionEvent -> {
            try {
                rowsCount = Integer.parseInt(rows.getText());
                columnsCount = Integer.parseInt(columns.getText());
                minesCount = Integer.parseInt(mines.getText());

                if (!checkValues()) {
                    return;
                }

                Body.restartBody();
                /*FieldConstructor.buildField(DifficultyMode.CUSTOM_MODE);*/
                setVisible(false);

                MinesweeperSwingView.getFrame().setSize(getDimensionCustomMode());

                Body.updateBody();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(dialog, "Only integers are allowed for input.");
                restartCustomMenu();
            }
        });

        cancelButton.addActionListener(actionEvent -> {
            setVisible(false);
            restartCustomMenu();
        });
    }

    public static Dimension getDimensionCustomMode() {
        Map<Integer, Integer> sizes = new Hashtable<>();
        int size = 500;

        if (rowsCount > 9 || columnsCount > 9) {
            for (int i = 10, j = 0; i <= 30; i++, j++) {
                if (j >= 4) {
                    size += 150;
                    j = 0;
                }
                sizes.put(i, size);
            }

            int width;
            int height;

            if (rowsCount < 9) {
                height = 500;
            } else {
                height = sizes.get(rowsCount);
            }

            if (columnsCount < 9) {
                width = 500;
            } else {
                width = sizes.get(columnsCount);
            }

            return new Dimension(width, height);
        }

        return new Dimension(size, size);
    }

    private boolean checkValues() {
        int maxNumbersOfMines = rowsCount * columnsCount;
        int maxNumbersOfCells = 30;
        int minNumbersOfCell = 1;

        if (minesCount <= 0 || rowsCount <= 0 || columnsCount <= 0) {
            JOptionPane.showMessageDialog(dialog, "Minimum  number of mines, rows or columns = " + minNumbersOfCell);
            restartCustomMenu();
            return false;
        }

        if (minesCount > maxNumbersOfMines) {
            JOptionPane.showMessageDialog(dialog, "Maximum number of mines = " + maxNumbersOfMines);
            restartCustomMenu();
            return false;
        }

        if (rowsCount > maxNumbersOfCells || columnsCount > maxNumbersOfCells) {
            JOptionPane.showMessageDialog(dialog, "Maximum number of rows or columns = " + maxNumbersOfCells);
            restartCustomMenu();
            return false;
        }

        return true;
    }

    private void restartCustomMenu() {
        rows.setText(null);
        columns.setText(null);
        mines.setText(null);
    }
}
