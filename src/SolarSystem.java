import java.util.ArrayList;

public class SolarSystem {
	//attr
	private String name;
	private ArrayList<Planet> planets;
	private double centerOfMass;
	
	//g & s
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	public void setPlanets(ArrayList<Planet> planets) {
		this.planets = planets;
	}
	public void addPlanet(Planet p) {
		planets.add(p);
	}
	
	public void removePlanet(String name) {
		planets.remove(findPlanet(name));
	}
	public void removePlanet(Planet p) {
		removePlanet(p.getName());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Planet getPlanet(int index) {
		return planets.get(index);
	}
	//constr
	public SolarSystem(){
		this.setPlanets(new ArrayList<Planet>());
	}
	public SolarSystem(ArrayList<Planet> planets) {
		setPlanets(planets);
	}
	
	//metods
	public void updateCenterOfMass() {
		
	}
	
	public int findPlanet(String name) {
	
		for(int i = 0;i<planets.size();i++) {
			if( planets.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean checkIfMoonExists(String name) {
		for(Planet p : planets) {
			if (p.findMoon(name) > 0) return true;
		}
		return false;
	}
	
	public Planet getPlanetofMoon(String name) {
		if (checkIfMoonExists(name)) {
			for(Planet p : planets) {
				if (p.findMoon(name) > 0) return p;
			}
		}
		return null;
	}
}
