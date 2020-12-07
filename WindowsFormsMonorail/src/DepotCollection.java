import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class DepotCollection {

	private final HashMap <String, Depot <Vehicle, IDoor>> depotStages;
	
    public String[] keys() {
        return depotStages.keySet().toArray(new String[depotStages.keySet().size()]);
    }

    private final  int pictureWidth;

    private final  int pictureHeight;
    
    private final String separator = ":";

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

    public boolean SaveData(String fileName) throws IOException {
    	
    	File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    	
		try(FileWriter fw = new FileWriter(fileName)) {
			
			fw.write("DepotCollection\n");
			
			for(var level : keys()) {

				fw.write("Depot" + separator + level + "\n");

				if(depotStages.isEmpty()) {
					return false;
				}
				
                ITransport train;

                for (int i = 0; (train = depotStages.get(level).get(i)) != null; i++) {
                	
                    if (train != null) {
                    	
                    	if(train instanceof Monorail) {
                    		fw.write("Monorail" + separator);
                        } else if(train instanceof Train) {
                            fw.write("Train" + separator);
                        }
                        fw.write(train + "\n");
                    }
                }
			}
		}
		
		return true;
    }
    
    public boolean SaveOnlyOneData(String fileName, String depotName) throws IOException {
    	
    	File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    	
		try(FileWriter fw = new FileWriter(fileName)) {
			
			fw.write("DepotCollection\n");
			fw.write("Depot" + separator + depotName + "\n");
			
			if(depotStages.isEmpty()) {
				return false;
			}
				
			ITransport train;	
			
            for (int i = 0; (train = depotStages.get(depotName).get(i)) != null; i++) {
            	
                if (train != null) {
                	
                	if(train instanceof Monorail) {
                		fw.write("Monorail" + separator);
                    } else if(train instanceof Train) {
                        fw.write("Train" + separator);
                    }
                    fw.write(train + "\n");
                }
            }
		}
		
		return true;
    }
    
    public boolean LoadData(String fileName) throws FileNotFoundException, IOException {

        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        }

        try (FileReader fr = new FileReader(fileName)) {
        	
            Scanner scanner = new Scanner(fr);
            String line;
            String key = "";
            boolean flagForStart = true;
            
            while (scanner.hasNextLine()) {
            	
                line = scanner.nextLine();
                
                if (line.contains("DepotCollection")) {
                	
                    depotStages.clear();
                    flagForStart = false;
                    continue;
                    
                } else if(flagForStart) {
                    return false;
                }
                
                Vehicle train = null;

                if (line.contains("Depot")) {
                    key = line.split(separator)[1];
                    depotStages.put(key, new Depot <Vehicle, IDoor> (pictureWidth, pictureHeight));
                    continue;
                }
                
                if (line.isEmpty()) {
                    continue;
                }

                if (line.split(separator)[0].equals("Train")) {
                    train = new Train(line.split(separator)[1]);
                } else if (line.split(separator)[0].equals("Monorail")) {
                	train = new Monorail(line.split(separator)[1]);
                }
                
                boolean result = depotStages.get(key).operatorAdd(train);
                
                if (!result) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean LoadOnlyOneData(String fileName) throws FileNotFoundException, IOException {

        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        }

        try (FileReader fr = new FileReader(fileName)) {
        	
            Scanner scanner = new Scanner(fr);
            String line;
            String key = "";
            boolean flagForStart = true;
            boolean oneDepot = false;
            
            while (scanner.hasNextLine()) {
            	
                line = scanner.nextLine();
                
                if (line.contains("DepotCollection")) {
                	
                    flagForStart = false;
                    continue;
                    
                } else if(flagForStart) {
                    return false;
                }
                
                Vehicle train = null;

                if (line.contains("Depot")) {
                	
                	if (oneDepot) {
                		break;
                	}
                	
                    key = line.split(separator)[1];
                    
                    if (depotStages.containsKey(key)) {
                    	depotStages.get(key).myClear();
                    }
                    
                    depotStages.put(key, new Depot <Vehicle, IDoor> (pictureWidth, pictureHeight));
                    oneDepot = true;
                    continue;
                }
                
                if (line.isEmpty()) {
                    continue;
                }

                if (line.split(separator)[0].equals("Train")) {
                    train = new Train(line.split(separator)[1]);
                } else if (line.split(separator)[0].equals("Monorail")) {
                	train = new Monorail(line.split(separator)[1]);
                }
                
                boolean result = depotStages.get(key).operatorAdd(train);
                
                if (!result) {
                    return false;
                }
            }
        }
        return true;
    }
}