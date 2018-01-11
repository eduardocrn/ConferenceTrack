package app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadFile {
	
	public ArrayList<Talk> read(String fileName) {
		ArrayList<Talk> talks = new ArrayList<>();
		FileReader fr = null;
		BufferedReader buffered = null;
		
		try {
			buffered = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fileName),
							"UTF-8"));
			
			String line = "";
			String[] args;
			long timeDuration;
			
			while ((line = buffered.readLine()) != null) {
				
				args = line.split(" ");
				
				if (args[args.length-1].equals("lightning")) {
					timeDuration = 5;
				} else {
					timeDuration = Long.parseLong(args[args.length-1].split("min")[0]);
				}
				
				talks.add(new Talk(line, timeDuration));
			}
			
		} catch (IOException ioEx) {
			System.out.println("Verifique se o arquivo informado existe.");
			System.out.println(ioEx.getMessage());
		}  finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					System.out.println("Não foi possível fechar o arquivo " + fileName);
					System.out.println(e.getMessage());
				}
			}
			
			if (buffered != null) {
				try {
					buffered.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
					System.out.println("Não foi possível fechar o arquivo " + fileName);
				}
			}
		}
		
		return talks;
	}
}
