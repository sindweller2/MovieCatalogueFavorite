package submission4.moviecataloguefavorite;

import android.provider.BaseColumns;

public class MovieDatabaseContract {
    static final class MovieDatabaseColumns implements BaseColumns {
        static final String table_name = "movie";
        static final String id = "id";
        static final String poster_path = "poster_path";
        static final String title = "title";
        static final String release_date = "release_date";
        static final String vote_average = "vote_average";
        static final String overview = "overview";
    }
}
