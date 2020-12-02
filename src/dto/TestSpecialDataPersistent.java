package dto;

import dao.Special;
import dao.SpecialScope;

import java.util.HashMap;
import java.util.Map;

public class TestSpecialDataPersistent {
    public static void main(String[] args) throws Exception {
        DataPersistence dp = new DataPersistence();

        // special i1 with complete fields
        Special i1 = new Special("1", "01-01-2020", "01-01-2022", "Special: 1", "100");
        i1.setDescription("This, is, description. Special 1 description.");
        i1.setDisclaimer("This, is \"disclaimer\", blah. Special 1 disclaimer.");
        i1.setYear("2020");
        i1.setBrand("Toyota");
        i1.setBodyType("SUV");
        i1.setIsNew("New");
        i1.setScopeParameter("scope parameter");
        i1.setScope(SpecialScope.ALL);

        // special i2 without fields{BodyType, IsNew, ScopeParameter, SpecialScope}
        Special i2 = new Special("2", "01-01-2020", "01-01-2022", "Special: 2", "200");
        i2.setDescription("This, is, \"description\". Special 2 description.");
        i2.setDisclaimer("This, is \"disclaimer\", blah. Special 2 disclaimer.");
        i2.setYear("2020");
        i2.setBrand("Toyota");

        // specials i3 without fields {Description, Disclaimer, Year, Brand}
        Special i3 = new Special("3", "01-01-2020", "01-01-2022", "Special: 3", "300");
        i3.setBodyType("SUV");
        i3.setIsNew("New");
        i3.setScopeParameter("scope parameter");
        i3.setScope(SpecialScope.ALL);

        Map<String, Special> allSpecials = new HashMap<>();
        allSpecials.put(i1.getSpecialId(), i1);
        allSpecials.put(i2.getSpecialId(), i2);
        allSpecials.put(i3.getSpecialId(), i3);

        dp.saveSpecials(allSpecials);

        List<Special> allSpecialsRead = dp.readSpecials();
        System.out.println(allSpecialsRead.get(0).getTitle());
        System.out.println(allSpecialsRead.get(1).getTitle());
        System.out.println(allSpecialsRead.get(2).getTitle());

    }


}
