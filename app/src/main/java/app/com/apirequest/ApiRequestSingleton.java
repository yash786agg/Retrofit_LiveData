package app.com.apirequest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by Yash on 14/1/18.
 */

public class ApiRequestSingleton
{
    private static final String BASE_URL = "http://www.mocky.io/v2/";
    private static Retrofit retrofit;

    public static Retrofit getClient()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
