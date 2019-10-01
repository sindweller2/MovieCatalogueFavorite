package submission4.moviecataloguefavorite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<MovieModel> movieModels;

    public ArrayList<MovieModel> getMovieModels() {
        return movieModels;
    }

    public MovieAdapter(Context context) {
        this.context = context;
        movieModels = new ArrayList<>();
    }

    public void setData(ArrayList<MovieModel> items) {
        movieModels.clear();
        movieModels.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(final MovieModel item) {
        this.movieModels.add(item);
        notifyItemInserted(movieModels.size() - 1);
    }

    public void clearData() {
        movieModels.clear();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(movieModels.get(position));
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mOverview;
        ImageView mPosterPath;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.m_title);
            mOverview = itemView.findViewById(R.id.m_overview);
            mPosterPath = itemView.findViewById(R.id.m_poster_path);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra(MovieDetailActivity.movie, movieModels.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

        void bind(MovieModel movieModel) {
            mTitle.setText(movieModel.getTitle());
            mOverview.setText(movieModel.getOverview());
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original/" + movieModel.getPoster_path())
                    .into(mPosterPath);
        }
    }
}
