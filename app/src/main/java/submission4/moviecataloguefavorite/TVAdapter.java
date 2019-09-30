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

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.TVViewHolder> {

    private Context context;
    private ArrayList<TVModel> tvModels;

    public ArrayList<TVModel> getTvModels() {
        return tvModels;
    }

    public TVAdapter(Context context) {
        this.context = context;
        tvModels = new ArrayList<>();
    }

    public void setData(ArrayList<TVModel> items) {
        tvModels.clear();
        tvModels.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(final TVModel item) {
        tvModels.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        tvModels.clear();
    }

    @NonNull
    @Override
    public TVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View tView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv, viewGroup, false);
        return new TVViewHolder(tView);
    }

    @Override
    public void onBindViewHolder(@NonNull TVViewHolder tvViewHolder, int position) {
        tvViewHolder.bind(tvModels.get(position));
    }

    @Override
    public int getItemCount() {
        return tvModels.size();
    }

    class TVViewHolder extends RecyclerView.ViewHolder {
        TextView tName;
        TextView tOverview;
        ImageView tPosterPath;

        TVViewHolder(@NonNull View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.t_name);
            tOverview = itemView.findViewById(R.id.t_overview);
            tPosterPath = itemView.findViewById(R.id.t_poster_path);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TVDetailActivity.class);
                    intent.putExtra(TVDetailActivity.tv, tvModels.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

        void bind(TVModel tvModel) {
            tName.setText(tvModel.getName());
            tOverview.setText(tvModel.getOverview());
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original/" + tvModel.getPoster_path())
                    .into(tPosterPath);
        }
    }
}
