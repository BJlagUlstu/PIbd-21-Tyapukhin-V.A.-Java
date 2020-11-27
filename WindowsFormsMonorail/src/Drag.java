import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Drag extends MouseAdapter {
    public void mousePressed(MouseEvent event) {
        JComponent c = (JComponent) event.getSource();
        Object handler = c.getTransferHandler();
        ((TransferHandler) handler).exportAsDrag(c, event, TransferHandler.COPY);
    }
}
