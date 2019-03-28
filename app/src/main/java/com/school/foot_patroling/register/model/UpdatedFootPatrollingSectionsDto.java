
package com.school.foot_patroling.register.model;

import java.util.List;

public class UpdatedFootPatrollingSectionsDto {

    private Integer count;
    private List<FootPatrollingSectionsDto_> footPatrollingSectionsDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<FootPatrollingSectionsDto_> getFootPatrollingSectionsDtos() {
        return footPatrollingSectionsDtos;
    }

    public void setFootPatrollingSectionsDtos(List<FootPatrollingSectionsDto_> footPatrollingSectionsDtos) {
        this.footPatrollingSectionsDtos = footPatrollingSectionsDtos;
    }

}
