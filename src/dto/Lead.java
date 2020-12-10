package dto;

import java.util.UUID;

public class Lead {

    private String leadId;
    private String vehicleId;
    private String dealerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String zipCode;
    private String usePurpose;
    private String contactPreference;
    private String contactTime;
    private String message;

    public Lead() {
    }
    
    public Lead(String vehicleId, String dealerId) {
        this.vehicleId = vehicleId;
        this.dealerId = dealerId;
        this.leadId = UUID.randomUUID().toString();
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setUsePurpose(String usePurpose) {
        this.usePurpose = usePurpose;
    }

    public void setContactPreference(String contactPreference) {
        this.contactPreference = contactPreference;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLeadId() {
        return leadId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getDealerId() {
        return dealerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getUsePurpose() {
        return usePurpose;
    }

    public String getContactPreference() {
        return contactPreference;
    }

    public String getContactTime() {
        return contactTime;
    }

    // message is collected from TextArea where user is enable to input multiple lines,
    // this getMessage method may return a String including multiple lines with comma and period.
    public String getMessage() {
        return message;
    }

    public String toCSVLine() {
        return leadId + "," + vehicleId + "," + dealerId + "," + firstName + "," + lastName + "," + emailAddress + "," + phoneNumber + "," + zipCode + "," +
                zipCode + "," + usePurpose + "," + contactPreference + "," + contactTime + "," + message;
    }

}
