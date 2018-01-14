package app.com.extras;

import java.util.Comparator;
import app.com.model.SortedFlight;

/*
 * Created by Yash on 14/1/18.
 */

public class SortedTimeComparator implements Comparator<SortedFlight>
{
    //sort flight in ascending order from sorted array to order list of flight based on time basics.
    public int compare(SortedFlight left, SortedFlight right)
    {
        return Long.valueOf(left.getDepartureTime()).compareTo(right.getDepartureTime());
    }
}
