import java.awt.Color;
import java.awt.Graphics;

public interface IDoor {

	void setPositionDoor(int x, int y);
	
	void DrawDoor(Graphics g);
	
	void setDopColor(Color color);
	
	void setDoorType(Door pickDoor);
	
	void countDoor(int count);
}
