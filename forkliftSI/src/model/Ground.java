package model;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import model.enums.PackageSize;

public class Ground implements Point {
	private static final int MAX_CAPACITY = 5;

	private int leftSpace;

	private List<Package> items;

	public Ground() {
		this.leftSpace = MAX_CAPACITY;
		items = new ArrayList<Package>();
	}

	public int getLeftSpace() {
		return leftSpace;
	}

	public List<Package> getItems() {
		return items;
	}

	public void addPackage(Package pack) {
		if (pack != null && leftSpace > 0
				&& pack.getPackageSize().equals(PackageSize.BIG)) {
			items.add(pack);
			leftSpace--;
		}
	}
}
