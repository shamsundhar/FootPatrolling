package com.school.foot_patroling.register;

import com.school.foot_patroling.register.model.DeviceAuthModel;
import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.register.model.RegistrationRequestModel;
import com.school.foot_patroling.reports.ReportModel;
import com.school.foot_patroling.reports.ReportResult;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

 @POST
 @Headers({"Content-Type: application/json"})
 Observable<Object> fileUpload(@Url String url, @Body byte[] fileMap);

 @POST
 @Multipart
 @Headers({"Content-Type: multipart/form-data"})
 Observable<String> fileUpload2(@Url String url, @Part("file") RequestBody file);
 //Observable<String> fileUpload2(@Url String url, @Body RequestBody requestBody);

 //Observable<String> fileUpload2(@Url String url, @Part("file\"; filename=\"shyamtest.png\"") RequestBody requestBody);
//@Body RequestBody requestBody
  //  Call<ResponseBody> uploadImage(@Part MultipartBody.Part file, @Part("name") RequestBody requestBody);
  //@Part("name") RequestBody requestBody2
   // @Body parts: MultipartBody
 // @Part("file\"; filename=\"pp.png\" ") RequestBody file
}
