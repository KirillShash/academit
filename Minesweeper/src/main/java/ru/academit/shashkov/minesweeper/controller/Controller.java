package ru.academit.shashkov.minesweeper.controller;

import lombok.AllArgsConstructor;
import ru.academit.shashkov.minesweeper.model.Model;

@AllArgsConstructor
public class Controller {
    private final Model model;

    public void run() {
        model.startPlay();
    }
}
