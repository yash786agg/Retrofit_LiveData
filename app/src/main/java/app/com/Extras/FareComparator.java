package app.com.Extras;

import java.util.Comparator;
import app.com.model.FaresData;

/*
 * Created by Yash on 11/1/18.
 */

public class FareComparator implements Comparator<FaresData>
{
    public int compare(FaresData left, FaresData right)
    {
        return Integer.valueOf(left.getFare()).compareTo(right.getFare());
    }
}
