package app.com.apirequest;

import app.com.model.FlightResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/*
 * Created by Yash on 14/1/18.
 */

public interface ApiInterface
{
    @GET// @GET is the type of request
    Call<FlightResponse> getFightDeatils(@Url String url);//@Url We will pass the Url using @Url tag as it will ignore the base url
}
