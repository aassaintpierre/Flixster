package com.example.flixster.adapter;





import android.content.Context;
//import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.flixster.MainActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;


import java.util.List;

public class MovieAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       RecyclerView.ViewHolder viewHolder ;
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       if(viewType==1){
           View populaire = inflater.inflate(R.layout.plus_pop,parent,false);
           viewHolder =new ViewHolder1(populaire);
       }
        else{
            View moins_pop = inflater.inflate(R.layout.item_movies,parent,false);
            viewHolder = new ViewHolder(moins_pop);
       }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        if(getItemViewType(position)==1){
            ViewHolder1 View1= (ViewHolder1) holder;
            View1.bind(movie);
        }
        else {
            ViewHolder View2 = (ViewHolder)holder;
            View2.bind(movie);

        }
    }
    @Override
    public  int getItemViewType(int position){
        if(movies.get(position).getAverage()>5){
            return 1;

        }
        return  0;
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivposter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.txtOverView);
            ivposter = itemView.findViewById(R.id.ivPoster);

        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTittle());
            tvOverview.setText(movie.getOverview());
            String ImageUrl;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                ImageUrl = movie.getBackdropPath();

            } else {

                ImageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(ImageUrl).placeholder(R.drawable.image).into(ivposter);


        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.backdrop);
        }

        public void bind(Movie movie) {

            String ImageUrl;
            ImageUrl = movie.getBackdropPath();


            Glide.with(context).load(ImageUrl).placeholder(R.drawable.image).into(imageView);

        }
    }
}
