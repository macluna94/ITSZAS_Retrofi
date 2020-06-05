package com.example.itszas_retrofi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.itszas_retrofi.Client.ApiItszas;
import com.example.itszas_retrofi.model.DatosItszas;
import com.example.itszas_retrofi.model.Usuario;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;



public class MainActivity extends AppCompatActivity {
    private static String token;
    private static String idUser;

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @NotNull
        @Override
        public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", " Bearer " +token)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();

    Retrofit.Builder builder = new Retrofit.Builder()
            .client(client)
            .baseUrl(ApiItszas.URL_API)
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiItszas apiItszas = retrofit.create(ApiItszas.class);

    TextView nombre, codigo, carrera,bonos, correo;
    ImageView img;
    SwipeRefreshLayout swipeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent= getIntent();
        String x_user = intent.getStringExtra("username");
        String x_pass = intent.getStringExtra("password");

        idUser = x_user;
        token = x_pass;


        nombre = (TextView) findViewById(R.id.textNombre);
        codigo = (TextView) findViewById(R.id.textCodigo);
        carrera = (TextView) findViewById(R.id.textCarrera);
        correo = (TextView) findViewById(R.id.textCorreo);
        bonos = (TextView) findViewById(R.id.textBonos);
        img = (ImageView) findViewById(R.id.img_user);


        swipeScreen = (SwipeRefreshLayout) findViewById(R.id.swipeScreen);

        getUsuario();

        swipeScreen.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsuario();
                swipeScreen.setRefreshing(false);
                Toast.makeText(getApplicationContext(), "Actualizado", Toast.LENGTH_SHORT).show();
            }
        });


    }



    private void getUsuario(){
        Call<DatosItszas> call = apiItszas.getUsuario(token, idUser);
        call.enqueue(new Callback<DatosItszas>() {
            @Override
            public void onResponse(Call<DatosItszas> call, Response<DatosItszas> response) {
                if (response.isSuccessful()){
                    nombre.setText(response.body().getUser());
                    codigo.setText(response.body().getCodigo());
                    carrera.setText(response.body().getCarrera());
                    bonos.setText(response.body().getBonos());
                    correo.setText(response.body().getCorreo());
                    Picasso.get().load(response.body().getImage()).placeholder(R.drawable.err_image).error(R.drawable.err_image).into(img);

                }else {
                    Toast.makeText(getApplicationContext(), "Server Error ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DatosItszas> call, Throwable t) {

            }
        });
    }


}
