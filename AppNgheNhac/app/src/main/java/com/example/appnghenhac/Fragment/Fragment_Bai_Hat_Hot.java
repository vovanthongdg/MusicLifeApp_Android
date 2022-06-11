package com.example.appnghenhac.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Adapter.BaihathotAdapter;
import com.example.appnghenhac.Model.Baihat;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.APIService;
import com.example.appnghenhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewBaihathot;
    BaihathotAdapter baihathotAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat_hot,container,false);
        recyclerViewBaihathot = view.findViewById(R.id.recyclerviewbaihathot);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GeBaiHatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> arrBaihat = (ArrayList<Baihat>) response.body();
                baihathotAdapter = new BaihathotAdapter(getActivity(),arrBaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaihathot.setLayoutManager(linearLayoutManager);
                recyclerViewBaihathot.setAdapter(baihathotAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
