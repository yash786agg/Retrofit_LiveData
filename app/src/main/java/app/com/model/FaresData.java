package app.com.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/*
 * Created by Yash on 10/1/18.
 */

public class FaresData implements Parcelable
{
    /*
    * fares":[
                {
                 "providerId":1,
                 "fare":11500
                }
            ]*/

    @SerializedName("providerId")
    private int providerId;

    @SerializedName("fare")
    private int fare;

    public FaresData()
    {}

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

    @Override
    public String toString()
    {
        return "FaresData{" +
                "providerId='" + providerId + '\'' +
                ", fare='" + fare + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(providerId);
        dest.writeInt(fare);
    }

    protected FaresData(Parcel in)
    {
        providerId = in.readInt();
        fare = in.readInt();
    }

    public static final Parcelable.Creator<FaresData> CREATOR = new Parcelable.Creator<FaresData>() {
        @Override
        public FaresData createFromParcel(Parcel in) {
            return new FaresData(in);
        }

        @Override
        public FaresData[] newArray(int size) {
            return new FaresData[size];
        }
    };
}
