package submission4.moviecataloguefavorite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static submission4.moviecataloguefavorite.MovieDatabaseContract.MovieDatabaseColumns.table_name;

public class MovieDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "moviedb.db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s "
                    + " ( %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT)",
            table_name,
            MovieDatabaseContract.MovieDatabaseColumns.id,
            MovieDatabaseContract.MovieDatabaseColumns.poster_path,
            MovieDatabaseContract.MovieDatabaseColumns.title,
            MovieDatabaseContract.MovieDatabaseColumns.release_date,
            MovieDatabaseContract.MovieDatabaseColumns.vote_average,
            MovieDatabaseContract.MovieDatabaseColumns.overview
    );

    MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
}
