import java.util.ArrayList;

public class Planet extends CelBody {
	private ArrayList<CelBody> moons;

	// constructors
	public Planet(String id, String name, double mass, Position pos, String orbiting) {
		super(id, name, mass, pos, orbiting);
		moons = new ArrayList<CelBody>();
	}

	public Planet(String id, String name, double mass, Position pos, String orbiting, ArrayList<CelBody> moons) {
		super(id, name, mass, pos, orbiting);
		setMoons(moons);
	}
	
	// g & s
	public ArrayList<CelBody> getMoons() {
		return moons;
	}

	public void setMoons(ArrayList<CelBody> moons) {
		this.moons = moons;
	}
	//the method getRadius is already inherited from the superclass CelBody this method instead is just for the planets in the sistem
	// the radius of a Planet infact is the radius of revolution around the star of the system 
	// the radius of a CelBody instead is the radius of revolution around his planet 
	
	public void setRadius() {
		setRadius(Math.sqrt (Math.pow(getPos().getX(), 2) + (Math.pow(getPos().getY(), 2) )));
	}
	
	
	//methods
	public void addMoon(CelBody moon) {
		int check = -1;
		if (findMoon(moon.getId()) == check)
			moons.add(moon);
	}

	public boolean removeMoon(String name) {
		int index = findMoon(name);
		if (index > 0) {
			moons.remove(index);
			return true;
		}
		return false;
	}

	public String showMoons() {
		String strToPrint = "";
		if (moons.size() == 0) {
			strToPrint = "Il pianeta non ha lune";
			return strToPrint;
		}
		for (CelBody m : moons) {
			strToPrint = strToPrint + " " + m.getName() + " ";
		}
		return strToPrint;
	}

	public int findMoon(String name) {
		for (int i = 0; i < moons.size(); i++) {
			if ((moons.get(i).getName()).equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	
