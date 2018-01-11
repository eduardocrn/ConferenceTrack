package app;

import java.time.LocalTime;
import java.util.ArrayList;

public class Track implements Comparable<Track> {
	
	private int id;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private ArrayList<Session> sessions;
	
	public Track (int id, LocalTime startTime, LocalTime endTime) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		sessions = new ArrayList<>();
		createSessions();
	}
	
	private void createSessions() {
		sessions.add(new Session(startTime, LocalTime.of(12, 0), SessionType.MORNING));
		sessions.add(new Session(LocalTime.of(12, 0), LocalTime.of(13, 0), SessionType.BREAK));
		sessions.add(new Session(LocalTime.of(13, 0), endTime, SessionType.AFTERNOON));
	}
	
	public boolean addTalk(Talk talk) {
		
		for (Session session : sessions) {
			if (session.addTalk(talk)) {	
				return true;
			}
		}
		
		return false;
	}
	
	public long getAvailableTime() {
		long timeLeft = 0;
		
		for (Session session : sessions) {
			timeLeft += session.getTimeLeft();
		}
		
		return timeLeft;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("Track ");
		buffer.append(id);
		buffer.append(System.lineSeparator());
		for (Session session : sessions) {
			buffer.append(session.toString());
		}
		buffer.append(System.lineSeparator());
		
		return buffer.toString();
	}

	@Override
	public int compareTo(Track other) {
		return (int) (getAvailableTime() - other.getAvailableTime());
	}

}
