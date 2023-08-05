package com.example.appchu.data;

import com.example.appchu.dto.DetailsReponseDTO;
import com.example.appchu.dto.ListProductsReponseDTO;
import com.example.appchu.dto.LoginRequestDTO;
import com.example.appchu.dto.LoginResponseDTO;
import com.example.appchu.dto.RegisterRequestDTO;
import com.example.appchu.dto.RegisterResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofit {
    @POST("/api/register.php")
    Call<RegisterResponseDTO> register(@Body RegisterRequestDTO body);

    @POST("/api/login.php")
    Call<LoginResponseDTO> login(@Body LoginRequestDTO body);

    @GET("/api/getAll.php")
    Call<ListProductsReponseDTO> getAll(@Query("keyword") String keyword);

    @GET("/api/getbyid.php")
    Call<DetailsReponseDTO> getById(@Query("id") int id);
}
