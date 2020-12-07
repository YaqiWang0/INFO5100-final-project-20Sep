package dao;

public class VehicleModel {

    private Vehicle vehicle;
    private Special special;

    // for case6 own attribute
    private String specialId;
    private double specialPrice;
    // ...etc

    public VehicleModel(Vehicle vehicle, Special special) {
        this.vehicle = vehicle;
        this.special = special;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        this.specialPrice = specialPrice;
    }
}
