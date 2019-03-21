package org.andela.app.javadevelopers.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.andela.app.javadevelopers.R;

public class DetailsActivity extends AppCompatActivity {

    TextView github_name, user_github_link;
    ImageView user_profile_photo;
    String github_username, github_link, user_profile_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        github_name =  findViewById(R.id.user_profile_name);
        user_github_link =  findViewById(R.id.user_github_link);
        user_profile_photo =  findViewById(R.id.user_profile_photo);

        Intent username = getIntent();
        github_username = username.getStringExtra("Github_username");
        github_link = username.getStringExtra("Github_link");
        user_profile_link = username.getStringExtra("Github_photo_link");

        github_name.setText(github_username);
        user_github_link.setText(github_link);
        Picasso.get()
                .load(user_profile_link)
                .placeholder(R.drawable.ac)
                .error(R.drawable.ic_menu_gallery)
                .into(user_profile_photo);

        user_github_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //View developer's github page
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(github_link));
                startActivity(i);
            }
        });

    }

}
