package controller;

import astar.Astar;
import astar.DestinationUnreachableException;
import astar.Node;
import model.Forklift;
import model.Truck;
import model.Warehouse;

import java.util.ArrayList;

public class ForkliftController implements Runnable {

    Warehouse warehouse;
    Forklift forklift;

    public ForkliftController(Warehouse warehouse, Forklift forklift) {
        this.warehouse = warehouse;
        this.forklift = forklift;
    }

    @Override
    public void run() {

        // test wyœwietlania

		/*Pathfinder pathfinder = new Pathfinder(warehouse);
        String path = pathfinder.findPath();
		WorldElement tempWorldElement;
		String[] pathNodes = path.split("-> ");
		for (String s : pathNodes) {
			System.out.println(s);
			int x = Integer.parseInt(s.split(" ")[1]);
			int y = Integer.parseInt(s.split(" ")[2]);
			tempWorldElement = warehouse.getWorldElement(x, y);
			warehouse.addWorldElement(forklift, x, y);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			warehouse.addWorldElement(tempWorldElement, x, y);

		}*/

//		Id3 id3 = new Id3(warehouse);
//		id3.justTest();
        Truck truck = new Truck(true);

        //GeneticController geneticController = new GeneticController(warehouse, truck);

    }

    private void moveForklift(int destinationX, int destinationY) throws DestinationUnreachableException {
        Astar aStar = new Astar(warehouse, forklift);
        ArrayList<Node> list = (ArrayList<Node>) aStar.findPath(forklift.getPositionX(), forklift.getPositionY(), destinationX, destinationY);
        if (list == null) {
            System.out.println("Punkt docelowy niedostepny");
            throw new DestinationUnreachableException();
        }

        for (int i = list.size() - 1; i >= 0; i--){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Poruszam forklift z "+forklift.getPositionX()+","+forklift.getPositionY()+" do "+list.get(i).getPosX()+","+list.get(i).getPosY());
            forklift.setPositionX(list.get(i).getPosX());
            forklift.setPositionY(list.get(i).getPosY());
        }
        System.out.println("Dojecha³em!");
    }

}
