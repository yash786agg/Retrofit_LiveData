package app.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import app.com.extras.DrawableColor;
import app.com.extras.RcylcVItemClick;
import app.com.extras.Utility;
import app.com.ixigotest.R;
import app.com.model.Appendix;
import app.com.model.SortedFlight;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by Yash on 14/1/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{
    private ArrayList<SortedFlight> sortedFlights;
    private Appendix appendix;
    private LayoutInflater layoutInflater;
    private Context context;
    private RcylcVItemClick clickListener;

    public RecyclerViewAdapter(Context context, ArrayList<SortedFlight> sortedFlights,Appendix appendix)
    {
        /*
         * RecyclerViewAdapter Constructor to Initialize Data which we get from MainActivity
         */

        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.sortedFlights = sortedFlights;
        this.appendix = appendix;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        /*
         * LayoutInflater is used to Inflate the view
         * from adapter_layout
         * for showing data in RecyclerView
         */

        View view = layoutInflater.inflate(R.layout.adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.MyViewHolder holder, final int position)
    {
        /*
         * onBindViewHolder is used to Set all the respective data
         * to Textview form sortedFlights ArrayList.
         */

        SortedFlight flightsData = sortedFlights.get(position);

        if (!TextUtils.isEmpty(appendix.getAirlines().get(flightsData.getAirlineCode())))
        {
            String airLine = appendix.getAirlines().get(flightsData.getAirlineCode());

            DrawableColor.setTvDrawableColor(holder.flightName,context,airLine);
        }

        if (flightsData.getFare() != 0)
        {
            String fare = context.getResources().getString(R.string.rupee)+String.valueOf(flightsData.getFare());

            holder.flightFare.setText(fare);
        }

        if (flightsData.getDepartureTime() != 0)
        {
            holder.departureTime.setText(Utility.convertTimeStampToTime(flightsData.getDepartureTime()));
        }

        if (flightsData.getArrivalTime() != 0)
        {
            holder.arrivalTime.setText(Utility.convertTimeStampToTime(flightsData.getArrivalTime()));

        }

        if(flightsData.getDepartureTime() != 0 && flightsData.getArrivalTime() != 0)
        {
            holder.flightDuration.setText(Utility.differenceTimeStap(flightsData.getDepartureTime(),flightsData.getArrivalTime()));
        }

        if (!TextUtils.isEmpty(holder.flightDuration.getText().toString()))
        {
            String timeValue = ""+holder.flightDuration.getText().toString().charAt(1);

            if(Integer.parseInt(timeValue) <= 3)
            {
                holder.flightStatus.setText(context.getResources().getString(R.string.nonStop));
            }
        }
    }

    public void addItems(ArrayList<SortedFlight> sortedFlights,Appendix appendix)
    {
        /*
         * addItems method is to add items in the sortedFlights and notifiy the adapter for the data change.
         */

        this.sortedFlights = sortedFlights;
        this.appendix = appendix;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        /*
         * getItemCount is used to get the size of respective deliveryArrayList
         */

        return sortedFlights.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        /*
         * Return the view type of the item at position for the purposes of view recycling.
         */

        return position;
    }

    public void setOnItemClickListener(final RcylcVItemClick mItemClickListener)
    {
        this.clickListener = mItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @BindView(R.id.flightName) TextView flightName;
        @BindView(R.id.flightFare) TextView flightFare;
        @BindView(R.id.departureTime) TextView departureTime;
        @BindView(R.id.arrivalTime) TextView arrivalTime;
        @BindView(R.id.flightDuration) TextView flightDuration;
        @BindView(R.id.flightStatus) TextView flightStatus;

        /*
         * MyViewHolder is used to Initializing the view.
         */

        MyViewHolder(View itemView)
        {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (clickListener != null)
            {
                clickListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
}