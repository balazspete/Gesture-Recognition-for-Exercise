package coordinates;

import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

/**
 * Class to represent the data of a GraphingCanvas
 * @author Balazs Pete
 *
 */
public class GraphData {
	
	private Map<String, CoordinateSeries> coordinateData;
	
	/**
	 * Create a new instance of GraphData
	 */
	public GraphData() {
		coordinateData = new HashMap<String, CoordinateSeries>();
		
	}
	
	/**
	 * Add a CoordinateSeries to the instance
	 * @param key key of series
	 * @param value series
	 */
	public void add(String key, CoordinateSeries value) {
		coordinateData.put(key, value);
	}
	
	/**
	 * Remove a CoordinateSeries from the instance
	 * @param key key of the series
	 */
	public void remove(String key) {
		coordinateData.remove(key);
	}
	
	/**
	 * Get a specific CoordinateSeries of key [key]
	 * @param key key of the desired CoordinateSeries
	 * @return the Coordinate series, or null if none matching
	 */
	public CoordinateSeries get(String key) {
		return coordinateData.get(key);
	}
	
	/**
	 * Get the set of Keys stored in the instance
	 * @return the set of keys
	 */
	public Set<String> getKeys() {
		return coordinateData.keySet();
	}
	
	/**
	 * Get all CoordinateSeries stored in the instance
	 * @return the Collection of CoordinateSeries
	 */
	public Collection<CoordinateSeries> getValues() {
		return coordinateData.values();
	}
	
	/**
	 * Remove highlighting from all series
	 */
	public void removeSeriesHighlight() {
		for(CoordinateSeries s : coordinateData.values()) {
			s.setHighlight(false);
		}
	}
	
}
