package com.example.appchu.dto;

public class DetailsReponseDTO {
    private boolean status;
    private ListProductsReponseDTO.ProductsReponseDTO products;

    public DetailsReponseDTO(boolean status, ListProductsReponseDTO.ProductsReponseDTO products) {
        this.status = status;
        this.products = products;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ListProductsReponseDTO.ProductsReponseDTO getProducts() {
        return products;
    }

    public void setProducts(ListProductsReponseDTO.ProductsReponseDTO products) {
        this.products = products;
    }
}
