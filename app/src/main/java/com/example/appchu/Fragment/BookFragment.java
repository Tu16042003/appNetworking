package com.example.appchu.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appchu.Detail;
import com.example.appchu.R;
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

public class BookFragment extends Fragment {

    RecyclerView listView;
    ProductsAdapter adapter;
    List<ListProductsReponseDTO.ProductsReponseDTO> pdlist;
    IRetrofit iRetrofit;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        listView = view.findViewById(R.id.listrecycler);

        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        listView.setLayoutManager(manager);
        pdlist = new ArrayList<ListProductsReponseDTO.ProductsReponseDTO>();

        iRetrofit = RetrofitHelper.createService(IRetrofit.class);

        adapter = new ProductsAdapter(pdlist, requireContext(), new ItemClickListener() {
            @Override
            public void onItemClick(ListProductsReponseDTO.ProductsReponseDTO view, int position) {
                Intent intent = new Intent(requireContext(), Detail.class);
                intent.putExtra("productId", position + 1);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
        iRetrofit.getAll("").enqueue(listProductsCallback);
        return view;
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