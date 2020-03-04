package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.common.DifficultyType;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;
import ru.academit.shashkov.minesweeper.view.menu.CustomModeMenu;
import ru.academit.shashkov.minesweeper.view.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class Display {
    private static final Map<DifficultyType, Dimension> frameSize;
    @Getter
    private static JFrame frame;
    @Getter
    private static JPanel header;
    @Getter
    private static JPanel body;

    static {
        frameSize = new EnumMap<>(DifficultyType.class);
        frameSize.put(DifficultyType.BEGINNER_MOD, new Dimension(500, 700));
        frameSize.put(DifficultyType.INTERMEDIATE_MOD, new Dimension(600, 700));
        frameSize.put(DifficultyType.EXPERT_MOD, new Dimension(1000, 700));
    }

    public void run() {
        frame = new JFrame("Minesweeper");
        header = new JPanel(new GridBagLayout());
        body = new JPanel(new GridBagLayout());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(frameSize.get(DifficultyType.BEGINNER_MOD));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(Menu.addMenu());
        frame.add(body);
        frame.setIconImage(IconsManager.getGameIcon().getImage());

        ActionListenerManager.startBeginnerMode();
        ActionListenerManager.startExpertMode();
        ActionListenerManager.startIntermediateMode();
        ActionListenerManager.exit();
        CustomModeMenu.addCustomMenu();
    }

    public static void restart() {
        body.removeAll();
    }

    public static void updatePanel() {
        body.updateUI();
    }

    public static void setFrameSizeForDifficult(DifficultyType difficultyType) {
        frame.setSize(frameSize.get(difficultyType));
    }
}
