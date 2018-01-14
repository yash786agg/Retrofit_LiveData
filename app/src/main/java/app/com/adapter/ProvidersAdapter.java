package app.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import app.com.ixigotest.R;
import app.com.model.Appendix;
import app.com.model.FaresData;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by Yash on 14/1/18.
 */

public class ProvidersAdapter extends RecyclerView.Adapter<ProvidersAdapter.MyViewHolder>
{
    private ArrayList<FaresData> faresData;
    private Appendix appendix;
    private LayoutInflater layoutInflater;
    private Context context;
    private boolean showProvider = false;

    public ProvidersAdapter(Context context, ArrayList<FaresData> faresData,Appendix appendix,boolean showProvider)
    {
        // RecyclerViewAdapter Constructor to Initialize Data which we get from FlightDetails Activity

        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.faresData = faresData;
        this.appendix = appendix;
        this.showProvider = showProvider;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // LayoutInflater is used to Inflate the view from adapter_layout for showing data in RecyclerView.

        View view = layoutInflater.inflate(R.layout.providers_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProvidersAdapter.MyViewHolder holder, final int position)
    {
        // onBindViewHolder is used to Set all the respective data to Textview form faresData ArrayList

        if(!showProvider)
        {
            if(position <= 1)
            {
                setItems(holder,position);
            }
            else
            {
                holder.providersll.setVisibility(View.GONE);
            }
        }
        else
        {
            holder.providersll.setVisibility(View.VISIBLE);
            setItems(holder,position);
        }
    }

    private void setItems(final ProvidersAdapter.MyViewHolder holder, final int position)
    {
        FaresData fares = faresData.get(position);

        if (fares.getProviderId() != 0)
        {
            String provider = appendix.getProviders().get(""+fares.getProviderId());

            holder.providerName.setText(provider);
        }

        if (fares.getFare() != 0)
        {
            String fare = context.getResources().getString(R.string.rupee)+String.valueOf(fares.getFare());

            holder.providerPrice.setText(fare);
        }
    }

    public void updateProvider(ArrayList<FaresData> faresData,Appendix appendix,boolean showProvider)
    {
        /*
         * updateProvider method is to update items in the faresData based on showProvider boolean
         * and notifiy the adapter for the data change.
         */

        this.faresData = faresData;
        this.appendix = appendix;
        this.showProvider = showProvider;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        /*
         * getItemCount is used to get the size of respective faresData ArrayList
         */

        return faresData.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        /*
         * Return the view type of the item at position for the purposes of view recycling.
         */

        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.providerName) TextView providerName;
        @BindView(R.id.providerPrice) TextView providerPrice;
        @BindView(R.id.providersll) LinearLayout providersll;

        // MyViewHolder is used to Initializing the view.

        MyViewHolder(View itemView)
        {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setTag(itemView);
        }
    }
}