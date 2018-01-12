package app.com.Extras;

import java.util.Comparator;
import app.com.model.SortedFlight;

/*
 * Created by Yash on 12/1/18.
 */

public class SortedTimeComparator implements Comparator<SortedFlight>
{
    public int compare(SortedFlight left, SortedFlight right)
    {
        return Long.valueOf(left.getDepartureTime()).compareTo(right.getDepartureTime());
    }
}
