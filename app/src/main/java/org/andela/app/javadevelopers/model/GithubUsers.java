package org.andela.app.javadevelopers.model;

import com.google.gson.annotations.SerializedName;

public class GithubUsers {

    @SerializedName("login")
    String username;

    @SerializedName("url")
    String githublink;

    @SerializedName("avatar_url")
    String profileimg;

    public String getUsername() {
        return username;
    }

    public String getGithublink() {
        return githublink;
    }

    public String getProfileimg() {
        return profileimg;
    }


}
