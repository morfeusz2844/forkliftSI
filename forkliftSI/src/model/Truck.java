package model;

import model.enums.PackageSize;
import model.enums.PackageType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Truck implements WorldElement {

	private static final String TYPE = "Truck";
	private static final int MAX_CAPACITY = 5;

	private int capacity;
	private boolean toUnpacking;

	private int positionx;
	private int positiony;


	private List<Package> listOfProducts;

	public Truck(boolean toUnpacking) {
		this.toUnpacking = toUnpacking;
		if (toUnpacking) {
			capacity = MAX_CAPACITY;
			listOfProducts = generateRandomPackageList();
		} else {
			capacity = 0;
			listOfProducts = new ArrayList<Package>();
		}
	}
	public Truck(boolean toUnpacking, int _x, int _y) {
		this.positionx=_x;
		this.positiony=_y;
		this.toUnpacking = toUnpacking;
		if (toUnpacking) {
			capacity = MAX_CAPACITY;
			listOfProducts = generateRandomPackageList();
		} else {
			capacity = 0;
			listOfProducts = new ArrayList<Package>();
		}
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean isToUnpacking() {
		return toUnpacking;
	}

	public List<Package> getListOfProducts() {
		return listOfProducts;
	}

	public Package takeElementFromTruck() {
		Iterator<Package> iterator = listOfProducts.iterator();
		if (iterator.hasNext()) {
			Package next = iterator.next();
			iterator.remove();
			return next;
		} else {
			return null;
		}
	}


	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

	private List<Package> generateRandomPackageList() {
		List<Package> list = new ArrayList<Package>();

		for (int i = 0; i < MAX_CAPACITY; i++) {
			list.add(generateSingleRandomPackage());
		}
		return list;
	}

	private Package generateSingleRandomPackage() {
		Random random = new Random();
		int packageSizeInt = random.nextInt(3);
		int packageTypeInt = random.nextInt(4);

		Package pack = new Package(switchOnPackageSize(packageSizeInt),
				switchOnPackageType(packageTypeInt));
		return pack;
	}

	private PackageSize switchOnPackageSize(int i) {
		switch (i) {
		case 0:
			return PackageSize.SMALL;
		case 1:
			return PackageSize.MEDIUM;
		case 2:
			return PackageSize.BIG;
		default:
			throw new IllegalStateException("Sth bad in switchOnPackageSize");
		}
	}

	private PackageType switchOnPackageType(int i) {
		switch (i) {
		case 0:
			return PackageType.NORMAL;
		case 1:
			return PackageType.FRAGILE;
		case 2:
			return PackageType.DANGEROUS;
		case 3:
			return PackageType.NORMAL;
		default:
			throw new IllegalStateException(
					"Sth wrong in switch on Package Type");
		}
	}
	public int getPositionx(){
		return this.positionx;
	}
	public int getPositiony(){
		return this.positiony;
	}
}
