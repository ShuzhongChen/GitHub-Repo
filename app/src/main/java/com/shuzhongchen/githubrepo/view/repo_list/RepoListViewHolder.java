package com.shuzhongchen.githubrepo.view.repo_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shuzhongchen.githubrepo.R;

/**
 * Created by shuzhongchen on 8/25/17.
 */

public class RepoListViewHolder extends RecyclerView.ViewHolder{

    TextView name;
    TextView language;
    TextView forks;
    TextView stars;

    public RepoListViewHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.repo_name);
        language = itemView.findViewById(R.id.repo_language);
        forks = itemView.findViewById(R.id.repo_forks);
        stars = itemView.findViewById(R.id.repo_stars);
    }
}