package gui;

import model.Forklift;
import model.Warehouse;
import net.miginfocom.swing.MigLayout;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {

    Warehouse warehouse;
    GridPanel gridPanel;
    Forklift forklift;

    JTextArea logPlace = new JTextArea(20, 40);

    public MainView(Warehouse warehouse, Forklift forklift) {
        this.warehouse = warehouse;
        this.forklift = forklift;
        this.gridPanel = new GridPanel(this.warehouse,this.forklift);
        SwingUtilities.invokeLater(() -> {
//            try {
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            }


            logPlace.setEditable(false);

            logPlace.setText("--Start Application ForkliftSI-- \n");

            JFrame frame = new JFrame("Inteligent ForkLift");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new MigLayout());
            frame.add(initializerLeftPanel(), "west");
            frame.add(logPlace, "east");
            frame.add(gridPanel, "east");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            this.autorefresh();

        });
    }

    private JPanel initializerLeftPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel forkliftStateLabel = new JLabel("-- Information about forklift -- ", JLabel.CENTER);
        JLabel fuelLevelLabel = new JLabel("Fuel level : ", JLabel.LEFT);
        JLabel fuelLevel = new JLabel(Integer.toString(forklift.getFuelLevel()), JLabel.RIGHT);
        JLabel capacityLevelLabel = new JLabel("Actual loading : ", JLabel.LEFT);
        JLabel capacityLevel = new JLabel(Integer.toString(forklift.getCapacity()), JLabel.RIGHT);

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridPanel.initializeGeneratedData();
                logPlace.append("Click!\n");
            }
        });

        refresh.addActionListener(e -> gridPanel.clearGridView());
        panel.setPreferredSize(new Dimension(200, 700));
        panel.setMaximumSize(new Dimension(200, 700));
        panel.add(forkliftStateLabel);
        panel.add(fuelLevelLabel);
        panel.add(fuelLevel);
        panel.add(capacityLevelLabel);
        panel.add(capacityLevel);
        panel.add(refresh);
        return panel;
    }

    private void autorefresh() {
        int INTERVAL = 500;

        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                //Refresh the panel
                gridPanel.initializeGeneratedData();
            }
        });

        timer.start();
    }

}
