package data;

import java.util.HashMap;

import data.util.ColumnTypes;

/**
 *
 * Created by Hamza on 4/26/2016.
 */
public class GCE_PHY_ANSWERSDB extends DataClass {

    public static final String ANSWER_CODE = "_id";
    public static final String ANSWER_TEXT = "ANSWER_TEXT";
    public static final String ANSWER_IMG_URL = "ANSWER_IMG";
    public static final String TABLE_NAME = "GCE_PHY_ANSWERS";


    public GCE_PHY_ANSWERSDB() {
        pkColumns = new HashMap<>();
        pkColumns.put(ANSWER_CODE, ColumnTypes.INTEGER);
        nonPkColumns = new HashMap<>();
        nonPkColumns.put(ANSWER_TEXT, ColumnTypes.TEXT + " " + ColumnTypes.UNIQUE);
        nonPkColumns.put(ANSWER_IMG_URL, ColumnTypes.TEXT + " " + ColumnTypes.UNIQUE);
        tableName = TABLE_NAME;
    }
}
