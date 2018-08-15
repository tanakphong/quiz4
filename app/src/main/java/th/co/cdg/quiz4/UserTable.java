package th.co.cdg.quiz4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserTable {
    public static final String TABLE = "userTABLE";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";

    DBOpenHelper dbOpenHelper;
    SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public UserTable(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
        writeSqLiteDatabase = dbOpenHelper.getWritableDatabase();
        readSqLiteDatabase = dbOpenHelper.getReadableDatabase();
    }

    public long addValue(String name, Integer age) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_AGE, age);
        long i = writeSqLiteDatabase.insert(TABLE, null, contentValues);
        return (i);
    }

    public List<UserModel> getAll() {
        List<UserModel> users = new ArrayList<>();
        //hp = new HashMap();
//        Cursor res = readSqLiteDatabase.rawQuery("select * from userTABLE;", null);

        Cursor res = readSqLiteDatabase.query(TABLE, new String[]{"*"},
                null, null, null, null, null, null);

        res.moveToFirst();

        while (!res.isAfterLast()) {
            Log.d("dlg", "getAll: " + res.getString(res.getColumnIndex(COLUMN_NAME)));
            users.add(new UserModel(
                    res.getInt(res.getColumnIndex(COLUMN_ID)),
                    res.getString(res.getColumnIndex(COLUMN_NAME)),
                    res.getInt(res.getColumnIndex(COLUMN_AGE))
            ));
            res.moveToNext();
        }
        res.close();
        return users;

    }

    public long updateValue(Integer id, String name, Integer age) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_AGE, age);

        long rows = writeSqLiteDatabase.update(TABLE, contentValues, " id = ?",
                new String[] { String.valueOf(id) });

        writeSqLiteDatabase.close();
        return rows; // return rows updated.
    }

    public long DeleteData(String id) {
        long rows =  writeSqLiteDatabase.delete(TABLE, "id = ?",
                new String[] { String.valueOf(id) });

        writeSqLiteDatabase.close();
        return rows; // return rows delete.
    }
}
