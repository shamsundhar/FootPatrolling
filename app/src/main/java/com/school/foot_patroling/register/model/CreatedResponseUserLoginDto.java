
package com.school.foot_patroling.register.model;

import java.util.List;

public class CreatedResponseUserLoginDto {

    private Integer count;
    private List<UserLoginDto> userLoginDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserLoginDto> getUserLoginDtos() {
        return userLoginDtos;
    }

    public void setUserLoginDtos(List<UserLoginDto> userLoginDtos) {
        this.userLoginDtos = userLoginDtos;
    }

}
