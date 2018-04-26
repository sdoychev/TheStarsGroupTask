package com.smd.studio.thestarsgrouptask.network;

import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrainWebService {

    @GET("/getStationDataByCodeXML")
    Call<List<TrainEntity>> getTrains(@Query("StationCode") String stationCode);
}
