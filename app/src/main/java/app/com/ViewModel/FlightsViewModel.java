package app.com.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import app.com.apiRequest.ApiInterface;
import app.com.apiRequest.ApiRequestSingleton;
import app.com.apiRequest.ApiUrl;
import app.com.model.FlightResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by Yash on 10/1/18.
 */

public class FlightsViewModel extends ViewModel
{


    private MutableLiveData<Response<FlightResponse>> flightsArrayList;

    public LiveData<Response<FlightResponse>> getFlight()
    {
        if (flightsArrayList == null)
        {
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
        //Call<JsonObject> call = apiService.getFightDeatils(ApiUrl.apiUrl);
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
            {
                // Log error here since request failed
            }
        });
    }
}
