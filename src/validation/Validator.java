package validation;
import java.util.regex.Pattern;

public class Validator {
    private static String regex1 = "^[0-9]{5}(?:-[0-9]{4})?$"; // check regex later
    private static String regex2 = "^[\sa-zA-Z-.]+";
    private static String regex3 = "^[\sa-zA-Z]+";
    private static final int maxNameRange = 100;
    //private static NormalizerImpl.ReorderingBuffer TextUtils;

    // to check if the word count of input dealer name is valid
    public static boolean isValidDealerName(String name) {
        if(name.length() > maxNameRange){
            System.out.println("The dealer name's length exceeds the limit, please type again.");
            return false;
        }
        try{
            if (Pattern.matches(regex2, name)) {
                return true;
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("Exception thrown: " + e);
        }
        System.out.println("This is an invalid dealer name, please type again.");
        return false;

    };

    // to check if the input zip code with valid number of digits
    public static boolean isValidZipCodeRange(String zipCode) {
        return Pattern.matches(regex1, zipCode);
    }

    // to check if the input zip code is a valid US zip code
    public static boolean isValidZipCode(String zipCode){
        try{
            if (Pattern.matches(regex1, zipCode)) {
                return true;
            }
        }catch (NumberFormatException e){
            System.out.println("Exception thrown: " + e);
        }
        System.out.println("This is an invalid ZipCode, please type again. ");
        return false;
    }

    // to check if the word count of input city or state name is valid
    public static boolean isValidCityName(String cityStateName) {
        try{
            if (Pattern.matches(regex3, cityStateName)) {
                return true;
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("Exception thrown: " + e);
        }
        System.out.println("This is an invalid city or state's name, please type again.");
        return false;

    };

    public static void main(String[] args){
        boolean res1 = isValidDealerName("BURIEN-CHEVROLET Inc.");
        System.out.println(res1);
        boolean res2 = isValidZipCode("98025");
        System.out.println(res2);
        boolean res3 = isValidZipCode("98025-1110");
        System.out.println(res3);
        boolean res4 = isValidCityName("Los Angeles");
        System.out.println(res4);
    }

}
