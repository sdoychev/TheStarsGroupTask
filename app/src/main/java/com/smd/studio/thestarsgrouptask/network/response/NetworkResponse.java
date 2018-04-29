package com.smd.studio.thestarsgrouptask.network.response;

import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ArrayOfObjStationData", strict = false)
public class NetworkResponse {
    @ElementList(inline = true)
    public List<TrainEntity> trains;

    public List<TrainEntity> getTrains() {
        return trains;
    }
}
