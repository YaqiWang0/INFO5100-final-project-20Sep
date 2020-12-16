// Please visit this page to update API key if current API key is expired.
// http://www.zipcodeapi.com/API#distance

package service;
// http://www.zipcodeapi.com/API#distance

import dao.Dealer;
import dto.DataPersistence;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class SearchDealer {
    public static final List<Dealer> ALL_DEALERS = new DataPersistence().getAllDealers();
    private static final String DISTANCE_SERVER = "https://www.zipcodeapi.com/rest/pGzOO9BxxZR5QGu2XuCb5w2SDhl1qbGzhnZTlWEIPsz3WHwovGEYBarjDsq4dDoZ/multi-distance.csv/";
    //vGrUYAvyxcZUerHdkHJTL7oTK03Hssnb7A8eEcR9k8bhWLtjqWCgMdIpgK6hd08e


    /**
     *
     * @param zipcode
     * @return List of dealers sorted by distance to the input zipcode
     * @throws MalformedURLException
     */
    public static List<Dealer> searchByZipCode(String zipcode) {
        //List<Dealer> dealerList = new ArrayList<>();
        String zipcodes = ALL_DEALERS.stream()
                .map(dealer -> dealer.getDealerAddress().getZipCode())
                .collect(Collectors.joining(","));

        Map<String, Double> zipcodeToDistance = queryDistances(zipcode, zipcodes);

        //set distances for all dealers
        for(Dealer dealer: ALL_DEALERS){
            dealer.setDistanceInMiles(zipcodeToDistance.get(dealer.getDealerAddress().getZipCode()));
        }
        ALL_DEALERS.sort(new Comparator<Dealer>() {
            @Override
            public int compare(Dealer o1, Dealer o2) {
                if(o1.getDistanceInMiles() > o2.getDistanceInMiles()) {
                    return 1;
                } else if(o1.getDistanceInMiles() == o2.getDistanceInMiles()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        return ALL_DEALERS;
    }

    /**
     *
     * @param zipcode
     * @param dealerZipcodes
     * @return Map from dealer zipcode to Distance to zipcode
     * @throws MalformedURLException
     */
    private static Map<String, Double> queryDistances(String zipcode, String dealerZipcodes) {
        String url = String.format(DISTANCE_SERVER + "%s/%s/miles",zipcode,dealerZipcodes);

        Map<String, Double> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"))) {
            reader.readLine();
            for (String line; (line = reader.readLine()) != null;) {
                String[] zipcodeInfo = line.split(",");
                String zip = zipcodeInfo[0];
                String dis = zipcodeInfo[1];
                double distance = Double.parseDouble(dis);
                map.put(zip,distance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     *
     * @param dealerName
     */
    public static List<Dealer> searchByName(String dealerName) {
        List<Dealer> dealerList = new ArrayList<>();

            for(Dealer dealers: ALL_DEALERS){
                if(dealers.getDealerName().contains(dealerName)){
                    dealerList.add(dealers);
                }
             }


        return dealerList;

    }

    public static void main(String[] args) {
        System.out.println(SearchDealer.searchByZipCode("98115"));

        //System.out.println(SearchDealer.searchByName("aj"));
        //SearchDealer.ALL_DEALERS.forEach(out::println);
    }
}
