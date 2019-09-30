package submission4.moviecataloguefavorite;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieFavoriteFragment extends Fragment {

    private static final String EXTRA_STATE = "EXTRA_STATE";
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MovieFavoriteHelper movieFavoriteHelper;

    public MovieFavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = getView().findViewById(R.id.progressBarMovieFavorite);
        recyclerView = getView().findViewById(R.id.rv_movie_favorite);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movieFavoriteHelper = MovieFavoriteHelper.getInstance(getContext());

        movieAdapter = new MovieAdapter(getContext());

        recyclerView.setAdapter(movieAdapter);

        if (savedInstanceState == null) {
            new LoadMovieFavoriteData().execute();
        } else {
            ArrayList<MovieModel> movieModels = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (movieModels != null) {
                movieAdapter.setData(movieModels);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadMovieFavoriteData().execute();
    }

        @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, movieAdapter.getMovieModels());
    }

    public class LoadMovieFavoriteData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieAdapter.setData(movieFavoriteHelper.selectMovie());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);

        }
    }
}
