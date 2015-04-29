package model;

import model.enums.PackageSize;

import java.util.ArrayList;
import java.util.List;

public class StorageRack extends WorldElement {
	private static final int MAX_CAPACITY = 20;
	private int leftSpace;

	List<Package> items;

	public StorageRack() {
		leftSpace = MAX_CAPACITY;
		items = new ArrayList<Package>();
	}

	public List<Package> getItems() {
		return items;
	}

	public void addPackage(Package pack) {
		if (pack != null
				&& leftSpace > 0
				&& (pack.getPackageSize().equals(PackageSize.MEDIUM) || pack
						.getPackageSize().equals(PackageSize.SMALL))) {
			items.add(pack);
			leftSpace = leftSpace - pack.getPackageSize().getValue();
		}
	}

	public int getLeftSpace() {
		return leftSpace;
	}

}
