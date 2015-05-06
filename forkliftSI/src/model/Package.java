package model;

import model.enums.PackageSize;
import model.enums.PackageType;


//@TODO zastanowiæ siê czy aby na pewno paczka ma implementowaæ WorldElement
public class Package implements WorldElement {
	private static final String TYPE = "Package";

	private final PackageSize packageSize;
	private final PackageType packageType;

	public Package(PackageSize packageSize, PackageType packageType) {
		this.packageSize = packageSize;
		this.packageType = packageType;
	}

	public PackageSize getPackageSize() {
		return packageSize;
	}

	public PackageType getPackageType() {
		return packageType;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean isPassable() {

		return false;
	}


}