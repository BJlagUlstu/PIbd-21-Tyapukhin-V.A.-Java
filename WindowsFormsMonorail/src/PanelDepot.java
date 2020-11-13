
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelDepot extends JPanel {
	
	Depot<Vehicle, IDoor> depot;
  
    public void paint(Graphics g)  {
    	
        super.paint(g);
        if (depot != null) {
        	depot.Draw(g);
        }
    }
    
    public void setDepot(Depot<Vehicle, IDoor> depot) {
    	this.depot = depot;
    }
}
