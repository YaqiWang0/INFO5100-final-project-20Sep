package ui.CheckLead;

import dto.Lead;
import dto.LeadDataHelper;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class MergeFormsHelper {
    private List<Lead> originalForms;
    private List<Lead> mergedForms;
    private List<List<String>> mergedIds;
    LeadDataHelper helper;

    MergeFormsHelper(String dealerId) {
        helper = LeadDataHelper.instance();
        originalForms = helper.getLeadsByDealer(dealerId);
        mergeData();
    }

    private void mergeData() {
        Map<String, List<Lead>> groupedMap = originalForms.stream().collect(groupingBy(Lead::getEmailAddress));
        mergedForms = groupedMap.entrySet().stream().map(v -> v.getValue().get(0)).collect(toList());
        mergedIds = groupedMap.entrySet().stream().map(v -> v.getValue()).
                map(v -> v.stream().map(u -> u.getVehicleId()).collect(toList())).collect(Collectors.toList());
    }

    public List<Lead> getMergedForms() {
        return mergedForms;
    }

    public List<List<String>> getMergedIds() {
        return mergedIds;
    }

    public LeadDataHelper getHelper() {
        return helper;
    }

    /*public static void main(String[] args) {
        new MergeFormsHelper("bae705d7-20da-4ee2-871f-345b2271992b");
    }*/
}


