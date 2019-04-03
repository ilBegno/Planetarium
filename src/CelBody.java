
public class CelBody {
	private String id;
	private String name;
	private double mass;
	private Position pos;
	private String orbiting;
	private double radius;
	//We decided to create a class CelBody because we thought that 
	// Constructors
	public CelBody(String id, String name, double mass, Position pos, String orbiting) {
		setId(id);
		setName(name);
		setMass(mass);
		setPos(pos);
		setOrbiting(orbiting);
	}
	public CelBody(String id, String name, double mass, Position pos, String orbiting, double radius) {
		setId(id);
		setName(name);
		setMass(mass);
		setPos(pos);
		setOrbiting(orbiting);
		setRadius(radius);
	}
	
	// Getters and setters
	public String getOrbiting() {
		return orbiting;
	}

	public void setOrbiting(String orbiting) {
		this.orbiting = orbiting;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double d) {
		this.radius = d;
	}

}
