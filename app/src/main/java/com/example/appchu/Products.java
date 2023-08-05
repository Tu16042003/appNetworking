package com.example.appchu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.appchu.adapter.ProductsAdapter;
import com.example.appchu.data.IRetrofit;
import com.example.appchu.data.RetrofitHelper;
import com.example.appchu.dto.ListProductsReponseDTO;
import com.example.appchu.inteface.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Products extends AppCompatActivity {

    RecyclerView listView;
    ProductsAdapter adapter;
    List<ListProductsReponseDTO.ProductsReponseDTO> pdlist;
    IRetrofit iRetrofit;
    SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        listView = findViewById(R.id.listrecycler);
        searchView = findViewById(R.id.search);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        listView.setLayoutManager(manager);
        pdlist = new ArrayList<ListProductsReponseDTO.ProductsReponseDTO>();

        iRetrofit = RetrofitHelper.createService(IRetrofit.class);

        adapter = new ProductsAdapter(pdlist,getApplicationContext(),new ItemClickListener(){
            @Override
            public void onItemClick(ListProductsReponseDTO.ProductsReponseDTO view, int position) {
                Intent intent = new Intent(Products.this, Detail.class);
                intent.putExtra("productId", position+1);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
        iRetrofit.getAll("").enqueue(listProductsCallback);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                newText = searchView.getQuery().toString();
                iRetrofit.getAll(newText).enqueue(listProductsCallback);
                return false;
            }
        });


    }


    Callback<ListProductsReponseDTO> listProductsCallback = new Callback<ListProductsReponseDTO>() {
        @Override
        public void onResponse(Call<ListProductsReponseDTO> call, Response<ListProductsReponseDTO> response) {
            if (response.isSuccessful()){
                ListProductsReponseDTO reponseDTO = response.body();
                pdlist.clear();
                pdlist.addAll(reponseDTO.getProducts());
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Call<ListProductsReponseDTO> call, Throwable t) {
            Log.d(">>> products", "onFailure: " + t.getMessage());
        }
    };

}