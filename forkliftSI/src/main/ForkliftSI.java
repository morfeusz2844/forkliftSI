package main;

import controller.ForkliftController;
import gui.MainView;
import model.Forklift;
import model.Warehouse;

import java.awt.*;

public class ForkliftSI {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();
        Forklift forklift = new Forklift();

        EventQueue.invokeLater(() -> new MainView(warehouse, forklift));

        ForkliftController controller = new ForkliftController(warehouse, forklift);
        controller.run();

    }
}
