package app.com.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import app.com.apirequest.ApiInterface;
import app.com.apirequest.ApiRequestSingleton;
import app.com.apirequest.ApiUrl;
import app.com.model.FlightResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by Yash on 14/1/18.
 */

public class FlightsViewModel extends ViewModel
{
    private MutableLiveData<Response<FlightResponse>> flightsArrayList;

    public LiveData<Response<FlightResponse>> getFlight()
    {
        if (flightsArrayList == null)
        {
            //  Intialization of MutableLiveData.

            flightsArrayList = new MutableLiveData<>();

            loadFlights();
        }

        return flightsArrayList;
    }

    private void loadFlights()
    {
        // Asyncronous operation to fetch Flights.

        ApiInterface apiService = ApiRequestSingleton.getClient().create(ApiInterface.class);

        //get call object for the request
        Call<FlightResponse> call = apiService.getFightDeatils(ApiUrl.apiUrl);

        // execute network request
        call.enqueue(new Callback<FlightResponse>()
        {
            @Override
            public void onResponse(Call<FlightResponse>call, Response<FlightResponse> response)
            {
               // ArrayList<Flights> flights = (ArrayList<Flights>) response.body().getFlights();

                flightsArrayList.setValue(response);
            }

            @Override
            public void onFailure(Call<FlightResponse>call, Throwable t)
            {}
        });
    }
}
