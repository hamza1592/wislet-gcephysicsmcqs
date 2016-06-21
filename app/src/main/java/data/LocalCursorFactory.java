package data;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;


/**
 *
 * Created by Hamza on 4/29/2016.
 */
public class LocalCursorFactory implements SQLiteDatabase.CursorFactory {
    @Override
    public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
        SQLiteCursor cursor = new SQLiteCursor(masterQuery,editTable,query);
        System.out.println(query);
        return cursor;
    }



}
