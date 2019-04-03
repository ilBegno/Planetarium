public class Position {
	private double x;
	private double y;

	// constructors
	public Position() {
	}

	public Position(double x, double y) {
		setX(x);
		setY(y);
	}

	// g & s
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
