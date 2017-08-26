package com.shuzhongchen.githubrepo.github;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.shuzhongchen.githubrepo.model.Repo;
import com.shuzhongchen.githubrepo.utils.ModelUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shuzhongchen on 8/25/17.
 */

public class Github {

    private static final String API_URL = "https://api.github.com/users/";
    private static final String KEY_USER = "/repos";

    private static final TypeToken<List<Repo>> Repo_LIST_TYPE = new TypeToken<List<Repo>>(){};

    private static OkHttpClient client = new OkHttpClient();

    private static Response makeRequest(Request request) throws IOException {
        Response response = client.newCall(request).execute();
        return response;
    }

    private static Response makeGetRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        return makeRequest(request);
    }

    public static <T> T parseResponse(Response response,
                                      TypeToken<T> typeToken) throws IOException, JsonSyntaxException {
        String responseString = response.body().string();
        return ModelUtils.toObject(responseString, typeToken);
    }

    public static Response getRepos(String username) throws IOException, JsonSyntaxException {
        String url = API_URL + username + KEY_USER;
        return makeGetRequest(url);
    }

    public static List<Repo> getRepoList(String username) throws IOException, JsonSyntaxException {
        String url = API_URL + username + KEY_USER;
        return parseResponse(makeGetRequest(url), Repo_LIST_TYPE);
    }
}

