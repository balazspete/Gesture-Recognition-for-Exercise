package events.event;

import java.util.EventObject;

public class LimitToGestureEvent extends EventObject {
	
	private String gesture;
	
	public LimitToGestureEvent(Object source, String gesture) {
		super(source);
		this.gesture = gesture;
	}
	
	public String getGesture() {
		return gesture;
	}

}
