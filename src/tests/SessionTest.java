package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import app.Session;
import app.SessionType;
import app.Talk;

class SessionTest {

	@Test
	void sessionEmptyTimeLeft() {
		Session s = new Session(LocalTime.of(10, 0), LocalTime.of(12, 0), SessionType.MORNING);
		
		assertEquals(120, s.getTimeLeft());
	}
	
	@Test
	void sessionFullTimeLeft() {
		Session s = new Session(LocalTime.of(10, 0), LocalTime.of(12, 0), SessionType.MORNING);
		
		s.addTalk(new Talk("T1", 60));
		s.addTalk(new Talk("T2", 30));
		s.addTalk(new Talk("T3", 30));
		
		assertEquals(0, s.getTimeLeft());
	}
	
	@Test
	void breakSessionTest() {
		Session s = new Session(LocalTime.of(15, 0), LocalTime.of(15, 45), SessionType.BREAK);
		
		boolean result = s.addTalk(new Talk("T1", 10));
		
		assertEquals(false, result);
	}

}
