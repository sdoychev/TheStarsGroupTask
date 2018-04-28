package com.smd.studio.thestarsgrouptask.network;

import com.smd.studio.thestarsgrouptask.network.response.NetworkResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrainWebService {

    @GET("getStationDataByCodeXML")
    Call<NetworkResponse> getTrains(@Query("StationCode") String stationCode);
}
