package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Activity.ListTheloaitheoChudeActivity;
import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListChudeAdapter extends RecyclerView.Adapter<ListChudeAdapter.ViewHolder> {

    Context context;
    ArrayList<ChuDe> arrChude;

    public ListChudeAdapter(Context context, ArrayList<ChuDe> arrChude) {
        this.context = context;
        this.arrChude = arrChude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_allchude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = arrChude.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imgAllChude);
    }

    @Override
    public int getItemCount() {
        return arrChude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAllChude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAllChude = itemView.findViewById(R.id.ivrowallchude);
            imgAllChude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListTheloaitheoChudeActivity.class);
                    intent.putExtra("chude",arrChude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
