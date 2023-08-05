package com.example.appchu.adapter;

import android.content.Context;
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
import com.example.appchu.R;
import com.example.appchu.model.Comic;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder>{

    List list;
    // Lưu Context để dễ dàng truy cập
    Context context;

    public ComicAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View listview =
                inflater.inflate(R.layout.item_comic, parent, false);

        ComicViewHolder viewHolder = new ComicViewHolder(listview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        Comic comic = (Comic) list.get(position);
        holder.tieude.setText(comic.getTieude());
        holder.theloai.setText(comic.getTheloai());
        holder.luotdoc.setText(String.valueOf(comic.getLuotdoc()));


        Glide.with(context)
                .load(comic.getAnh())
                .placeholder(R.drawable.ic_person_pin_24)
                .error(R.drawable.ic_book_24)
                .into(holder.anhtruyen);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ComicViewHolder extends RecyclerView.ViewHolder {
        ImageView anhtruyen;
        TextView tieude,theloai,luotdoc;
        LinearLayout Linear;

        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            anhtruyen = itemView.findViewById(R.id.anhtruyen);
            tieude = itemView.findViewById(R.id.tieude);
            theloai = itemView.findViewById(R.id.theloai);
            luotdoc = itemView.findViewById(R.id.luotdoc);

            Linear = itemView.findViewById(R.id.linear);
            Linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                                    tieude.getText() +" | "
                                            + "Đang phát triển", Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }
}
