package ru.academit.shashkov.minesweeper.view.menu;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomModeMenu {
    @Getter
    private static int rows;
    @Getter
    private static int columns;
    @Getter
    private static int mines;

    public static void addCustomMenu() {
        JFrame frame = new JFrame("Custom mode");
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(IconsManager.getGameIcon().getImage());

        JPanel panel = new JPanel(new GridBagLayout());
        frame.setContentPane(panel);

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel rowsLabel = new JLabel("Rows");
        JTextField rowsCount = new JTextField();
        JLabel columnsLabel = new JLabel("Columns");
        JTextField columnsCount = new JTextField();
        JLabel minesLabel = new JLabel("Mines");
        JTextField minesCount = new JTextField();

        rowsCount.setColumns(2);
        columnsCount.setColumns(2);
        minesCount.setColumns(2);

        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");

        constraints.weighty = 1;

        panel.add(rowsLabel, constraints);
        panel.add(rowsCount, constraints);

        constraints.gridy = 1;
        panel.add(columnsLabel, constraints);
        panel.add(columnsCount, constraints);

        constraints.gridy = 2;
        panel.add(minesLabel, constraints);
        panel.add(minesCount, constraints);

        constraints.gridy = 3;
        panel.add(acceptButton, constraints);

        constraints.gridx = 1;
        panel.add(cancelButton, constraints);

        Menu.getCustomMode().addActionListener(actionEvent -> {
            frame.setVisible(true);
        });

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    rows = Integer.parseInt(rowsCount.getText());
                    columns = Integer.parseInt(columnsCount.getText());
                    mines = Integer.parseInt(minesCount.getText());
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "Only integers are allowed for input.");
                    rowsCount.setText(null);
                    columnsCount.setText(null);
                    minesCount.setText(null);
                }
            }
        });
    }
}
