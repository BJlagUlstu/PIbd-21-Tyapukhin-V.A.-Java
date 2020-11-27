
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelTrainConfig extends JPanel {
	
	ITransport transport;
  
    public void paint(Graphics g)  {
    	
        super.paint(g);
        if (transport != null) {
        	transport.DrawTransport(g);
        }
    }
    
    public void setTransport(ITransport transport) {
    	this.transport = transport;
    	transport.SetPosition(20, 25, 1200, 700);
    }
}
