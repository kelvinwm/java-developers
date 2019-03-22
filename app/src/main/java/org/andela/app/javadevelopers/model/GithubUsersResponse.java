package org.andela.app.javadevelopers.model;

import java.util.List;

public class GithubUsersResponse {

    List<GithubUsers> items;

    public GithubUsersResponse(List<GithubUsers> items) {
        this.items = items;
    }
}
