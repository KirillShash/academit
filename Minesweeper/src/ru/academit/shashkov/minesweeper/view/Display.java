package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Display {
    @Getter
    private static JPanel panel = new JPanel(new GridBagLayout());

    public void run() {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(Menu.addMenu());
        frame.setContentPane(panel);
    }
}
