package com.example.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appnghenhac.Adapter.DanhsachPlaylistAdapter;
import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.APIService;
import com.example.appnghenhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachPlaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewDsPlaylist;
    DanhsachPlaylistAdapter danhsachPlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_playlist);
        anhxa();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetDanhsachPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrPlaylist = (ArrayList<Playlist>) response.body();
                danhsachPlaylistAdapter = new DanhsachPlaylistAdapter(DanhsachPlaylistActivity.this,arrPlaylist);
                recyclerViewDsPlaylist.setLayoutManager(new GridLayoutManager(DanhsachPlaylistActivity.this,2));
                recyclerViewDsPlaylist.setAdapter(danhsachPlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PlayLists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbardsplaylist);
        recyclerViewDsPlaylist = findViewById(R.id.recyclerviewdsplaylist);
    }
}