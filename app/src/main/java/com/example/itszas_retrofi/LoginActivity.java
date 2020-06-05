package com.example.itszas_retrofi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itszas_retrofi.Client.ApiItszas;
import com.example.itszas_retrofi.model.Login;
import com.example.itszas_retrofi.model.User;

import java.lang.annotation.IncompleteAnnotationException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static String token, user_id;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiItszas.URL_API)
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiItszas apiItszas = retrofit.create(ApiItszas.class);

    EditText usuario, password;
    Button btnlogin, btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnregister = (Button) findViewById(R.id.btnregister);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn(usuario.getText().toString(), password.getText().toString());
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SingUpActivity.class);
                startActivity(intent);
            }
        });

    }


    private void LogIn(String usuario, String password){
        Login login = new Login(usuario, password);
        Call<User> call = apiItszas.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    token = response.body().getToken();
                    user_id = response.body().getId();

                    Toast.makeText(getApplicationContext(), user_id, Toast.LENGTH_SHORT).show();

                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    main.putExtra("username", user_id);
                    main.putExtra("password", token);
                    startActivity(main);


                }
                else {
                    Toast.makeText(getApplicationContext(), "Datos Erroneos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Conexion", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
