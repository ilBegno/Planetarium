import java.util.ArrayList;

public class Planet extends CelBody {
	private ArrayList<CelBody> moons;
	
	//constructors
	public Planet(String id,String name,double mass,Position pos,String orbiting) {
		super(id,name,mass,pos,orbiting);
		moons = new ArrayList<CelBody>();
	}
	public Planet(String id,String name,double mass,Position pos,String orbiting,ArrayList<CelBody> moons) {
		super(id,name,mass,pos,orbiting);
		setMoons(moons);
	}
	
	
	public ArrayList<CelBody> getMoons() {
		return moons;
	}
	public void setMoons(ArrayList<CelBody> moons) {
		this.moons = moons;
	}
	public void addMoon(CelBody moon) {
		int check = -1;
		if(findMoon(moon.getId()) == check) moons.add(moon);
	}
	public boolean removeMoon(String id) {
		int index = findMoon(id);
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
		for (CelBody m: moons) 
		{
			strToPrint = strToPrint +  " " + m.getName() +  "%n";	
		}
		return strToPrint;
	}
	
	public int findMoon(String name) {
		for(int i = 0; i < moons.size(); i++) {
			if(moons.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
}
