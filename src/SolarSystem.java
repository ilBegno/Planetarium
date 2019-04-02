import java.util.ArrayList;

public class SolarSystem {
	//attr
	private String name;
	private ArrayList<Planet> planets;
	private int currentId = 0;
	private Position centerOfMass;
	
	
	//g & s
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	public void setPlanets(ArrayList<Planet> planets) {
		this.planets = planets;
	}
	public boolean addPlanet(Planet p) {
		if (findPlanet(p.getId())== -1) {// se non esiste un altro pianeta con lo stesso Id
			planets.add(p);
			return true;
		}
		return false;	
	}
	
	public void removePlanet(String name) {
		planets.remove(findPlanetbyName(name));
	}
	public void removePlanet(Planet p) {
		removePlanet(p.getId());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public Position getCenterOfMass() {
		return centerOfMass;
	}
	public void setCenterOfMass(Position centerOfMass) {
		this.centerOfMass = centerOfMass;
	}
	
	public Planet getPlanet(int index) {
		return planets.get(index);
	}
	
	public int getCurrentId() {
		return currentId;
	}
	public void incrId() {
		this.currentId++;
	}
	
	//constr
	public SolarSystem(){
		this.setPlanets(new ArrayList<Planet>());
		Position pos = new Position(0, 0);
		setCenterOfMass(pos);
	}
	public SolarSystem(ArrayList<Planet> planets) {
		setPlanets(planets);
	}
	
	//metods
	public int findPlanet(String id) {
		for(int i = 0;i<planets.size();i++) {
			if( planets.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	public int findPlanetbyName(String name) {
		for(int i = 0; i < planets.size(); i++) {
			if( planets.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
		
	}
	
	public Planet findPlanetofMoon(String name) {
		for (Planet p: planets) {
			for(int i = 0; i < p.getMoons().size(); i++)
				if(p.getMoons().get(i).getName().equals(name)) return p;
		}		
		return null;
			
	}
	public boolean checkIfMoonExists(String name) {
		for(Planet p : planets) {
			if (p.findMoon(name) >= 0) return true;
		}
		return false;
	}
	
	public Planet getPlanetofMoon(String name) {
		if (checkIfMoonExists(name)) {
			for(Planet p : planets) {
				if (p.findMoon(name) >= 0) return p;
			}
		}
		return null;
	}
	
	public void calcCoF() {
		double totalMass = 0;
		Position cof;
		double sumX = 0;
		double sumY = 0;
		for(Planet p: planets) {
			totalMass = totalMass + p.getMass();
			sumX = p.getPos().getX() * p.getMass();
			sumY = p.getPos().getY() * p.getMass();
			for (CelBody m: p.getMoons()) {
				totalMass = totalMass + m.getMass();
				sumX = m.getPos().getX() * m.getMass();
				sumY = m.getPos().getY() * m.getMass();
			}
				
		}
		cof = new Position(sumX/totalMass, sumY/totalMass);
		setCenterOfMass(cof);
	}
	

}
