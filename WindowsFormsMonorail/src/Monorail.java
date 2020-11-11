import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Monorail {

	private int _startPosX;

	private int _startPosY;

	private int _pictureWidth;

	private int _pictureHeight;

	private int monorailWidth = 270;

	private int monorailHeight = 60;

	private int monorailWidthMagnet = 20;
	
	private int panel_limitation = 215;

	public int MaxSpeed;
	
	public int pickDoor;
	
	public MonorailDoor door;

	public void setMaxSpeed(int maxspeed) {

		MaxSpeed = maxspeed;
	}

	public float Weight;

	public void setWeight(int weight) {

		Weight = weight;
	}

	public Color MainColor;

	public void setMainColor(Color mainColor) {

		MainColor = mainColor;
	}

	public Color DopColor;

	public void setDopColor(Color dopColor) {

		DopColor = dopColor;
	}

	public boolean SportLine;

	public void setSportLine(boolean sportLine) {

		SportLine = sportLine;
	}

	public boolean Headlights;

	public void setHeadlights(boolean headlights) {

		Headlights = headlights;
	}

	public boolean BottomMonorail;

	public void setBottomMonorail(boolean bottomMonorail) {

		BottomMonorail = bottomMonorail;
	}

	Monorail(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean sportLine, boolean headlights, boolean bottomMonorail) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		DopColor = dopColor;
		SportLine = sportLine;
		Headlights = headlights;
		BottomMonorail = bottomMonorail;
		pickDoor = new Random().nextInt(Door.values().length);
		door = new MonorailDoor(DopColor, pickDoor);
	}

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}
	
	public void MoveTransport(Direction direction) {
		float step = MaxSpeed * 100 / Weight;

		switch (direction) {

		case Right:
			if (_startPosX + step < _pictureWidth - monorailWidth) {
				_startPosX += step;
			}
			break;

		case Left:
			if (_startPosX - step - monorailWidthMagnet > 0)
			{
				_startPosX -= step;
			}
			break;

		case Up:
			if (_startPosY - step > 0) {
				_startPosY -= step;
			}
			break;

		case Down:
			if (_startPosY + step < _pictureHeight - monorailHeight - panel_limitation) {
				_startPosY += step;
			}
			break;
		}
	}

	public void DrawTransport(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		BasicStroke pen = new BasicStroke(1);
		g.setColor(Color.BLACK);

		if (Headlights) {
			g.setColor(Color.YELLOW);
			g.fillOval(_startPosX + 245, _startPosY + 25, 8, 10);
			g.setColor(Color.BLACK);
			g.drawOval(_startPosX + 245, _startPosY + 25, 8, 10);
		}

		if (BottomMonorail) {
			g.drawRect(_startPosX - 10, _startPosY + 34, 25, 8);
			g.setColor(Color.BLUE);
			g.fillRect(_startPosX + 5, _startPosY + 43, 240, 8);
			g.setColor(Color.BLACK);
			g.drawRect(_startPosX + 5, _startPosY + 43, 240, 8);
		}

		//monorail body
		g.setColor(MainColor);
		g.fillArc(_startPosX + 150, _startPosY, 100, 85, 0, 90);
		g.fillArc(_startPosX, _startPosY, 100, 85, 180, -90);
		g.setColor(Color.BLACK);
		g.drawArc(_startPosX + 150, _startPosY, 100, 85, 0, 90);
		g.drawArc(_startPosX, _startPosY, 100, 85, 180, -90);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(_startPosX + 50, _startPosY, 150, 30);
		g.setColor(Color.BLACK);
		g.drawRect(_startPosX + 50, _startPosY, 150, 44);
		g.drawLine(_startPosX, _startPosY + 43, _startPosX + 250, _startPosY + 43);

		//glass
		g.setColor(Color.CYAN);
		g.fillRect(_startPosX + 55, _startPosY + 5, 12, 20);
		g.fillRect(_startPosX + 70, _startPosY + 5, 12, 20);
		g.fillRect(_startPosX + 170, _startPosY + 5, 12, 20);
		g.fillRect(_startPosX + 185, _startPosY + 5, 12, 20);
		g.fillRect(_startPosX + 85, _startPosY + 5, 12, 20);
		g.fillRect(_startPosX + 155, _startPosY + 5, 12, 20);
		g.fillArc(_startPosX + 168, _startPosY + 5, 70, 40, 0, 90);
		g.fillArc(_startPosX + 12, _startPosY + 5, 70, 40, 180, -90);
		g.setColor(Color.BLACK);
		g.drawRect(_startPosX + 55, _startPosY + 5, 12, 20);
		g.drawRect(_startPosX + 70, _startPosY + 5, 12, 20);
		g.drawRect(_startPosX + 85, _startPosY + 5, 12, 20);
		g.drawRect(_startPosX + 155, _startPosY + 5, 12, 20);
		g.drawRect(_startPosX + 170, _startPosY + 5, 12, 20);
		g.drawRect(_startPosX + 185, _startPosY + 5, 12, 20);
		g.drawArc(_startPosX + 168, _startPosY + 5, 70, 40, 0, 90);
		g.drawArc(_startPosX + 12, _startPosY + 5, 70, 40, 180, -90);
		g.drawLine(_startPosX + 13, _startPosY + 25, _startPosX + 46, _startPosY + 25);
		g.drawLine(_startPosX + 204, _startPosY + 25, _startPosX + 237, _startPosY + 25);
		g.drawLine(_startPosX + 47, _startPosY + 5, _startPosX + 47, _startPosY + 25);
		g.drawLine(_startPosX + 203, _startPosY + 5, _startPosX + 203, _startPosY + 25);
		
		//doors
		door.setPositionDoor(_startPosX, _startPosY);
		door.DrawDoor(g);
		
		if (SportLine) {
			g2.drawLine(_startPosX + 50, _startPosY + 28, _startPosX + 200, _startPosY + 28);
			g2.drawLine(_startPosX + 50, _startPosY + 32, _startPosX + 200, _startPosY + 32);
			
			BasicStroke pen_2 = new BasicStroke(4);
			g2.setStroke(pen_2);
			g2.setColor(Color.BLUE);
			g2.drawArc(_startPosX + 10, _startPosY + 30, 90, 30, 180, -90);
			g2.drawArc(_startPosX + 150, _startPosY + 30, 90, 30, 0, 90);
			g2.drawLine(_startPosX + 50, _startPosY + 30, _startPosX + 200, _startPosY + 30);	
		}
	}
}
