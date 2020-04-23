package ru.academit.shashkov.minesweeper.main;

import ru.academit.shashkov.minesweeper.controller.Controller;
import ru.academit.shashkov.minesweeper.model.GameManager;
import ru.academit.shashkov.minesweeper.model.Model;
import ru.academit.shashkov.minesweeper.storage.HighScoresFile;
import ru.academit.shashkov.minesweeper.view.MinesweeperSwingView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new GameManager(new HighScoresFile());
        Controller controller = new Controller(model);
        MinesweeperSwingView view = new MinesweeperSwingView(controller);
        model.setMinesweeperView(view);
        model.prepareGame();
        SwingUtilities.invokeLater(model::initMinesweeperView);
    }
}
