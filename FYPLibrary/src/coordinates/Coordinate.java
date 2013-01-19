package coordinates;

public class Coordinate {

	private double x, y, z;
	
	public Coordinate(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z= z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
	public double getRoundX() {
		return round(x);
	}

	public double getRoundY() {
		return round(y);
	}

	public double getRoundZ() {
		return round(z);
	}
	
	public String toString(){
		return "" + x + "  " + y + "  " + z + "";
	}
	
	private double round(double d) {
		return (int) Math.round(d * 100) / 100.0;
	}
}
