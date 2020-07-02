package com.example.movies_app.adapters;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_app.R;
import com.example.movies_app.databinding.ItemMovieBinding;
import com.example.movies_app.models.Movie;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private LayoutInflater layoutInflater;
    private final List<Movie> movies;

    public MovieAdapter(@NonNull List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemMovieBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.binding.setMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;

        MovieViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}