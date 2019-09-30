package submission4.moviecataloguefavorite;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class TVFragment extends Fragment {

    private TVAdapter tvAdapter;
    private ProgressBar progressBar;
    private TVViewModel tvViewModel;

    public TVFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvViewModel = ViewModelProviders.of(this).get(TVViewModel.class);
        tvViewModel.getData().observe(this, getData);

        tvAdapter = new TVAdapter(getContext());
        tvAdapter.notifyDataSetChanged();

        RecyclerView recyclerView = getView().findViewById(R.id.rv_tv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(tvAdapter);

        progressBar = getView().findViewById(R.id.progressBar);

        tvViewModel.setData(getResources().getString(R.string.language));
        showLoading(true);

    }

    private Observer<ArrayList<TVModel>> getData = new Observer<ArrayList<TVModel>>() {
        @Override
        public void onChanged(ArrayList<TVModel> tModels) {
            if (tModels != null) {
                tvAdapter.setData(tModels);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}