package model;

public class Forklift implements WorldElement {

	private static final String TYPE = "Forklift";

	private final static int MAX_FUEL = 100;
	private final static int STARTING_POSITION_X = 0;
	private final static int STARTING_POSITION_Y = 0;
	private int positionX;
	private int positionY;

	private int capacity;

	private int fuelLevel;

	private WorldElement carriedWorldElement;

	public Forklift() {
		this.carriedWorldElement=null;
		this.fuelLevel = MAX_FUEL;
		this.positionX = STARTING_POSITION_X;
		this.positionY = STARTING_POSITION_Y;
	}

	public Forklift(int positionX, int positionY, int fuelLevel) {
		this.carriedWorldElement=null;
		this.positionX = positionX;
		this.positionY = positionY;
		this.fuelLevel = fuelLevel;
	}

	public Forklift(int fuelLevel) {
		this.carriedWorldElement=null;
		this.positionX = STARTING_POSITION_X;
		this.positionY = STARTING_POSITION_Y;
		this.fuelLevel = fuelLevel;
	}

	public void pickElement(){

	}

	public void dropElement(){

	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(int fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String getType() {
		return TYPE;
	}
}
