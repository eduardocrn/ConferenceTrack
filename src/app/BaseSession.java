package app;

import java.time.LocalTime;

public class BaseSession {
	
	protected LocalTime startTime;
	
	protected LocalTime endTime;
	
	protected SessionType sessionType;
	
	public BaseSession(LocalTime startTime, LocalTime endTime, SessionType sessionType) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.sessionType = sessionType;
	}
	

}
