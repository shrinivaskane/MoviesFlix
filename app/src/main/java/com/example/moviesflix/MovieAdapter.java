package com.example.moviesflix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterVH> {

    private List<MovieResponse> movieResponseList;
    private Context context;
    private ClickedItem clickedItem;

    public MovieAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<MovieResponse> movieResponseList) {
        this.movieResponseList = movieResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MovieAdapter.MovieAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieAdapterVH holder, int position) {
        MovieResponse movieResponse = movieResponseList.get(position);

        String title = movieResponse.getTitle();
        String prefix="";

        holder.prefix.setText(prefix);
        holder.title.setText(title);
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedMovie(movieResponse);
            }
        });


    }


    public interface ClickedItem{
        public void ClickedMovie(MovieResponse userResponse);
    }

    @Override
    public int getItemCount() {
        return movieResponseList.size();
    }

    public class MovieAdapterVH extends RecyclerView.ViewHolder {

        TextView title;
        TextView prefix;
        ImageView imageMore;

        public MovieAdapterVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            prefix = itemView.findViewById(R.id.prefix);
            imageMore = itemView.findViewById(R.id.imageMore);


        }
    }
}
