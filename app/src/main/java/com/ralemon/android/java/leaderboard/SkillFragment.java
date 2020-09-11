package com.ralemon.android.java.leaderboard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillFragment extends Fragment {
    private Context mContext;
    private List<Skill> mSkillList;
    private SkillAdapter mAdapter;
    private RecyclerView mSkillRecyclerView;
    public SkillFragment() {
    }

    public SkillFragment(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill, container, false);
        mSkillRecyclerView = (RecyclerView) view.findViewById(R.id.skill_recycler);
        mSkillList = new ArrayList<>();

        mSkillRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SkillAdapter(mSkillList, mContext);
        mSkillRecyclerView.setAdapter(mAdapter);



        LeaderService skillService = ServiceBuilder.buildService(LeaderService.class);
        final Call<List<Skill>> itemsRequest = skillService.getSkill();

        //final ProgressBar loading = (ProgressBar) view.findViewById(R.id.progressIndicator);


        itemsRequest.enqueue(new Callback<List<Skill>>() {
            @Override
            public void onResponse(Call<List<Skill>> request, Response<List<Skill>> response) {
                if(response.isSuccessful()) {
                    List<Skill> skillLeaders = response.body();
                    mSkillList.addAll(skillLeaders);
                    mAdapter.notifyDataSetChanged();



                }else if(response.code() == 401){
                    Toast.makeText(mContext, "Your Session has expired.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Failed to retrieve items.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Skill>> request, Throwable t) {
                if(t instanceof IOException) {
                    Toast.makeText(mContext, "A connection error occured.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Failed to retrieve items.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}