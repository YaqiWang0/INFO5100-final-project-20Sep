package dao;

import java.util.List;

public class VehicleModel {

    private Vehicle vehicle;
    //Cheapest active special
    private Special special;

    // for case6 own attribute
    private String specialId;
    private float specialPrice;
    private boolean haveSpecial;

	//All specials
    private List<VehicleModel> allSpecials;


	public VehicleModel(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
        
	public VehicleModel(Vehicle vehicle, Special special) {
        this.vehicle = vehicle;
        this.special = special;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Special getSpecial() {
        return special;
    }

    public float getSpecialPrice() {
        return specialPrice;
    }

    public String getSpecialId() {
		return specialId;
	}
    
    public boolean isHaveSpecial() {
		return haveSpecial;
	}    
    
    public List<VehicleModel> getAllSpecials() {
 		return allSpecials;
 	}

	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void setSpecial(Special special) {
		this.special = special;
	}

	public void setSpecialPrice(float specialPrice) {
        this.specialPrice = specialPrice;
    }
	
	public void setHaveSpecial(boolean haveSpecial) {
		this.haveSpecial = haveSpecial;
	}
	
	public void setAllSpecials(List<VehicleModel> allSpecials) {
		this.allSpecials = allSpecials;
	}
	
	
	public void addToAllSpecials(VehicleModel special) {
		allSpecials.add(special);
	}
}
