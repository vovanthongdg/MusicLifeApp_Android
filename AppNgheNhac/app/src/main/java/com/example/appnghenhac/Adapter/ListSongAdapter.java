package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Activity.PlayMusicActivity;
import com.example.appnghenhac.Model.Baihat;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.APIService;
import com.example.appnghenhac.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> arrayListBaihatls;

    public ListSongAdapter(Context context, ArrayList<Baihat> arrayListBaihatls) {
        this.context = context;
        this.arrayListBaihatls = arrayListBaihatls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_list_song,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = arrayListBaihatls.get(position);
        holder.txtTenbaihat.setText(baihat.getTenBaiHat());
        holder.txtindex.setText(position + 1 + "");
        holder.txtTencasi.setText(baihat.getCaSi());
    }

    @Override
    public int getItemCount() {
        return arrayListBaihatls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenbaihat, txtTencasi, txtindex;
        ImageView imglikelistsong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.tvlistindex);
            txtTenbaihat = itemView.findViewById(R.id.tvtenbaihat);
            txtTencasi = itemView.findViewById(R.id.tvtencasi);
            imglikelistsong = itemView.findViewById(R.id.ivlikelistsong);
            imglikelistsong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imglikelistsong.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1", arrayListBaihatls.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("cc")) {
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imglikelistsong.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc",arrayListBaihatls.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
