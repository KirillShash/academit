package ru.academit.shashkov.minesweeper.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class Score implements Serializable {
    private final String name;
    private final long time;
}
