package main;

import org.eclipse.swt.widgets.Display;

public class ForkliftSI {

    public static void main(String[] args) {
        Display display = new Display();
        new gui.Gui(display);
        display.dispose();
    }
    
}
