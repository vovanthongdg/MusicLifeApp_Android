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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> arrBaihat;

    public SearchAdapter(Context context, ArrayList<Baihat> arrBaihat) {
        this.context = context;
        this.arrBaihat = arrBaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_search,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = arrBaihat.get(position);
        holder.txtTenbaihat.setText(baihat.getTenBaiHat());
        holder.txttencasi.setText(baihat.getCaSi());
        Picasso.with(context)
                .load(baihat.getHinhBaiHat())
                .into(holder.imgBaihat);
    }

    @Override
    public int getItemCount() {
        return arrBaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenbaihat, txttencasi;
        ImageView imgBaihat, imgLuotthich;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.tvsearchbaihat);
            txttencasi = itemView.findViewById(R.id.tvsearchcasi);
            imgBaihat = itemView.findViewById(R.id.ivsearchbaihat);
            imgLuotthich = itemView.findViewById(R.id.ivsearchlike);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc",arrBaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLuotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1",arrBaihat.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("cc")) {
                                Toast.makeText(context,"Đã thích!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLuotthich.setEnabled(false);
                }
            });
        }
    }
}
