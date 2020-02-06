package ru.academit.shashkov.temperatureconverter.main;

import ru.academit.shashkov.temperatureconverter.view.Display;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Display display = new Display();
            display.run();
        });
    }
}