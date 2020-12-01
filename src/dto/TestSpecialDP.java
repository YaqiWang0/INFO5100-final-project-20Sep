package dto;

import dao.Special;

public class TestSpecialDataPersistent {
    public static void main(String[] args) throws Exception {
        DataPersistent dp = new DataPersistent();

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

        dp.saveSpecialToFile(i1);
        dp.saveSpecialToFile(i2);
        dp.saveSpecialToFile(i3);

        dp.removeSpecialFromFile(i2);

        i1.setYear("1111");
        dp.updateSpecialFromFile(i1);
    }


}
