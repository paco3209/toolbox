package com.example.francisco.toolbox.MovieList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.francisco.toolbox.Main2Activity;
import com.example.francisco.toolbox.Model.Movie;
import com.example.francisco.toolbox.Model.MovieListResponse;
import com.example.francisco.toolbox.R;
import com.example.francisco.toolbox.adapter.movieAdapter;
import com.example.francisco.toolbox.network.ApiClient;
import com.example.francisco.toolbox.network.ApiInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.francisco.toolbox.network.ApiClient.API_KEY;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener  {

    private movieAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieListResponse> call = service.getPopularMovies(API_KEY, 10);


        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                generateMoviList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

            }
        });




    }

    private void generateMoviList(List<Movie> movieList){
        recyclerView  =(RecyclerView)findViewById(R.id.rv_movies);

        adapter = new movieAdapter(this,movieList, movieList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false);


        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);




    }


    @Override
    public void onMovieItemClick(int position) {
        if (position == -1) {
            return;
        }
        Intent intent = new Intent(this, Main2Activity.class);

        startActivity(intent);
    }
}