package dao;

public enum BodyType {
    VAN("VAN"),

    SUV("SUV"),

    CAR("CAR"),

    TRUCK("TRUCK"),

    WAGON("WAGON"),

    CARGO_VAN("CARGO VAN"),

    COMMERCIAL_VEHICLE("COMMERCIAL VEHICLE");

    String type;

    BodyType(String type){this.type=type;}
}