
package com.school.foot_patroling.register.model;


import java.util.List;

public class CreatedResponseOheLocationDto {

    private Integer count;

    public List<OheLocationDto> getOheLocationDtoList() {
        return oheLocationDtos;
    }

    public void setOheLocationDtoList(List<OheLocationDto> oheLocationDtos) {
        this.oheLocationDtos = oheLocationDtos;
    }

    private List<OheLocationDto> oheLocationDtos;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }



}
