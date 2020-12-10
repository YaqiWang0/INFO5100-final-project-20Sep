package dao;

import java.util.*;

public class Special {
	
	private String specialId;
	private Date startDate;
	private Date endDate;
	private String title;
	private String description;
	private String disclaimer;
	private int discountValue;
	private int discountPercent;
	private boolean isValidOnCashPayment;
	private boolean isValidOnLoan;
	private boolean isValidOnLease;
	private boolean isValidOnCheckPayment;

	private String dealerId;

	private String valueOfVehicle;
	private String year;
	private String isNew;
	private String brand;
	private String model;
	private String scopeMiles;
	//stores the list of id's of qualifying vehicles
	private List<String> specialScope; // use single word only for parsing purposes in data persistence


	public Special() {
		this.specialId=UUID.randomUUID().toString();
		this.title = "";
		this.valueOfVehicle = "";
		this.year = "";
		this.brand = "";
		this.model = "";
		this.isNew = "";
		this.scopeMiles = "";
		this.specialScope = new ArrayList<>();
		this.specialId=UUID.randomUUID().toString();
		this.discountValue = 0;
		this.discountPercent = 0;
		this.isValidOnCashPayment = false;
		this.isValidOnCheckPayment = false;
		this.isValidOnLoan = false;
		this.isValidOnLease = false;
	}

	public Special(String dealerId, Date startDate, Date endDate, String title, String valueOfVehicle) {
		this.specialId=UUID.randomUUID().toString();
		
		this.dealerId=Objects.requireNonNull(dealerId,"DearId should not be null");
		this.startDate=startDate;
		this.endDate=Objects.requireNonNull(endDate,"EndDate should not be null");
		this.title=Objects.requireNonNull(title,"Title should not be null.");
		this.valueOfVehicle =Objects.requireNonNull(valueOfVehicle,"Value should not be null");
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
	
	public void setStartDate(Date startDate) {
		this.startDate=startDate;
	}
	
	public void setEndDate(Date endDate) {
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
	
	public void setValueOfVehicle(String valueOfVehicle) {
		this.valueOfVehicle =Objects.requireNonNull(valueOfVehicle,"Value should not be null");
	}
	
	public void setYear(String year) {
		this.year=year;
	}
	
	public void setBrand(String brand) {
		this.brand=brand;
	}
	
	public void setBodyType(String bodyType){
		this.model = bodyType;
	}
	
	public void setIsNew(String isNew) {
		this.isNew=isNew;
	}
	
	public void setScope(List<String> specialScope) {
		this.specialScope = specialScope;
	}
	
	public void setScopeMiles(String parameter) {
		this.scopeMiles =parameter;
	}
	
	public String getSpecialId() {
		return this.specialId;
	}
	
	public String getDealerId() {
		return this.dealerId;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public Date getEndDate() {
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
	
	public String getValueOfVehicle() {
		return this.valueOfVehicle;
	}
	
	public String getYear() {
		return this.year;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public String getBodyType() {
		return this.model;
	}
	
	public String isNew() {
		return this.isNew;
	}
	
	public List<String> getScope() {
		return this.specialScope;
	}
	
	public String getScopeMiles() {
		return this.scopeMiles;
	}

	public void setDiscountValue(int discountValue) {
		this.discountValue = discountValue;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getDiscountValue() {
		return discountValue;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public boolean getIsValidOnCashPayment() {
		return isValidOnCashPayment;
	}

	public void setValidOnCashPayment(boolean validOnCashPayment) {
		isValidOnCashPayment = validOnCashPayment;
	}

	public boolean getIsValidOnLoan() {
		return isValidOnLoan;
	}

	public void setValidOnLoan(boolean validOnLoan) {
		isValidOnLoan = validOnLoan;
	}

	public boolean getIsValidOnLease() {
		return isValidOnLease;
	}

	public void setValidOnLease(boolean validOnLease) {
		isValidOnLease = validOnLease;
	}

	public boolean getIsValidOnCheckPayment() {
		return isValidOnCheckPayment;
	}

	public void setValidOnCheckPayment(boolean validOnCheckPayment) {
		isValidOnCheckPayment = validOnCheckPayment;
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
		String specialScopeStr = String.join(" ", specialScope);

		// convert a Special to csv data
		String row = specialId + ","
				+ dealerId + ","
				+ startDate.getTime() + ","
				+ endDate.getTime() + ","
				+ csvTitle + ","
				+ csvDescription + ","
				+ discountValue + ","
				+ discountPercent + ","
				+ isValidOnCashPayment + ","
				+ isValidOnCheckPayment + ","
				+ isValidOnLoan + ","
				+ isValidOnLease + ","
				+ csvDisclaimer + ","
				+ valueOfVehicle + ","
				+ year + ","
				+ brand + ","
				+ model + ","
				+ isNew + ","
				+ scopeMiles + ","
				+ specialScopeStr;

		return row;
	}

	public String toString(){
		// escape comma and double quotes in title, description and disclaimer
		// other variables of Special should not contain any comma or double quotes
		String csvTitle = title == null ? "\"<ti></ti>\"" : "\"<ti>" + title + "</ti>\"";
		String csvDescription = description == null ? "\"<de></de>\"" : "\"<de>" + description + "</de>\"";
		String csvDisclaimer = disclaimer == null ? "\"<di></di>\"" : "\"<di>" + disclaimer + "</di>\"";
		String specialScopeStr = String.join(" ", specialScope);

		// convert a Special to csv data
		String row = specialId + ","
				+ dealerId + ","
				+ startDate + ","
				+ endDate + ","
				+ csvTitle + ","
				+ csvDescription + ","
				+ discountValue + ","
				+ discountPercent + ","
				+ isValidOnCashPayment + ","
				+ isValidOnCheckPayment + ","
				+ isValidOnLoan + ","
				+ isValidOnLease + ","
				+ csvDisclaimer + ","
				+ valueOfVehicle + ","
				+ year + ","
				+ brand + ","
				+ model + ","
				+ isNew + ","
				+ scopeMiles + ","
				+ specialScopeStr;

		return row;
	}
}
