package coordinates;

import java.util.LinkedList;

/**
 * An interface to manage the input coordinates of a FiniteStateMachine
 * @author Balazs Pete
 *
 */
public class CoordinateRepository extends LinkedList<Coordinate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8393103281240430459L;

	public final static int DEFAULT_MAXIMUM_SIZE = 20;
	
	private int maximumSize;
	
	/**
	 * Create an instance of CoordinateRepository
	 */
	public CoordinateRepository() {
		maximumSize = DEFAULT_MAXIMUM_SIZE;
	}
	
	/**
	 * Create an instance of CoordinateRepository with a specified maximum size
	 * @param maximumSize The maximum size of the repository
	 */
	public CoordinateRepository(int maximumSize) {
		this.maximumSize = maximumSize;
	}
	
	/**
	 * Add a Coordinate to the repository, making sure it does not overflow
	 * @param coordinate The coordinate to be stores
	 */
	public void store(Coordinate coordinate) {
		add(coordinate);
		if(size() > maximumSize) remove();
	}
	
	public Coordinate getLast() {
		return get(size()-1);
	}
	
	/**
	 * Remove all Coordinates from the repository
	 */
	public void empty() {
		removeRange(0, size());
	}
	
	/**
	 * Get the maximum size of the repository
	 * @return The maximum number of coordinates stored in the object
	 */
	public int maxSize() {
		return maximumSize;
	}
	
	public static void main(String[] args) {
		CoordinateRepository r = new CoordinateRepository();
		int count = 0;
		while(count < 30){
			r.store(new Coordinate(count, count, count));
			System.out.println(count + " " +r.size());
			count++;
		}
	}
}
