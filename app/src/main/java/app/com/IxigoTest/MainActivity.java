package app.com.IxigoTest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import app.com.Adapter.RecyclerViewAdapter;
import app.com.Extras.FareComparator;
import app.com.Extras.RcylcVItemClick;
import app.com.Extras.SortedFareComparator;
import app.com.Extras.SortedTimeComparator;
import app.com.Extras.Utility;
import app.com.ViewModel.FlightsViewModel;
import app.com.model.Appendix;
import app.com.model.FlightResponse;
import app.com.model.Flights;
import app.com.model.SortedFlight;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RcylcVItemClick,View.OnClickListener
{
    static
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ArrayList<Flights> flights;
    private ArrayList<SortedFlight> sortedFlights;
    private Appendix appendix;
    private RecyclerViewAdapter recyclerViewAdapter;
    private TextView flightJourney,flightDate;
    private  TextView cheap,early;
    private int sortType = 0;// Cheap --> 0 and Early ---> 1
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         *  Intialization of RecyclerView
         **/

        RecyclerView flightsRclv = findViewById(R.id.flightsRclv);

         /*
         *  Intialization of ArrayList
         **/

        flights = new ArrayList<>();

        sortedFlights = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter(this,sortedFlights,appendix);

        flightsRclv.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter.setOnItemClickListener(this);

        /*
         *  Above we used LinearLayoutManager to show Data in Listview Form
         *  Similarly GridLayoutManager --> Is used to Display Data in Gridview with same Size of Columns.
         *  Similarly StaggeredGridLayoutManager --> Is used to Display Data in Gridview with Different Size of Columns
         *  according to size of image or data.
         */

        flightsRclv.setAdapter(recyclerViewAdapter);

        flightJourney = findViewById(R.id.flightJourney);

        flightDate = findViewById(R.id.flightDate);

        cheap = findViewById(R.id.cheap);
        cheap.setOnClickListener(this);

        early = findViewById(R.id.early);
        early.setOnClickListener(this);

        FlightsViewModel viewModel = ViewModelProviders.of(this).get(FlightsViewModel.class);
        viewModel.getFlight().observe(this, new Observer<Response<FlightResponse>>()
        {
            @Override
            public void onChanged(@Nullable Response<FlightResponse> flightResponseResponse)
            {
                if (flightResponseResponse != null)
                {
                    flights = (ArrayList<Flights>) flightResponseResponse.body().getFlights();

                    appendix = flightResponseResponse.body().getAppendix();

                    String journey = flights.get(0).getOriginCode() +MainActivity.this.getResources().getString(R.string.dash)+flights.get(0).getDestinationCode();

                    flightJourney.setText(journey);

                    flightDate.setText(Utility.getCurrentDate());

                    for(int i = 0; i < flights.size(); i++)
                    {
                        Collections.sort(flights.get(i).getFareArrayList(), new FareComparator());

                        Log.i(TAG, "onBindViewHolder: "+flights.get(i).getFareArrayList().get(0).getFare());

                        SortedFlight sortedFlight = new SortedFlight();

                        sortedFlight.setIndexPosition(i);
                        sortedFlight.setOriginCode(flights.get(i).getOriginCode());
                        sortedFlight.setDestinationCode(flights.get(i).getDestinationCode());
                        sortedFlight.setDepartureTime(flights.get(i).getDepartureTime());
                        sortedFlight.setArrivalTime(flights.get(i).getArrivalTime());
                        sortedFlight.setFare(flights.get(i).getFareArrayList().get(0).getFare());
                        sortedFlight.setProviderId(flights.get(i).getFareArrayList().get(0).getProviderId());
                        sortedFlight.setAirlineCode(flights.get(i).getAirlineCode());
                        sortedFlight.setFlightClass(flights.get(i).getFlightClass());

                        sortedFlights.add(sortedFlight);
                    }

                    Log.i(TAG, "sortedFlights: "+ sortedFlights);

                    sortFlights(sortType);

                    //Log.i(TAG, "Main onResponse Code: "+flightResponseResponse.code());
                    Log.i(TAG, "Main Response: " + flights);
                    Log.i(TAG, "Main onResponse: "+flights.size());
                   // Log.i(TAG, "Main onResponse Appendix item: "+flightResponseResponse.body().getAppendix());
                   // Log.i(TAG, "Main onResponse Appendix item: "+flightResponseResponse.body().getAppendix().getAirports().get(flightResponseResponse.body().getFlights().get(1).getDestinationCode()));

                }
            }
        });
    }

    private void sortFlights(int sortType)
    {
        if(sortType == 0)
        {
            cheap.setSelected(true);
            early.setSelected(false);

            Collections.sort(sortedFlights, new SortedFareComparator());

            Log.i(TAG, "After sortedFlights Fare: "+ sortedFlights);
        }
        else
        {
            early.setSelected(true);
            cheap.setSelected(false);

            Collections.sort(sortedFlights, new SortedTimeComparator());

            Log.i(TAG, "After sortedFlights Time: "+ sortedFlights);
        }

        recyclerViewAdapter.addItems(sortedFlights,appendix);
    }

    @Override
    public void onItemClick(View view, int position)
    {

        Intent flightDetailIntent = new Intent(MainActivity.this,FlightDetails.class);
        flightDetailIntent.putParcelableArrayListExtra("flights",flights);
        flightDetailIntent.putExtra("indexPosition",sortedFlights.get(position).getIndexPosition());
        flightDetailIntent.putExtra("appendix",appendix);
        startActivity(flightDetailIntent);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.cheap:

                sortFlights(0);

                break;

            case R.id.early:

                sortFlights(1);

                break;
        }
    }
}
