package com.example.itszas_retrofi.Client;

import com.example.itszas_retrofi.model.DatosItszas;
import com.example.itszas_retrofi.model.Login;
import com.example.itszas_retrofi.model.User;
import com.example.itszas_retrofi.model.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiItszas {
    final static String URL_API = "http://192.168.1.74:5000/"; //"https://apibonositszas.herokuapp.com/";

    @POST("auth/login")
    Call<User> login(@Body Login login);
/*
    @GET("user_db/{id}")
    Call<Usuario> getUsuario(
            @Header("Authorization") String authToken,
            @Path("id") String idUser

    );
*/
    @GET("user_itszas/{username}")
    Call<DatosItszas> getUsuario(
            @Header("Authorization") String authToken,
            @Path("username") String idUser

    );
    @POST("auth/signup")
    Call<User> register(@Body Login login);


}
