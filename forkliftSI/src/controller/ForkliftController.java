package controller;

import id3.Id3;

import java.util.ArrayList;
import java.util.List;

import model.Forklift;
import model.Package;
import model.StorageRack;
import model.Ground;
import model.Truck;
import model.Warehouse;
import model.WorldElement;
import astar.Astar;
import astar.DestinationUnreachableException;
import astar.Node;

public class ForkliftController implements Runnable {

    Warehouse warehouse;
    Forklift forklift;
    private boolean programDoesNotMakeAnySense = true; 

    public ForkliftController(Warehouse warehouse, Forklift forklift) {
        this.warehouse = warehouse;
        this.forklift = forklift;
    }

    @Override
    public void run() {
    	
    	while(programDoesNotMakeAnySense){
    		
    		Id3 id3 = new Id3(warehouse);
    		String prediction = id3.getPrediction();
    		
    		if(prediction.equals("no")){
    			Truck truckToPack = id3.getTruckToPack();
    			int[] position = id3.findPlaceForPackingToTruck();
    			try {
					moveForklift(position[1], position[0]);
					WorldElement worldElement = warehouse.getWorldElement(position[0], position[1]);
					Package pack = null;
					if(worldElement.getType().equals("StorageRack")){
						pack = ((StorageRack)worldElement).pickPackage();
					}
					else if(worldElement.getType().equals("Ground")){
						pack = ((Ground)worldElement).pickPackage();
					}
					forklift.pickElement(pack);
					moveForklift(truckToPack.getPositiony(), truckToPack.getPositionx());
					truckToPack.addPackage(pack);
				} catch (DestinationUnreachableException e) {
					e.printStackTrace();
				}
	
    		} else if (prediction.equals("yes")) {
    			Truck truckToUnpack = id3.getTruckToUnpack();
    			
    			GeneticController geneticController = new GeneticController(warehouse, truckToUnpack);
    			List<int[]> productPlaces = geneticController.getProductPlaces();
    			
    			for(int[] positions : productPlaces){
    				try {
    					moveForklift(truckToUnpack.getPositiony(), truckToUnpack.getPositionx());
    					forklift.pickElement(truckToUnpack.takeElementFromTruck());
						moveForklift(positions[1], positions[0]);
						WorldElement worldElement = warehouse.getWorldElement(positions[0], positions[1]);
						Package pack = forklift.dropElement();
						if(worldElement.getType().equals("StorageRack")){
							StorageRack rack = (StorageRack)worldElement;
							rack.addPackage(pack);
						}
						else if(worldElement.getType().equals("Ground")){
							Ground ground = (Ground)worldElement;
							ground.addPackage(pack);
						}
						
					} catch (DestinationUnreachableException e) {
						e.printStackTrace();
					}
    			}
    		}
    }
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
