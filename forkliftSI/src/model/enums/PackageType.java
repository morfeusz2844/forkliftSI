package model.enums;

public enum PackageType {
	NORMAL(6), FRAGILE(8), DANGEROUS(9), GROCERIES(7);

	private int value;
	private PackageType(int value){this.value=value;}
	public int getValue(){return this.value;}

}
