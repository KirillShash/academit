package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class Header {
    private static final String TIMER_FORMAT = "mm:ss";
    private static final String START_TIME_VALUE = "00:00";

    private static final int DISPLAY_ELEMENT_WIDTH = 50;
    private static final int DISPLAY_ELEMENT_HEIGHT = 30;
    private static final Dimension displayElementSize = new Dimension(DISPLAY_ELEMENT_WIDTH, DISPLAY_ELEMENT_HEIGHT);
    private final SimpleDateFormat simpleDateFormat;
    private final ListenerCreator listenerCreator;

    private JButton restartButton;
    private JLabel timer;
    private JLabel remainingBombs;
    @Getter
    private JPanel headerPanel;

    Header(ListenerCreator listenerCreator) {
        this.listenerCreator = listenerCreator;
        headerPanel = new JPanel();
        createHeader();
        this.simpleDateFormat = new SimpleDateFormat(TIMER_FORMAT);
    }


    private void createDisplay() {
        JLabel timerIcon = new JLabel();
        timerIcon.setIcon(IconsManager.getTimer());
        timerIcon.setPreferredSize(new Dimension(30, 30));

        timer = new JLabel();
        timer.setPreferredSize(displayElementSize);
        timer.setHorizontalAlignment(SwingConstants.CENTER);

        restartButton = new JButton(IconsManager.getBaseRestartButtonIcon());
        restartButton.setContentAreaFilled(false);
        restartButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        restartButton.setPreferredSize(displayElementSize);
        restartButton.setHorizontalAlignment(SwingConstants.CENTER);
        restartButton.setFocusPainted(false);
        restartButton.addActionListener(listenerCreator.createListenerForRestartButton());

        JLabel remainingBombsIcon = new JLabel();
        remainingBombsIcon.setIcon(IconsManager.getRemainingBombs());
        remainingBombsIcon.setPreferredSize(displayElementSize);
        remainingBombsIcon.setHorizontalAlignment(SwingConstants.CENTER);

        remainingBombs = new JLabel();
        remainingBombs.setPreferredSize(displayElementSize);
        remainingBombs.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(timerIcon);
        headerPanel.add(timer);
        headerPanel.add(restartButton);
        headerPanel.add(remainingBombs);
        headerPanel.add(remainingBombsIcon);

        headerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        headerPanel.add(remainingBombsIcon, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        headerPanel.add(remainingBombs, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        headerPanel.add(restartButton, gbc);

        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        headerPanel.add(timer, gbc);

        gbc.gridx = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        headerPanel.add(timerIcon, gbc);
    }
}
