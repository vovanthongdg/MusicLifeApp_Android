package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Activity.ListSongActivity;
import com.example.appnghenhac.Model.TheLoai;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTheloaitheoChudeAdapter extends RecyclerView.Adapter<ListTheloaitheoChudeAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> arrTheloai;

    public ListTheloaitheoChudeAdapter(Context context, ArrayList<TheLoai> arrTheloai) {
        this.context = context;
        this.arrTheloai = arrTheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_theloai_theo_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = arrTheloai.get(position);
        holder.txtTenTheloai.setText(theLoai.getTenTheLoai());
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgTheloai);
    }

    @Override
    public int getItemCount() {
        return arrTheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTheloai;
        TextView txtTenTheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTheloai = itemView.findViewById(R.id.ivtheloaitheochude);
            txtTenTheloai= itemView.findViewById(R.id.tvtheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("idtheloai",arrTheloai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
