package submission4.moviecataloguefavorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import static submission4.moviecataloguefavorite.MovieDatabaseContract.MovieDatabaseColumns.id;
import static submission4.moviecataloguefavorite.MovieDatabaseContract.MovieDatabaseColumns.overview;
import static submission4.moviecataloguefavorite.MovieDatabaseContract.MovieDatabaseColumns.poster_path;
import static submission4.moviecataloguefavorite.MovieDatabaseContract.MovieDatabaseColumns.release_date;
import static submission4.moviecataloguefavorite.MovieDatabaseContract.MovieDatabaseColumns.table_name;
import static submission4.moviecataloguefavorite.MovieDatabaseContract.MovieDatabaseColumns.title;
import static submission4.moviecataloguefavorite.MovieDatabaseContract.MovieDatabaseColumns.vote_average;

public class MovieFavoriteHelper {
    private static final String DATABASE_TABLE = table_name;
    private static MovieDatabaseHelper movieDatabaseHelper;
    private static MovieFavoriteHelper movieFavoriteHelper;
    private static SQLiteDatabase sqLiteDatabase;

    private MovieFavoriteHelper(Context context) {
        movieDatabaseHelper = new MovieDatabaseHelper(context);
    }

    public static MovieFavoriteHelper getInstance(Context context) {
        if (movieFavoriteHelper == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (movieFavoriteHelper == null) {
                    movieFavoriteHelper = new MovieFavoriteHelper(context);
                }
            }
        }
        return movieFavoriteHelper;
    }

    public void close() {
        movieDatabaseHelper.close();

        if (sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }

    public ArrayList<MovieModel> selectMovie() {
        ArrayList<MovieModel> arrayList = new ArrayList<>();

        sqLiteDatabase = movieDatabaseHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        MovieModel movieModel;

        if (cursor.getCount() > 0) {
            do {
                movieModel = new MovieModel();
                movieModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(id)));
                movieModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(title)));
                movieModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(overview)));
                movieModel.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(poster_path)));
                movieModel.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(vote_average)));
                movieModel.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(release_date)));

                arrayList.add(movieModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<MovieModel> selectMovie(String movie_id) {
        ArrayList<MovieModel> arrayList = new ArrayList<>();

        sqLiteDatabase = movieDatabaseHelper.getReadableDatabase();

        String selection = id + " = ?";
        String[] selectionArgs = { movie_id };

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, null,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        MovieModel movieModel;

        if (cursor.getCount() > 0) {
            do {
                movieModel = new MovieModel();
                movieModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(id)));
                movieModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(title)));
                movieModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(overview)));
                movieModel.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(poster_path)));
                movieModel.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(vote_average)));
                movieModel.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(release_date)));

                arrayList.add(movieModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertMovie(MovieModel movieModel) {
        sqLiteDatabase = movieDatabaseHelper.getWritableDatabase();

        ContentValues args = new ContentValues();
        args.put(id, movieModel.getId());
        args.put(title, movieModel.getTitle());
        args.put(overview, movieModel.getOverview());
        args.put(poster_path, movieModel.getPoster_path());
        args.put(vote_average, movieModel.getVote_average());
        args.put(release_date, movieModel.getRelease_date());
        sqLiteDatabase.insert(DATABASE_TABLE, null, args);
    }

    public void deleteMovie(String movie_id) {
        sqLiteDatabase = movieDatabaseHelper.getWritableDatabase();
        sqLiteDatabase.delete(table_name, id + " = '" + movie_id + "'", null);
    }
}
