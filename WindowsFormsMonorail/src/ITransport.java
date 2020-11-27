import java.awt.Color;
import java.awt.Graphics;

public interface ITransport {

	void SetPosition(int x, int y, int width, int height);

	void MoveTransport(Direction direction);

	void DrawTransport(Graphics g);
	
	void setMainColor(Color color);
}
