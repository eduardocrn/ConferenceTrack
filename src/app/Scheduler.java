package app;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Scheduler {
	
	private ArrayList<Talk> talks;
	
	private ArrayList<Track> tracks;
	
	public Scheduler(ArrayList<Talk> talks) {
		this.talks = talks;
	}
	
	// algoritmo guloso, baseado em first fit, encaixa talk no primeiro horario que encontra
	public boolean sched() {
		
		Talk netWorking = new Talk("Networking Event", 5);
		netWorking.setTimeRestriction(LocalTime.of(16, 0), LocalTime.of(17, 0));
		
		tracks = new ArrayList<>();
		
		tracks.add(new Track(1, LocalTime.of(9, 0), LocalTime.of(17, 0)));
		tracks.add(new Track(2, LocalTime.of(9, 0), LocalTime.of(17, 0)));
		
		// talks de duracao maior sao processadas primeiro, balancear carga nas tracks
		Collections.sort(talks, Collections.reverseOrder());
		
		int talkIndex = 0;
		
		Talk talk = null;
		
		while (talks.size() > 0) {
			
			talk = talks.get(talkIndex);
			
			// Prioriza track que tem mais tempo disponivel
			Collections.sort(tracks, Collections.reverseOrder());
			
			for (Track track : tracks) {
				
				if (track.addTalk(talk)) {
					talks.remove(talkIndex);	
					break;
				}
				else {
					talkIndex++;
					continue;
				}
			}
			
			if (talkIndex >= talks.size()) {
				talkIndex = 0;
			}
			
		}
		
		if (!tracks.get(0).addTalk(netWorking) || !tracks.get(1).addTalk(netWorking) ) {
			System.out.println("Time restriction problem, networking event no added");
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		for (Track track : tracks) {
			buffer.append(track.toString());
			buffer.append(System.lineSeparator());
		}
		
		return buffer.toString();
	}
	
}
