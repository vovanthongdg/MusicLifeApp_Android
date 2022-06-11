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

import com.example.appnghenhac.Activity.PlayMusicActivity;
import com.example.appnghenhac.Adapter.PlayMusicAdapter;
import com.example.appnghenhac.R;

public class Fragment_Play_ListSong extends Fragment {

    View view;
    RecyclerView recyclerViewPlayListsong;
    PlayMusicAdapter playMusicAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_listsong,container,false);
        recyclerViewPlayListsong = view.findViewById(R.id.recyclerviewplaylistsong);
        if (PlayMusicActivity.arrBaihat.size() > 0 ) {
            playMusicAdapter = new PlayMusicAdapter(getActivity(), PlayMusicActivity.arrBaihat);
            recyclerViewPlayListsong.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayListsong.setAdapter(playMusicAdapter);
        }
        return view;
    }
}
