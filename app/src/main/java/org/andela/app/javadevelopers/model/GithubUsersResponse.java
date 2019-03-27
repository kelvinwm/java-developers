package org.andela.app.javadevelopers.model;


import java.util.ArrayList;

public class GithubUsersResponse {
    
    ArrayList<GithubUsers> items;

    public GithubUsersResponse(ArrayList<GithubUsers> items) {
        this.items = items;
    }

    //Getter method that returns a list of Github users
    public ArrayList<GithubUsers> getGithubUsersList() {
        return items;
    }
}
