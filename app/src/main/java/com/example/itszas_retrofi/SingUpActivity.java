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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingUpActivity extends AppCompatActivity {

    Button setregister;
    EditText usuario, passwordtxt1, passwordtxt2;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiItszas.URL_API)
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiItszas apiItszas = retrofit.create(ApiItszas.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        setregister = (Button) findViewById(R.id.setregister);
        usuario = (EditText) findViewById(R.id.usuariotxt);
        passwordtxt1 = (EditText) findViewById(R.id.passwordtxt1);
        passwordtxt2 = (EditText) findViewById(R.id.passwordtxt2);




        setregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = passwordtxt1.getText().toString();
                String y = passwordtxt2.getText().toString();

                if (x.contentEquals(y) && usuario.getText().length() !=0 ){
                    Toast.makeText(getApplicationContext(), "Iguales", Toast.LENGTH_SHORT).show();
                    SingUp(usuario.getText().toString(), y);

                }else {
                    Toast.makeText(getApplicationContext(), "No son Iguales", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void SingUp(String username, String password){
        Login login = new Login(username, password);
        Call<User> call = apiItszas.register(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "Registrado", Toast.LENGTH_SHORT).show();
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Usuario Ya Registrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

}
