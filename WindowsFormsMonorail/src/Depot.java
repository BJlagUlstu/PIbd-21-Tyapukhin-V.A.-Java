import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Depot <T extends ITransport, D extends IDoor> {

	private List<T> _places;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int _placeSizeWidth = 380;

    private final int _placeSizeHeight = 100;
    
    private int _maxCount;

    public Depot(int picWidth, int picHeight) {
    	int columns = picWidth / _placeSizeWidth;
    	int rows = picHeight / _placeSizeHeight;
    	_maxCount = columns * rows;
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        _places = new ArrayList<T>();
    }

    public boolean operatorAdd(T train) throws DepotOverflowException, DepotAlreadyHaveException {
    	if (_places.size() >= _maxCount)
    		throw new DepotOverflowException();
    	
    	if (_places.contains(train))
    		throw new DepotAlreadyHaveException();
    	
        _places.add(train);
        int x = (_places.size() - 1) / (pictureHeight / _placeSizeHeight);
        int y = (_places.size() - 1) - x * (pictureHeight / _placeSizeHeight);
        train.SetPosition(x * _placeSizeWidth + 15, y * _placeSizeHeight + 25, pictureWidth, pictureHeight);
        return true;
    }

    public T operatorSub(int index) throws DepotNotFoundException {
        if (index < 0 || index >= _places.size())
        	throw new DepotNotFoundException(index);
        
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
    
    public T get(int index) {
        if (index < 0 || index > _places.size() - 1) {
            return null;
        }
        return _places.get(index);
    }
    
    public List<T> getPlace() {
        return  _places;
    }
    
    public void Draw(Graphics g) {
        DrawMarking(g);
        for (int i = 0; i < _places.size(); ++i) {
        	_places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 30, pictureWidth, pictureHeight);
            _places.get(i).DrawTransport(g);
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
    
    public T GetNext(int index) {
        if (index < 0 || index >= _places.size())
        {
            return null;
        }
        return _places.get(index);
    }
    
    public void myClear() {
    	_places.clear();
    }
    
	public void Sort() {
    	_places.sort((Comparator<? super T>) new TrainComparer());
    }
    
    public void info() {
    	for(ITransport train: _places) {
    		System.out.println(train.toString());
    	}
    }
}