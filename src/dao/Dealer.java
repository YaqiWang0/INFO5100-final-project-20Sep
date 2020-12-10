package dao;

import java.util.*;
import java.util.UUID;

public class Dealer extends GenericModel{
	
	private String dealerId;
	private String dealerName;
	private Address dealerAddress;
	private double distanceInMiles;

	public Dealer() {this.modelType = "dealers"; }

	public Dealer(String dealerId, String dealerName, Address dealerAddress) {
		this.dealerId=dealerId==null ? UUID.randomUUID().toString(): dealerId;
		this.dealerName=Objects.requireNonNull(dealerName,"Dealer's name cannot be null");
		this.dealerAddress=dealerAddress;
		this.modelType="dealers";
	}

	public void setDealerId(String dealerId) {
		this.dealerId=dealerId==null ? UUID.randomUUID().toString(): dealerId;
	}
	
	public void setDealerName(String dealerName) {
		this.dealerName=Objects.requireNonNull(dealerName,"Dealer's name cannot be null");
	}

	public double getDistanceInMiles() {
		return distanceInMiles;
	}

	public void setDistanceInMiles(double distanceInMiles) {
		this.distanceInMiles = distanceInMiles;
	}

	public void setDealerAddress(Address address) {
		this.dealerAddress=address;
	}
	
	public String getDealerId() {
		return this.dealerId;
	}
	
	public String getDealerName() {
		return this.dealerName;
	}
	
	public Address getDealerAddress() {
		return this.dealerAddress;
	}
	
	public String toString() {
		return "Dealer Id is: "+this.dealerId
				+"\nDealer Name is: "+this.dealerName
				+"\n"+this.dealerAddress.toString()
				+"\n" +this.distanceInMiles;
	}

	@Override
	public String toCSVLine() {
		return this.dealerId + "," + this.dealerName + "," + this.dealerAddress.toCSVLine();
	}
	
	
}
