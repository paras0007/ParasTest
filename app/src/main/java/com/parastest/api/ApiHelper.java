package com.parastest.api;


import com.parastest.model.ProductDetailsResponse;
import com.parastest.model.ProductListResponse;
import com.parastest.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiHelper {

    @GET("/admin/products.json")
    Call<ProductListResponse> getTrends(@Header(Constant.TOKEN_NAME) String token);

    @GET("/admin/products/{id}.json")
    Call<ProductDetailsResponse> getProductDetails(@Path("id") String id, @Header(Constant.TOKEN_NAME) String token);

}