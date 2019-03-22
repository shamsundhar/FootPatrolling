
package com.school.foot_patroling.register.model;

import java.util.List;

public class CreatedFootPatrollingSectionsDto {

    private Integer count;
    private List<FootPatrollingSectionsDto> footPatrollingSectionsDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<FootPatrollingSectionsDto> getFootPatrollingSectionsDtos() {
        return footPatrollingSectionsDtos;
    }

    public void setFootPatrollingSectionsDtos(List<FootPatrollingSectionsDto> footPatrollingSectionsDtos) {
        this.footPatrollingSectionsDtos = footPatrollingSectionsDtos;
    }

}
