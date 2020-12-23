import java.util.Comparator;

public class TrainComparer implements Comparator<Vehicle> {

	@Override
	public int compare(Vehicle x, Vehicle y) {
	    if (x instanceof Monorail && y instanceof Monorail) {
	        return ComparerMonorail((Monorail) x, (Monorail) y);
	    }
	    if (x instanceof Train && y instanceof Train) {
	        return ComparerTrain((Train) x, (Train) y);
	    }
	    return 0;
	}
	
	private int ComparerTrain(Train x, Train y) {
	    if (x.MaxSpeed != y.MaxSpeed) {
	        return Integer.compare(x.MaxSpeed, y.MaxSpeed);
	    }
	    if (x.Weight != y.Weight) {
	        return Integer.compare((int)x.Weight, (int)y.Weight);
	    }
	    if (x.MainColor != y.MainColor) {
	        return Integer.compare(x.MainColor.getRGB(), y.MainColor.getRGB());
	    }
	    return 0;
	}
	
	private int ComparerMonorail(Monorail x, Monorail y) {
	    int result = ComparerTrain(x, y);
	    if (result != 0) {
	        return result;
	    }
	    if (x.DopColor != y.DopColor) {
	        return Integer.compare(x.DopColor.getRGB(), y.DopColor.getRGB());
	    }
	    if (x.SportLine != y.SportLine) {
	        if (x.SportLine) return 1;
	        else return -1;
	    }
	    if (x.Headlights != y.Headlights) {
	        if (x.Headlights) return 1;
	        else return -1;
	    }
	    if (x.BottomMonorail != y.BottomMonorail) {
	        if (x.BottomMonorail) return 1;
	        else return -1;
	    }
	    return 0;
	}
}
