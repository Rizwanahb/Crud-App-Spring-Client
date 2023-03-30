package com.example.springclient.retrofit;

import com.example.springclient.model.Product;
import com.example.springclient.model.ProductResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductAPI {
    @Headers("Content-Type: application/json")
    @GET("productList")
    Call<List<Product>> getAllProducts();

     @GET("Product/{id}")
    Call<Product> getPersonById(@Path("pNo")int id);
    @POST("saveProduct")
    Call<ProductResponse> save(@Body Product product) ;

    @PUT("updateProduct")
    Call<ProductResponse> updateProduct(@Body Product product);

    @DELETE("deleteProduct/{pNo}")
    Call<ProductResponse> deleteProduct(@Path("pNo") int pNo);





}
