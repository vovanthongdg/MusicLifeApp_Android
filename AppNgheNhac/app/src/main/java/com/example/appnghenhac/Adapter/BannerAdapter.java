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
import androidx.viewpager.widget.PagerAdapter;

import com.example.appnghenhac.Activity.ListSongActivity;
import com.example.appnghenhac.Model.Quangcao;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Quangcao> quangcaoArrayList;

    public BannerAdapter(Context context, ArrayList<Quangcao> quangcaoArrayList) {
        this.context = context;
        this.quangcaoArrayList = quangcaoArrayList;
    }

    @Override
    public int getCount() {
        return quangcaoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_banner, null);

        ImageView imgBgBanner = view.findViewById(R.id.imagebg_banner);
        ImageView imgSongBanner = view.findViewById(R.id.imageview_banner);
        TextView titleSongBanner = view.findViewById(R.id.title_bai_hat);
        TextView txtNoiDung = view.findViewById(R.id.noi_dung);

        Picasso.with(context).load(quangcaoArrayList.get(position).getHinhAnh()).into(imgBgBanner);
        Picasso.with(context).load(quangcaoArrayList.get(position).getHinhBaiHat()).into(imgSongBanner);
        titleSongBanner.setText(quangcaoArrayList.get(position).getTenBaiHat());
        txtNoiDung.setText(quangcaoArrayList.get(position).getNoiDung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("banner", quangcaoArrayList.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
