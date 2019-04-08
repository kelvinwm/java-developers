package org.andela.app.javadevelopers.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.andela.app.javadevelopers.R;
import org.andela.app.javadevelopers.RecylerClickListener;
import org.andela.app.javadevelopers.adapter.GithubAdapter;
import org.andela.app.javadevelopers.model.GithubUsers;
import org.andela.app.javadevelopers.presenter.GithubPresenter;
import org.andela.app.javadevelopers.util.ConnectivityHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GithubUsersView {
    private ArrayList<GithubUsers> developerlistinstance = new ArrayList<>();
    private static String LIST_STATE = "list_state";
    private Parcelable savedRecyclerlayoutstate;
    private static final String BUNDLE_RECYCLER_LAYOUT = "recycler_layout";
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDialog;
    RecyclerView.LayoutManager layoutManager;
    private GithubAdapter githubAdapter;
    View parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.developer_list);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        parentLayout = findViewById(android.R.id.content);
        progressDialog = new ProgressDialog(MainActivity.this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecylerClickListener(getApplicationContext(),
                recyclerView, new RecylerClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                GithubUsers githubUsers = developerlistinstance.get(position);
                Intent view_details = new Intent(MainActivity.this,
                        DetailsActivity.class);
                view_details.putExtra("Github_username", githubUsers.getUsername());
                view_details.putExtra("Github_link", githubUsers.getGithublink());
                view_details.putExtra("Github_photo_link", githubUsers.getProfileimg());
                startActivity(view_details);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        if (savedInstanceState != null) {
            developerlistinstance = savedInstanceState.getParcelableArrayList(LIST_STATE);
            savedRecyclerlayoutstate = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
        }
        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchGithubUsers();
            }
        });

        checkInternetConnection();
    }

    private void checkInternetConnection() {
        if (ConnectivityHelper.isConnectedToNetwork(this)) {
            loadUsers();
        } else {
            //Show Snackbar
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkInternetConnection();
                        }
                    });
            // Changing message text color
            snackbar.setActionTextColor(Color.GREEN);

            snackbar.show();
        }
    }

    private void loadUsers() {
        progressDialog.setTitle("Loading users");
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
        fetchGithubUsers();
    }

    private void fetchGithubUsers() {

        GithubPresenter githubPresenter = new GithubPresenter(this);
        githubPresenter.getDevelopers();

    }


    @Override
    public void githubUsersReady(ArrayList<GithubUsers> githubUsersList) {
        developerlistinstance = githubUsersList;

        GithubAdapter githubAdapter = new GithubAdapter(developerlistinstance);
        githubAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(githubAdapter);
        progressDialog.dismiss();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_STATE, developerlistinstance);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
    }




    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        developerlistinstance = savedInstanceState.getParcelableArrayList(LIST_STATE);
        savedRecyclerlayoutstate = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
        super.onRestoreInstanceState(savedInstanceState);
    }
   private void restoreLayoutPosition(){
        if(savedRecyclerlayoutstate != null){
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerlayoutstate);
        }
   }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
