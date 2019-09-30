package submission4.moviecataloguefavorite;

import android.provider.BaseColumns;

public class TVDatabaseContract {
    static final class TVDatabaseColumns implements BaseColumns {
        static final String table_name = "tv";
        static final String id = "id";
        static final String poster_path = "poster_path";
        static final String name = "name";
        static final String first_air_date = "first_air_date";
        static final String vote_average = "vote_average";
        static final String overview = "overview";
    }
}
