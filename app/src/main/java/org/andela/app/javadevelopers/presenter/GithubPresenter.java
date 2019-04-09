package org.andela.app.javadevelopers.presenter;

import android.util.Log;

import org.andela.app.javadevelopers.model.GithubUsers;
import org.andela.app.javadevelopers.model.GithubUsersResponse;
import org.andela.app.javadevelopers.service.GithubService;
import org.andela.app.javadevelopers.view.GithubUsersView;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

    private GithubUsersView githubUsersView;
    private GithubService githubService;

    public GithubPresenter(GithubUsersView githubUsersView) {
        this.githubUsersView = githubUsersView;

        if(this.githubService==null){
            this.githubService = new GithubService();
        }
    }
    public void getDevelopers() {

        githubService
                .getGithubApi()
                .getGithubDevelopers()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                        if (!response.isSuccessful()) {
                            Log.d("TAG", "Unsuccessful returned: " + response.code());
                            return;
                        }

                        GithubUsersResponse dev = response.body();
                        if(dev != null && dev.getGithubUsersList() != null){
                            ArrayList<GithubUsers> result = dev.getGithubUsersList();
                            githubUsersView.githubUsersReady(result);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                        Log.d("TAG", "onFailure returned: " + t);
                    }
                });
    }
}
