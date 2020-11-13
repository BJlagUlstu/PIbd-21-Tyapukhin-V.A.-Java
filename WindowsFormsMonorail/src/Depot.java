import java.awt.Graphics;
import java.util.ArrayList;

public class Depot <T extends ITransport, D extends IDoor> {

	private ArrayList<T> _places;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int _placeSizeWidth = 380;

    private final int _placeSizeHeight = 100;

    public Depot(int picWidth, int picHeight) {
    	int columns = picWidth / _placeSizeWidth;
    	int rows = picHeight / _placeSizeHeight;
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        _places = new ArrayList<>();
        for (int i = 0; i < columns * rows; i++) {
            _places.add(null);
        }
    }

    public boolean operatorAdd(T train) {
    	for (int i = 0; i < _places.size(); i++) {
    		if (_places.get(i) == null) {
                int x = i / (pictureHeight / _placeSizeHeight);
                int y = i - x * (pictureHeight / _placeSizeHeight);
                train.SetPosition(x * _placeSizeWidth + 15, y * _placeSizeHeight + 25, pictureWidth, pictureHeight);
                _places.set(i, train);
                return true;
            }
        }
        return false;
    }

    public T operatorSub(int index) {
        if (index < 0 || index > _places.size() - 1)
            return null;

        if (_places.get(index) == null)
            return null;

        T train = _places.get(index);
        _places.remove(index);
        return train;
    }
    
    public boolean moreOrEqual(int number) {
        int counter = 0;
        for (int i = 0; i < _places.size(); i++) {
            if (_places.get(i) != null) {
            	counter++;
            }
        }
        return number >= counter;
    }

    public boolean lessOrEqual(int number) {
        return !moreOrEqual(number);
    }
    
    public void Draw(Graphics g) {
        DrawMarking(g);
        for (T train : _places) {
            if (train != null) {
            	train.DrawTransport(g);
            }
        }
    }

    private void DrawMarking(Graphics g) {
        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j) {
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + _placeSizeWidth / 2, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
    }
}