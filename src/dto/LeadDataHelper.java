package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.Dealer;
import dao.GenericModel;
import dao.Vehicle;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


public class LeadDataHelper {
    
    private static LeadDataHelper instance;
    
    private DataPersistence dp;
    
    private List<Lead> leads;
    private Map<String, Integer> leadIdMapping = new HashMap<>();
    
    private List<Vehicle> vehicles;
    private Map<String, Integer> vehicleIdMapping = new HashMap<>();
    
    private List<Dealer> dealers;
    private Map<String, Integer> dealerNameMapping = new HashMap<>();

    private List<Lead> mergedLeads;
    private List<List<String>> mergedVehicleIds;
    
    
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
        initIdMapping(dealers, dealerNameMapping);
        
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
     * @param dealerName  specify dealer which the leads data belongs to. 
     * @return a List contains all leads object which belongs to the specific dealer 
     */
    public List<Lead> getLeadsByDealer(String dealerName) {
        List<Lead> leads = new ArrayList<>();
        
        for(Lead lead: getLeads()) {
            if(lead.getDealerName().equals(dealerName)) {
                leads.add(lead);
            }
        }
        return leads;
    }

    /**
     * merge leads by email address which are filtered by specific dealer id
     * @param dealerName  specify dealer which the leads data belongs to.
     */
    public void mergeLeadsHelper(String dealerName) {
        List<Lead> originalLeads = getLeadsByDealer(dealerName);
        Map<String, List<Lead>> groupedMap = originalLeads.stream().
                                        collect(groupingBy(Lead::getEmailAddress));
        mergedLeads = groupedMap.entrySet().stream().
                                    map(v -> v.getValue().get(0)).collect(toList());
        mergedVehicleIds = groupedMap.entrySet().stream().map(v -> v.getValue()).
                                            map(v -> v.stream().map(u -> u.getVehicleId()).
                                            collect(toList())).collect(Collectors.toList());
    }

    /**
     * Get merged leads
     * @return merged leads
     */
    public List<Lead> getMergedLeads() { return this.mergedLeads; }

    /**
     * Get merged vehicle ids
     * @return merged vehicle ids
     */
    public List<List<String>> getMergedVehicleIds() { return this.mergedVehicleIds; }


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
     * @param dealerName
     * @return Dealer object, of null if not exits
     */
    public Dealer getDealer(String dealerName) {
        return (Dealer) getObject(dealerName, dealers, dealerNameMapping);
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