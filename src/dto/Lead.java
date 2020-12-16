package dto;

import dao.GenericModel;

import java.util.UUID;

public class Lead extends GenericModel {

    private String leadId;
    private String vehicleId;
    private String dealerName;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String zipCode;
    private String usePurpose;
    private String contactPreference;
    private String contactTime;
    private String message;
    private String replyNotes;
    private boolean read;
    private boolean contacted;

    public Lead() {
    }

    public Lead(String vehicleId, String dealerName) {
        this.vehicleId = vehicleId;
        this.dealerName = dealerName;
        this.leadId = UUID.randomUUID().toString();
        this.replyNotes = "";
        this.read = false;
        this.contacted = false;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
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

    public void setReplyNotes(String replyNotes) {
        this.replyNotes = replyNotes;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setContacted(boolean contacted) {
        this.contacted = contacted;
    }

    public String getLeadId() {
        return leadId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getDealerName() {
        return dealerName;
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

    public String getReplyNotes() {
        return replyNotes;
    }

    public boolean getRead() {
        return read;
    }

    public boolean getContacted() {
        return contacted;
    }
    
    public String toCSVLine() {
        return leadId + "," + vehicleId + "," + dealerName + "," + firstName + "," + lastName + "," + emailAddress + "," + phoneNumber + "," + zipCode + "," +
                zipCode + "," + usePurpose + "," + contactPreference + "," + contactTime + "," + message;
    }

    @Override
    public String toString() {
        return "<Lead> id: " + this.getVehicleId();
    }

    @Override
    public String getId() {
        return this.getLeadId();
    }

}
