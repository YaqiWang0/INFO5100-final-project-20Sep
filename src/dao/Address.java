
public class Address {

	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;
	
	public Address(String address1, String address2, String city, String state, String zipCode) {
		this.address1=address1;
		this.address2=address2;
		this.city=city;
		this.state=state;
		this.zipCode=zipCode;
	}
	
	public void setAddressInfo(String address1,String address2) {
		this.address1=address1;
		this.address2=address2;
	}
	
	public void setCity(String city) {
		this.city=city;
		
	}
	
	public void setState(String state) {
		this.state=state;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode=zipCode;
	}
	
	public String getAddressInfo() {
		return this.address1+'\n'+this.address2;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getZipCode() {
		return this.zipCode;
	}
	
	public String toString() {
		return "The deatiled address is:\nAddress1: "+this.address1
				+"\nAddress2: "+this.address2
				+"\nCity: "+this.city
				+"\nState: "+this.state
				+"\nZipCode: "+this.zipCode;
	}
	
	
	
}
