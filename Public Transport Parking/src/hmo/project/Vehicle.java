package hmo.project;


public class Vehicle {
	
	private int id;
	private double length;
	private int serie;
	private int timeOfDeparture;
	private int layoutType;
	private int numberOfZeros = 0;
	
	

	public Vehicle() {
		super();
	}
	
	public Vehicle(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getLength() {
		return length;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public int getSerie() {
		return serie;
	}
	
	public void setSerie(int serie) {
		this.serie = serie;
	}
	
	public int getTimeOfDeparture() {
		return timeOfDeparture;
	}

	public void setTimeOfDeparture(int timeOfDeparture) {
		this.timeOfDeparture = timeOfDeparture;
	}

	public int getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(int layoutType) {
		this.layoutType = layoutType;
	}
	
	public int getNumberOfZeros() {
		return numberOfZeros;
	}

	public void setNumberOfZeros(int numberOfZeros) {
		this.numberOfZeros = numberOfZeros;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d,l: %.1f, s: %d, tod: %d, lt: %d",
				this.getId(),
				this.getLength(),
				this.getSerie(),
				this.getTimeOfDeparture(),
				this.getLayoutType());
	}
	
}
