import java.util.ArrayList;

public class CelBody {
	private String id;
	private String name;
	private double mass;
	private Position pos;
	private int numMoons;
	private ArrayList<CelBody> moons;
	private String orbiting;
	//Costruttore
	public CelBody() {	
		pos = new Position();
		moons = new ArrayList<CelBody>();
		orbiting = "Sun";
	}
	public CelBody(CelBody p) {
		pos = new Position();
		numMoons = 0;
		orbiting = p.getName();
	}
	
	//Getters and setters
	public ArrayList<CelBody> getMoons() {
		return moons;
	}
	public void setMoons(ArrayList<CelBody> moons) {
		this.moons = moons;
	}
	public String getOrbiting() {
		return orbiting;
	}
	public void setOrbiting(String orbiting) {
		this.orbiting = orbiting;
	}
	public int getNumMoons() {
		return numMoons;
	}
	public void setNumMoons(int numMoons) {
		this.numMoons = numMoons;
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
	
}
