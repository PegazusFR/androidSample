package galaxy.pegazus.bienetre.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yklausz on 08/05/2015.
 */
public class LogDayDBHelper extends SQLiteOpenHelper {


    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String NUMERIC_TYPE = " NUMERIC";



    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LogDay.LogDayEntry.TABLE_NAME + " (" +
                    LogDay.LogDayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LogDay.LogDayEntry.COLUMN_NAME_DATE + NUMERIC_TYPE + COMMA_SEP +
                    LogDay.LogDayEntry.COLUMN_NAME_ASSESSMENT + INTEGER_TYPE + COMMA_SEP +
                    LogDay.LogDayEntry.COLUMN_NAME_COMMENT + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LogDay.LogDayEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LogDayDBHelper.db";

    public LogDayDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.i("SQLite DB", "Creation");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
        Log.i("SQLite DB", "onUpgrade");
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onUpgrade(db, oldVersion, newVersion);
        Log.i("SQLite DB", "onDowngrade");
    }


    /**
     * Insert  an {@LogDay}
     * @param logDay
     * @return
     */
    public long insertLog(LogDay logDay){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(LogDay.LogDayEntry.COLUMN_NAME_DATE, logDay.getDate());
        values.put(LogDay.LogDayEntry.COLUMN_NAME_ASSESSMENT, logDay.getAssessment());
        values.put(LogDay.LogDayEntry.COLUMN_NAME_COMMENT, logDay.getComment());
        // insertion
        long result = db.insert(LogDay.LogDayEntry.TABLE_NAME, // table
                null, values);

        db.close();
        Log.i("SQLiteDB:Add: id=", logDay.toString());
        return result;
    }

    /**
     * Insert  an {@LogDayEntity}
     * @param logDay
     * @return
     */
    public int updateLog(LogDay logDay){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LogDay.LogDayEntry._ID, logDay.get_id());
        values.put(LogDay.LogDayEntry.COLUMN_NAME_DATE, logDay.getDate());
        values.put(LogDay.LogDayEntry.COLUMN_NAME_ASSESSMENT, logDay.getAssessment());
        values.put(LogDay.LogDayEntry.COLUMN_NAME_COMMENT, logDay.getComment());
        // insertion
        int result = db.update(LogDay.LogDayEntry.TABLE_NAME, values, " _id= ? ", new String[]{logDay.get_id().toString()});

        db.close();
        Log.i("SQLiteDB:Update:id=  ", logDay.toString());
        return result;
    }




    /**
     * Get all logs
     * @return
     */
    public List<LogDay> showAll() {

        List<LogDay> logs = new LinkedList<LogDay>();
        String query = "SELECT * FROM " + LogDay.LogDayEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        LogDay logday = null;
        if (cursor.moveToFirst()) {
            do {
                logday = new LogDay();
                logday.set_id(cursor.getInt(0));
                logday.setDate(cursor.getLong(1));
                logday.setAssessment(cursor.getInt(2));
                logday.setComment(cursor.getString(3));
                logs.add(logday);
            } while (cursor.moveToNext());
        }
        Log.i("SQLiteDB:Show All : ", logs.toString());
        return logs;
    }


    /**
     * Get a log
     * @return
     */
    public LogDay getLog(String id) {

        LogDay  resulat = new LogDay();
        String query = "SELECT * FROM " + LogDay.LogDayEntry.TABLE_NAME +" WHERE +"+ LogDay.LogDayEntry._ID+"="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                resulat.set_id(cursor.getInt(0));
                resulat.setDate(cursor.getLong(1));
                resulat.setAssessment(cursor.getInt(2));
                resulat.setComment(cursor.getString(3));

            } while (cursor.moveToNext());
        }
        Log.i("SQLiteDB:Show All : ", resulat.toString());
        return resulat;
    }



    /**
     * Get all logs
     * @return cursor
     */
    public Cursor showAllcursor() {

        List<LogDay> logs = new LinkedList<LogDay>();
        String query = "SELECT _id , date FROM " + LogDay.LogDayEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

}
