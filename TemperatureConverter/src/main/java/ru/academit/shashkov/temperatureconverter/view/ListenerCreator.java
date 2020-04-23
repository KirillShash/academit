package ru.academit.shashkov.temperatureconverter.view;

import lombok.AllArgsConstructor;
import ru.academit.shashkov.temperatureconverter.controller.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@AllArgsConstructor
public class ListenerCreator {
    private final Controller controller;

    public MouseListener createListenerForConvertButton() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    controller.convert();
                }
            }
        };
    }
}
