package controller;

import astar.Astar;
import model.Forklift;
import model.Truck;
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

		Astar astar = new Astar(warehouse,forklift);
		astar.findPath(7,7,1,1);

		//GeneticController geneticController = new GeneticController(warehouse,truck);

	}

}
