package ru.academit.shashkov.temperatureconverter.model;

import ru.academit.shashkov.temperatureconverter.view.Window;

import javax.swing.*;

public class Model {
    double inputTemperature;
    private double result;

    public String printResult() {
        return String.format("%.2f", result);
    }

    public void convert() {
        try {
            inputTemperature = Double.parseDouble(Window.getInputText().getText());

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(Window.getFrame(), "Only numbers and one dot are available for input.");
            Window.getInputText().setText(null);
        }

        String leftType = (String) Window.getComboBoxLeft().getSelectedItem();
        String rightType = (String) Window.getComboBoxRight().getSelectedItem();
        result = inputTemperature;

        assert leftType != null;
        assert rightType != null;

        if (leftType.equals("K") && rightType.equals("F")) {
            result = (inputTemperature - 273.15) * 9 / 5 + 32;
        }

        if (leftType.equals("K") && rightType.equals("C")) {
            result = inputTemperature - 273.15;
        }

        if (leftType.equals("F") && rightType.equals("K")) {
            result = (inputTemperature - 32) * 5 / 9 + 273.15;
        }

        if (leftType.equals("F") && rightType.equals("C")) {
            result = (inputTemperature - 32) * 5 / 9;
        }

        if (leftType.equals("C") && rightType.equals("K")) {
            result = inputTemperature + 273.15;
        }

        if (leftType.equals("C") && rightType.equals("F")) {
            result = (inputTemperature * 9 / 5) + 32;
        }
    }
}
