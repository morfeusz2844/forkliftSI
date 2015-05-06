package controller;

import controller.Dijkstra2.Pathfinder;
import model.Forklift;
import model.Warehouse;

public class ForkliftController implements Runnable {

    Warehouse warehouse;
    Forklift forklift;

    public ForkliftController(Warehouse warehouse, Forklift forklift) {
        this.warehouse = warehouse;
        this.forklift = forklift;
    }

    @Override
    public void run() {

        //test wyœwietlania

        Pathfinder pathfinder = new Pathfinder(warehouse);
        pathfinder.findPath();

    }




}
