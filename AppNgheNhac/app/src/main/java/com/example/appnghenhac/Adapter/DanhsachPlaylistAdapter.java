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

import com.example.appnghenhac.Activity.DanhsachPlaylistActivity;
import com.example.appnghenhac.Activity.ListSongActivity;
import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachPlaylistAdapter extends RecyclerView.Adapter<DanhsachPlaylistAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> arrPlaylist;

    public DanhsachPlaylistAdapter(Context context, ArrayList<Playlist> arrPlaylist) {
        this.context = context;
        this.arrPlaylist = arrPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_danhsachplaylist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = arrPlaylist.get(position);
        Picasso.with(context)
                .load(playlist.getHinhNen())
                .into(holder.imgHinhNen);
        holder.txtTenPlaylist.setText(playlist.getTenPL());
    }

    @Override
    public int getItemCount() {
        return arrPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhNen;
        TextView txtTenPlaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhNen = itemView.findViewById(R.id.ivdsplaylist);
            txtTenPlaylist = itemView.findViewById(R.id.tvdsplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("itemplaylist",arrPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
