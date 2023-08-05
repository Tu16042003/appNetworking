package com.example.appchu.dto;

import java.util.List;

public class ListProductsReponseDTO {
    private boolean status;
    private List<ProductsReponseDTO> products;

    public ListProductsReponseDTO() {
    }

    public ListProductsReponseDTO(boolean status, List<ProductsReponseDTO> products) {
        this.status = status;
        this.products = products;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ProductsReponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsReponseDTO> products) {
        this.products = products;
    }

    public class ProductsReponseDTO{
        private int id;
        private String name;
        private Float price;
        private int quantity;
        private String image;
        private String description;
        private int categogy_id;
        private String category_name;

        public ProductsReponseDTO(int id, String name, Float price, int quantity, String image, String description, int categogy_id, String category_name) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.image = image;
            this.description = description;
            this.categogy_id = categogy_id;
            this.category_name = category_name;
        }

        public ProductsReponseDTO() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getCategogy_id() {
            return categogy_id;
        }

        public void setCategogy_id(int categogy_id) {
            this.categogy_id = categogy_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }
}
