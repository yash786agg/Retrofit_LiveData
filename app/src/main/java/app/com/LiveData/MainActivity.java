package app.com.LiveData;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import app.com.adapter.RecyclerViewAdapter;
import app.com.extras.RcylcVItemClick;
import app.com.extras.SortFlights;
import app.com.extras.SortedFareComparator;
import app.com.extras.SortedTimeComparator;
import app.com.extras.Utility;
import app.com.viewmodel.FlightsViewModel;
import app.com.model.Appendix;
import app.com.model.FlightResponse;
import app.com.model.Flights;
import app.com.model.SortedFlight;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RcylcVItemClick,View.OnClickListener
{
    //Application supports the latest Android Architecture Component
    //Code Structure: MVVM (Model View View Controller) with Live Data and View Model are Used.
    //Application support both Potrait and Landsacpe mode.

    static
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ArrayList<Flights> flights;
    private ArrayList<SortedFlight> sortedFlights;
    private Appendix appendix;
    private RecyclerViewAdapter recyclerViewAdapter;
    @BindView(R.id.flightJourney) TextView flightJourney;
    @BindView(R.id.flightDate) TextView flightDate;
    @BindView(R.id.cheap) TextView cheap;
    @BindView(R.id.early) TextView early;
    @BindView(R.id.flightsRclv) RecyclerView flightsRclv;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.backButton) ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*  Intialization of ButterKnife */

        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);

        backButton.setVisibility(View.GONE);

        /*  Intialization of ArrayList */

        flights = new ArrayList<>();

        /* sortedFlights ArrayList is a shallow copy of flights arrayList to sort the flight based on fares(Cheap to High)
        * and Early basics without affecting the original arrayList*/

        sortedFlights = new ArrayList<>();

        /*  Intialization of RecyclerView Adapter */

        recyclerViewAdapter = new RecyclerViewAdapter(this,sortedFlights,appendix);

        /* We used LinearLayoutManager to show Data in List Form */

        flightsRclv.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter.setOnItemClickListener(this);

        flightsRclv.setAdapter(recyclerViewAdapter);

        // Network connectivity check to check whether Internet is connected or not.

        networkCheck();
    }

    private void networkCheck()
    {
        // Observe Flight data
        FlightsViewModel viewModel = ViewModelProviders.of(this).get(FlightsViewModel.class);
        viewModel.getFlight().observe(this, new Observer<Response<FlightResponse>>()
        {
            @Override
            public void onChanged(@Nullable Response<FlightResponse> flightResponseResponse)
            {
                if (flightResponseResponse != null)
                {
                    progressBar.setVisibility(View.GONE);

                    flights = (ArrayList<Flights>) flightResponseResponse.body().getFlights();

                    appendix = flightResponseResponse.body().getAppendix();

                    setData();
                }
            }
        });
    }

    /** Creates list of flight */
    private void setData()
    {
        String journey = flights.get(0).getOriginCode() +MainActivity.this.getResources().getString(R.string.dash)+flights.get(0).getDestinationCode();

        flightJourney.setText(journey);

        flightDate.setText(Utility.getCurrentDate());

        /* Sort flight based on Fare array of each flight data
        * @param flights and sortedFlights arrayList.
        * @return sortedFlights array based on cheapest price of each and every flight.*/
        sortedFlights = SortFlights.getSortedFlight(flights,sortedFlights);

        int sortType = 0;// Cheap --> 0 and Early ---> 1
        sortFlights(sortType);
    }

    /*Sort flight based on cheapest fare or time of each flight */
    private void sortFlights(int sortType)
    {
        if(sortType == 0) // sortType --> 0 means sort based on cheapest fare
        {
            cheap.setSelected(true);
            early.setSelected(false);

            Collections.sort(sortedFlights, new SortedFareComparator());
        }
        else// sortType --> 1 means sort based on flight timing
        {
            early.setSelected(true);
            cheap.setSelected(false);

            Collections.sort(sortedFlights, new SortedTimeComparator());
        }

        //setting all the data to adapter after all sorting operation
        recyclerViewAdapter.addItems(sortedFlights,appendix);
    }

    @Override//Item Click listener for recyclerview item
    public void onItemClick(View view, int position)
    {
        Intent flightDetailIntent = new Intent(MainActivity.this,FlightDetails.class);
        flightDetailIntent.putParcelableArrayListExtra(MainActivity.this.getResources().getString(R.string.flights),flights);
        flightDetailIntent.putExtra(MainActivity.this.getResources().getString(R.string.indexPosition),sortedFlights.get(position).getIndexPosition());
        flightDetailIntent.putExtra(MainActivity.this.getResources().getString(R.string.appendix),appendix);
        startActivity(flightDetailIntent);
    }

    @Override//Click listener on cheap and early text to perform and show data based on sort
    @OnClick({R.id.cheap, R.id.early})
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.cheap:

                sortFlights(0);// Cheap --> 0 and Early ---> 1

                break;

            case R.id.early:

                sortFlights(1);// Cheap --> 0 and Early ---> 1

                break;
        }
    }
}
