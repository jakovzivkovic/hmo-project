package hmo.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		
		String file = "/Users/Jakov/Documents/Faks/Diplomski/3. semestar/HMO/hmo-project/Public Transport Parking/resources/instanca1"
				+ ".txt";
		Scanner scanner = new Scanner(new File(file));
		parseInstanceFile(scanner);
		parkingAlgorithm();
		scanner.close();
	
		int i=0;
		for (Lane lane : lanes){
			Collections.sort(lane.getVehicles(), new Comparator<Vehicle>() {

		        public int compare(Vehicle v1, Vehicle v2) {
					return Integer.valueOf(v2.getTimeOfDeparture()).compareTo(Integer.valueOf(v1.getTimeOfDeparture()));
		        }
		    });
			System.out.printf("%d.  ",i+1);
			System.out.print(lane.getVehicles());
			System.out.printf("remaing: %.1f\n", lane.getLength()-lane.getCurrentLength());
			
			i++;
		}
	}

	private static void parkingAlgorithm() {
		// TODO Auto-generated method stub
		List<Vehicle> sortedByTime = new ArrayList<>();
		List<Vehicle> remaining = new ArrayList<>();
		List<Vehicle> remove = new ArrayList<>();
		
		sortedByTime.addAll(vehicles);
		remaining.addAll(vehicles);
		
		
		Collections.sort(sortedByTime, new Comparator<Vehicle>() {

	        public int compare(Vehicle v1, Vehicle v2) {
				return Integer.valueOf(v2.getTimeOfDeparture()).compareTo(Integer.valueOf(v1.getTimeOfDeparture()));
	        }
	    });
		
		
		for( Vehicle vehicle: sortedByTime){
			for(Lane lane : lanes){
				if(lane.getBlocked()){
					if(matrix[vehicle.getId()-1][lane.getId()-1] == 1){
						if(lane.getCurrentLength() == 0){
							if(lane.getCurrentLength() + vehicle.getLength() <= lane.getLength() ){
									lane.setSeries(vehicle.getSerie());
									lane.setCurrentLength(vehicle.getLength());
									lane.getVehicles().add(vehicle);
									remove.add(vehicle);
									break;
								
							}else{
								continue;
							}
						}else{
							if(lane.getCurrentLength() + vehicle.getLength() + 0.5 <= lane.getLength() && vehicle.getSerie() == lane.getSeries()){
								lane.setCurrentLength(lane.getCurrentLength() + vehicle.getLength() + 0.5);
								lane.getVehicles().add(vehicle);
								remove.add(vehicle);
								break;
							}else{
								continue;
							}
						}
					}else{
						continue;
					}
				}else{
					
				}
			}
				
		}
		 
		remaining.removeAll(remove);
		
		Collections.sort(remaining, new Comparator<Vehicle>() {
	        public int compare(Vehicle v1, Vehicle v2) {
	        	int value1 = Integer.valueOf(v2.getNumberOfZeros()).compareTo(Integer.valueOf(v1.getNumberOfZeros()));
	            if (value1 == 0) {
	                int value2 = Integer.valueOf(v2.getTimeOfDeparture()).compareTo(Integer.valueOf(v1.getTimeOfDeparture()));;
	                return value2;
	            }
	            return value1;
	        }
	    });
		

		for(Vehicle vehicle : remaining){
			for (Lane lane : lanes) {
				if(lane.getBlocked() == false){
					if(matrix[vehicle.getId()-1][lane.getId()-1] == 1){
						if(lane.getCurrentLength() == 0){
							if(lane.getCurrentLength() + vehicle.getLength() <= lane.getLength() ){
									lane.setSeries(vehicle.getSerie());
									lane.setCurrentLength(vehicle.getLength());
									lane.getVehicles().add(vehicle);
									break;
								
							}else{
								continue;
							}
						}else{
							if(lane.getCurrentLength() + vehicle.getLength() + 0.5 <= lane.getLength() && vehicle.getSerie() == lane.getSeries()){
								lane.setCurrentLength(lane.getCurrentLength() + vehicle.getLength() + 0.5);
								lane.getVehicles().add(vehicle);
								break;
							}else{
								continue;
							}
						}
					}else{
						continue;
					}
				}
			}
		}
		
		
	}

	private static void parseInstanceFile(Scanner scanner) {
		
		numberOfVehicles = Integer.valueOf(scanner.nextLine());
		numberOfLanes = Integer.valueOf(scanner.nextLine());
		
		double[] vehiclesLengths  =new double[numberOfVehicles];
		for (int i=0; i < numberOfVehicles; i++){
			vehiclesLengths[i] = Double.valueOf(scanner.next());
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
				if(matrix[i][j]==0){
					vehicles.get(i).setNumberOfZeros(vehicles.get(i).getNumberOfZeros()+1);
				}
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
