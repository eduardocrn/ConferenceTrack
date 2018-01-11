package app;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Session extends BaseSession {

	private ArrayList<Talk> talks;
	
	private long timeLeft = 0;
	
	private LocalTime currentTime;
	
	public Session (LocalTime startTime, LocalTime endTime, SessionType sessionType) {
		super(startTime, endTime, sessionType);
		
		currentTime = LocalTime.of(startTime.getHour(), startTime.getMinute());
		
		if (sessionType != SessionType.BREAK)
			timeLeft = Duration.between(startTime, endTime).toMinutes();
		
		talks = new ArrayList<>();
	}
	
	public boolean addTalk(Talk talk) {
		// Intervalo não possui talks
		if (sessionType == SessionType.BREAK) {
			return false;
		}
		
		// Verifica se é possível adicionar talk a sessao
		if ((timeLeft - talk.getDuration()) < 0) {
			return false;
		}
		
		// verifica restricao de horario inicio/termino
		if (!talk.timeIsAvailable(currentTime)) {
			return false;
		}
		
		talks.add(talk);
		timeLeft -= talk.getDuration();
		currentTime = currentTime.plusMinutes(talk.getDuration());
		
		return true;
	}
	
	public long getTimeLeft() {
		return timeLeft;
	}
	
	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("hh:mma");
		LocalTime hour = LocalTime.of(startTime.getHour(), startTime.getMinute());
		
		if (sessionType == SessionType.BREAK) {
			buffer.append(hour.format(formatter));
			buffer.append(" Lunch");
			buffer.append(System.lineSeparator());
		} else {
			for (Talk talk : talks) {
				buffer.append(hour.format(formatter));
				buffer.append(" " + talk.toString());
				buffer.append(System.lineSeparator());
				
				hour = hour.plusMinutes(talk.getDuration());
			}
		}
		
		return buffer.toString();
	}
}
