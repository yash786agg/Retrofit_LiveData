package app.com.Extras;

import java.util.Comparator;
import app.com.model.SortedFlight;

/*
 * Created by Yash on 11/1/18.
 */

public class SortedFareComparator implements Comparator<SortedFlight>
{
    public int compare(SortedFlight left, SortedFlight right)
    {
        return Integer.valueOf(left.getFare()).compareTo(right.getFare());
    }
}