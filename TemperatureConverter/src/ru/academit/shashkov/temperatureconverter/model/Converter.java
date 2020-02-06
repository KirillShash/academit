package ru.academit.shashkov.temperatureconverter.model;

public class Converter {
    private double result;
    private double temperature;
    private String leftStatement;
    private String rightStatement;

    public Converter(double temperature, String leftStatement, String rightStatement) {
        this.temperature = temperature;
        this.rightStatement = rightStatement;
        this.leftStatement = leftStatement;
    }

    public String printResult() {
        return String.format("%.2f", result);
    }

    public void convert() {
        result = temperature;

        if (leftStatement.equals("K") && rightStatement.equals("F")) {
            result = (temperature - 273.15) * 9 / 5 + 32;
            ;
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
