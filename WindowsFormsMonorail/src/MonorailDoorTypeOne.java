import java.awt.Color;
import java.awt.Graphics;

public class MonorailDoorTypeOne implements IDoor {

	private Door door;
	
	private int _startPosX;

	private int _startPosY;

	public Color DopColor = Color.BLACK;
	
	MonorailDoorTypeOne(Color dopcolor, int count) {

		DopColor = dopcolor;
		countDoor(count);
	}
	
	MonorailDoorTypeOne(int count) {

		countDoor(count);
	}

	public void DrawDoor(Graphics g) {

		g.setColor(DopColor);

		switch (door) {

		case number_1: {

			g.fillOval(_startPosX + 120, _startPosY + 5, 12, 35);
		}
		break;

		case number_2: {
			g.fillOval(_startPosX + 112, _startPosY + 5, 12, 35);
			g.fillOval( _startPosX + 130, _startPosY + 5, 12, 35);
		}
		break;

		case number_3: {	
			
			g.fillOval(_startPosX + 120, _startPosY + 5, 12, 35);
			g.fillOval(_startPosX + 102, _startPosY + 5, 12, 35);
			g.fillOval(_startPosX + 138, _startPosY + 5, 12, 35);
		}
		break;
		}
	}

	public void countDoor(int count) {

		switch(count) {
		
		case 0:
			door = Door.number_1;
			break;
		case 1:
			door = Door.number_2;
			break;
		case 2:
			door = Door.number_3;
			break;
		}
	}
	
	public void setPositionDoor(int x, int y) {
		_startPosX = x;
		_startPosY = y;
	}

	public void setDopColor(Color color) {
		DopColor = color;
	}
	
	public void setDoorType(Door pickDoor) {
		door = pickDoor;
	}
	
	@Override
	public String toString() {
		return "Subtype 1";
	}
}
