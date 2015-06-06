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

    private Random random;

    private void initializeBlankWarehouseMap() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                warehouseMap[i][j] = new Blank();
            }
        }
    }

    public void initializeWarehouseMap() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (i == 0) {
                    if (j == 0 || j == 14) {
                        warehouseMap[i][j] = new Service();
                    } else {
                        if (j % 3 == 0) {
                            warehouseMap[i][j] = new Ramp();
                        } else if (j % 3 == 1) {
                            warehouseMap[i][j] = new Truck(false,i,j);
                        } else {
                            warehouseMap[i][j] = new Truck(true,i,j);
                        }
                    }
                } else if (i > 1 && (j == 0 || j == 2 || j == 3 || j == 5 || j == 6)) {
                    warehouseMap[i][j] = new StorageRack();
                } else if (i == 1 || (i > 1 && (j == 1 || j == 4 || j == 7)) || ((i == 4 || i == 7 || i == 10 || i == 13) && j > 7)) {
                    warehouseMap[i][j] = new Road();
                } else {
                    warehouseMap[i][j] = new Ground();
                }
            }
        }
        generateRandomWorldMap();
    }

    public void addWorldElement(WorldElement worldElement, int positionX, int positionY) {
        this.warehouseMap[positionX][positionY] = worldElement;
    }

    public WorldElement getWorldElement(int positionX, int positionY) {
        if (positionX >= 0 && positionX < SIZE_X && positionY >= 0 && positionY < SIZE_Y)
            return warehouseMap[positionX][positionY];
        else return new Blank();
    }

    @Deprecated
    public String[] getPassableAdjacencies(int x, int y) {

        String[] adjacencies = new String[4];

        if (this.getWorldElement(x - 1, y).isPassable())
            adjacencies[0] = this.getWorldElement(x - 1, y).getType() + " " + String.valueOf(x - 1) + " " + (y);
        if (this.getWorldElement(x, y - 1).isPassable())
            adjacencies[1] = this.getWorldElement(x, y - 1).getType() + " " + (x) + " " + String.valueOf(y - 1);
        if (this.getWorldElement(x, y + 1).isPassable())
            adjacencies[2] = this.getWorldElement(x, y + 1).getType() + " " + (x) + " " + String.valueOf(y + 1);
        if (this.getWorldElement(x + 1, y).isPassable())
            adjacencies[3] = this.getWorldElement(x + 1, y).getType() + " " + String.valueOf(x + 1) + " " + (y);

        return adjacencies;
    }

    public void generateRandomWorldMap(){
        this.random = new Random();
        for (int i = 1; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (warehouseMap[i][j].getType().equals("StorageRack")) {
                    int randomCapacity = random.nextInt(((StorageRack) warehouseMap[i][j]).getMaxCapacity());
                    int randomTypePackage = random.nextInt(3);
                    for (int d = 0; d < randomCapacity; d++) {
                        switch (randomTypePackage) {
                            case 0: {
                                ((StorageRack) warehouseMap[i][j]).addPackage(new Package(PackageSize.SMALL, PackageType.NORMAL));
                                break;
                            }
                            case 1: {
                                ((StorageRack) warehouseMap[i][j]).addPackage(new Package(PackageSize.SMALL, PackageType.FRAGILE));
                            }
                            case 2: {
                                ((StorageRack) warehouseMap[i][j]).addPackage(new Package(PackageSize.SMALL, PackageType.DANGEROUS));
                            }
                            case 3: {
                                ((StorageRack) warehouseMap[i][j]).addPackage(new Package(PackageSize.SMALL, PackageType.GROCERIES));
                            }
                        }
                    }
                } else if (warehouseMap[i][j].getType().equals("Ground")) {
                    int randomCapacity = random.nextInt(((Ground) warehouseMap[i][j]).getMaxCapacity());
                    int randomTypePackage = random.nextInt(3);
                    int randomSizePackage = random.nextInt(2);
                    PackageSize packageGenerate = PackageSize.BIG;
                    switch (randomSizePackage){
                        case 0:{
                            packageGenerate = PackageSize.BIG;
                        }
                        case 1:{
                            packageGenerate = PackageSize.MEDIUM;
                        }
                    }
                    for (int d = 0; d < randomCapacity; d++) {
                        switch (randomTypePackage) {
                            case 0: {
                                ((Ground) warehouseMap[i][j]).addPackage(new Package(packageGenerate, PackageType.NORMAL));
                                break;
                            }
                            case 1: {
                                ((Ground) warehouseMap[i][j]).addPackage(new Package(packageGenerate, PackageType.FRAGILE));
                            }
                            case 2: {
                                ((Ground) warehouseMap[i][j]).addPackage(new Package(packageGenerate, PackageType.DANGEROUS));
                            }
                            case 3: {
                                ((Ground) warehouseMap[i][j]).addPackage(new Package(packageGenerate, PackageType.GROCERIES));
                            }
                        }
                    }
                }
            }
        }
    }
    public WorldElement[][] getWarehouseMap(){
        return this.warehouseMap;
    }

    public boolean isPassable(int i, int j) {

        return warehouseMap[i][j].isPassable();
    }
}

