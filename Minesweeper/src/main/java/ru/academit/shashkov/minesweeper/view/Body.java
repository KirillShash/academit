package ru.academit.shashkov.minesweeper.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Body {
    @Getter
    private static JPanel body;

    public static void addBody(GridBagConstraints constraints) {
        body = new JPanel(new GridBagLayout());
        MinesweeperView.getFrame().add(body, constraints);
    }

    public static void restartBody() {
        body.removeAll();
    }

    public static void updateBody() {
        body.updateUI();
    }
}
