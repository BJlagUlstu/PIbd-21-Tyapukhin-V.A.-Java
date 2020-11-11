import java.awt.Color;
import java.awt.Graphics;

public abstract class Vehicle implements ITransport {
	
        protected int _startPosX;

        protected int _startPosY;

        protected int _pictureWidth;

        protected int _pictureHeight;
        
    	public int MaxSpeed;

    	public int getMaxSpeed(int maxspeed) {

    		return maxspeed;
    	}

    	public void setMaxSpeed(int maxspeed) {

    		MaxSpeed = maxspeed;
    	}
    	
    	public float Weight;

    	public int getWeight(int weight) {

    		return weight;
    	}

    	public void setWeight(int weight) {

    		Weight = weight;
    	}

    	public Color MainColor;

    	public Color getMainColor(Color mainColor) {

    		return mainColor;
    	}

    	public void setMainColor(Color mainColor) {

    		MainColor = mainColor;
    	}

        public void SetPosition(int x, int y, int width, int height)
        {
            _startPosX = x;
            _startPosY = y;
            _pictureWidth = width;
            _pictureHeight = height;
        }

        public abstract void MoveTransport(Direction direction);

        public abstract void DrawTransport(Graphics g);
    }
