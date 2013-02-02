package coordinates;

import java.util.Collection;
import java.util.Set;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;

public class GraphData {
	
	private Map<String, CoordinateSeries> coordinateData;
	
	public GraphData() {
		coordinateData = new HashMap<String, CoordinateSeries>();
		
	}
	
	public void add(String key, CoordinateSeries value) {
		coordinateData.put(key, value);
	}
	
	public void remove(String key) {
		coordinateData.remove(key);
	}
	
	public CoordinateSeries get(String key) {
		return coordinateData.get(key);
	}
	
	public Set<String> getKeys() {
		return coordinateData.keySet();
	}
	
	public Collection<CoordinateSeries> getValues() {
		return coordinateData.values();
	}
	
	public void removeSeriesHighlight() {
		for(CoordinateSeries s : coordinateData.values()) {
			s.setHighlight(false);
		}
	}
	
}
