package model;

import model.enums.PackageSize;
import model.enums.PackageType;

import java.util.Random;

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

    public Warehouse() {
        initializeWarehouseMap();
    }

    private void initializeWarehouseMap() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                warehouseMap[i][j] = new Blank();
            }
        }
    }

    public void initializeRandomizedWarehouseMap() {
        Random rand = new Random();
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (i == 0) {
                    if (j == 0 || j == 14) {
                        warehouseMap[i][j] = new Service();
                    } else {
                        warehouseMap[i][j] = new Ramp();
                    }
                } else if (i > 1 && (j == 0 || j == 2 || j == 3 || j == 5 || j == 6)) {
                    warehouseMap[i][j] = new StorageRack();
                }else if (i==1 || (i>1 && (j==1||j==4||j==7)) || ((i==4 || i==7 || i==10 || i==13) && j>7)){
                    warehouseMap[i][j] = new Road();
                } else {
                    warehouseMap[i][j] = new Blank();
                }
            }
        }
    }

    public void addWorldElement(WorldElement worldElement, int positionX, int positionY) {
        this.warehouseMap[positionX][positionY] = worldElement;
    }

    public WorldElement getWorldElement(int positionX, int positionY) {
        return warehouseMap[positionX][positionY];
    }
}

