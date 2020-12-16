package dto;

import dao.BodyType;
import dao.Vehicle;
import dao.GenericModel;

public class VehicleFile extends GenericModel{
    private String vehicleId;
    private String dealerId;
    private String year;
    private String category;
    private String make;
    private String model;
    private String trim;
    private String type;
    private String price;
    private String photo;

    public VehicleFile(String dealerId) {
        this.dealerId = dealerId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Vehicle toVehicle() {
        Vehicle v = new Vehicle(this.dealerId, this.vehicleId);
        v.setYear(this.year);
        v.setBrand(this.make);
        v.setModel(this.model);
        if (!this.type.equals("")) {
            v.setBodyType(BodyType.valueOf(this.type.replace(' ', '_')));
        } else {
            v.setBodyType(BodyType.valueOf("CAR"));
        }
        v.setIsNew(this.category.equalsIgnoreCase("new"));
        v.setPrice(this.price);
        v.setTrim(this.trim);
        v.getImg().add(this.photo);
        return v;
    }
}
