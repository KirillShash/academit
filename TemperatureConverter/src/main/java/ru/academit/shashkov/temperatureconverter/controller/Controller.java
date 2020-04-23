package ru.academit.shashkov.temperatureconverter.controller;

import ru.academit.shashkov.temperatureconverter.model.Model;
import ru.academit.shashkov.temperatureconverter.view.View;

public class Controller {
    private final Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void convert() {
        model.convert();
        view.setResult(model.printResult());
    }
}
