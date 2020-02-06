package ru.academit.shashkov.temperatureconverter.view;

import ru.academit.shashkov.temperatureconverter.model.Converter;

import javax.swing.*;
import java.awt.*;

public class Display {
    public String leftType;
    public String rightType;

    public void run() {
        JFrame frame = new JFrame("Temperature converter");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.setContentPane(panel);

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;

        JTextField textField1 = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        textField1.setColumns(2);
        panel.add(textField1, c);

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

        JLabel result = new JLabel("result");
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
                leftType = (String) comboBoxLeft.getSelectedItem();
                rightType = (String) comboBoxRight.getSelectedItem();

                Converter converter = new Converter(Double.parseDouble(textField1.getText()), leftType, rightType);
                converter.convert();

                result.setText(converter.printResult());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "Only numbers and one dot are available for input.");
                textField1.setText(null);
            }
        });

    }
}

