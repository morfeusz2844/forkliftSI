package gui;

import model.Warehouse;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
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
        Border border = null;
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        for (int row = 0; row < warehouse.getSizeX(); row++) {
            for (int col = 0; col < warehouse.getSizeY(); col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                cellPanel[row][col] = (new SingleCell());
                border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                cellPanel[row][col].setBorder(border);
                add(cellPanel[row][col], gbc);
            }
        }
    }

    public void initializeGeneratedData() {


        for (int i = 0; i < warehouse.getSizeX(); i++) {
            for (int j = 0; j < warehouse.getSizeY(); j++) {

                String objectType = warehouse.getWorldElement(i, j).getType();
                cellPanel[i][j].add(new JLabel(objectType));
                if (objectType.equals("Package")){
                    cellPanel[i][j].setPackagePositionHere();}
                else if (objectType.equals("Blank")){
                    cellPanel[i][j].setBlankPositionHere();}
                else if (objectType.equals("StorageRack")){
                    cellPanel[i][j].setStorageRackPositionHere();}
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
