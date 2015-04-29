package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Gui {

    public Gui(Display display) {
        Shell shell = new Shell(display);
        shell.setText("ForkLiftSI");
        shell.setSize(400, 400);

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

}