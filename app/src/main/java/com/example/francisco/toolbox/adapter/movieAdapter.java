package com.example.francisco.toolbox.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.francisco.toolbox.Main2Activity;
import com.example.francisco.toolbox.Model.Movie;
import com.example.francisco.toolbox.MovieList.MainActivity;
import com.example.francisco.toolbox.R;
import com.example.francisco.toolbox.network.ApiClient;

import java.util.List;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.MyViewHolder>  {

    private MainActivity movieListActivity;
    private List<Movie> movieList;
    private List<Movie> originalMovieList;

    public movieAdapter(MainActivity movieListActivity, List<Movie> movieList, List<Movie> originalMovieList) {
        this.movieListActivity = movieListActivity;
        this.movieList = movieList;
        this.originalMovieList = originalMovieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Movie movie = movieList.get(position);

        holder.textoPelicula.setText(movie.getTitle());


        // loading album cover using Glide library
        Glide.with(movieListActivity)
                .load(ApiClient.IMAGE_BASE_URL + movie.getThumbPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        return false;
                    }
                })
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background))
                .into(holder.imagenPelicula);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieListActivity.onMovieItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView textoPelicula;
        public ImageView imagenPelicula;

        public MyViewHolder(View itemView) {
            super(itemView);

            textoPelicula = itemView.findViewById(R.id.textoImagen);
            imagenPelicula = itemView.findViewById(R.id.imagenPelicula);

        }


    }



}
