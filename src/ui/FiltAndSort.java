package ui;

import java.util.*;

public class FiltAndSort {
    //
    private static final int FILTER_CATEGORY_COUNT = 7;
    private static final Integer[] ORDER = {2, 4, 5, 7, 6, 8, 8};
    private static final String[] CATEGORIES = {"CATEGORY", "MAKE", "MODEL", "TYPE",
            "BODY STYLE", "ABOVE PRICE", "BELOW PRICE"};

    public static ArrayList filter(ArrayList data, HashMap<String, HashSet<String>> container) {
        ArrayList<String[]> result = new ArrayList<>();
        int count = 0;
        Set<String> keySet = container.keySet();
        for (int i = 0; i < FILTER_CATEGORY_COUNT; i++) {
            if (container.get(CATEGORIES[i]) != null) {
                break;
            } else {
                count++;
            }
        }
        if (count == 7) {
            result = data;
        } else {
            addToResult(count, data, result, container);
            count++;
            for (int i = count; i < FILTER_CATEGORY_COUNT; i++) {
                if (container.get(CATEGORIES[i]) != null) {
                    deleteFromResult(i, data, result, container);
                }
            }
        }
        return result;
    }

    private static void addToResult(int count, ArrayList<String[]> data, ArrayList<String[]> result, HashMap<String, HashSet<String>> container) {
        for (int i = 0; i < container.get(CATEGORIES[count]).size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                String[] currentData = data.get(j);
                if (container.get(CATEGORIES[count]).contains(currentData[ORDER[count]].toLowerCase())) {
                    result.add(currentData);
                }
            }
        }
    }

    private static void deleteFromResult(int count, ArrayList<String[]> data, ArrayList<String[]> result, HashMap<String, HashSet<String>> container) {
        for (int i = 0; i < container.get(CATEGORIES[count]).size(); i++) {
            for (int j = 0; j < result.size(); j++) {
                String[] currentData = result.get(i);
                if (!container.get(CATEGORIES[count]).contains(currentData[ORDER[count]].toLowerCase())) {
                    result.remove(j);
                }
            }
        }

    }

    // Parameter: User's selected sorting preference
    // Select and sort the vehicle objects and store it in a LinkedHashSet
    public static ArrayList sort(int userSelectedSort, ArrayList filteredList) {
        if (userSelectedSort == 0) {
            return filteredList;
        } else if (userSelectedSort == 1) {
            sortByNumber(filteredList, false, 8); // sort price from high to low
        } else if (userSelectedSort == 2) {
            sortByNumber(filteredList, true, 8); // sort price from low to high
        } else if (userSelectedSort == 3) {
            sortByNumber(filteredList, false, 3); // sort year from high to low
        } else if (userSelectedSort == 4) {
            sortByNumber(filteredList, true, 3); // sort year from low to high
        }
        return filteredList;
    }

    private static void sortByNumber(ArrayList filteredList, boolean lowToHigh, int index) {
        if (lowToHigh) {
            try {
                Collections.sort(filteredList, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return (int) Double.parseDouble(o1[index]) - (int) Double.parseDouble(o2[index]);
                        //return Integer.valueOf(o1[index]) - Integer.valueOf(o2[index]);
                    }
                });
            } catch (NumberFormatException e) {
                System.out.println("index is : " + index);
                e.printStackTrace();
            }
        } else {
            try {
                Collections.sort(filteredList, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return (int) Double.parseDouble(o2[index]) - (int) Double.parseDouble(o1[index]);
                        //return Integer.valueOf(o2[index]) - Integer.valueOf(o1[index]);
                    }
                });
            } catch (NumberFormatException e) {
                System.out.println("index is : " + index);
                e.printStackTrace();
            }
        }
    }
}
