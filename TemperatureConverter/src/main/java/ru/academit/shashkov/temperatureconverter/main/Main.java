package ru.academit.shashkov.temperatureconverter.main;

import ru.academit.shashkov.temperatureconverter.controller.Controller;
import ru.academit.shashkov.temperatureconverter.model.Model;
import ru.academit.shashkov.temperatureconverter.view.Window;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Model model = new Model();
            Controller controller = new Controller(model);
            Window window = new Window(controller);
            controller.setView(window);
            window.init();
        });
    }
}