package app.com.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import app.com.IxigoTest.R;
import app.com.model.Appendix;
import app.com.model.FaresData;

/*
 * Created by Yash on 12/1/18.
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
        /*
         * RecyclerViewAdapter Constructor to Initialize Data which we get from DeliveryList Fragment
         */

        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.faresData = faresData;
        this.appendix = appendix;
        this.showProvider = showProvider;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        /*
         * LayoutInflater is used to Inflate the view
         * from adapter_layout
         * for showing data in RecyclerView
         */

        View view = layoutInflater.inflate(R.layout.providers_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProvidersAdapter.MyViewHolder holder, final int position)
    {
        /*
         * onBindViewHolder is used to Set all the respective data
         * to Textview or Imagview form deliveryArrayList
         * ArrayList Object.
         */

        if(!showProvider)
        {
            Log.i("Main", "onClick bookProvider onBindViewHolder if: "+showProvider);

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
            Log.i("Main", "onClick bookProvider onBindViewHolder else: "+showProvider);

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
         * addItems method is to add items in the deliverArrayList and notifiy the adapter for the data change.
         */

        this.faresData = faresData;
        this.appendix = appendix;
        this.showProvider = showProvider;

        Log.i("Main", "onClick bookProvider updateProvider: "+showProvider);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        /*
         * getItemCount is used to get the size of respective deliveryArrayList
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
        TextView providerName,providerPrice;
        LinearLayout providersll;

        /*
         * MyViewHolder is used to Initializing the view.
         */

        MyViewHolder(View itemView)
        {
            super(itemView);

            providerName = itemView.findViewById(R.id.providerName);

            providerPrice = itemView.findViewById(R.id.providerPrice);

            providersll = itemView.findViewById(R.id.providersll);

            itemView.setTag(itemView);
        }
    }
}