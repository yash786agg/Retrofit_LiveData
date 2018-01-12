package app.com.IxigoTest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import app.com.Adapter.RecyclerViewAdapter;
import app.com.Extras.RcylcVItemClick;
import app.com.Extras.SortFlights;
import app.com.Extras.SortedFareComparator;
import app.com.Extras.SortedTimeComparator;
import app.com.Extras.Utility;
import app.com.ViewModel.FlightsViewModel;
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

        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);

        backButton.setVisibility(View.GONE);
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

        FlightsViewModel viewModel = ViewModelProviders.of(this).get(FlightsViewModel.class);
        viewModel.getFlight().observe(this, new Observer<Response<FlightResponse>>()
        {
            @Override
            public void onChanged(@Nullable Response<FlightResponse> flightResponseResponse)
            {
                Log.i("Main", "onChanged: "+flightResponseResponse);
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

    private void setData()
    {
        String journey = flights.get(0).getOriginCode() +MainActivity.this.getResources().getString(R.string.dash)+flights.get(0).getDestinationCode();

        flightJourney.setText(journey);

        flightDate.setText(Utility.getCurrentDate());

        sortedFlights = SortFlights.getSortedFlight(flights,sortedFlights);

        int sortType = 0;// Cheap --> 0 and Early ---> 1
        sortFlights(sortType);
    }

    private void sortFlights(int sortType)
    {
        if(sortType == 0)
        {
            cheap.setSelected(true);
            early.setSelected(false);

            Collections.sort(sortedFlights, new SortedFareComparator());
        }
        else
        {
            early.setSelected(true);
            cheap.setSelected(false);

            Collections.sort(sortedFlights, new SortedTimeComparator());
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
    @OnClick({R.id.cheap, R.id.early,R.id.Portrait,R.id.Landscape})
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

            case R.id.Portrait:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;

            case R.id.Landscape:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
        }
    }
}
