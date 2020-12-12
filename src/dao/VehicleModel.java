package dao;

import java.util.List;

public class VehicleModel {

    private Vehicle vehicle;
    //Cheapest active special
    private Special special;

    // for case6 own attribute
    private String specialId;
    //according to case 5, price is integer; better float? 
    private float specialPrice;
    private String incentiveType;
    //All Specials
    private List<Special> allSpecials;


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
    
    public String getIncentiveType() {
		return incentiveType;
	}
    
    public List<Special> getAllSpecials() {
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
	
	public void setIncentiveType(String incentiveType) {
		this.incentiveType = incentiveType;
	}
	
	public void setAllSpecials(List<Special> allSpecials) {
		this.allSpecials = allSpecials;
	}
	
	public void addToAllSpecials(Special special) {
		allSpecials.add(special);
	}
}
