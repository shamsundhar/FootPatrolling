package com.school.foot_patroling.register.model;

import java.util.List;

public class AppToServerCreatedResponseObservationsDto {
    private String count;
    private List<Observation> observationsDtos;


    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Observation> getObservationsDtos() {
        return observationsDtos;
    }

    public void setObservationsDtos(List<Observation> observationsDtos) {
        this.observationsDtos = observationsDtos;
    }
}
