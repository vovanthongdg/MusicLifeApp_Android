package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView tenPlaylist;
        ImageView imgBackgroungPl, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.tenPlaylist = convertView.findViewById(R.id.ten_playlist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imageview_playlist);
            viewHolder.imgBackgroungPl = convertView.findViewById(R.id.imageviewbg_playlist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext())
                .load(playlist.getHinhNen())
                .into(viewHolder.imgBackgroungPl);
        Picasso.with(getContext())
                .load(playlist.getHinhIcon())
                .into(viewHolder.imgPlaylist);
        viewHolder.tenPlaylist.setText(playlist.getTenPL());
        return convertView;
    }
}
