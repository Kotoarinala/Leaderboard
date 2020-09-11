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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningFragment extends Fragment {
    private Context mContext;
    private List<Learning> mLearningLeadersList;
    private LeaderAdapter mAdapter;
    private RecyclerView mLearningRecyclerView;

    public LearningFragment() {
    }
    public LearningFragment(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning, container, false);
        mLearningRecyclerView = (RecyclerView) view.findViewById(R.id.learning_recycler);
        mLearningLeadersList = new ArrayList<>();

        mLearningRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new LeaderAdapter(mLearningLeadersList, mContext);
        mLearningRecyclerView.setAdapter(mAdapter);



        LeaderService leaderService = ServiceBuilder.buildService(LeaderService.class);
        final Call<List<Learning>> itemsRequest = leaderService.getLearning();

        //final ProgressBar loading = (ProgressBar) view.findViewById(R.id.progressIndicator);


        itemsRequest.enqueue(new Callback<List<Learning>>() {
            @Override
            public void onResponse(Call<List<Learning>> request, Response<List<Learning>> response) {
                if(response.isSuccessful()) {
                    List<Learning> learningLeaders = response.body();
                    mLearningLeadersList.addAll(learningLeaders);
                    mAdapter.notifyDataSetChanged();



                }else if(response.code() == 401){
                    Toast.makeText(mContext, "Your Session has expired.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Failed to retrieve items.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Learning>> request, Throwable t) {
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