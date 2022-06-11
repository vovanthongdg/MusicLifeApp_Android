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
import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> arrAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrAlbum) {
        this.context = context;
        this.arrAlbum = arrAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = arrAlbum.get(position);
        holder.txtTenAlbum.setText(album.getTenAlbum());
        holder.txtTenCaSiAlbum.setText(album.getTenCaSiAlbum());
        Picasso.with(context)
                .load(album.getHinhAlbum())
                .into(holder.imgAlbum);

    }

    @Override
    public int getItemCount() {
        return arrAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAlbum;
        TextView txtTenAlbum, txtTenCaSiAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbum = itemView.findViewById(R.id.imgviewalbum);
            txtTenAlbum = itemView.findViewById(R.id.txttenalbum);
            txtTenCaSiAlbum = itemView.findViewById(R.id.txttencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("album",arrAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
