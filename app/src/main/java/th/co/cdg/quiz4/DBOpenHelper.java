package th.co.cdg.quiz4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by tphon on 5/12/2560.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE = "create table userTABLE (id integer primary key, name text, age integer);";


    public DBOpenHelper(Context context) {

        super(context, Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
