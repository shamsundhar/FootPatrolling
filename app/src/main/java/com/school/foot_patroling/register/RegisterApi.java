package com.school.foot_patroling.register;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterApi {

 // https://edsensewebtst.azurewebsites.net/api/Academic/GetDayWiseScheduleByUser?currentDate=08-20-2018
 @GET("Academic/GetDayWiseScheduleByUser")
 @Headers({"Content-Type: application/json"})
 Observable<String> register();
}
