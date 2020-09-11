package com.ralemon.android.java.leaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeaderService {
    @GET("hours")
    Call<List<Learning>> getLearning();

    @GET("skilliq")
    Call<List<Skill>> getSkill();
}
