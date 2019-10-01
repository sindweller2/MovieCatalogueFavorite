package submission4.moviecataloguefavorite;


import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class TVFavoriteFragment extends Fragment {

    private static final String EXTRA_STATE = "EXTRA_STATE";
    private TVAdapter tvAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TVFavoriteHelper tvFavoriteHelper;

    public TVFavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = getView().findViewById(R.id.progressBarTVFavorite);
        recyclerView = getView().findViewById(R.id.rv_tv_favorite);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tvFavoriteHelper = TVFavoriteHelper.getInstance(getContext());

        tvAdapter = new TVAdapter(getContext());

        recyclerView.setAdapter(tvAdapter);

        if (savedInstanceState == null) {
            new LoadTVFavoriteData().execute();
        } else {
            ArrayList<TVModel> tvModels = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (tvModels != null) {
                tvAdapter.setData(tvModels);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadTVFavoriteData().execute();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, tvAdapter.getTvModels());
    }

    public class LoadTVFavoriteData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tvAdapter.setData(tvFavoriteHelper.selectTV());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);

        }
    }
}
