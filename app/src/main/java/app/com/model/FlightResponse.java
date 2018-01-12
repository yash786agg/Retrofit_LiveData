package app.com.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/*
 * Created by Yash on 10/1/18.
 */

public class FlightResponse implements Parcelable
{
    @SerializedName("flights")
    private List<Flights> flights;

    @SerializedName("appendix")
    private Appendix appendix;

    public Appendix getAppendix() {
        return appendix;
    }

    public List<Flights> getFlights()
    {
        return flights;
    }

    @Override
    public String toString()
    {
        return "FlightResponse{" +
                "flights='" + flights + '\'' +
                ", appendix='" + appendix + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeTypedList(flights);
        dest.writeSerializable(appendix);
    }

    private FlightResponse(Parcel in)
    {
        in.readTypedList(flights,Flights.CREATOR);
        in.readSerializable();
    }

    public static final Parcelable.Creator<FlightResponse> CREATOR = new Parcelable.Creator<FlightResponse>() {
        @Override
        public FlightResponse createFromParcel(Parcel in) {
            return new FlightResponse(in);
        }

        @Override
        public FlightResponse[] newArray(int size) {
            return new FlightResponse[size];
        }
    };
}
