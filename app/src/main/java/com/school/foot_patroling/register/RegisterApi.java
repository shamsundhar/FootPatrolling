package com.school.foot_patroling.register;

import com.school.foot_patroling.register.model.DeviceAuthModel;
import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.register.model.RegistrationRequestModel;
import com.school.foot_patroling.reports.ReportModel;
import com.school.foot_patroling.reports.ReportResult;

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
 Observable<MasterDto> register(@Url String url, @Body RegistrationRequestModel model);

 @GET
 @Headers({"Content-Type: application/json"})
 Observable<Object> getReportNames(@Url String url);

 @POST
 @Headers({"Content-Type: application/json"})
 Observable<ReportResult> executeReport(@Url String url, @Body ReportModel model);


}
