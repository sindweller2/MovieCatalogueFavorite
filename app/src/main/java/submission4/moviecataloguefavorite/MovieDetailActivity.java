package submission4.moviecataloguefavorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String movie = "movie";
    private Boolean isFavorite = false;
    private Menu menuItem = null;
    private MovieFavoriteHelper movieFavoriteHelper;
    private MovieAdapter movieAdapter = new MovieAdapter(this);

    ImageView mdPosterPath;
    TextView mdTitle, mdReleasDate, mdVoteAverage, mdOverview;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        progressBar = findViewById(R.id.progressBar);
        mdPosterPath = findViewById(R.id.md_poster_path);
        mdTitle = findViewById(R.id.md_title);
        mdReleasDate = findViewById(R.id.md_release_date);
        mdVoteAverage = findViewById(R.id.md_vote_average);
        mdOverview = findViewById(R.id.md_overview);

        movieFavoriteHelper = MovieFavoriteHelper.getInstance(getApplicationContext());

        new LoadMovieData().execute();

        favoriteState();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        menuItem = menu;
        setFavorite();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_to_favorite) {
            if (isFavorite) {
                removeFromFavorite();
            } else {
                addToFavorite();
            }
            isFavorite = !isFavorite;
            setFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    public void favoriteState() {
        MovieModel movieModel = getIntent().getParcelableExtra(movie);
        movieAdapter.setData(movieFavoriteHelper.selectMovie(movieModel.getId().toString()));

        if (movieAdapter.getItemCount() > 0) {
            isFavorite = true;
        }
    }

    public void setFavorite() {
        if (isFavorite)
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites));
        else
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites));
    }

    private void addToFavorite() {
        try {
            MovieModel movieModel = getIntent().getParcelableExtra(movie);
            movieFavoriteHelper.insertMovie(movieModel);
            Toast.makeText(this, getResources().getString(R.string.addfavorite), Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void removeFromFavorite() {
        try {
            MovieModel movieModel = getIntent().getParcelableExtra(movie);
            movieFavoriteHelper.deleteMovie(movieModel.getId().toString());
            Toast.makeText(this, getResources().getString(R.string.removefavorite), Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public class LoadMovieData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);

            MovieModel movieModel = getIntent().getParcelableExtra(movie);

            Glide.with(getApplicationContext())
                    .load("https://image.tmdb.org/t/p/original/" + movieModel.getPoster_path())
                    .into(mdPosterPath);

            mdTitle.setText(movieModel.getTitle());
            mdReleasDate.setText(movieModel.getRelease_date());
            mdVoteAverage.setText(movieModel.getVote_average().toString());
            mdOverview.setText(movieModel.getOverview());
        }
    }
}
