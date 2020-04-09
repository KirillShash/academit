package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.common.*;
import ru.academit.shashkov.minesweeper.controller.Controller;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;
import ru.academit.shashkov.minesweeper.view.menu.CustomModeMenu;
import ru.academit.shashkov.minesweeper.view.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MinesweeperSwingView implements MinesweeperView {
    private final Controller controller;

    @Getter
    private static final JFrame mainFrame = new JFrame();

    public MinesweeperSwingView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void init(int rowsCount, int columnsCount) {
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setIconImage(IconsManager.getGameIcon().getImage());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(500, 500);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setJMenuBar(Menu.addMenu());

        GridBagConstraints frameConstraints = new GridBagConstraints();
        Header.addHeader(frameConstraints);
        frameConstraints.gridy = 1;
        Body.addBody(frameConstraints);

        CustomModeMenu customModeMenu = new CustomModeMenu();
        customModeMenu.addCustomMenu();
    }

    @Override
    public void startGame() {

    }

    @Override
    public void showCellContent(int x, int y, CellContent content) {

    }

    @Override
    public void finishGame(boolean isVictory) {

    }

    @Override
    public void restartGame() {

    }

    @Override
    public void restartGameWithNewDifficulty(int rowsNumber, int columnsNumber) {

    }

    @Override
    public void updateCellState(int x, int y, CellState state) {

    }

    @Override
    public void modifyTimer(long timeSpent) {

    }

    @Override
    public void showRemainingBombsNumber(int flagsNumber) {

    }

    @Override
    public void notifyPlayerAboutRecord() {

    }

    public void showCustomSettings(DifficultyMode difficultyMode) {

    }

    public void showHighScores(Map<String, Score> highScores) {

    }

    @Override
    public void hideHighScoreNotification() {

    }

    @Override
    public void hideCustomSettingsInput() {

    }

    @Override
    public void exitGame() {

    }
}
