package com.example.network;
import java.util.Map;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface NetApi {
    @GET("/article/list/0/json")
    Call<ResponseBody> listRepos();

    //统一get请求
    @GET
    Observable<ResponseBody> getDynamicData(@Url String url);

    //统一post请求
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postDynamicData(@Url String url, @FieldMap Map<String, Object> map);
}
