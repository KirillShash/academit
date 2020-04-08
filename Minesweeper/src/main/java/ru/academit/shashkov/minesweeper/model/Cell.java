package ru.academit.shashkov.minesweeper.model;

import lombok.AllArgsConstructor;
import ru.academit.shashkov.minesweeper.common.CellState;

@AllArgsConstructor
public class Cell {
    private int x;
    private int y;
    private CellState cellState;
}
