package app.com.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/*
 * Created by Yash on 10/1/18.
 */

public class Appendix implements Serializable //implements Parcelable
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

    public void setAirlines(Map<String, String> airlines) {
        this.airlines = airlines;
    }

    public Map<String, String> getAirports() {
        return airports;
    }

    public void setAirports(Map<String, String> airports) {
        this.airports = airports;
    }

    public Map<String, String> getProviders() {
        return providers;
    }

    public void setProviders(Map<String, String> providers) {
        this.providers = providers;
    }

    /*public Appendix()
    { }

    @Override
    public String toString()
    {
        return "Appendix{" +
                "airports='" + airports + '\'' +
                "airlines='" + airlines + '\'' +
                ", providers='" + providers + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeMap(airports);
        dest.writeMap(airlines);
        dest.writeMap(providers);
    }

    private Appendix(Parcel in)
    {
        in.readMap(airports,Appendix.class.getClassLoader());
        in.readMap(airlines,Appendix.class.getClassLoader());
        in.readMap(providers,Appendix.class.getClassLoader());
    }

    public static final Parcelable.Creator<Appendix> CREATOR = new Parcelable.Creator<Appendix>() {
        @Override
        public Appendix createFromParcel(Parcel in) {
            return new Appendix(in);
        }

        @Override
        public Appendix[] newArray(int size) {
            return new Appendix[size];
        }
    };*/
}
