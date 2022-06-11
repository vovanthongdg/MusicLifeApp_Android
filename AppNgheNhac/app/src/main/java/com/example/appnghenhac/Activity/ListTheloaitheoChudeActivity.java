package com.example.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appnghenhac.Adapter.ListTheloaitheoChudeAdapter;
import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.Model.TheLoai;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.APIService;
import com.example.appnghenhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTheloaitheoChudeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewTheloai;
    Toolbar toolbarTheloai;
    ListTheloaitheoChudeAdapter listTheloaitheoChudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_theloaitheo_chude);
        GetIntent();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.GetTheloaitheoChude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> arrTheloai = (ArrayList<TheLoai>) response.body();
                listTheloaitheoChudeAdapter = new ListTheloaitheoChudeAdapter(ListTheloaitheoChudeActivity.this,arrTheloai);
                recyclerViewTheloai.setLayoutManager(new GridLayoutManager(ListTheloaitheoChudeActivity.this,2));
                recyclerViewTheloai.setAdapter(listTheloaitheoChudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewTheloai = findViewById(R.id.recyclerviewtheloai);
        toolbarTheloai = findViewById(R.id.toolbartheloai);
        setSupportActionBar(toolbarTheloai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbarTheloai.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")) {
            chuDe = (ChuDe) intent.getSerializableExtra("chude");

        }
    }
}