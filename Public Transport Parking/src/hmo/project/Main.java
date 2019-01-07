package hmo.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static List<Vehicle> vehicles = new ArrayList<>();
	public static List<Lane> lanes = new ArrayList<>();
	public static int numberOfVehicles;
	public static int numberOfLanes;
	public static Map<Integer,List<Integer>> blockedTracks;
	public static int[][] matrix;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String file = "/Users/Jakov/Documents/Faks/Diplomski/3. semestar/HMO/hmo-project/Public Transport Parking/resources/instanca1.txt";
		Scanner scanner = new Scanner(new File(file));
		parseInstanceFile(scanner);
		scanner.close();
//		for (Vehicle vehicle : vehicles){
//			System.out.println(vehicle);
//		}
		for (Lane lane : lanes){
			System.out.print(lane);
			System.out.println(lane.getBlockedBy());
		}
	}

	private static void parseInstanceFile(Scanner scanner) {
		
		numberOfVehicles = Integer.valueOf(scanner.nextLine());
		numberOfLanes = Integer.valueOf(scanner.nextLine());
		
		int[] vehiclesLengths  =new int[numberOfVehicles];
		for (int i=0; i < numberOfVehicles; i++){
			vehiclesLengths[i] = Integer.valueOf(scanner.next());
			Vehicle vehicle = new Vehicle(i+1);
			vehicle.setLength(vehiclesLengths[i]);
			vehicles.add(vehicle);
		}
		
		int[] vehiclesSeries = new int[numberOfVehicles];
		for (int i=0; i<numberOfVehicles; i++){
			vehiclesSeries[i] = Integer.valueOf(scanner.next());
			Vehicle vehicle = vehicles.get(i);
			vehicle.setSerie(vehiclesSeries[i]);
			vehicles.set(i, vehicle);
		}
		
		matrix = new int[numberOfVehicles][numberOfLanes];
		for(int i = 0; i < numberOfVehicles; i++){
			for(int j=0; j < numberOfLanes;j ++){
				matrix[i][j] = Integer.valueOf(scanner.next());
			}
		}
		int[] laneLengths = new int[numberOfLanes];
		for(int i = 0; i < numberOfLanes; i++){
			laneLengths[i] = Integer.valueOf(scanner.next());
			Lane lane = new Lane(i+1);
			lane.setLength(laneLengths[i]);
			lanes.add(lane);
		}
		
		int[] vehiclesTimeOfDeparture = new int[numberOfVehicles];
		for (int i=0; i<numberOfVehicles; i++){
			vehiclesTimeOfDeparture[i] = Integer.valueOf(scanner.next());
			Vehicle vehicle = vehicles.get(i);
			vehicle.setTimeOfDeparture(vehiclesTimeOfDeparture[i]);
			vehicles.set(i, vehicle);
		}
		
		int[] vehiclesLayoutType = new int[numberOfVehicles];
		for (int i=0; i<numberOfVehicles; i++){
			vehiclesLayoutType[i] = Integer.valueOf(scanner.next());
			Vehicle vehicle = vehicles.get(i);
			vehicle.setLayoutType(vehiclesLayoutType[i]);
			vehicles.set(i, vehicle);
		}
		scanner.nextLine();
		scanner.nextLine();
		blockedTracks = new HashMap<>(); 
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] splited = line.split(" ");
			
			for (int i = 1; i < splited.length; i++ ){
				Lane lane = lanes.get(Integer.valueOf(splited[i])-1);
				if(lane.getBlocked() == false){
					lane.setBlocked(true);
				}
				lane.getBlockedBy().add(lanes.get(Integer.valueOf(splited[0])-1));
				lanes.set(Integer.valueOf(splited[i])-1, lane);
				
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
		
	}

}
