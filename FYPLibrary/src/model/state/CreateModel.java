package model.state;

import java.util.Vector;

public class CreateModel {

	private Vector<FuzzyPoint> points;
	private double unit;
	
	public CreateModel(Vector<FuzzyPoint> points) throws InsufficentModelDataError {
		if(points.size() < 2) throw new InsufficentModelDataError();
		this.points = points;
	}
	
	public void calculate() {
		
	}
	
	private double calculateUnit() {
		
		
		return 0;
	}
	
}
