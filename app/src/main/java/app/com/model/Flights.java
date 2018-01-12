package app.com.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by Yash on 10/1/18.
 */

public class Flights implements Parcelable
{
    /*
    * {
        "originCode":"DEL",
        "destinationCode":"BOM",
        "departureTime":1396614600000,
        "arrivalTime":1396625400000,
        "fares":
        [
            {
                "providerId":1,
                "fare":11500
            },
            {
                "providerId":2,
                "fare":10500
            }
        ],
        "airlineCode":"G8",
        "class":"Business"
      }*/

    @SerializedName("originCode")
    private String originCode;

    @SerializedName("destinationCode")
    private String destinationCode;

    @SerializedName("departureTime")
    private long departureTime;

    @SerializedName("arrivalTime")
    private long arrivalTime;

    @SerializedName("fares")
    private List<FaresData> fareArrayList;

    @SerializedName("airlineCode")
    private String airlineCode;

    @SerializedName("class")
    private String flightClass;

    public Flights()
    {
        //Link ---> https://stackoverflow.com/questions/6774645/android-how-to-use-readtypedlist-method-correctly-in-a-parcelable-class
        fareArrayList = new ArrayList<>();
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<FaresData> getFareArrayList() {
        return fareArrayList;
    }

    public void setFareArrayList(List<FaresData> fareArrayList) {
        this.fareArrayList = fareArrayList;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    @Override
    public String toString()
    {
        return "Flights{" +
                "originCode='" + originCode + '\'' +
                "destinationCode='" + destinationCode + '\'' +
                "departureTime='" + departureTime + '\'' +
                "arrivalTime='" + arrivalTime + '\'' +
                "fareArrayList='" + fareArrayList + '\'' +
                "airlineCode='" + airlineCode + '\'' +
                ", flightClass='" + flightClass + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(originCode);
        dest.writeString(destinationCode);
        dest.writeLong(departureTime);
        dest.writeLong(arrivalTime);
        dest.writeTypedList(fareArrayList);
        dest.writeString(airlineCode);
        dest.writeString(flightClass);
    }

    private Flights(Parcel in)
    {
        this();

        originCode = in.readString();
        destinationCode = in.readString();
        departureTime = in.readLong();
        arrivalTime = in.readLong();
        in.readTypedList(fareArrayList,FaresData.CREATOR);
        airlineCode = in.readString();
        flightClass = in.readString();
    }

    public static final Parcelable.Creator<Flights> CREATOR = new Parcelable.Creator<Flights>() {
        @Override
        public Flights createFromParcel(Parcel in) {
            return new Flights(in);
        }

        @Override
        public Flights[] newArray(int size) {
            return new Flights[size];
        }
    };
}
