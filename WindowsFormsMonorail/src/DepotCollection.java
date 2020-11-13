import java.util.HashMap;

public class DepotCollection {

	private final HashMap <String, Depot <Vehicle, IDoor>> depotStages;
	
    public String[] keys() {
        return depotStages.keySet().toArray(new String[depotStages.keySet().size()]);
    }

    private final  int pictureWidth;

    private final  int pictureHeight;

    public DepotCollection(int pictureWidth, int pictureHeight) {
        depotStages = new HashMap <String, Depot <Vehicle, IDoor>> ();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    public void addDepot(String name) {
        if (depotStages.containsKey(name)) {
            return;
        }
        depotStages.put(name, new Depot <Vehicle, IDoor> (pictureWidth, pictureHeight));
    }

    public void delDepot(String name) {
        if (depotStages.containsKey(name)) {
            depotStages.remove(name);
        }
    }

    public Vehicle get(String index, int id) {
        if (depotStages.containsKey(index)) {
            return depotStages.get(index).get(id);
        }
        return null;
    }
    
    public Depot<Vehicle, IDoor> get(String index) {
        if (depotStages.containsKey(index)) {
            return depotStages.get(index);
        }
        return null;
    }
}