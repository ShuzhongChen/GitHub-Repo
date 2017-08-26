package com.shuzhongchen.githubrepo.view.repo_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuzhongchen.githubrepo.R;
import com.shuzhongchen.githubrepo.model.Repo;

import java.util.List;

/**
 * Created by shuzhongchen on 8/25/17.
 */

public class RepoListAdapter extends RecyclerView.Adapter {

    private List<Repo> data;

    public RepoListAdapter(@NonNull List<Repo> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new RepoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Repo repo = data.get(position);
        RepoListViewHolder repoViewHolder = (RepoListViewHolder) holder;

        repoViewHolder.name.setText(repo.name);
        repoViewHolder.language.setText(repo.language);
        repoViewHolder.forks.setText(String.valueOf(repo.forks));
        repoViewHolder.stars.setText(String.valueOf(repo.watchers_count));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
