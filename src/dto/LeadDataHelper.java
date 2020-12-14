package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Dealer;
import dao.GenericModel;
import dao.Vehicle;


public class LeadDataHelper {
    
    private static LeadDataHelper instance;
    
    private DataPersistence dp;
    
    private List<Lead> leads;
    private Map<String, Integer> leadIdMapping = new HashMap<>();
    
    private List<Vehicle> vehicles;
    private Map<String, Integer> vehicleIdMapping = new HashMap<>();
    
    private List<Dealer> dealers;
    private Map<String, Integer> dealerIdMapping = new HashMap<>();
    
    
    private LeadDataHelper() {
        dp = new DataPersistence();
        
        initData();
    }
    
    /**
     * Singleton pattern, avoid frequent file io.
     * @return LeadDataHelper instance
     */
    public static LeadDataHelper instance() {
        if (instance == null) {
            instance = new LeadDataHelper();
        }
        return instance;
    }
    
    /**
     * Read leads, dealers and vehicles from data files to Lists.<br>
     * Initialize  {id:index} mappings for these lists, 
     * providing efficient queries for object id.
     */
    private void initData() {
        leads = dp.getAllLeads();
        initIdMapping(leads, leadIdMapping);
        
        dealers = dp.getAllDealers();
        initIdMapping(dealers, dealerIdMapping);
        
        vehicles = dp.getAllVehicles();
        initIdMapping(vehicles, vehicleIdMapping);
    }
    
    /**
     * Initialize {id:index} mappings for list
     * @param list
     * @param mapping
     */
    private void initIdMapping(List list, Map<String, Integer> mapping) {
        mapping.clear();
        for (int i = 0; i < list.size(); i++) {
            mapping.put(((GenericModel)list.get(i)).getId(), i);
        }
        
    }
    
    /**
     * Get all leads data from cache.
     * @return A List contains all the lead objects.
     */
    public List<Lead> getLeads() {
        return leads;
    }
    
    /**
     * Reload Leads, Dealers, Vehicles data from data files to cached lists.
     */
    public void reloadData() {
        this.initData();
    }
    
    /**
     * Get leads which filter by specific dealer id
     * @param dealerId  specify dealer which the leads data belongs to. 
     * @return a List contains all leads object which belongs to the specific dealer 
     */
    public List<Lead> getLeadsByDealer(String dealerId) {
        List<Lead> leads = new ArrayList<>();
        
        for(Lead lead: getLeads()) {
            if(lead.getDealerId().equals(dealerId)) {
                leads.add(lead);
            }
        }
        return leads;
    }
    
    /**
     * Get Object from list by object id.
     * 
     * @param id
     * @param objects
     * @param idMapping
     * @return the object corresponding to the id, null if cannot find such object. 
     */
    private Object getObject(String id, List objects, Map<String, Integer> idMapping) {
        Integer idx = (Integer) idMapping.get(id);
        return idx == null ? null : objects.get(idx);
    }
    
    /**
     * Get Lead object by lead id
     * @param leadId
     * @return Lead object, or null if not exits
     */
    public Lead getLead(String leadId) {
        return (Lead) getObject(leadId, leads, leadIdMapping);
    }
    
    /**
     * Get Vehicle object by vehicle id
     * @param vehicleId
     * @return Vehicle object, or null if not exits
     */
    public Vehicle getVehicle(String vehicleId) {
        return (Vehicle) getObject(vehicleId, vehicles, vehicleIdMapping);
    }
    
    /**
     * Get Dealer object by dealer id
     * @param dealerId
     * @return Dealer object, of null if not exits
     */
    public Dealer getDealer(String dealerId) {
        return (Dealer) getObject(dealerId, dealers, dealerIdMapping);
    }
    
//    public static void main(String[] args) {
//        LeadDataHelper helper = LeadDataHelper.instance();
//        
//        List<Lead> leads = helper.getLeads();
//        System.out.println(leads);
//        
//        leads = helper.getLeadsByDealer("bae705d7-20da-4ee2-871f-345b2271992b");
//        System.out.println(leads);
//        
//        Dealer dealer = helper.getDealer("bae705d7-20da-4ee2-871f-345b2271992b");
//        System.out.println(dealer);
//        
//        Vehicle vehicle = helper.getVehicle("47026c7e-e6b6-49c9-ba43-cad17cb38b3f");
//        System.out.println(vehicle);
//    }
}