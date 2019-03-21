package org.andela.app.javadevelopers.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.andela.app.javadevelopers.R;
import org.andela.app.javadevelopers.model.Developer;

import java.util.List;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.viewHolder>{

    public List<Developer> developerList;
    public  class viewHolder extends RecyclerView.ViewHolder{
        ImageView profimage;
        TextView username;

        public viewHolder( View itemView) {
            super(itemView);
            profimage = (ImageView) itemView.findViewById(R.id.prof_image);
            username = (TextView) itemView.findViewById(R.id.git_username);
        }
    }

    public RecycleviewAdapter(List<Developer> developerList) {
        this.developerList = developerList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card_layout, viewGroup, false);

        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int i) {
        Developer developer = developerList.get(i);
        viewHolder.username.setText(developer.getUsername());
//        viewHolder.genre.setText(movie.getGenre());
    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }


}
