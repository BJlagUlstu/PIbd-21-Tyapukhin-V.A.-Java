import java.awt.Color;
import java.awt.Graphics;

public class MonorailDoorTypeTwo implements IDoor {

	private Door door;
	
	private int _startPosX;

	private int _startPosY;

	public Color DopColor = Color.PINK;
	
	MonorailDoorTypeTwo(Color dopcolor, int count) {

		DopColor = dopcolor;
		countDoor(count);
	}
	
	MonorailDoorTypeTwo(int count) {

		countDoor(count);
	}

	public void DrawDoor(Graphics g) {

		g.setColor(Color.BLACK);

		switch (door) {

		case number_1: {

			g.fillRect(_startPosX + 120, _startPosY + 5, 12, 32);
			g.drawRect(_startPosX + 120, _startPosY + 5, 12, 32);
			g.setColor(DopColor);
			g.fillOval(_startPosX + 121, _startPosY + 6, 10, 19);
		}
		break;

		case number_2: {
			
			g.fillRect(_startPosX + 112, _startPosY + 5, 12, 32);
			g.fillRect( _startPosX + 130, _startPosY + 5, 12, 32);
			g.drawRect(_startPosX + 112, _startPosY + 5, 12, 32);
			g.drawRect(_startPosX + 130, _startPosY + 5, 12, 32);
			g.setColor(DopColor);
			g.fillOval(_startPosX + 113, _startPosY + 6, 10, 19);
			g.fillOval( _startPosX + 131, _startPosY + 6, 10, 19);
		}
		break;

		case number_3: {	
			
			g.fillRect(_startPosX + 120, _startPosY + 5, 12, 32);
			g.fillRect(_startPosX + 102, _startPosY + 5, 12, 32);
			g.fillRect(_startPosX + 138, _startPosY + 5, 12, 32);
			g.drawRect(_startPosX + 102, _startPosY + 5, 12, 32);
			g.drawRect(_startPosX + 138, _startPosY + 5, 12, 32);
			g.drawRect(_startPosX + 120, _startPosY + 5, 12, 32);
			g.setColor(DopColor);
			g.fillOval(_startPosX + 121, _startPosY + 6, 10, 19);
			g.fillOval(_startPosX + 103, _startPosY + 6, 10, 19);
			g.fillOval(_startPosX + 139, _startPosY + 6, 10, 19);
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
		return "Subtype 2";
	}
	
	public boolean Equals(IDoor other) {
	    MonorailDoorTypeTwo currentDoor = (MonorailDoorTypeTwo)(other);
		if (other == null) {
	        return false;
	    }
	    if (door != (currentDoor).door) {
	        return false;
	    }
	    return true;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!(obj.getClass() == getClass())) {
	        return false;
	    } else {
	        return Equals((IDoor) obj);
	    }
	}
}
