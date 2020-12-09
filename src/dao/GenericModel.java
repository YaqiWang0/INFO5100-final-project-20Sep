package dao;

public class GenericModel {
    public String modelType;

    public GenericModel(){}

    public GenericModel(String model){
        this.modelType = model;
    }

    public String getModelType(){
        return modelType;
    }

    public String toCSVLine() { return ""; }
}
