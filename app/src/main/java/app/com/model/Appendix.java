package app.com.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Map;

/*
 * Created by Yash on 10/1/18.
 */

public class Appendix implements Serializable
{
    /*
    * "appendix":
    * {
        "airlines":
        {
            "SG":"Spicejet",
            "AI":"Air India",
            "G8":"Go Air",
            "9W":"Jet Airways",
            "6E":"Indigo"
        },
        "airports":
        {
            "DEL":"New Delhi",
            "BOM":"Mumbai"
        },
        "providers":
        {
            "1":"MakeMyTrip",
            "2":"Cleartrip",
            "3":"Yatra",
            "4":"Musafir"
        }
     }*/

    @SerializedName("airlines")
    private Map<String, String> airlines;

    @SerializedName("airports")
    private Map<String, String> airports;

    @SerializedName("providers")
    private Map<String, String> providers;

    public Map<String, String> getAirlines() {
        return airlines;
    }

    public Map<String, String> getAirports() {
        return airports;
    }

    public Map<String, String> getProviders() {
        return providers;
    }

}
