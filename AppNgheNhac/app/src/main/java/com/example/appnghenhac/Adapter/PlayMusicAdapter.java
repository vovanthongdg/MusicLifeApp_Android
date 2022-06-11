package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Model.Baihat;
import com.example.appnghenhac.R;

import java.util.ArrayList;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihat> arrBaihat;

    public PlayMusicAdapter(Context context, ArrayList<Baihat> arrBaihat) {
        this.context = context;
        this.arrBaihat = arrBaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_play_music,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = arrBaihat.get(position);
        holder.txtIndex.setText(position + 1 + "");
        holder.txtTenbaihat.setText(baihat.getTenBaiHat());
        holder.txtCasi.setText(baihat.getCaSi());
    }

    @Override
    public int getItemCount() {
        return arrBaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtTenbaihat, txtCasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.tvplaymusicindex);
            txtTenbaihat = itemView.findViewById(R.id.tvplaymusictenbaihat);
            txtCasi = itemView.findViewById(R.id.tvplaynhactencasi);
        }
    }
}
