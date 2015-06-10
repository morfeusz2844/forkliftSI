package gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SingleCell extends JPanel {

    private static final long serialVersionUID = 1L;
    private Color defaultBackground;

    public SingleCell() {
        setMinimumSize(new Dimension(50, 50));
        setPreferredSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));

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

    public void setServicePositionHere() {
        setBackground(new Color(255, 255, 10));
    }

    public void setRampPositionHere() {
        setBackground(new Color(0, 255, 255));
    }

    public void setRoadPositionHere(){setBackground(new Color(0,0,0));}

    public void setBlankPositionHere() {
        setBackground(new Color(150, 250, 50));
    }

    public void setGroundPositionHere() {
        setBackground(new Color(100, 100, 100));
    }

    public void setStorageRackPositionHere() {
        setBackground(new Color(10, 10, 90));
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
}
