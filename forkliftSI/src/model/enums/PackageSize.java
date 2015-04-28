package model.enums;

public enum PackageSize {
	SMALL(1), MEDIUM(2), BIG(4);

	private int value;

	private PackageSize(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
