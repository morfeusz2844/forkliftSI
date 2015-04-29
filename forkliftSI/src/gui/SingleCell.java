package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class SingleCell extends JPanel {

    private static final long serialVersionUID = 1L;
    private Color defaultBackground;

    public SingleCell() {
        initializeMouseListener();
    }

    private void initializeMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                defaultBackground = getBackground();
                setBackground(new Color(100, 220, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackground);
            }
        });
    }

    public void setForkliftPositionHere() {
        setBackground(new Color(150, 25, 100));
    }

    public void setForkliftPositionNotHere() {
        setBackground(Color.BLUE);
    }

    public void setClearCellHere() {
        setBackground(defaultBackground);
        validate();
    }

    public void setAlgoritmPositionHere() {
        setBackground(new Color(120, 165, 100));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(65, 65);
    }
}
