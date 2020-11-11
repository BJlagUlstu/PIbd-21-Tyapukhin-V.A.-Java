
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelMonorail extends JPanel {
	
    Monorail monorail;

    public PanelMonorail(Monorail monorail) {
    	
        this.monorail = monorail;
    }
  
    public void paint(Graphics g)  {
    	
        super.paint(g);
        monorail.DrawTransport(g);
    }
}
