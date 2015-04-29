package model;

public class Warehouse {
	private final int SIZE_X = 15;
	private final int SIZE_Y = 15;

	private WorldElement[][] warehouseMap = new WorldElement[SIZE_X][SIZE_Y];

	public int getSizeX() {
		return SIZE_X;
	}
	public int getSizeY() {
		return SIZE_Y;
	}

	public Warehouse(){
		initializeWarehouseMap();
	}

	private void initializeWarehouseMap(){
		for(int i = 0; i < SIZE_X; i++ ){
			for(int j = 0; j < SIZE_Y; j++ ){
				warehouseMap[i][j] = new Blank();
			}
		}
	}

	public void addWorldElement(WorldElement worldElement, int positionX, int positionY){
		this.warehouseMap[positionX][positionY] = worldElement;
	}

	public WorldElement getWorldElement(int positionX, int positionY){
		return warehouseMap[positionX][positionY];
	}
}

