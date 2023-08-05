package com.example.appchu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchu.data.IRetrofit;
import com.example.appchu.data.RetrofitHelper;
import com.example.appchu.dto.LoginRequestDTO;
import com.example.appchu.dto.LoginResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    Button btn_llogin;
    TextView  btn_lregister;
    EditText email, password;
    IRetrofit iRetrofit;
//    DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        databaseHelper = new DatabaseHelper(this);
        email =findViewById(R.id.email);
        password =findViewById(R.id.password);
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);

        btn_llogin =findViewById(R.id.btn_llogin);
        btn_lregister =findViewById(R.id.btn_lregister);

        btn_lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onLogin(View view){
        String username = email.getText().toString();
        String pass = password.getText().toString();
        //        check dang nhap
        if(username.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(), "Chưa nhập username or password", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "thành công", Toast.LENGTH_SHORT).show();
        }
        LoginRequestDTO requestDTO = new LoginRequestDTO(username, pass);
        iRetrofit.login(requestDTO).enqueue(login);
    }
    Callback<LoginResponseDTO> login = new Callback<LoginResponseDTO>() {
        @Override
        public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
            if (response.isSuccessful()){
                LoginResponseDTO loginResponse = response.body();
                if (loginResponse.isStatus()){
                    //chuyen main
                    Toast.makeText(Login.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Login.this, "khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };



}