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

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillsViewHolder> {
    private List<Skill> mSkillsList;
    private Context mContext;

    public SkillAdapter(List<Skill> learningLeadersList, Context context) {
        mSkillsList = learningLeadersList;
        mContext = context;
    }

    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SkillsViewHolder(
                LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_leader_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, int position) {
        Skill skill = mSkillsList.get(position);
        holder.bind(skill);
    }

    @Override
    public int getItemCount() {
        return mSkillsList.size();
    }

    public class SkillsViewHolder extends RecyclerView.ViewHolder {
        ImageView mLeaderBadge;
        TextView mLeaderName, mLeaderDetails;
        public SkillsViewHolder(@NonNull View itemView) {
            super(itemView);
            mLeaderBadge = itemView.findViewById(R.id.leader_badge_image);
            mLeaderName = itemView.findViewById(R.id.leader_name);
            mLeaderDetails = itemView.findViewById(R.id.leader_details);
        }

        public void bind(Skill skill) {
            String details = skill.getScore() + " skill IQ Score, " + skill.getCountry();
            mLeaderName.setText(skill.getName());
            mLeaderDetails.setText(details);
            Picasso.get()
                    .load(skill.getBadgeUrl())
                    .into(mLeaderBadge);
        }
    }
}
