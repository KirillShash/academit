package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.common.DifficultyType;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;
import ru.academit.shashkov.minesweeper.view.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class Display {
    private static final Map<DifficultyType, Integer> frameSize = new EnumMap<>(DifficultyType.class);
    @Getter
    private static JFrame frame;
    @Getter
    private static JPanel panel;

    public void run() {
        frame = new JFrame("Minesweeper");
        panel = new JPanel(new GridBagLayout());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(Menu.addMenu());
        frame.setContentPane(panel);
        frame.setIconImage(IconsManager.getGameIcon().getImage());

        ActionListenerManager.startBeginnerMode();
        ActionListenerManager.startExpertMode();
        ActionListenerManager.startIntermediateMode();
        ActionListenerManager.exit();
    }

    public static void restart() {
        panel.removeAll();
    }

    public static void updatePanel() {
        panel.updateUI();
    }

    public static void setFrameSize(DifficultyType difficultyType) {

    }

    private int getSize() {

        return 1;
    }
}
