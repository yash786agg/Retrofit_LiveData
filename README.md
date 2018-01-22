# Retrofit_LiveData


Android Architecture Component with View Model and Live Data along with Retrofit for Api Request and ButterKnife to remove
the Boiler Plate code.


This sample showcases the following Architecture Components:

* [ViewModels](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html)
* [LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html)

The 3rd party Library used

* [Retrofit](http://square.github.io/retrofit/)
* [Butter Knife](http://jakewharton.github.io/butterknife/)

This sample contains two screens: a list of flights and a flight detail view.

#### Presentation layer

The presentation layer consists of the following components:
* A main activity display the list of flights.
* A FlightDetails to display a flight detail review with different providers.

The app uses a Model-View-ViewModel (MVVM) architecture for the presentation layer. Each of the fragments corresponds to a MVVM View. The View and ViewModel communicate  using LiveData and the following design principles:

* ViewModel objects don't have references to activities, fragments, or Android views. That would cause leaks on configuration changes, such as a screen rotation, because the system retains a ViewModel across the entire lifecycle of the corresponding view.
* ViewModel objects expose data using `LiveData` objects. `LiveData` allows you to observe changes to data across multiple components of your app without creating explicit and rigid dependency paths between them.
* Views, including the fragments used in this sample, subscribe to corresponding `LiveData` objects. Because `LiveData` is lifecycle-aware, it doesn’t push changes to the underlying data if the observer is not in an active state, and this helps to avoid many common bugs. This is an example of a subscription:

```java
viewModel.getFlight().observe(this, new Observer<Response<FlightResponse>>()
        {
            @Override
            public void onChanged(@Nullable Response<FlightResponse> flightResponseResponse)
            {
                if (flightResponseResponse != null)
                {
                    flights = (ArrayList<Flights>) flightResponseResponse.body().getFlights();

                    appendix = flightResponseResponse.body().getAppendix();
                }
            }
        });
  ```      
  #### Data layer
  ```java
  
    @SerializedName("flights")
    private List<Flights> flights;

    @SerializedName("appendix")
    private Appendix appendix;
```

Queries that return a `LiveData` object can be observed, so when  a change in one of the affected tables is detected, `LiveData` delivers a notification of that change to the registered observers.
