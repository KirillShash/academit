package ru.academit.shashkov.temperatureconverter.view;

import ru.academit.shashkov.temperatureconverter.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Display implements View {
    private final Controller controller;
    private JLabel result;

    public Display(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        JFrame frame = new JFrame("Temperature converter");
        frame.setSize(350, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.setContentPane(panel);

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;

        JTextField inputText = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        inputText.setColumns(2);
        panel.add(inputText, c);

        JComboBox<String> comboBoxLeft = new JComboBox<>();
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
        panel.add(b, c);

        result = new JLabel("result");
        c.gridx = 1;
        c.gridy = 2;
        panel.add(result, c);

        JLabel resultType = new JLabel("Result type");
        c.gridx = 2;
        c.gridy = 2;
        panel.add(resultType, c);

        JComboBox<String> comboBoxRight = new JComboBox<>();
        comboBoxRight.addItem("C");
        comboBoxRight.addItem("K");
        comboBoxRight.addItem("F");
        c.gridx = 3;
        c.gridy = 2;
        panel.add(comboBoxRight, c);

        b.addActionListener((e) -> {
            try {
                String leftType = (String) comboBoxLeft.getSelectedItem();
                String rightType = (String) comboBoxRight.getSelectedItem();
                controller.convert(Double.parseDouble(inputText.getText()), leftType, rightType);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "Only numbers and one dot are available for input.");
                inputText.setText(null);
            }
        });

    }

    @Override
    public void setResult(String text) {
        result.setText(text);
    }
}

