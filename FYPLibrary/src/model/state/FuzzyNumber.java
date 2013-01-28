package model.state;

public class FuzzyNumber {

	private double value;
	private double error;
	
	public FuzzyNumber(double value) {
		this.value = value;
		this.error = 0;
	}
	
	public FuzzyNumber(double value, double error) {
		this.value= value;
		this.error = error;
	}
	
	public double getValue() {
		return value;
	}
	
	public double getError() {
		return error;
	}
	
	public boolean isVithin(double number) {
		return isVithin(number, 0);
	}
	
	public boolean isVithin(FuzzyNumber number) {
		return isVithin(number.getValue(), number.getError());
	}
	
	private boolean isVithin(double v, double e) {
		double err = error + e;
		if(value - err <= v && v <= value + err) {
			return true;
		}
		return false;
	}
	
}
