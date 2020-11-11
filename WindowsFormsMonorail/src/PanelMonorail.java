
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelMonorail extends JPanel {
	
	ITransport monorail;

    public PanelMonorail(ITransport monorail) {
    	
        this.monorail = monorail;
    }
  
    public void paint(Graphics g)  {
    	
        super.paint(g);
        monorail.DrawTransport(g);
    }
}
