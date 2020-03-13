package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.view.iconsmanager.IconsManager;

import javax.swing.*;
import java.awt.*;

public class Header {
    @Getter
    private static final JButton smileButton;
    @Getter
    private static final JLabel minesNumber;
    @Getter
    private static final JLabel timer;
    @Getter
    private static final JPanel header;

    static {
        smileButton = new JButton(IconsManager.getBaseRestartButtonIcon());
        minesNumber = new JLabel("30");
        timer = new JLabel("0000");
        header = new JPanel(new GridBagLayout());
    }

    public static void addHeader(GridBagConstraints constraints) {
        smileButton.setBorder(null);
        smileButton.setFocusPainted(false);
        smileButton.setContentAreaFilled(false);

        minesNumber.setIcon(IconsManager.getRemainingBombs());
        minesNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        timer.setIcon(IconsManager.getTimer());

        GridBagConstraints headerConstraints = new GridBagConstraints();

        header.add(minesNumber, headerConstraints);
        header.add(smileButton, headerConstraints);
        header.add(timer, headerConstraints);

        MinesweeperView.getFrame().add(header, constraints);
    }
}
