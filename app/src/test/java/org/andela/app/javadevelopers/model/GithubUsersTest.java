package org.andela.app.javadevelopers.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GithubUsersTest {

    private final String username = "k33ptoo";
    private final String githublink = "https://github.com/k33ptoo";
    private final String profileimg = "https://avatars0.githubusercontent.com/u/6637970?v=4";

    GithubUsers githubUsers = new GithubUsers(username, githublink, profileimg);

    @Test
    public void getUserName() {
        assertEquals(username, githubUsers.getUsername());
    }

    @Test
    public void getGithubLink() {
        assertEquals(githublink, githubUsers.getGithublink());
    }

    @Test
    public void getProfile_image() {
        assertEquals(profileimg, githubUsers.getProfileimg());
    }

}