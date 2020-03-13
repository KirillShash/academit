package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.common.DifficultyType;
import ru.academit.shashkov.minesweeper.controller.Controller;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;
import ru.academit.shashkov.minesweeper.view.menu.CustomModeMenu;
import ru.academit.shashkov.minesweeper.view.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class MinesweeperView implements View {
    private static final Map<DifficultyType, Dimension> frameSize;
    @Getter
    private static final JFrame frame;
    private final Controller controller;

    public MinesweeperView(Controller controller) {
        this.controller = controller;
    }

    static {
        frame = new JFrame("Minesweeper");
        frameSize = new EnumMap<>(DifficultyType.class);
        frameSize.put(DifficultyType.BEGINNER_MODE, new Dimension(500, 500));
        frameSize.put(DifficultyType.INTERMEDIATE_MODE, new Dimension(600, 700));
        frameSize.put(DifficultyType.EXPERT_MODE, new Dimension(1000, 700));
        frameSize.put(DifficultyType.CUSTOM_MODE, CustomModeMenu.getDimensionCustomMode());
    }

    @Override
    public void run() {
        frame.setLayout(new GridBagLayout());
        frame.setIconImage(IconsManager.getGameIcon().getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(frameSize.get(DifficultyType.BEGINNER_MODE));
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

    public static void setFrameSizeForDifficult(DifficultyType difficultyType) {
        frame.setSize(frameSize.get(difficultyType));
    }
}
