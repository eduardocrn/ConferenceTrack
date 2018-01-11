package app;

public class Main {

	public static void main(String[] args) {
		
		String filePath;
		
		if (args.length > 0) {
			filePath = args[0];
		} else {
			filePath = "src/data/input.txt";
		}
		
		LoadFile lf = new LoadFile();
		
		Scheduler scheduler = new Scheduler(lf.read(filePath));
		
		scheduler.sched();
		
		System.out.println(scheduler);

	}

}
