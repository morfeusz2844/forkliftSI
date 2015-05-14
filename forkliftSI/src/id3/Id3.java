package id3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.Ground;
import model.Package;
import model.StorageRack;
import model.Truck;
import model.Warehouse;
import model.WorldElement;
import model.enums.PackageSize;
import model.enums.PackageType;

public class Id3 {

	private List<Truck> truckLane = new ArrayList<Truck>();
	private List<StorageRack> storageRacksList = new ArrayList<StorageRack>();
	private List<Ground> groundList = new ArrayList<Ground>();
	private int MAX_GROUND_CAPACITY = 63 * 5;
	private int MAX_STORAGERACK_CAPACITY = 13 * 5 * 10;
	private Warehouse warehouse;

	public Id3(Warehouse warehouse) {
		this.warehouse = warehouse;
		prepareTruckLaneInformations();
		runTests();
	}

	public void runTests() {
		Algorithm algo = new Algorithm();
		Tree tree = null;
		try {
			tree = algo.runAlgorithm(fileToPath("tescik.txt"), "unpack", " ");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// String[] instance = { null, "high", "medium", "high", "low" };
		String[] instance = { null, countNumberOfTrucksToUnpack(),
				countOverallCapacityInWarehouse(),
				countCapacityOnStorageRacks(), countCapacityOnGround() };
		String prediction = tree.predictTargetAttributeValue(instance);
		tree.print();
		algo.printStatistics();
		System.out.println("The class that is predicted is: " + prediction);
		System.out.println(prediction);
	}

	public static String fileToPath(String filename)
			throws UnsupportedEncodingException {
		URL url = Id3.class.getResource(filename);
		return java.net.URLDecoder.decode(url.getPath(), "UTF-8");
	}

	public void justTest() {
		prepareTruckLaneInformations();
	}

	private String countNumberOfTrucksToUnpack() {
		int countPack = 0;
		int countUnpack = 0;
		for (Truck truck : truckLane) {
			if (truck.isToUnpacking())
				countUnpack++;
			else
				countPack++;
		}
		if (countUnpack == 0)
			return "none";
		else if (countUnpack < countPack)
			return "low";
		else if (countUnpack == countPack)
			return "medium";
		else
			return "high";
	}

	private String countOverallCapacityInWarehouse() {
		int racks = calculateCurrentStowageOfStorageRacksOnWarehouse();
		int ground = calculateCurrentStowageOfGroundOnWarehouse();
		int sum = racks + ground;
		int cap = MAX_GROUND_CAPACITY + MAX_STORAGERACK_CAPACITY;
		if (racks == 0 && ground == 0)
			return "none";
		if (sum < (0.4 * cap))
			return "low";
		if (sum >= (0.4 * cap) && sum < (0.6 * cap))
			return "medium";
		else
			return "high";
	}

	private String countCapacityOnStorageRacks() {
		int racks = calculateCurrentStowageOfStorageRacksOnWarehouse();
		if (racks == 0)
			return "none";
		else if (racks < (0.4 * MAX_STORAGERACK_CAPACITY))
			return "low";
		else if (racks >= (0.4 * MAX_STORAGERACK_CAPACITY)
				&& racks < (0.6 * MAX_STORAGERACK_CAPACITY))
			return "medium";
		else
			return "high";
	}

	private String countCapacityOnGround() {
		int ground = calculateCurrentStowageOfGroundOnWarehouse();
		if (ground == 0)
			return "none";
		else if (ground < (0.4 * MAX_GROUND_CAPACITY))
			return "low";
		else if (ground >= (0.4 * MAX_GROUND_CAPACITY)
				&& ground < (0.6 * MAX_GROUND_CAPACITY))
			return "medium";
		else
			return "high";
	}

	private void prepareTruckLaneInformations() {
		Truck truck;
		WorldElement currentlyProcessed;
		for (int i = 1; i < warehouse.getSizeX() - 1; i++) {
			currentlyProcessed = warehouse.getWorldElement(0, i);
			if (currentlyProcessed.getType().equals("Truck")) {
				truck = (Truck) currentlyProcessed;
				truckLane.add(truck);
			}
		}
	}

	private int calculateCurrentStowageOfStorageRacksOnWarehouse() {
		int capacity = 0;
		WorldElement currentlyProcessed;
		StorageRack rack;
		for (int i = 0; i < warehouse.getSizeX(); i++) {
			for (int j = 0; j < warehouse.getSizeY(); j++) {
				currentlyProcessed = warehouse.getWorldElement(i, j);
				if (currentlyProcessed.getType().equals("StorageRack")
						&& i % 4 == 0) {
					rack = (StorageRack) currentlyProcessed;
					rack.addPackage(new Package(PackageSize.MEDIUM,
							PackageType.NORMAL));
					rack.addPackage(new Package(PackageSize.MEDIUM,
							PackageType.NORMAL));
					rack.addPackage(new Package(PackageSize.MEDIUM,
							PackageType.NORMAL));
					storageRacksList.add(rack);
					capacity += (10 - rack.getLeftSpace());
				} else if (currentlyProcessed.getType().equals("StorageRack")) {
					rack = (StorageRack) currentlyProcessed;
					storageRacksList.add(rack);
					capacity += (10 - rack.getLeftSpace());
				}
			}
		}
		return capacity;
	}

	private int calculateCurrentStowageOfGroundOnWarehouse() {

		int capacity = 0;
		WorldElement currentlyProcessed;
		Ground ground;
		for (int i = 0; i < warehouse.getSizeX(); i++) {
			for (int j = 0; j < warehouse.getSizeY(); j++) {
				currentlyProcessed = warehouse.getWorldElement(i, j);
				if (currentlyProcessed.getType().equals("Ground")
						&& (j % 4 == 0 || j % 4 == 1)) {
					ground = (Ground) currentlyProcessed;
					ground.addPackage(new Package(PackageSize.BIG,
							PackageType.NORMAL));
					ground.addPackage(new Package(PackageSize.BIG,
							PackageType.NORMAL));
					ground.addPackage(new Package(PackageSize.BIG,
							PackageType.FRAGILE));
					ground.addPackage(new Package(PackageSize.BIG,
							PackageType.NORMAL));
					ground.addPackage(new Package(PackageSize.BIG,
							PackageType.GROCERIES));
					groundList.add(ground);
					capacity += (5 - ground.getLeftSpace());
				} else if (currentlyProcessed.getType().equals("Ground")) {
					ground = (Ground) currentlyProcessed;
					groundList.add(ground);
					capacity += (5 - ground.getLeftSpace());
				}
			}
		}
		return capacity;
	}

}
