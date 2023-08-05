package com.example.appchu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.appchu.R;
import com.example.appchu.dto.ListProductsReponseDTO;
import com.example.appchu.inteface.ItemClickListener;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.PdViewHolder> {
    private List<ListProductsReponseDTO.ProductsReponseDTO> product;
    private Context context;

    private ItemClickListener itemClickListener;


    public ProductsAdapter(List<ListProductsReponseDTO.ProductsReponseDTO> product, Context context, ItemClickListener itemClickListener) {
        this.product = product;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ProductsAdapter.PdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử sinh viên
        View listview =
                inflater.inflate(R.layout.item_products, parent, false);

        ProductsAdapter.PdViewHolder viewHolder = new ProductsAdapter.PdViewHolder(listview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.PdViewHolder holder, @SuppressLint("RecyclerView") int i) {
        ListProductsReponseDTO.ProductsReponseDTO products =(ListProductsReponseDTO.ProductsReponseDTO) product.get(i);

        holder.pdName.setText(products.getName());
        holder.pdPrice.setText(String.valueOf(products.getPrice()));

        if(products.getImage().contains("127.0.0.1")){
            products.setImage(products.getImage().replace("127.0.0.1", "10.0.2.2"));
        }
        Log.d(">>>>TAG", "onBindViewHolder: "+products.getImage());

        Glide.with(context)
                .load(products.getImage())
//                .placeholder(R.drawable.ic_person_pin_24)
                .apply(RequestOptions.circleCropTransform())
                .error(R.drawable.ic_book_24)
                .into(holder.pdImage);
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(products,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class PdViewHolder extends RecyclerView.ViewHolder {

        ImageView pdImage;
        TextView pdName,pdPrice;
        LinearLayout linear;
        public PdViewHolder(@NonNull View view) {
            super(view);
            pdImage = view.findViewById(R.id.productsImage);
            pdName = view.findViewById(R.id.productsName);
            pdPrice = view.findViewById(R.id.productsPrice);
            linear = view.findViewById(R.id.item);

        }
    }

}
