package org.andela.app.javadevelopers.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GithubUsersResponseTest {

     GithubUsersResponse githubUsersResponse;
     ArrayList<GithubUsers> githubUsersArrayList = new ArrayList<>();
    GithubUsers l1 = new GithubUsers("k33ptoo", "https://github.com/k33ptoo",
            "https://avatars0.githubusercontent.com/u/6637970?v=4");

     @Test
    public void githubUserList(){
         githubUsersArrayList.add(l1);
         githubUsersResponse = new GithubUsersResponse(githubUsersArrayList);

         assertEquals(githubUsersArrayList, githubUsersResponse.getGithubUsersList());
     }
}