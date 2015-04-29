package gui;

import model.*;
import model.Package;
import model.enums.PackageSize;
import model.enums.PackageType;

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
		warehouse.addWorldElement(new Blank(),0,0);
		warehouse.addWorldElement(new Package(PackageSize.BIG, PackageType.DANGEROUS),1,1);
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

                cellPanel[row][col] = new SingleCell();
                border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                cellPanel[row][col].setBorder(border);
                add(cellPanel[row][col], gbc);
            }
        }
    }

    public void initializeGeneratedData() {


        for (int i = 0; i < warehouse.getSizeX(); i++) {
            for (int j = 0; j < warehouse.getSizeY(); j++) {

				String objectType = warehouse.getWorldElement(i,j).getType();
				if (objectType.equals("Package"))
					cellPanel[i][j].setPackagePositionHere();
				if (objectType.equals("Blank"))
					cellPanel[i][j].setBlankPositionHere();

                cellPanel[i][j].add(new JLabel("     "));
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
