package app.com.extras;

import java.util.Comparator;
import app.com.model.FaresData;

/*
 * Created by Yash on 14/1/18.
 */

public class FareComparator implements Comparator<FaresData>
{
    //sort price in ascending order from fare array inside flight array.
    public int compare(FaresData left, FaresData right)
    {
        return Integer.valueOf(left.getFare()).compareTo(right.getFare());
    }
}
