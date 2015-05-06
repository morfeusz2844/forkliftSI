package controller;

import model.Blank;
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

        warehouse.addWorldElement(new Blank(), 3, 3);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouse.addWorldElement(new Blank(), 3, 4);
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouse.addWorldElement(new Blank(), 4, 4);
    }


}
