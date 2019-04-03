import java.util.ArrayList;

public class SolarSystem {
	// attr
	private String name;
	private String sunName;
	private double sunMass;
	private ArrayList<Planet> planets;
	private int currentId = 0;
	private Position centerOfMass;

	// g & s
	public ArrayList<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(ArrayList<Planet> planets) {
		this.planets = planets;
	}

	public boolean addPlanet(Planet p) {
		if (findPlanet(p.getId()) == -1) {// se non esiste un altro pianeta con lo stesso Id
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

	public String getSunName() {
		return sunName;
	}

	public void setSunName(String sunName) {
		this.sunName = sunName;
	}

	public double getSunMass() {
		return sunMass;
	}

	public void setSunMass(double sunMass) {
		this.sunMass = sunMass;
	}

	// constr
	public SolarSystem() {
		this.setPlanets(new ArrayList<Planet>());
		Position pos = new Position(0, 0);
		setCenterOfMass(pos);
	}

	public SolarSystem(ArrayList<Planet> planets) {
		setPlanets(planets);
	}

	// methods
	public int findPlanet(String id) {
		for (int i = 0; i < planets.size(); i++) {
			if (planets.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public int findPlanetbyName(String name) {
		for (int i = 0; i < planets.size(); i++) {
			if (planets.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;

	}

	public Planet findPlanetofMoon(String name) {
		for (Planet p : planets) {
			for (int i = 0; i < p.getMoons().size(); i++)
				if (p.getMoons().get(i).getName().equals(name))
					return p;
		}
		return null;

	}

	public boolean checkIfPlanetExists(String name) {
		if (findPlanetbyName(name) >= 0)
			return true;
		return false;
	}

	public boolean checkIfMoonExists(String name) {
		for (Planet p : planets) {
			if (p.findMoon(name) >= 0)
				return true;
		}
		return false;
	}

	public CelBody getMoonInSistem(String name) {
		if (checkIfMoonExists(name))
			return findPlanetofMoon(name).getMoons().get(findPlanetofMoon(name).findMoon(name));
		return null;
	}

	public Planet getPlanetofMoon(String name) {
		if (checkIfMoonExists(name)) {
			for (Planet p : planets) {
				if (p.findMoon(name) >= 0)
					return p;
			}
		}
		return null;
	}

	public void calcCoF() {
		double totalMass = 0;
		Position cof;
		double sumX = 0;
		double sumY = 0;
		for (Planet p : planets) {
			totalMass = totalMass + p.getMass();
			sumX = p.getPos().getX() * p.getMass();
			sumY = p.getPos().getY() * p.getMass();
			for (CelBody m : p.getMoons()) {
				totalMass = totalMass + m.getMass();
				sumX = m.getPos().getX() * m.getMass();
				sumY = m.getPos().getY() * m.getMass();
			}

		}
		totalMass += getSunMass();
		cof = new Position(sumX / totalMass, sumY / totalMass);
		setCenterOfMass(cof);
	}

	//metods for the route
	public void fromPlanetToMoon(String start, String end) {
		double sum = 0;
		if (findPlanetofMoon(end).getName().equals(start)) {
			sum = getMoonInSistem(end).getRadius();

			System.out.printf("Il percorso univoco da %s a %s e'" + "%n%s - %s"
					+ "%nLa luna %s orbita intorno al pianeta %s" + "%nLo spazio percorso e' %f", start, end, start,
					end, end, start, sum);
		} else {
			sum = planets.get(findPlanetbyName(end)).getRadius() + findPlanetofMoon(end).getRadius()
					+ getMoonInSistem(end).getRadius();

			System.out.printf(
					"Il percorso univoco da %s a %s e'" + "%n%s - %s - %s - %s" + "%nLo spazio percorso e' %f", start,
					end, start, sunName, findPlanetofMoon(end).getName(), end, sum);
		}

	}

	public void fromPlanetToPlanet(String start, String end) {
		double sum = planets.get(findPlanetbyName(start)).getRadius() + planets.get(findPlanetbyName(end)).getRadius();
		System.out.printf("Il percorso univoco da %s a %s e'" + "%n%s - %s - %s" + "%nLo spazio percorso e' %f", start,
				end, start, sunName, end, sum);

	}

	public void fromMoonToMoon(String start, String end) {
		double sum = 0;
		if (findPlanetofMoon(start).getName().equals(findPlanetofMoon(end).getName())) {
			sum = getMoonInSistem(start).getRadius() + getMoonInSistem(end).getRadius();
			System.out.printf("Il percorso univoco da %s a %s e'" + "%n%s - %s - %s" + "%nLo spazio percorso e' %f",
					start, end, start, findPlanetofMoon(start).getName(), end, sum);

		} else
			sum = getMoonInSistem(start).getRadius() + findPlanetofMoon(start).getRadius()
					+ findPlanetofMoon(end).getRadius() + getMoonInSistem(end).getRadius();
		System.out.printf(
				"Il percorso univoco da %s a %s e'" + "%n%s - %s - %s - %s - %s" + "%nLo spazio percorso e' %f", start,
				end, start, findPlanetofMoon(start).getName(), sunName, findPlanetofMoon(end).getName(), end, sum);
	}

	public void fromMoonToPlanet(String start, String end) {
		double sum = 0;
		if (findPlanetofMoon(start).getName().equals(end)) {
			sum = getMoonInSistem(start).getRadius();
			System.out.printf("Il percorso univoco da %s a %s e'" + "%n%s - %s"
					+ "%nLa luna %s orbita intorno al pianeta %s" + "%nLo spazio percorso e' %f", start, end, start,
					end, start, end, sum);
		} else {
			sum = getMoonInSistem(start).getRadius() + getPlanetofMoon(start).getRadius()+ getPlanet(findPlanetbyName(end)).getRadius();
			System.out.printf("Il percorso univoco da %s a %s e'" + "%n%s - %s - %s - %s" + "%nLo spazio percorso e' %f", start, end, start,
					findPlanetofMoon(start).getName(), sunName, end, sum);
		}
	}

}
