package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import app.Talk;

class TalkTest {

	@Test
	void talkStartEndTimeRestriction() {
		
		Talk t = new Talk("Test talk", 30);
		t.setTimeRestriction(LocalTime.of(14, 0), LocalTime.of(15, 0));
		
		boolean result = t.timeIsAvailable(LocalTime.of(13, 55));
		
		assertEquals(false, result);
	}
	
	@Test
	void talkStartEndTimeRestrictionOk() {
		
		Talk t = new Talk("Test talk", 30);
		t.setTimeRestriction(LocalTime.of(14, 0), LocalTime.of(15, 0));
		
		boolean result = t.timeIsAvailable(LocalTime.of(14, 25));
		
		assertEquals(true, result);
	}
	


}
