package app.com.apiRequest;

import app.com.model.FlightResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/*
 * Created by Yash on 10/1/18.
 */

public interface ApiInterface
{
    @GET()
    Call<FlightResponse> getFightDeatils(@Url String url);
}
