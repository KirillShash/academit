package ru.academit.shashkov.minesweeper.view;

import ru.academit.shashkov.minesweeper.common.*;
import ru.academit.shashkov.minesweeper.controller.Controller;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;
import ru.academit.shashkov.minesweeper.view.menu.CustomMode;
import ru.academit.shashkov.minesweeper.view.menu.HighScoresMenu;
import ru.academit.shashkov.minesweeper.view.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MinesweeperSwingView implements MinesweeperView {
    private JFrame mainFrame;
    private Header header;
    private Minefield minefield;
    private HighScoresMenu highScoresMenu;
    private CustomMode customModeFrame;

    private final Controller controller;
    private final ListenerCreator listenerCreator;

    public MinesweeperSwingView(Controller controller) {
        this.controller = controller;
        listenerCreator = new ListenerCreator(controller, this);
    }

    @Override
    public void init(int rowsNumber, int columnsNumber) {
        mainFrame = new JFrame("Minesweeper");
        mainFrame.setIconImage(IconsManager.getGameIcon().getImage());
        mainFrame.setLayout(new BorderLayout());

        createMenu();
        createHeader();
        createMinefield(rowsNumber, columnsNumber);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        header.setTimerStartValue();
    }

    @Override
    public void startGame() {
        controller.startTimer();
    }

    private void createMinefield(int rowsNumber, int columnsNumber) {
        minefield = new Minefield(rowsNumber, columnsNumber, listenerCreator);
        mainFrame.add(minefield.getPlayingField(), BorderLayout.CENTER);
    }

    private void createMenu() {
        Menu menu = new Menu(listenerCreator);
        mainFrame.setJMenuBar(menu.getMenuBar());
        customModeFrame = new CustomMode(listenerCreator);
        highScoresMenu = new HighScoresMenu(listenerCreator);
    }

    private void createHeader() {
        header = new Header(listenerCreator);
        mainFrame.add(header.getHeaderPanel(), BorderLayout.NORTH);
    }

    @Override
    public void updateCellState(int x, int y, CellState state) {
        minefield.updateCellState(x, y, state);
    }

    @Override
    public void finishGame(boolean isVictory) {
        minefield.blockMinefieldButtons();
        controller.stopTimer();
        header.updateRestartButtonIcon(isVictory);
    }

    @Override
    public void restartGameWithNewDifficulty(int rowsNumber, int columnsNumber) {
        minefield.restartGameWithNewDifficulty(rowsNumber, columnsNumber);
        controller.restartTimer();
        setBaseRestartButton();
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }

    private void setBaseRestartButton() {
        header.setBaseRestartButtonIcon();
    }

    @Override
    public void restartGame() {
        minefield.restartGameWithSameDifficulty();
        controller.restartTimer();
        setBaseRestartButton();
    }

    @Override
    public void showCellContent(int x, int y, CellContent content) {
        minefield.showCellContent(x, y, content);
    }


    @Override
    public void modifyTimer(long timeSpent) {
        header.modifyTimer(timeSpent);
    }

    @Override
    public void showRemainingBombsNumber(int flagsNumber) {
        header.showRemainingBombsNumbers(flagsNumber);
    }

    @Override
    public void notifyPlayerAboutRecord() {
        highScoresMenu.notifyPlayerAboutRecord();
    }

    @Override
    public void showCustomSettings(DifficultyMode difficultyMode) {
        customModeFrame.show
                (difficultyMode.getRowsNumber(), difficultyMode.getColumnsNumber(), difficultyMode.getMinesNumber());
    }

    @Override
    public void showHighScores(Map<String, Score> highScores) {
        highScoresMenu.showHighScoresFrame(highScores);
    }

    @Override
    public void hideHighScoreNotification() {
        highScoresMenu.hideNotifyFrame();
    }

    @Override
    public void hideCustomSettingsInput() {
        customModeFrame.hide();
    }

    @Override
    public void exitGame() {
        controller.startTimer();
        mainFrame.dispose();
        System.exit(0);
    }
}
