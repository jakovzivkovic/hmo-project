package hmo.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String file = "/Users/Jakov/Documents/Faks/Diplomski/3. semestar/HMO/hmo-project/Public Transport Parking/resources/instanca1.txt";
		Scanner scanner = new Scanner(new File(file));
		int numberOfVehicles = Integer.valueOf(scanner.nextLine());
		//System.out.println(numberOfVehicles);
		int numberOfLanes = Integer.valueOf(scanner.nextLine());
		//System.out.println(numberOfLanes);
		int[] vehiclesLengths  =new int[numberOfVehicles];
		for (int i=0; i<numberOfVehicles; i++){
			vehiclesLengths[i] = Integer.valueOf(scanner.next());
			//System.out.println(vehiclesLengths[i]);
		}
		int[] vehiclesSeries = new int[numberOfVehicles];
		for (int i=0; i<numberOfVehicles; i++){
			vehiclesSeries[i] = Integer.valueOf(scanner.next());
			//System.out.println(vehiclesSeries[i]);
		}
		int[][] matrix = new int[numberOfVehicles][numberOfLanes];
		for(int i = 0; i < numberOfVehicles; i++){
			for(int j=0; j < numberOfLanes;j ++){
				matrix[i][j] = Integer.valueOf(scanner.next());
				//System.out.print(matrix[i][j]);
			}
				//System.out.println("");
		}
		int[] laneLengths = new int[numberOfLanes];
		for(int i = 0; i < numberOfLanes; i++){
			laneLengths[i] = Integer.valueOf(scanner.next());
			//System.out.println(laneLengths[i]);
		}
		int[] vehiclesTimeOfDeparture = new int[numberOfVehicles];
		for (int i=0; i<numberOfVehicles; i++){
			vehiclesTimeOfDeparture[i] = Integer.valueOf(scanner.next());
			//System.out.println(vehiclesTimeOfDeparture[i]);
		}
		int[] vehiclesLayoutType = new int[numberOfVehicles];
		for (int i=0; i<numberOfVehicles; i++){
			vehiclesLayoutType[i] = Integer.valueOf(scanner.next());
			//System.out.println(vehiclesLayoutType[i]);
		}
		scanner.nextLine();
		scanner.nextLine();
		Map<Integer,List<Integer>> blockedTracks = new HashMap<>(); 
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] splited = line.split(" ");
			
			for (int i = 1; i < splited.length; i++ ){
				if (blockedTracks.containsKey(Integer.valueOf(splited[i])) ){
					List<Integer> list = blockedTracks.get(Integer.valueOf(splited[i]));
					list.add(Integer.valueOf(splited[0]));
					blockedTracks.put(Integer.valueOf(splited[i]), list);	
				}else{
					List<Integer> list = new ArrayList<>();
					list.add(Integer.valueOf(splited[0]));
					blockedTracks.put(Integer.valueOf(splited[i]), list);
				}
			}
		}
		for(Integer rez : blockedTracks.get(Integer.valueOf(15))){
			System.out.println(rez);
		}
		System.out.println(blockedTracks.keySet());
		
		
		
		
		scanner.close();
	}

}
