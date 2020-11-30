package dao;

import java.util.*;
import java.util.UUID;

public class Dealer extends GenericModel{
	
	private String dealerId;
	private String dealerName;
	private Address dealerAddress;
	
	public Dealer(String dealerName, Address dealerAddress) {
		this.dealerId=UUID.randomUUID().toString();
		this.dealerName=Objects.requireNonNull(dealerName,"Dealer's name cannot be null");
		this.dealerAddress=dealerAddress;
	}
	
	public void setDealerName(String dealerName) {
		this.dealerName=Objects.requireNonNull(dealerName,"Dealer's name cannot be null");
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
				+"\n"+this.dealerAddress.toString();
	}

	public String toCSVLine() {
		return this.dealerId + "," + this.dealerName + "," + this.dealerAddress.toCSVLine();
	}
	
	
}
