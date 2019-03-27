package org.andela.app.javadevelopers.view;

import org.andela.app.javadevelopers.model.GithubUsers;

import java.util.ArrayList;
import java.util.List;

public interface GithubUsersView {

    void githubUsersReady(ArrayList<GithubUsers> githubUsersList);
}
