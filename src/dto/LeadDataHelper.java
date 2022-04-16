package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        dealerNameMapping.clear();
        for (int i = 0; i < dealers.size(); i++) {
            dealerNameMapping.put(dealers.get(i).getDealerName(), i);
        }
        
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
     * Get merged leads
     * @return merged leads
     */
    public List<Lead> getMergedLeads(String dealerName) { 
        List<Lead> mergedLeads = new ArrayList<Lead>();
        Set<String> emailSet = new HashSet<>();
        
        for (Lead lead : getLeadsByDealer(dealerName)) {
            String email = lead.getEmailAddress();
            if (!emailSet.contains(email)) {
                mergedLeads.add(lead);
                emailSet.add(email);
            }
        }
        
        return mergedLeads;
    }
    
    public Vehicle[] getVehiclesByEmail(String dealerName, String email) {
        List<Lead> originalLeads = getLeadsByDealer(dealerName);
        List<Vehicle>  vehicles = new ArrayList<>();
        for (Lead lead : originalLeads) {
            if (lead.getEmailAddress().equals(email)) {
                Vehicle v = getVehicle(lead.getVehicleId());
                if (v != null) vehicles.add(v);
            }
        }
        
        Vehicle[] vehicleArr = new Vehicle[vehicles.size()];
        return vehicles.toArray(vehicleArr);
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
     * @param dealerName
     * @return Dealer object, of null if not exits
     */
    public Dealer getDealer(String dealerName) {
        return (Dealer) getObject(dealerName, dealers, dealerNameMapping);
    }
    
    
    public void removeAndSave(Lead leadRemoved) {
        List<Lead> newLeads = new ArrayList<Lead>();
        for (Lead lead : getLeads()) {
            if (!lead.getEmailAddress().equals(leadRemoved.getEmailAddress())) {
                newLeads.add(lead);
            }
        }
        
        dp.writeLeads(newLeads);
        this.reloadData();
    }

    public List<Lead> filter(List<Lead> leads, String filterType, String value) {
        List<Lead> filteredLeads = new ArrayList<>();
        for (Lead lead : leads) {
            if (filterType.equals("Contact Preference") && lead.getContactPreference().equals(value)) {
                filteredLeads.add(lead);
            }
            else if(filterType.equals("Use Purpose") && lead.getUsePurpose().equals(value)) {
                filteredLeads.add(lead);
            }
            else if(filterType.equals("Read") && lead.getRead() == (value.equals("Read"))) { 
                filteredLeads.add(lead);
            }
            else if(filterType.equals("Contacted") && lead.getContacted() == (value.equals("Contacted"))) { 
                filteredLeads.add(lead);
            }
        }
        return filteredLeads;
    }

    public String getDealerName(String dealerId) {
        for (Dealer dealer : dealers) {
            if(dealer.getDealerId().equals(dealerId)) {
                return dealer.getDealerName();
            }
        }
        return null;
    }
}