import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Monorail extends Train {

	public Color DopColor;

	public void setDopColor(Color dopColor) {

		DopColor = dopColor;
		door.setDopColor(DopColor);
	}

	public void setDoorType(IDoor pickDoor) {
		
		door = pickDoor;
		door.countDoor(numberOfDoor);
		door.setDopColor(DopColor);
	}
	
	public void setCountDoor(int count) {
		
		door.countDoor(count);
		numberOfDoor = count;
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
	
	int numberOfDoor = 0;

	Monorail(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean sportLine, boolean headlights, boolean bottomMonorail) {
		
		super(maxSpeed, weight, mainColor, 270, 70);
        DopColor = dopColor;
        SportLine = sportLine;
        Headlights = headlights;
        BottomMonorail = bottomMonorail;
        
        numberOfDoor = new Random().nextInt(Door.values().length);
        int rnd = new Random().nextInt(3);
        choiceOfOrnament(rnd, numberOfDoor);
    }
	
    public Monorail(String info) {
    	
    	super(info);
    	String[] strs = info.split(separator);
        if (strs.length == 9) {
        	MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Integer.parseInt(strs[1]);
            String[] MaincolorRGB = strs[2].split(",");
            MainColor = new Color(Integer.parseInt(MaincolorRGB[0]), Integer.parseInt(MaincolorRGB[1]), Integer.parseInt(MaincolorRGB[2]));
            String[] DopcolorRGB = strs[3].split(",");
            DopColor = new Color(Integer.parseInt(DopcolorRGB[0]), Integer.parseInt(DopcolorRGB[1]), Integer.parseInt(DopcolorRGB[2])); 
            SportLine = Boolean.parseBoolean(strs[4]);
            Headlights = Boolean.parseBoolean(strs[5]);
            BottomMonorail = Boolean.parseBoolean(strs[6]);
            numberOfDoor = Integer.parseInt(strs[7]);
            
            if(strs[8].equals("Usual")) {
            	choiceOfOrnament(0, numberOfDoor);
            } else if(strs[8].equals("Subtype 1")) {
            	choiceOfOrnament(1, numberOfDoor);
            } else if(strs[8].equals("Subtype 2")) {
            	choiceOfOrnament(2, numberOfDoor);
            }
            setDoorType(door);
        }
    }

	public void choiceOfOrnament(int number, int pickDoor) {
		
		switch(number) {
		
		case 0: door = new MonorailDoor(DopColor, pickDoor);
		break;
		
		case 1: door = new MonorailDoorTypeOne(DopColor, pickDoor);
		break;
		
		case 2: door = new MonorailDoorTypeTwo(DopColor, pickDoor);
		break;
		}
	}
	
	@Override 
    public void DrawTransport(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		BasicStroke pen = new BasicStroke(1);
		g2.setStroke(pen);
		
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

        super.DrawTransport(g);

		if (SportLine) {
			g2.setColor(Color.BLACK);
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

    @Override
    public String toString() {
    	return super.toString() + separator + DopColor.getRed() + "," + DopColor.getGreen() + "," + DopColor.getBlue() + separator + SportLine + separator + Headlights + separator + BottomMonorail + separator + numberOfDoor + separator + door;
    }
}