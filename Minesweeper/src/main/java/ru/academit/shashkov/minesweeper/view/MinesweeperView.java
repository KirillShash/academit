package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.common.DifficultyMode;
import ru.academit.shashkov.minesweeper.controller.Controller;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;
import ru.academit.shashkov.minesweeper.view.menu.CustomModeMenu;
import ru.academit.shashkov.minesweeper.view.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class MinesweeperView implements View {
    private static final Map<DifficultyMode, Dimension> frameSize;
    @Getter
    private static final JFrame frame;
    private final Controller controller;

    public MinesweeperView(Controller controller) {
        this.controller = controller;
    }

    static {
        frame = new JFrame("Minesweeper");
        frameSize = new EnumMap<>(DifficultyMode.class);
        frameSize.put(DifficultyMode.BEGINNER_MODE, new Dimension(500, 500));
        frameSize.put(DifficultyMode.INTERMEDIATE_MODE, new Dimension(600, 700));
        frameSize.put(DifficultyMode.EXPERT_MODE, new Dimension(1000, 700));
        frameSize.put(DifficultyMode.CUSTOM_MODE, CustomModeMenu.getDimensionCustomMode());
    }

    @Override
    public void run() {
        frame.setLayout(new GridBagLayout());
        frame.setIconImage(IconsManager.getGameIcon().getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(frameSize.get(DifficultyMode.BEGINNER_MODE));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(Menu.addMenu());

        GridBagConstraints frameConstraints = new GridBagConstraints();
        Header.addHeader(frameConstraints);
        frameConstraints.gridy = 1;
        Body.addBody(frameConstraints);

        CustomModeMenu customModeMenu = new CustomModeMenu();
        customModeMenu.addCustomMenu();
    }

    public static void setFrameSizeForDifficult(DifficultyMode difficultyMode) {
        frame.setSize(frameSize.get(difficultyMode));
    }
}
