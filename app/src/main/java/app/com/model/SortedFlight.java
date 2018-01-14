package app.com.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
/*
 * Created by Yash on 14/1/18.
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

    @SerializedName("departureTime")
    private long departureTime;

    @SerializedName("arrivalTime")
    private long arrivalTime;

    @SerializedName("fare")
    private int fare;

    @SerializedName("airlineCode")
    private String airlineCode;

    private int indexPosition;

    public SortedFlight()
    {}

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
                "departureTime='" + departureTime + '\'' +
                "arrivalTime='" + arrivalTime + '\'' +
                "fare='" + fare + '\'' +
                "airlineCode='" + airlineCode + '\'' +
                ", indexPosition='" + indexPosition + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(departureTime);
        dest.writeLong(arrivalTime);
        dest.writeInt(fare);
        dest.writeString(airlineCode);
        dest.writeInt(indexPosition);
    }

    private SortedFlight(Parcel in)
    {
        this();

        departureTime = in.readLong();
        arrivalTime = in.readLong();
        fare = in.readInt();
        airlineCode = in.readString();
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
