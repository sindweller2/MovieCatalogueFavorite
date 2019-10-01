package submission4.moviecataloguefavorite;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public class MovieModel implements Parcelable {

    private String poster_path;
    private Integer id;
    private String title;
    private Double vote_average;
    private String overview;
    private String release_date;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public MovieModel(Parcel in) {
        this.poster_path = in.readString();
        this.id = in.readInt();
        this.title = in.readString();
        this.vote_average = in.readDouble();
        this.overview = in.readString();
        this.release_date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.poster_path);
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeDouble(this.vote_average);
        parcel.writeString(this.overview);
        parcel.writeString(this.release_date);
    }

    MovieModel(JSONObject jsonObject) {

        try {

            String poster_path = jsonObject.getString("poster_path");
            Integer id = jsonObject.getInt("id");
            String title = jsonObject.getString("title");
            Double vote_average = jsonObject.getDouble("vote_average");
            String overview = jsonObject.getString("overview");
            String release_date = jsonObject.getString("release_date");

            this.poster_path = poster_path;
            this.id = id;
            this.title = title;
            this.vote_average = vote_average;
            this.overview = overview;
            this.release_date = release_date;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public MovieModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }
}