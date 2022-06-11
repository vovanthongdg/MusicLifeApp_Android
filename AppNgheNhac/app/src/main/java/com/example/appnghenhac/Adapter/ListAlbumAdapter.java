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

public class ListAlbumAdapter extends RecyclerView.Adapter<ListAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> arrListAlbum;

    public ListAlbumAdapter(Context context, ArrayList<Album> arrListAlbum) {
        this.context = context;
        this.arrListAlbum = arrListAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_list_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = arrListAlbum.get(position);
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgListAlbum);
        holder.txtListAlbum.setText(album.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return arrListAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgListAlbum;
        TextView txtListAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgListAlbum = itemView.findViewById(R.id.ivlistalbum);
            txtListAlbum = itemView.findViewById(R.id.tvlistalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("album",arrListAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
