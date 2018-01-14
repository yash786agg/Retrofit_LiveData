package app.com.extras;

import java.util.Comparator;
import app.com.model.SortedFlight;

/*
 * Created by Yash on 14/1/18.
 */

public class SortedFareComparator implements Comparator<SortedFlight>
{
    //sort flight in ascending order from sorted array to order list of flight based on fare.

    public int compare(SortedFlight left, SortedFlight right)
    {
        return Integer.valueOf(left.getFare()).compareTo(right.getFare());
    }
}