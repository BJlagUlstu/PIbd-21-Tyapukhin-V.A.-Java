
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelDepot extends JPanel {
	
	Depot<ITransport, IDoor> depot;
	
	PanelDepot(Depot<ITransport, IDoor> depot) {
		
		this.depot = depot;
	}
  
    public void paint(Graphics g)  {
    	
        super.paint(g);
        depot.Draw(g);
    }
}
