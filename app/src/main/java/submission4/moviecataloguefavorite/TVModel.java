package submission4.moviecataloguefavorite;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TVModel implements Parcelable {

    private String original_name;
    private String name;
    private Double popularity;
    private String origin_country;
    private Integer vote_count;
    private String first_air_date;
    private String backdrop_path;
    private String original_language;
    private Integer id;
    private Double vote_average;
    private String overview;
    private String poster_path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public TVModel(Parcel in) {

//        this.original_name = in.readString();
        this.name = in.readString();
//        this.popularity = in.readDouble();
//        this.origin_country = in.readString();
//        this.vote_count = in.readInt();
        this.first_air_date = in.readString();
//        this.backdrop_path = in.readString();
//        this.original_language = in.readString();
        this.id = in.readInt();
        this.vote_average = in.readDouble();
        this.overview = in.readString();
        this.poster_path = in.readString();

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

//        parcel.writeString(this.original_name);
        parcel.writeString(this.name);
//        parcel.writeDouble(this.popularity);
//        parcel.writeString(this.origin_country);
//        parcel.writeInt(this.vote_count);
        parcel.writeString(this.first_air_date);
//        parcel.writeString(this.backdrop_path);
//        parcel.writeString(this.original_language);
        parcel.writeInt(this.id);
        parcel.writeDouble(this.vote_average);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster_path);

    }

    TVModel(JSONObject jsonObject) {

        try {

            String original_name = jsonObject.getString("original_name");
            String name = jsonObject.getString("name");
            Double popularity = jsonObject.getDouble("popularity");
            String origin_country = jsonObject.getString("origin_country");
            Integer vote_count = jsonObject.getInt("vote_count");
            String first_air_date = jsonObject.getString("first_air_date");
            String backdrop_path = jsonObject.getString("backdrop_path");
            String original_language = jsonObject.getString("original_language");
            Integer id = jsonObject.getInt("id");
            Double vote_average = jsonObject.getDouble("vote_average");
            String overview = jsonObject.getString("overview");
            String poster_path = jsonObject.getString("poster_path");

            this.original_name = original_name;
            this.name = name;
            this.popularity = popularity;
            this.origin_country = origin_country;
            this.vote_count = vote_count;
            this.first_air_date = first_air_date;
            this.backdrop_path = backdrop_path;
            this.original_language = original_language;
            this.id = id;
            this.vote_average = vote_average;
            this.overview = overview;
            this.poster_path = poster_path;

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static final Creator<TVModel> CREATOR = new Creator<TVModel>() {
        @Override
        public TVModel createFromParcel(Parcel in) {
            return new TVModel(in);
        }

        @Override
        public TVModel[] newArray(int size) {
            return new TVModel[size];
        }
    };

    public TVModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
