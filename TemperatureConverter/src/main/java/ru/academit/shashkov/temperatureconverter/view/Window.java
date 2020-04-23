package ru.academit.shashkov.temperatureconverter.view;

import lombok.Getter;
import ru.academit.shashkov.temperatureconverter.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Window implements View {
    private JLabel result;
    private final Controller controller;

    @Getter
    private static JFrame frame;
    @Getter
    private static JTextField inputText;
    @Getter
    private static JComboBox<String> comboBoxLeft;
    @Getter
    private static JComboBox<String> comboBoxRight;

    public Window(Controller controller) {
        this.controller = controller;
    }

    public void addFrame() {
        frame = new JFrame("Temperature converter");
        frame.setSize(350, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.setContentPane(panel);

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;

        inputText = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        inputText.setColumns(2);
        panel.add(inputText, c);

        comboBoxLeft = new JComboBox<>();
        comboBoxLeft.addItem("K");
        comboBoxLeft.addItem("C");
        comboBoxLeft.addItem("F");
        c.gridx = 3;
        c.gridy = 0;
        panel.add(comboBoxLeft, c);

        JLabel inputType = new JLabel("Input type");
        c.gridx = 2;
        c.gridy = 0;
        panel.add(inputType, c);

        JButton b = new JButton("convert");
        c.gridx = 1;
        c.gridy = 1;
        b.addMouseListener(new ListenerCreator(controller).createListenerForConvertButton());
        panel.add(b, c);

        result = new JLabel("result");
        c.gridx = 1;
        c.gridy = 2;
        panel.add(result, c);

        JLabel resultType = new JLabel("Result type");
        c.gridx = 2;
        c.gridy = 2;
        panel.add(resultType, c);

        comboBoxRight = new JComboBox<>();
        comboBoxRight.addItem("C");
        comboBoxRight.addItem("K");
        comboBoxRight.addItem("F");
        c.gridx = 3;
        c.gridy = 2;
        panel.add(comboBoxRight, c);
    }

    public void init() {
        addFrame();
    }

    @Override
    public void setResult(String text) {
        result.setText(text);
    }
}

