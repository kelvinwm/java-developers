package org.andela.app.javadevelopers.service;

import org.andela.app.javadevelopers.model.GithubUsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubApi {

    @GET("search/users?q=+language:java+location:nairobi")
    Call<GithubUsersResponse> getGithubDevelopers();
}
