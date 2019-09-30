package submission4.moviecataloguefavorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static submission4.moviecataloguefavorite.TVDatabaseContract.TVDatabaseColumns.first_air_date;
import static submission4.moviecataloguefavorite.TVDatabaseContract.TVDatabaseColumns.name;
import static submission4.moviecataloguefavorite.TVDatabaseContract.TVDatabaseColumns.overview;
import static submission4.moviecataloguefavorite.TVDatabaseContract.TVDatabaseColumns.poster_path;
import static submission4.moviecataloguefavorite.TVDatabaseContract.TVDatabaseColumns.table_name;
import static submission4.moviecataloguefavorite.TVDatabaseContract.TVDatabaseColumns.id;
import static submission4.moviecataloguefavorite.TVDatabaseContract.TVDatabaseColumns.vote_average;

public class TVFavoriteHelper {
    private static final String DATABASE_TABLE = table_name;
    private static TVDatabaseHelper tvDatabaseHelper;
    private static TVFavoriteHelper tvFavoriteHelper;
    private static SQLiteDatabase sqLiteDatabase;

    private TVFavoriteHelper(Context context) {
        tvDatabaseHelper = new TVDatabaseHelper(context);
    }

    public static TVFavoriteHelper getInstance(Context context) {
        if (tvFavoriteHelper == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (tvFavoriteHelper == null) {
                    tvFavoriteHelper = new TVFavoriteHelper(context);
                }
            }
        }
        return tvFavoriteHelper;
    }

    public void close() {
        tvDatabaseHelper.close();

        if (sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }

    public ArrayList<TVModel> selectTV() {
        ArrayList<TVModel> arrayList = new ArrayList<>();

        sqLiteDatabase = tvDatabaseHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        TVModel tvModel;

        if (cursor.getCount() > 0) {
            do {
                tvModel = new TVModel();
                tvModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(id)));
                tvModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(name)));
                tvModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(overview)));
                tvModel.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(poster_path)));
                tvModel.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(vote_average)));
                tvModel.setFirst_air_date(cursor.getString(cursor.getColumnIndexOrThrow(first_air_date)));

                arrayList.add(tvModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<TVModel> selectTV(String tv_id) {
        ArrayList<TVModel> arrayList = new ArrayList<>();

        sqLiteDatabase = tvDatabaseHelper.getReadableDatabase();

        String selection = id + " = ?";
        String[] selectionArgs = { tv_id };

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, null,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        TVModel tvModel;

        if (cursor.getCount() > 0) {
            do {
                tvModel = new TVModel();
                tvModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(id)));
                tvModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(name)));
                tvModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(overview)));
                tvModel.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(poster_path)));
                tvModel.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(vote_average)));
                tvModel.setFirst_air_date(cursor.getString(cursor.getColumnIndexOrThrow(first_air_date)));

                arrayList.add(tvModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertTV(TVModel tvModel) {
        sqLiteDatabase = tvDatabaseHelper.getWritableDatabase();

        ContentValues args = new ContentValues();
        args.put(id, tvModel.getId());
        args.put(name, tvModel.getName());
        args.put(overview, tvModel.getOverview());
        args.put(poster_path, tvModel.getPoster_path());
        args.put(vote_average, tvModel.getVote_average());
        args.put(first_air_date, tvModel.getFirst_air_date());
         sqLiteDatabase.insert(DATABASE_TABLE, null, args);
    }

    public void deleteTV(String tv_id) {
        sqLiteDatabase = tvDatabaseHelper.getWritableDatabase();
         sqLiteDatabase.delete(table_name, id + " = '" + tv_id + "'", null);
    }
}
