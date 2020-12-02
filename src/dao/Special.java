package dao;

import java.util.*;

public class Special {
	
	private String specialId;
	private String dealerId;
	private String startDate;
	private String endDate;
	private String title;
	private String description;
	private String disclaimer;
	private String value;
	private String year;
	private String brand;
	private String bodytype;
	private String isNew;
	private String scopeParameter;
	private SpecialScope specialScope;

	public Special() {
		this.specialId ="";
		this.dealerId = "";
		this.startDate = "";
		this.endDate = "";
		this.title = "";
		this.value = "";
		this.year = "";
		this.brand = "";
		this.bodytype = "";
		this.isNew = "";
		this.scopeParameter = "";
		this.specialScope = SpecialScope.ALL;
	}
	
	public Special(String dealerId, String startDate, String endDate, String title, String value) {
		this.specialId=UUID.randomUUID().toString();
		
		this.dealerId=Objects.requireNonNull(dealerId,"DearId should not be null");
		this.startDate=startDate;
		this.endDate=Objects.requireNonNull(endDate,"EndDate should not be null");
		this.title=Objects.requireNonNull(title,"Title should not be null.");
		this.value=Objects.requireNonNull(value,"Value should not be null");
	}

	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId=Objects.requireNonNull(dealerId,"DearId should not be null");
	}
	
	public void setDealerIdFromExistingDealer(Dealer dealer) {
		this.dealerId=Objects.requireNonNull(dealer.getDealerId(),"The input dealer should not be null");
	}
	
	public void setStartDate(String startDate) {
		this.startDate=startDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate=Objects.requireNonNull(endDate,"EndDate should not be null");
	}
	
	public void setTitle(String title) {
		this.title=Objects.requireNonNull(title,"Title should not be null.");
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public void setDisclaimer(String disclaimer) {
		this.disclaimer=disclaimer;
	}
	
	public void setValue(String value) {
		this.value=Objects.requireNonNull(value,"Value should not be null");
	}
	
	public void setYear(String year) {
		this.year=year;
	}
	
	public void setBrand(String brand) {
		this.brand=brand;
	}
	
	public void setBodyType(String bodyType){
		this.bodytype = bodyType;
	}
	
	public void setIsNew(String isNew) {
		this.isNew=isNew;
	}
	
	public void setScope(SpecialScope specialScope) {
		this.specialScope = specialScope;
	}
	
	public void setScopeParameter(String parameter) {
		this.scopeParameter=parameter;
	}
	
	public String getSpecialId() {
		return this.specialId;
	}
	
	public String getDealerId() {
		return this.dealerId;
	}
	
	public String getStartDate() {
		return this.startDate;
	}
	
	public String getEndDate() {
		return this.endDate;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getDisclaimer() {
		return this.disclaimer;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getYear() {
		return this.year;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public String getBodyType() {
		return this.bodytype;
	}
	
	public String isNew() {
		return this.isNew;
	}
	
	public SpecialScope getScope() {
		return this.specialScope;
	}
	
	public String getScopeParameter() {
		return this.scopeParameter;
	}

	/**
	 * Convert this Special to the csv format.
	 * by Tianyu Bai
	 */
	public String toCSVLine(){
		// escape comma and double quotes in title, description and disclaimer
		// other variables of Special should not contain any comma or double quotes
		String csvTitle = title == null ? "\"<ti></ti>\"" : "\"<ti>" + title + "</ti>\"";
		String csvDescription = description == null ? "\"<de></de>\"" : "\"<de>" + description + "</de>\"";
		String csvDisclaimer = disclaimer == null ? "\"<di></di>\"" : "\"<di>" + disclaimer + "</di>\"";

		// convert a Special to csv data
		String row = specialId + ","
				+ dealerId + ","
				+ startDate + ","
				+ endDate + ","
				+ csvTitle + ","
				+ csvDescription + ","
				+ csvDisclaimer + ","
				+ value + ","
				+ year + ","
				+ brand + ","
				+ bodytype + ","
				+ isNew + ","
				+ scopeParameter + ","
				+ specialScope;

		return row;
	}
}
