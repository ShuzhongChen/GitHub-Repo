package com.shuzhongchen.githubrepo;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shuzhongchen.githubrepo.github.Github;
import com.shuzhongchen.githubrepo.model.Repo;
import com.shuzhongchen.githubrepo.view.base.SpaceItemDecoration;
import com.shuzhongchen.githubrepo.view.repo_list.RepoListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String INVALID_USERNAME_TOAST = "Invalid user name!";

    private static final String NO_REPO_TOAST = "This user doesn't have any repo!";

    private static final String INVALID_USERNAME_MESSAGE = "{\"message\":\"Not Found\",\"documentation_url\":\"https://developer.github.com/v3\"}";

    private boolean validUserName = true;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loadRepoButton = (Button) findViewById(R.id.load_repo_button);

        loadRepoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = ((EditText) findViewById(R.id.user_name_edit)).getText().toString();
                AsyncTaskCompat.executeParallel(new LoadRepoTask(user_name));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.addItemDecoration(new SpaceItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.spacing_medium)));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void setupUI(@NonNull List<Repo> repos) {
        recyclerView.setAdapter(new RepoListAdapter(repos));
    }

    private void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    private class LoadRepoTask extends AsyncTask<Void, Void, List<Repo>> {

        String username;

        LoadRepoTask(String username) {
            this.username = username;
        }

        @Override
        protected List<Repo> doInBackground(Void... params) {
            try {
                Response response = Github.getRepos(username);

                if (response.body().string().equals(INVALID_USERNAME_MESSAGE)) {
                    validUserName = false;
                    return null;
                } else {
                    return Github.getRepoList(username);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Repo> repos) {
            if (validUserName) {
                if (repos.size() == 0) {
                    showToast(NO_REPO_TOAST);
                }
                setupUI(repos);
            } else {
                setupUI(new ArrayList<Repo>());
                showToast(INVALID_USERNAME_TOAST);
                validUserName = true;
            }
        }

    }
}

