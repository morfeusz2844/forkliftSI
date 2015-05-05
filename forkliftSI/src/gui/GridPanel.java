package gui;

import model.Warehouse;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {

    Warehouse warehouse;

    SingleCell cellPanel[][];

    public GridPanel() {
        this.warehouse = new Warehouse();
        warehouse.initializeRandomizedWarehouseMap();
        cellPanel = new SingleCell[warehouse.getSizeX()][warehouse.getSizeY()];
        initializeGridView();
        initializeGeneratedData();
    }

    private void initializeGridView() {
        setLayout(new GridLayout(15, 15));
        setPreferredSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(700, 700));
        for (int row = 0; row < warehouse.getSizeX(); row++) {
            for (int col = 0; col < warehouse.getSizeY(); col++) {
                cellPanel[row][col] = (new SingleCell());
                add(cellPanel[row][col], this);
            }
        }
    }

    public void initializeGeneratedData() {
        for (int i = 0; i < warehouse.getSizeX(); i++) {
            for (int j = 0; j < warehouse.getSizeY(); j++) {
                String objectType = warehouse.getWorldElement(i, j).getType();
                cellPanel[i][j].setToolTipText(objectType);

                if (objectType.equals("Package")) {
                    cellPanel[i][j].setPackagePositionHere();
                } else if (objectType.equals("Blank")) {
                    cellPanel[i][j].setBlankPositionHere();
                } else if (objectType.equals("StorageRack")) {
                    cellPanel[i][j].setStorageRackPositionHere();
                } else if (objectType.equals("Service")){
                    cellPanel[i][j].setServicePositionHere();
                } else if (objectType.equals("Ramp")){
                    cellPanel[i][j].setRampPositionHere();
                }
            }
        }
    }

    public void clearGridView() {
        for (int row = 0; row < warehouse.getSizeX(); row++) {
            for (int col = 0; col < warehouse.getSizeY(); col++) {
                cellPanel[row][col].setClearCellHere();
                cellPanel[row][col].validate();
            }
        }
    }
}
