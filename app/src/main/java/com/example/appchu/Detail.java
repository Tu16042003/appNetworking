package com.example.appchu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.appchu.data.IRetrofit;
import com.example.appchu.data.RetrofitHelper;
import com.example.appchu.dto.DetailsReponseDTO;
import com.example.appchu.dto.ListProductsReponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    IRetrofit iRetrofit;
    int productId = -1;
    TextView name,price;
    ImageView image,goback;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name = findViewById(R.id.Name);
        price =findViewById(R.id.Price);
        image = findViewById(R.id.Image);
        goback = findViewById(R.id.goback);
        productId = getIntent().getIntExtra("productId",-1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
        iRetrofit.getById(productId).enqueue(detailsCallback);
        Toast.makeText(this, ""+productId, Toast.LENGTH_SHORT).show();
    }
    Callback<DetailsReponseDTO> detailsCallback = new Callback<DetailsReponseDTO>() {
        @Override
        public void onResponse(Call<DetailsReponseDTO> call, Response<DetailsReponseDTO> response) {
            if (response.isSuccessful()){
                DetailsReponseDTO reponseDTO = response.body();
                ListProductsReponseDTO.ProductsReponseDTO pbd = reponseDTO.getProducts();
                name.setText(pbd.getName());
                price.setText(String.valueOf(pbd.getPrice()));
                // load ảnh cần đổi host
                if(pbd.getImage().contains("127.0.0.1")){
                    pbd.setImage(pbd.getImage().replace("127.0.0.1", "10.0.2.2"));
                }
                Log.d(">>>>>>>", "detail"+pbd.getImage());
                Glide.with(Detail.this)
                        .load(pbd.getImage())
//                        .apply(RequestOptions.circleCropTransform())
                        .error(R.drawable.ic_book_24)
                        .into(image);
            }
        }

        @Override
        public void onFailure(Call<DetailsReponseDTO> call, Throwable t) {

        }

    };
    public void onBack(View view){
        onBackPressed();
    }

}