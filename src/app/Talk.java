package app;

import java.time.LocalTime;

public class Talk implements Comparable<Talk> {
	
	private String name;
	
	private long duration;
	
	private LocalTime startTimeRestriction;
	
	private LocalTime endTimeRestriction;
	
	public Talk(String name, long timeDuration) {
		this.name = name;
		this.duration = timeDuration;
		this.startTimeRestriction = LocalTime.of((int) Config.INIT_TIME, 0);
		this.endTimeRestriction = LocalTime.of((int) Config.END_TIME, 0);
	}
	
	public String getName() {
		return name;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public void setTimeRestriction(LocalTime start, LocalTime end) {
		startTimeRestriction = start;
		endTimeRestriction = end;
	}
	
	public boolean timeIsAvailable(LocalTime currentTime) {
		LocalTime endTime = LocalTime.of(currentTime.getHour(), currentTime.getMinute());
		
		return (currentTime.isAfter(startTimeRestriction) || currentTime.equals(startTimeRestriction))  
				&& (endTime.isBefore(endTimeRestriction) || endTimeRestriction.equals(endTime) );	
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Talk other) {
		return (int) (duration - other.duration);
	}

}
