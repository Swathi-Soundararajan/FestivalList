package com.example.user.festivallist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by user on 08-04-2018.
 */

public class ThisYearFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<FestivalList> festivalLists;
    private RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
    public ThisYearFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("fragment created","this year");
        View view = inflater.inflate(R.layout.thisyear_frag,container,false);
        return view;
    }

}

