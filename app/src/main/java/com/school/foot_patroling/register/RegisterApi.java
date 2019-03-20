package com.school.foot_patroling.register;

import com.school.foot_patroling.register.model.DeviceAuthModel;
import com.school.foot_patroling.register.model.RegistrationRequestModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RegisterApi {

 @POST
 @Headers({"Content-Type: application/json"})
 Observable<DeviceAuthModel> register(@Url String url, @Body RegistrationRequestModel model);
}
