package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.LoadFile;
import app.Scheduler;

class SchedulerTest {

	@Test
	void schedulerDefaultInputTest() {
		
		String filePath = "src/data/input.txt";
		
		LoadFile lf = new LoadFile();
		
		Scheduler scheduler = new Scheduler(lf.read(filePath));
		
		boolean result = scheduler.sched();
		
		assertEquals(true, result);
	}
	
	@Test
	void schedulerBadConfigTest() {
		
		String filePath = "src/data/input_bad.txt";
		
		LoadFile lf = new LoadFile();
		
		Scheduler scheduler = new Scheduler(lf.read(filePath));
		
		boolean result = scheduler.sched();
		
		assertEquals(false, result);
	}
	
}
