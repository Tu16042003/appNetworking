package com.example.appchu.inteface;

import com.example.appchu.dto.ListProductsReponseDTO;

public interface ItemClickListener {
    void onItemClick(ListProductsReponseDTO.ProductsReponseDTO view, int position);
}
