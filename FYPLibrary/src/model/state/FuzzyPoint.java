package model.state;

import java.util.ArrayList;
import java.util.List;

public class FuzzyPoint {

	private FuzzyNumber x = null, y = null, z = null;
	private double horizontalAlignment = 0;
	
	public FuzzyPoint() {
	}
	
	public FuzzyPoint(FuzzyNumber x, FuzzyNumber y, FuzzyNumber z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public FuzzyNumber getX() throws InvalidDimensionException {
		if(x == null) throw new InvalidDimensionException();
		return x;
	}

	public void setX(FuzzyNumber x) {
		this.x = x;
	}

	public FuzzyNumber getY() throws InvalidDimensionException {
		if(y == null) throw new InvalidDimensionException();
		return y;
	}

	public void setY(FuzzyNumber y) {
		this.y = y;
	}

	public FuzzyNumber getZ() throws InvalidDimensionException {
		if(z == null) throw new InvalidDimensionException();
		return z;
	}

	public void setZ(FuzzyNumber z) {
		this.z = z;
	}
	
	public List<FuzzyNumber> getValues() {
		List<FuzzyNumber> l = new ArrayList<FuzzyNumber>();
		if(x != null) l.add(x);
		if(y != null) l.add(y);
		if(z != null) l.add(z);
		
		return l;
	}

	public double getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(double horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}
	
}
