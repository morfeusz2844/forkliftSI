package gui;

import model.Forklift;
import model.Ground;
import model.StorageRack;
import model.Warehouse;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {

    Warehouse warehouse;
    Forklift forklift;

    SingleCell cellPanel[][];

    public GridPanel(Warehouse warehouse, Forklift forklift) {
        this.warehouse = warehouse;
        this.forklift=forklift;
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
                String toolTipType = warehouse.getWorldElement(i, j).getType();

                int objectSize;
                if (objectType== "StorageRack" ){
                    objectSize = ((StorageRack)warehouse.getWorldElement(i,j)).getLeftSpace();
                    toolTipType = toolTipType+" free space:"+objectSize;
                } else if(objectType =="Ground"){
                    objectSize = ((Ground)warehouse.getWorldElement(i,j)).getLeftSpace();
                    toolTipType = toolTipType+" free space:"+objectSize;
                }

                cellPanel[i][j].setToolTipText(toolTipType);
                cellPanel[forklift.getPositionY()][forklift.getPositionX()].setForkliftPositionHere();
                if (objectType.equals("Forklift")){
                    cellPanel[i][j].setForkliftPositionHere();
                } else if (objectType.equals("Blank")) {
                    cellPanel[i][j].setBlankPositionHere();
                } else if (objectType.equals("StorageRack")) {
                    cellPanel[i][j].setStorageRackPositionHere();
                } else if (objectType.equals("Service")){
                    cellPanel[i][j].setServicePositionHere();
                } else if (objectType.equals("Ramp")){
                    cellPanel[i][j].setRampPositionHere();
                } else if (objectType.equals("Road")){
                    cellPanel[i][j].setRoadPositionHere();
                } else if (objectType.equals("Ground")){
                    cellPanel[i][j].setGroundPositionHere();
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
