package com.example.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appnghenhac.Adapter.ListChudeAdapter;
import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.APIService;
import com.example.appnghenhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListChudeActivity extends AppCompatActivity {

    RecyclerView recyclerViewAllChude;
    Toolbar toolbarAllChude;
    ListChudeAdapter listChudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chude);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> arrChude = (ArrayList<ChuDe>) response.body();
                listChudeAdapter = new ListChudeAdapter(ListChudeActivity.this,arrChude);
                recyclerViewAllChude.setLayoutManager(new GridLayoutManager(ListChudeActivity.this,1));
                recyclerViewAllChude.setAdapter(listChudeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllChude = findViewById(R.id.recyclerviewallchude);
        toolbarAllChude = findViewById(R.id.toolbarchude);
        setSupportActionBar(toolbarAllChude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarAllChude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}