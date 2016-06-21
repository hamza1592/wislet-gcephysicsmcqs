package data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

import data.util.ColumnTypes;

/**
 *
 * Created by Hamza on 4/20/2016.
 */
public abstract class DataClass {
    String tableName;
    HashMap<String, String> nonPkColumns;
    HashMap<String, String> pkColumns;

    public void createTable(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder("CREATE TABLE ").append(tableName).append("(");
        for (String pkColumn : pkColumns.keySet()) {
            query.append(pkColumn)
                    .append(" ")
                    .append(pkColumns.get(pkColumn))
                    .append(" ")
                    .append(ColumnTypes.PRIMARY_KEY)
                    .append(",");
        }

        for (String nonPkColumn : nonPkColumns.keySet()) {
            query.append(nonPkColumn)
                    .append(" ")
                    .append(nonPkColumns.get(nonPkColumn))
                    .append(",");
        }

        int lastIndexOfComma = query.lastIndexOf(",");
        if (lastIndexOfComma > -1)
            query.deleteCharAt(lastIndexOfComma);

        query.append(");");
        System.out.println(query);
        db.execSQL(query.toString());
    }

    public Cursor selectData(SQLiteDatabase db, ArrayList<String> selectCols, HashMap<String,Object> whereClause){
        StringBuilder whereClauseString = new StringBuilder("");
        ArrayList<String> whereClauseArray = new ArrayList<>();
        if (whereClause!=null && whereClause.size()>0) {
            int count = 0;
            for (String whereCol : whereClause.keySet()) {
                if ((whereClause.get(whereCol)).getClass().equals(ArrayList.class)){
                   whereClauseString.append(whereCol).append(" IN (");
                    for(Object inClause: (ArrayList) whereClause.get(whereCol)){
                        whereClauseString.append("? ,");
                        whereClauseArray.add(inClause+"");
                        count++;
                    }
                    int lastIndexOfComma = whereClauseString.lastIndexOf(",");
                    if (lastIndexOfComma > -1)
                        whereClauseString.deleteCharAt(lastIndexOfComma);
                    whereClauseString.append(") AND ");

                }
                else {
                    whereClauseString.append(whereCol).append(" = ? AND ");
                    whereClauseArray.add(whereClause.get(whereCol)+"");
                    count++;
                }

            }
        }
        int lastIndexOfAnd = whereClauseString.lastIndexOf("AND");
        if(lastIndexOfAnd > -1)
            whereClauseString.delete(lastIndexOfAnd, lastIndexOfAnd+"AND".length());

        String[] selectColsAsArray = selectCols.toArray(new String[selectCols.size()]);
        String[] whereClauseArgs = whereClauseArray.toArray(new String[whereClauseArray.size()]);
        if (whereClauseArgs.length > 0 && whereClauseArgs[0] == null)
            whereClauseArgs = null;
        return db.query(tableName, selectColsAsArray, whereClauseString.toString(), whereClauseArgs, null, null, null);
    }
    public Cursor selectData(SQLiteDatabase db, ArrayList<String> selectCols, ContentValues whereClause) {


        StringBuilder whereClauseString = new StringBuilder("");
        String[] whereClauseArgs = new String[0];
        if (whereClause!=null && whereClause.size()>0) {
            whereClauseArgs = new String[whereClause.size()];
            int count = 0;
            for (String whereCol : whereClause.keySet()) {
                whereClauseString.append(whereCol).append(" = ? AND ");
                whereClauseArgs[count] = whereClause.getAsString(whereCol);
            }
        }
        int lastIndexOfAnd = whereClauseString.lastIndexOf("AND");
        if(lastIndexOfAnd > -1)
            whereClauseString.delete(lastIndexOfAnd, lastIndexOfAnd+"AND".length());

        String[] selectColsAsArray = selectCols.toArray(new String[selectCols.size()]);
        return db.query(tableName, selectColsAsArray, whereClauseString.toString(), whereClauseArgs, null, null, null);
    }

    public Long insertData(SQLiteDatabase db, ContentValues contentValues) {
        return db.insert(tableName, "", contentValues);
    }

    public int updateData(SQLiteDatabase db,ContentValues insertClause, ContentValues whereClause){

        StringBuilder whereClauseString = new StringBuilder("");
        String[] whereClauseArgs = new String[0];
        if (whereClause!=null && whereClause.size()>0) {
            whereClauseArgs = new String[whereClause.size()];
            int count = 0;
            for (String whereCol : whereClause.keySet()) {
                whereClauseString.append(whereCol).append(" = ? AND ");
                whereClauseArgs[count] = whereClause.getAsString(whereCol);
            }
        }
        int lastIndexOfAnd = whereClauseString.lastIndexOf("AND");
        if(lastIndexOfAnd > -1)
            whereClauseString.delete(lastIndexOfAnd, lastIndexOfAnd+"AND".length());


        return db.update(this.tableName,insertClause,whereClauseString.toString(),whereClauseArgs);
    }

    public int deleteData(SQLiteDatabase db,ContentValues whereClause){
        StringBuilder whereClauseString = new StringBuilder();

        String[] whereClauseArgs = new String[0];
        if (whereClause!=null && whereClause.size()>0) {
            whereClauseArgs = new String[whereClause.size()];
            int count = 0;
            for (String whereCol : whereClause.keySet()) {
                whereClauseString.append(whereCol).append(" = ? AND ");
                whereClauseArgs[count] = whereClause.getAsString(whereCol);
            }
        }
        int lastIndexOfAnd = whereClauseString.lastIndexOf("AND");
        if(lastIndexOfAnd > -1)
            whereClauseString.delete(lastIndexOfAnd, lastIndexOfAnd+"AND".length());

        return db.delete(this.tableName,whereClauseString.toString(),whereClauseArgs);
    }




}
