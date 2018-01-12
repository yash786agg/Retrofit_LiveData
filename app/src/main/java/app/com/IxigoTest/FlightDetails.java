package app.com.IxigoTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import app.com.Adapter.ProvidersAdapter;
import app.com.Extras.Utility;
import app.com.model.Appendix;
import app.com.model.FaresData;
import app.com.model.FlightResponse;
import app.com.model.Flights;

/*
 * Created by Yash on 12/1/18.
 */

public class FlightDetails extends AppCompatActivity implements View.OnClickListener
{
    private int indexPosition;
    private ArrayList<Flights> flights;
    private ProvidersAdapter providersAdapter;
    private RecyclerView providersRclv;
    private Button bookProvider;
    private Flights flightsData;
    private Appendix appendix;
    private boolean showProvider = false;

    private static final String TAG = "Main";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_details);

        flights = getIntent().getParcelableArrayListExtra("flights");

        Log.i(TAG, "onCreate flights: "+flights);

        indexPosition = getIntent().getIntExtra("indexPosition", 0);

        Log.i(TAG, "onCreate indexPosition: "+indexPosition);

        appendix = (Appendix) getIntent().getSerializableExtra("appendix");

        Log.i(TAG, "onCreate appendix: "+appendix.toString());

        Log.i(TAG, "onCreate Airports: "+appendix.getAirports());
        Log.i(TAG, "onCreate Airlines: "+appendix.getAirlines());
        Log.i(TAG, "onCreate Providers: "+appendix.getProviders());

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        TextView flightJourney = findViewById(R.id.flightJourney);

        TextView flightDate = findViewById(R.id.flightDate);

        flightsData = flights.get(indexPosition);

        String journey = flights.get(0).getOriginCode() +this.getResources().getString(R.string.dash)+flights.get(0).getDestinationCode();

        flightJourney.setText(journey);

        flightDate.setText(Utility.getCurrentDate());

        TextView flightLocation = findViewById(R.id.flightLocation);

        String location = appendix.getAirports().get(flightsData.getOriginCode())+" "+this.getResources().getString(R.string.dash)
                +" "+ appendix.getAirports().get(flightsData.getDestinationCode());

        flightLocation.setText(location);

        TextView flightClass = findViewById(R.id.flightClass);

        String flightType = appendix.getAirlines().get(flightsData.getAirlineCode())+" "+this.getResources().getString(R.string.dash)
                +" "+ flightsData.getFlightClass();

        flightClass.setText(flightType);

        TextView flightOriginTime = findViewById(R.id.flightOriginTime);

        String originTime = flights.get(0).getOriginCode()+" "+ Utility.convertTimeStampToTime(flightsData.getDepartureTime());

        flightOriginTime.setText(originTime);

        TextView flightOriginDay = findViewById(R.id.flightOriginDay);

        flightOriginDay.setText(Utility.getCurrentDate());

        TextView flightDestTime = findViewById(R.id.flightDestTime);

        String destTime = flights.get(0).getDestinationCode()+" "+ Utility.convertTimeStampToTime(flightsData.getArrivalTime());

        flightDestTime.setText(destTime);

        TextView flightDestDay = findViewById(R.id.flightDestDay);

        flightDestDay.setText(Utility.getCurrentDate());

        bookProvider = findViewById(R.id.bookProvider);

        bookProvider.setOnClickListener(this);

        providersRclv = findViewById(R.id.providersRclv);

        Log.i("Main", "Provider: "+flightsData.getFareArrayList().size());

        if(flightsData.getFareArrayList().size() > 2)
        {
            showProvider = false;
        }
        else
        {
            showProvider = true;
            bookProvider.setVisibility(View.GONE);
        }

        providersAdapter = new ProvidersAdapter(this, (ArrayList<FaresData>) flightsData.getFareArrayList(),appendix,showProvider);

        providersRclv.setLayoutManager(new LinearLayoutManager(this));

        providersRclv.setAdapter(providersAdapter);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.backButton:

                moveToPreviousScreen();

                break;

            case R.id.bookProvider:

                Log.i(TAG, "onClick bookProvider: "+showProvider);

                if(!showProvider)
                {
                    Log.i(TAG, "onClick bookProvider if before: "+showProvider);

                    bookProvider.setText(FlightDetails.this.getResources().getString(R.string.viewLess));
                    showProvider = true;

                    Log.i(TAG, "onClick bookProvider if after: "+showProvider);
                }
                else
                {
                    Log.i(TAG, "onClick bookProvider else before: "+showProvider);

                    bookProvider.setText(FlightDetails.this.getResources().getString(R.string.viewAll));
                    showProvider = false;

                    Log.i(TAG, "onClick bookProvider else after: "+showProvider);
                }

                providersAdapter.updateProvider((ArrayList<FaresData>)flightsData.getFareArrayList(),appendix,showProvider);

                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        moveToPreviousScreen();
    }

    private void moveToPreviousScreen()
    {
        finish();
    }
}
