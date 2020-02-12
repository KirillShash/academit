package ru.academit.shashkov.minesweeper.main;

import ru.academit.shashkov.minesweeper.view.Display;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Display display = new Display();
            display.run();
        });
    }
}
