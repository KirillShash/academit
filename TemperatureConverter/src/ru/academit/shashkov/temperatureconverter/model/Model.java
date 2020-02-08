package ru.academit.shashkov.temperatureconverter.model;

public class Model {
    private double result;

    public String getResult() {
        return String.format("%.2f", result);
    }

    public void convert(double temperature, String leftStatement, String rightStatement) {
        result = temperature;

        if (leftStatement.equals("K") && rightStatement.equals("F")) {
            result = (temperature - 273.15) * 9 / 5 + 32;
        }

        if (leftStatement.equals("K") && rightStatement.equals("C")) {
            result = temperature - 273.15;
        }

        if (leftStatement.equals("F") && rightStatement.equals("K")) {
            result = (temperature - 32) * 5 / 9 + 273.15;
        }

        if (leftStatement.equals("F") && rightStatement.equals("C")) {
            result = (temperature - 32) * 5 / 9;
        }

        if (leftStatement.equals("C") && rightStatement.equals("K")) {
            result = temperature + 273.15;
        }

        if (leftStatement.equals("C") && rightStatement.equals("F")) {
            result = (temperature * 9 / 5) + 32;
        }
    }
}
