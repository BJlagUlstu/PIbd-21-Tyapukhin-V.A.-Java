import java.awt.Color;
import java.awt.Graphics;

public class MonorailDoor implements IDoor {

	private Door door;

	private int _startPosX;

	private int _startPosY;

	public Color DopColor = Color.GREEN;
	
	MonorailDoor(Color dopcolor, int count) {

		DopColor = dopcolor;
		countDoor(count);
	}
	
	MonorailDoor(int count) {

		countDoor(count);
	}

	public void DrawDoor(Graphics g) {

		g.setColor(DopColor);

		switch (door) {

		case number_1: {

			g.fillRect(_startPosX + 120, _startPosY + 5, 12, 20);
			g.setColor(Color.BLACK);
			g.drawRect(_startPosX + 120, _startPosY + 5, 12, 32);
			g.fillRect(_startPosX + 120, _startPosY + 25, 12, 12);
		}
		break;

		case number_2: {
			g.fillRect(_startPosX + 112, _startPosY + 5, 12, 20);
			g.fillRect( _startPosX + 130, _startPosY + 5, 12, 20);
			g.setColor(Color.BLACK);
			g.drawRect(_startPosX + 112, _startPosY + 5, 12, 32);
			g.drawRect(_startPosX + 130, _startPosY + 5, 12, 32);
			g.fillRect(_startPosX + 112, _startPosY + 25, 12, 12);
			g.fillRect(_startPosX + 130, _startPosY + 25, 12, 12);
		}
		break;

		case number_3: {	
			
			g.fillRect(_startPosX + 120, _startPosY + 5, 12, 20);
			g.fillRect(_startPosX + 102, _startPosY + 5, 12, 20);
			g.fillRect(_startPosX + 138, _startPosY + 5, 12, 20);
			g.setColor(Color.BLACK);
			g.drawRect(_startPosX + 102, _startPosY + 5, 12, 32);
			g.drawRect(_startPosX + 138, _startPosY + 5, 12, 32);
			g.drawRect(_startPosX + 120, _startPosY + 5, 12, 32);
			g.fillRect(_startPosX + 102, _startPosY + 25, 12, 12);
			g.fillRect(_startPosX + 138, _startPosY + 25, 12, 12);
			g.fillRect(_startPosX + 120, _startPosY + 25, 12, 12);
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
		return "Usual";
	}
}
