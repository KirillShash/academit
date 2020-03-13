package ru.academit.shashkov.minesweeper.main;

import ru.academit.shashkov.minesweeper.controller.Controller;
import ru.academit.shashkov.minesweeper.model.GameManager;
import ru.academit.shashkov.minesweeper.model.Model;
import ru.academit.shashkov.minesweeper.view.MinesweeperView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new GameManager();
        Controller controller = new Controller(model);
        MinesweeperView view = new MinesweeperView(controller);
        /*model.setMinesweeperView(view);
        model.prepareGame();*/
        SwingUtilities.invokeLater(model::startPlay);
    }
}
