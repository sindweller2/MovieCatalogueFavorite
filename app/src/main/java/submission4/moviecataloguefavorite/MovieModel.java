package submission4.moviecataloguefavorite;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieModel implements Parcelable {

    private Double popularity;
    private Integer vote_count;
    private String poster_path;
    private Integer id;
    private String backdrop_path;
    private String original_language;
    private String original_title;
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
//        this.popularity = in.readDouble();
//        this.vote_count = in.readInt();
        this.poster_path = in.readString();
        this.id = in.readInt();
//        this.backdrop_path = in.readString();
//        this.original_language = in.readString();
//        this.original_title = in.readString();
        this.title = in.readString();
        this.vote_average = in.readDouble();
        this.overview = in.readString();
        this.release_date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeDouble(this.popularity);
//        parcel.writeInt(this.vote_count);
        parcel.writeString(this.poster_path);
        parcel.writeInt(this.id);
//        parcel.writeString(this.backdrop_path);
//        parcel.writeString(this.original_language);
//        parcel.writeString(this.original_title);
        parcel.writeString(this.title);
        parcel.writeDouble(this.vote_average);
        parcel.writeString(this.overview);
        parcel.writeString(this.release_date);
    }

    MovieModel(JSONObject jsonObject) {

        try {

            Double popularity = jsonObject.getDouble("popularity");
            Integer vote_count = jsonObject.getInt("vote_count");
            String poster_path = jsonObject.getString("poster_path");
            Integer id = jsonObject.getInt("id");
            String backdrop_path = jsonObject.getString("backdrop_path");
            String original_language = jsonObject.getString("original_language");
            String original_title = jsonObject.getString("original_title");
            String title = jsonObject.getString("title");
            Double vote_average = jsonObject.getDouble("vote_average");
            String overview = jsonObject.getString("overview");
            String release_date = jsonObject.getString("release_date");

            this.popularity = popularity;
            this.vote_count = vote_count;
            this.poster_path = poster_path;
            this.id = id;
            this.backdrop_path = backdrop_path;
            this.original_language = original_language;
            this.original_title = original_title;
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