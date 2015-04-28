package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import model.Warehouse;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {

	SingleCell cellPanel[][] = new SingleCell[Warehouse.getSizeX()][Warehouse.getSizeY()];

	public GridPanel() {
		initializeGridView();
		initializeGeneratedData();
	}

	private void initializeGridView() {
		Border border = null;
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());

		for (int row = 0; row < Warehouse.getSizeX(); row++) {
			for (int col = 0; col < Warehouse.getSizeY(); col++) {
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
		for (int i = 0; i < Warehouse.getSizeX(); i++) {
			for (int j = 0; j < Warehouse.getSizeY(); j++) {
				
				cellPanel[i][j].add(new JLabel("     "));
			}
		}
	}


	public void clearGridView() {
		for (int row = 0; row < Warehouse.getSizeX(); row++)
			for (int col = 0; col < Warehouse.getSizeY(); col++) {
				cellPanel[row][col].setClearCellHere();
				cellPanel[row][col].validate();
			}
	}


}