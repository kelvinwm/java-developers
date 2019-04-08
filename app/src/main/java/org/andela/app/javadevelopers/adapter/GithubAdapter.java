package org.andela.app.javadevelopers.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.andela.app.javadevelopers.R;
import org.andela.app.javadevelopers.model.GithubUsers;

import java.util.List;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.viewHolder>{

    public List<GithubUsers> developerList;
    public  class viewHolder extends RecyclerView.ViewHolder{
        ImageView profimage;
        TextView username;

        public viewHolder( View itemView) {
            super(itemView);
            profimage = (ImageView) itemView.findViewById(R.id.prof_image);
            username = (TextView) itemView.findViewById(R.id.git_username);
        }
    }

    public GithubAdapter(List<GithubUsers> developerList) {
        this.developerList = developerList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card_layout, viewGroup, false);

        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int i) {
        GithubUsers developer = developerList.get(i);
        viewHolder.username.setText(developer.getUsername());
        Picasso.get()
                .load(developer.getProfileimg())
                .placeholder(R.drawable.ac)
                .error(R.drawable.ic_menu_gallery)
                .into(viewHolder.profimage);
    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }


}
