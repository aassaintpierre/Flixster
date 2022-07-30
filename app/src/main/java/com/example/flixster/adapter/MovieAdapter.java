


package com.example.flixster.adapter;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.databinding.ItemMoviesBinding;
import com.example.flixster.databinding.PlusPopBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static Context context;
    List<Movie> movies;
    private static final int MAIN = 0, BACKDROP = 1;
    private static final double HIGH_RATING = 5;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getItemViewType(int position) {
        double rating = movies.get(position).getRating();
        if (rating > (float) HIGH_RATING)
            return BACKDROP;
        return MAIN;

}


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            PlusPopBinding populaire = DataBindingUtil.inflate(inflater, R.layout.plus_pop, parent, false);
            viewHolder = new ViewHolder1(populaire);
        } else {
            ItemMoviesBinding moins_pop = DataBindingUtil.inflate(inflater, R.layout.item_movies, parent, false);
            viewHolder = new ViewHolder(moins_pop);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        if (getItemViewType(position) == 1) {
            ViewHolder1 View1 = (ViewHolder1) holder;
            View1.bind(movie);
        } else {
            ViewHolder View2 = (ViewHolder) holder;
            View2.bind(movie);

        }
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMoviesBinding itemBinding;


        public ViewHolder(@NonNull ItemMoviesBinding moviesBinding) {
            super(moviesBinding.getRoot());
          this.itemBinding = moviesBinding;

        }

        public void bind(Movie movie) {
            itemBinding.setMovie(movie);
            itemBinding.executePendingBindings();

            itemBinding.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // first parameter is the context, second is the class of the activity to launch
                    Intent i = new Intent(context, DetailActivity.class);
                    // put "extras" into the bundle for access in the second activity
                    i.putExtra("movie", Parcels.wrap(movie));
                    // brings up the second activity
                    context.startActivity(i);
                }
            });
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        PlusPopBinding popBinding;

        public ViewHolder1(@NonNull PlusPopBinding popBinding) {
            super(popBinding.getRoot());
            this.popBinding = popBinding;
        }

        public void bind(Movie movie) {
            popBinding.setMovie(movie);
            popBinding.executePendingBindings();

            popBinding.container2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // first parameter is the context, second is the class of the activity to launch
                    Intent a = new Intent(context, DetailActivity.class);
                    // put "extras" into the bundle for access in the second activity
                    a.putExtra("movie", Parcels.wrap(movie));
//                    context.startActivity(a);

//                    Pair<View, String> transitionTitlle = Pair.create((View)movies, "transitionTitlle");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, popBinding.backdrop,"transitionTitlle");


                    // brings up the second activity

                    context.startActivity(a, options.toBundle());

                }
            });
        }
    }


    public static class BindingAdapterUtils {
        @BindingAdapter({"imageUrl1"})
        public static void loadImage1(ImageView img, String ImageUrl) {
            int radius = 40;

            Glide.with(context)
                    .load(ImageUrl)
                    .fitCenter()
                    .transform(new RoundedCorners(radius))
                    .placeholder(R.drawable.image)
                    .into(img);
        }


        @BindingAdapter({"imageUrl"})
        public static void loadImage(ImageView img, String ImageUrl) {
            int radius = 40;

            Glide.with(context)
                    .load(ImageUrl)
                    .fitCenter()
                    .transform(new RoundedCorners(radius))
                    .placeholder(R.drawable.image)
                    .into(img);
        }
    }

}


