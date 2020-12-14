package ui;

import dao.Special;
import dao.Vehicle;

import java.util.List;

public class SpecialModel {

    private Vehicle vehicle;
    //Cheapest active special
    private Special special;

    // for case6 own attribute
    private String specialId;
    private float specialPrice;
    private boolean haveSpecial;

	//All specials
    private List<SpecialModel> allSpecials;


	public SpecialModel(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
        
	public SpecialModel(Vehicle vehicle, Special special) {
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
    
    public List<SpecialModel> getAllSpecials() {
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
	
	public void setAllSpecials(List<SpecialModel> allSpecials) {
		this.allSpecials = allSpecials;
	}
	
	
	public void addToAllSpecials(SpecialModel special) {
		allSpecials.add(special);
	}
}
