package com.ralemon.android.java.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.LeadersViewHolder> {
    private List<Learning> mLearningList;
    private Context mContext;

    public LeaderAdapter(List<Learning> learningLeadersList, Context context) {
        mLearningList = learningLeadersList;
        mContext = context;
    }

    @NonNull
    @Override
    public LeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeadersViewHolder(
                LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_leader_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeadersViewHolder holder, int position) {
        Learning learning = mLearningList.get(position);
        holder.bind(learning);
    }

    @Override
    public int getItemCount() {
        return mLearningList.size();
    }

    public class LeadersViewHolder extends RecyclerView.ViewHolder {
        ImageView mLeaderBadge;
        TextView mLeaderName, mLeaderDetails;
        public LeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            mLeaderBadge = itemView.findViewById(R.id.leader_badge_image);
            mLeaderName = itemView.findViewById(R.id.leader_name);
            mLeaderDetails = itemView.findViewById(R.id.leader_details);
        }

        public void bind(Learning learning) {
            String details = learning.getHours() + " learning hours, " + learning.getCountry();
            mLeaderName.setText(learning.getName());
            mLeaderDetails.setText(details);
            Picasso.get()
                    .load(learning.getBadgeUrl())
                    .into(mLeaderBadge);
        }
    }
}
