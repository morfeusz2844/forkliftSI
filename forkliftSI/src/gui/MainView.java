package gui;

import model.Forklift;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {

    GridPanel gridPanel = new GridPanel();
    static Forklift forklift = new Forklift();

    JLabel forkliftStateLabel = new JLabel("-- Stan pocz¹tkowy wózka -- ");
    JLabel fuelLevelLabel = new JLabel("Poziom paliwa : ");
    public static JLabel fuelLevel = new JLabel(Integer.toString(forklift.getFuelLevel()));
    JLabel capacityLevelLabel = new JLabel("Obecne za³adowanie : ");
    public static JLabel capacityLevel = new JLabel(Integer.toString(forklift.getCapacity()));

    JButton refresh = new JButton("Odswiez");
    //JTextArea logPlace = new JTextArea(20,40);

    public MainView() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                initializeListeners();
                //JScrollPane scroll = new JScrollPane(logPlace);
                //logPlace.setEditable(false);
                JFrame frame = new JFrame("Inteligentny Wózek wid³owy");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new MigLayout());

                frame.add(forkliftStateLabel, "cell 0 0");
                frame.add(fuelLevelLabel, "cell 0 1");
                frame.add(fuelLevel, "w 80!, cell 0 1");
                frame.add(capacityLevelLabel, "cell 0 2");
                frame.add(capacityLevel, "cell 0 2");
                frame.add(refresh, "cell 0 9, wrap");
                //frame.add(logPlace, "east");
                frame.add(gridPanel, "east");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private void initializeListeners() {

        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                gridPanel.clearGridView();
            }
        });
    }
}
