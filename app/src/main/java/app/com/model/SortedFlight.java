package app.com.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
/*
 * Created by Yash on 11/1/18.
 */


public class SortedFlight implements Parcelable
{
    /*
    * {
        "originCode":"DEL",
        "destinationCode":"BOM",
        "departureTime":1396614600000,
        "arrivalTime":1396625400000,
         "providerId":1,
        "fare":11500
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

    @SerializedName("providerId")
    private int providerId;

    @SerializedName("fare")
    private int fare;

    @SerializedName("airlineCode")
    private String airlineCode;

    @SerializedName("class")
    private String flightClass;

    private int indexPosition;

    public SortedFlight()
    {}

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

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
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

    public int getIndexPosition() {
        return indexPosition;
    }

    public void setIndexPosition(int indexPosition) {
        this.indexPosition = indexPosition;
    }

    @Override
    public String toString()
    {
        return "SortedFlight{" +
                "originCode='" + originCode + '\'' +
                "destinationCode='" + destinationCode + '\'' +
                "departureTime='" + departureTime + '\'' +
                "arrivalTime='" + arrivalTime + '\'' +
                "providerId='" + providerId + '\'' +
                "fare='" + fare + '\'' +
                "airlineCode='" + airlineCode + '\'' +
                "indexPosition='" + indexPosition + '\'' +
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
        dest.writeInt(providerId);
        dest.writeInt(fare);
        dest.writeString(airlineCode);
        dest.writeString(flightClass);
        dest.writeInt(indexPosition);
    }

    private SortedFlight(Parcel in)
    {
        this();

        originCode = in.readString();
        destinationCode = in.readString();
        departureTime = in.readLong();
        arrivalTime = in.readLong();
        providerId = in.readInt();
        fare = in.readInt();
        airlineCode = in.readString();
        flightClass = in.readString();
        indexPosition = in.readInt();
    }

    public static final Parcelable.Creator<SortedFlight> CREATOR = new Parcelable.Creator<SortedFlight>() {
        @Override
        public SortedFlight createFromParcel(Parcel in) {
            return new SortedFlight(in);
        }

        @Override
        public SortedFlight[] newArray(int size) {
            return new SortedFlight[size];
        }
    };
}
