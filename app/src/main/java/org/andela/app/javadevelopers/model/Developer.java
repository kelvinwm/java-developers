package org.andela.app.javadevelopers.model;

public class Developer {
    String username;
    String githublink;
    String profileimg;

    public Developer(String username, String githublink, String profileimg) {
        this.username = username;
        this.githublink = githublink;
        this.profileimg = profileimg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGithublink() {
        return githublink;
    }

    public void setGithublink(String githublink) {
        this.githublink = githublink;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }
}
