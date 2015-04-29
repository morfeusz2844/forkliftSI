package main;

import gui.MainView;

import java.awt.EventQueue;

public class ForkliftSI {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView();
            }
        });
    }
}
