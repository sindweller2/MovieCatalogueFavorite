package submission4.moviecataloguefavorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TVDetailActivity extends AppCompatActivity {

    public static final String tv = "tv";
    private Boolean isFavorite = false;
    private Menu menuItem = null;
    private TVFavoriteHelper tvFavoriteHelper;
    private TVAdapter tvAdapter = new TVAdapter(this);

    ImageView tdPosterPath;
    TextView tdName, tdFirstAirDate, tdVoteAverage, tdOverview;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        progressBar = findViewById(R.id.progressBar);
        tdPosterPath = findViewById(R.id.td_poster_path);
        tdName = findViewById(R.id.td_name);
        tdFirstAirDate = findViewById(R.id.td_first_air_date);
        tdVoteAverage = findViewById(R.id.td_vote_average);
        tdOverview = findViewById(R.id.td_overview);

        tvFavoriteHelper = TVFavoriteHelper.getInstance(getApplicationContext());

        new LoadTVData().execute();

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

    public void favoriteState(){
        TVModel tvModel = getIntent().getParcelableExtra(tv);
        tvAdapter.setData(tvFavoriteHelper.selectTV(tvModel.getId().toString()));

        if (tvAdapter.getItemCount() > 0) {
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
            TVModel tvModel = getIntent().getParcelableExtra(tv);
            tvFavoriteHelper.insertTV(tvModel);
            Toast.makeText(this, getResources().getString(R.string.addfavorite), Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void removeFromFavorite() {
        try {
            TVModel tvModel = getIntent().getParcelableExtra(tv);
            tvFavoriteHelper.deleteTV(tvModel.getId().toString());
            Toast.makeText(this, getResources().getString(R.string.removefavorite), Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public class LoadTVData extends AsyncTask<Void, Void, Void> {
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

            TVModel tvModel = getIntent().getParcelableExtra(tv);

            Glide.with(getApplicationContext())
                    .load("https://image.tmdb.org/t/p/original/" + tvModel.getPoster_path())
                    .into(tdPosterPath);

            tdName.setText(tvModel.getName());
            tdFirstAirDate.setText(tvModel.getFirst_air_date());
            tdVoteAverage.setText(tvModel.getVote_average().toString());
            tdOverview.setText(tvModel.getOverview());
        }
    }
}