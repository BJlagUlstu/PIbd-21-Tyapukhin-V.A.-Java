import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Train extends Vehicle {

	protected int monorailWidth = 260;

    protected int monorailHeight = 60;

    protected final int monorailWidthMagnet = 20;

	public IDoor door;

    public Train(int maxSpeed, float weight, Color mainColor) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        door = new MonorailDoor(2);
    }

    protected Train(int maxSpeed, float weight, Color mainColor, int monoWidth, int monoHeight) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        this.monorailWidth = monoWidth;
        this.monorailHeight = monoHeight;
    }

    @Override
    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;

        switch (direction) {

            case Right:
                if (_startPosX + step < _pictureWidth - monorailWidth) {
                    _startPosX += step;
                }
                break;

            case Left:
                if (_startPosX - step - monorailWidthMagnet > 0) {
                    _startPosX -= step;
                }
                break;

            case Up:
                if (_startPosY - step > 0) {
                    _startPosY -= step;
                }
                break;

            case Down:
                if (_startPosY + step < _pictureHeight - monorailHeight) {
                    _startPosY += step;
                }
                break;
        }
    }

    @Override
    public void DrawTransport(Graphics g) {
    	
		Graphics2D g2 = (Graphics2D) g;
		BasicStroke pen = new BasicStroke(1);
		g2.setStroke(pen);
		
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
    }
}
