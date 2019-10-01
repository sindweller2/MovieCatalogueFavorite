package submission4.moviecataloguefavorite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static submission4.moviecataloguefavorite.TVDatabaseContract.TVDatabaseColumns.table_name;

public class TVDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tvdb.db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s "
                    + " ( %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT)",
            table_name,
            TVDatabaseContract.TVDatabaseColumns.id,
            TVDatabaseContract.TVDatabaseColumns.poster_path,
            TVDatabaseContract.TVDatabaseColumns.name,
            TVDatabaseContract.TVDatabaseColumns.first_air_date,
            TVDatabaseContract.TVDatabaseColumns.vote_average,
            TVDatabaseContract.TVDatabaseColumns.overview
    );

    TVDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
}
