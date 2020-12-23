
public class DepotNotFoundException extends Exception {
	
	public DepotNotFoundException(int i) {
		
		super("Не найден поезд по месту " + i);
    }
}
