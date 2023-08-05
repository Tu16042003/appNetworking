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
import com.example.appchu.dto.RegisterRequestDTO;
import com.example.appchu.dto.RegisterResponseDTO;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.textview.MaterialTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity {

//    DatabaseHelper databaseHelper;
    EditText edtname, edtpassword, edtemail;
    TextView btn_login;
    Button btn_register;

    // import interface retrofit
    IRetrofit iRetrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        databaseHelper = new DatabaseHelper(this);
        edtname = findViewById(R.id.name);
        edtpassword = findViewById(R.id.password);
        edtemail = findViewById(R.id.email);

        iRetrofit = RetrofitHelper.createService(IRetrofit.class);

        btn_register = findViewById(R.id.btn_register);
        btn_login =findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                if(username.equals("") || password.equals("") || confirm_password.equals("")){
////                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
////                }else{
////                    if(password.equals(confirm_password)){
////                        Boolean checkusername = databaseHelper.CheckUsername(username);
////                       if(checkusername == true){
////                            Boolean insert = databaseHelper.Insert(username, password);
////                            if(insert == true){
////                               Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
////                           }
////                       }else{
////                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
////                        }
////                    }else{
////                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
////                    }
////                }
//            }
//        });

    }

    public void onRegister(View view) {
        String name = edtname.getText().toString();
        String password = edtpassword.getText().toString();
        String email = edtemail.getText().toString();
        // bat loi
        if (name.equals("") || password.equals("") || email.equals("")) {
            Toast.makeText(Register.this, "Chua nhap", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Register.this, "thanh cong", Toast.LENGTH_SHORT).show();
        }
        //
        RegisterRequestDTO requestDTO = new RegisterRequestDTO(email, password, name);
        iRetrofit.register(requestDTO).enqueue(register);
    }
    Callback<RegisterResponseDTO> register = new Callback<RegisterResponseDTO>() {
            @Override
            public void onResponse(Call<RegisterResponseDTO> call, Response<RegisterResponseDTO> response) {
                if (response.isSuccessful()){
                    RegisterResponseDTO registerResponse = response.body();
                    if (registerResponse.isStatus()){
                        //chuyen login
                        Toast.makeText(Register.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(Register.this, "khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseDTO> call, Throwable t) {
                Log.d(">>> register", "onFailure: " + t.getMessage());
            }
        };

}