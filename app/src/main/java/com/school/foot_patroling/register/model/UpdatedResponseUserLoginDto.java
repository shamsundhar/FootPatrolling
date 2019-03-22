
package com.school.foot_patroling.register.model;

import java.util.List;

public class UpdatedResponseUserLoginDto {

    private Integer count;
    private List<UserLoginDto_> userLoginDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserLoginDto_> getUserLoginDtos() {
        return userLoginDtos;
    }

    public void setUserLoginDtos(List<UserLoginDto_> userLoginDtos) {
        this.userLoginDtos = userLoginDtos;
    }

}
