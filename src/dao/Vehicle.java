package dao;

import java.util.*;
import java.util.UUID;

public class Vehicle extends GenericModel{
	
	private String vehicleId;
	private String dealerId;
	private String year;
	private String brand;
	private String model;
	private boolean isNew;
	private String price;
	private String exteriorColor;
	private String interiorColor;
	private BodyType bodyType;
	private String miles;
	private ArrayList<String> features;
	private ArrayList<String> imgUrls;

	public Vehicle() { this.modelType = "vehicles"; }
	
	public Vehicle(String dealerId) {
		this.dealerId=Objects.requireNonNull(dealerId,"Dealer's Id cannot be null");
		this.vehicleId=UUID.randomUUID().toString();
		this.features=new ArrayList<>();
		this.imgUrls=new ArrayList<>();
		this.modelType="vehicles";
	}
	
	public Vehicle(String dealerId, String year, 
			String brand, String model, boolean isNew, 
			String price, String exteriorColor, 
			String interiorColor, BodyType bodyType, 
			String miles) {
		this.dealerId=Objects.requireNonNull(dealerId,"Dealer's Id cannot be null");
		this.vehicleId=UUID.randomUUID().toString();
		this.year=year;
		this.brand=brand;
		this.model=model;
		this.isNew=isNew;
		this.price=price;
		this.exteriorColor=exteriorColor;
		this.interiorColor=interiorColor;
		this.bodyType=bodyType;
		this.miles=miles;
		this.features=new ArrayList<>();
		this.imgUrls=new ArrayList<>();
		this.modelType="vehicles";
	}
	
	public void setVehicleId(String vehicleId) {
		this.vehicleId=vehicleId;
	}
	
	public void setYear(String year) {
		this.year=year;
	}
	
	public void setBrand(String brand) {
		this.brand=brand;
	}
	
	public void setModel(String model) {
		this.model=model;
	}
	
	public void setIsNew(boolean isNew) {
		this.isNew=isNew;
	}
	
	public void setPrice(String price) {
		this.price=price;
	}
	
	public void setExteriorColor(String color) {
		this.exteriorColor=color;
	}
	
	public void setInteriorColor(String color) {
		this.interiorColor=color;
	}
	
	public void setBodyType(BodyType bodytype) {
		this.bodyType=bodytype;
	}
	
	public void addFeatures(String feature) {
		this.features.add(feature);
	}
	
	public void setMiles(String miles) {
		this.miles=miles;
	}
	
	public void addImgUrl(String imgUrl) {
		this.imgUrls.add(imgUrl);
	}
	
	public String getVehicleId() {
		return this.vehicleId;
	}
	
	public String getDealerId() {
		return this.dealerId;
	}
	
	public String getYear() {
		return this.year;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public boolean getStatus() {
		return this.isNew;
	}
	
	public String getPrice() {
		return this.price;
	}
	
	public String getExteriorColor() {
		return this.exteriorColor;
	}
	
	public String getInteriorColor() {
		return this.interiorColor;
	}
	
	public BodyType getBodyType() {
		return this.bodyType;
	}
	
	public ArrayList<String> getFeatures(){
		return this.features;
	}
	
	public String getMiles() {
		return this.miles;
	}
	
	public ArrayList<String> getImg(){
		return this.imgUrls;
	}
	
	public String getFeatureDetails() {
		if(features.isEmpty()) {
			return "The feature list is empty. Waiting for more details";
		}
		String result="";
		
		for(String feature: features) {
			result=result+'\t'+feature;
		}
		return result;
	}

	public String toCSVLine() {
		String featuresString = String.join("\t", features);
		String imgUrlsString = String.join("\t", imgUrls);

		return vehicleId+","+dealerId+","+year+","+brand+","+model+","+ isNew +","+price+","+exteriorColor
				+","+interiorColor+","+bodyType+","+miles+","+featuresString+","+imgUrlsString;
	}
}
