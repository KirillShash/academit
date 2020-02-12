package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class Menu {
    private JMenuBar menuBar;
    private static JMenu menu;

    public static JMenuBar addMenu() {
        JMenuBar menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        JMenu newGame = new JMenu("New game");
        JMenu highScoreTable = new JMenu("High score table");

        JButton easyMode = new JButton("Easy");
        JMenuItem mediumMode = new JMenuItem("Medium");
        JMenuItem hardMode = new JMenuItem("Hard");
        JMenuItem customMode = new JMenuItem("Custom Mode");

        newGame.add(easyMode);
        newGame.add(mediumMode);
        newGame.add(hardMode);
        newGame.add(customMode);

        JMenuItem easyModeHighScore = new JMenuItem("Easy");
        JMenuItem mediumModeHighScore = new JMenuItem("Medium");
        JMenuItem hardModeHighScore = new JMenuItem("Hard");

        highScoreTable.add(easyModeHighScore);
        highScoreTable.add(mediumModeHighScore);
        highScoreTable.add(hardModeHighScore);

        JMenuItem exit = new JMenuItem("Exit");

        menu.add(newGame);
        menu.add(highScoreTable);
        menu.add(exit);
        menuBar.add(menu);

        easyMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        return menuBar;
    }
}
