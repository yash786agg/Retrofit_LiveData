package app.com.extras;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import app.com.ixigotest.R;

/*
 * Created by Yash on 14/1/18.
 */

public class DrawableColor
{
    // setTvDrawableColor is used to set the textview of fligtname and color value of flight icon based on flight name.
    public static void setTvDrawableColor(TextView textView, Context context, String airLine)
    {
        textView.setText(airLine);

        for (Drawable drawable : textView.getCompoundDrawables())
        {
            if (drawable != null)
            {
                if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.spiceJet)))
                {
                    drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN));

                    textView.setTextColor(ContextCompat.getColor(context, R.color.red));
                }
                else  if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.airIndia)))
                {
                    drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(context, R.color.ornageRed), PorterDuff.Mode.SRC_IN));

                    textView.setTextColor(ContextCompat.getColor(context, R.color.ornageRed));
                }
                else  if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.goAir)))
                {
                    drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN));

                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                }
                else  if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.indiGo)))
                {
                    drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(context, R.color.indiGo), PorterDuff.Mode.SRC_IN));

                    textView.setTextColor(ContextCompat.getColor(context, R.color.indiGo));
                }
                else  if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.jetAirways)))
                {
                    drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(context, R.color.darkBlue), PorterDuff.Mode.SRC_IN));

                    textView.setTextColor(ContextCompat.getColor(context, R.color.darkBlue));
                }
            }
        }
    }

    // setImgvDrawableColor is used to set the color value of flight icon based on AirLine name.

    public static void setImgvDrawableColor(ImageView flight,Context context,String airLine)
    {
        if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.spiceJet)))
        {
            flight.setColorFilter(ContextCompat.getColor(context, R.color.red));
        }
        else  if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.airIndia)))
        {
            flight.setColorFilter(ContextCompat.getColor(context, R.color.colorOrange));
        }
        else  if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.goAir)))
        {
            flight.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }
        else  if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.indiGo)))
        {
            flight.setColorFilter(ContextCompat.getColor(context, R.color.indiGo));
        }
        else  if(airLine.equalsIgnoreCase(context.getResources().getString(R.string.jetAirways)))
        {
            flight.setColorFilter(ContextCompat.getColor(context, R.color.darkBlue));
        }
    }
}
