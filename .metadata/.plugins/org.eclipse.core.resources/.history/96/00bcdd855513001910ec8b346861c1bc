package hmo.project;

import java.util.ArrayList;
import java.util.List;

public class Lane {
	
	private int id;
	private int series = 0;
	private double length;
	private double currentLength = 0;
	private Boolean blocked = false;
	private List<Lane> blockedBy;
	private List<Vehicle> vehicles;


	public Lane() {
		super();
	}

	public Lane(int id) {
		super();
		this.id = id;
		blockedBy = new ArrayList<>();
		vehicles = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Boolean getBlocked() {
		return blocked;
		
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public List<Lane> getBlockedBy() {
		return blockedBy;
	}
	
	public double getCurrentLength() {
		return currentLength;
	}

	public void setCurrentLength(double currentLength) {
		this.currentLength = currentLength;
	}
	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

//	public void setBlockedBy(List<Lane> blockedBy) {
//		this.blockedBy = blockedBy;
//	}
	
	@Override
	public String toString() {
		return String.format("Lane id: %d, length: %f, blocked: %b	" ,
				this.getId(),
				this.getLength(),
				this.getBlocked());
	}
	
	
}
