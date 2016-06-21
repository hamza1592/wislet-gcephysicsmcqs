package data;

import java.util.HashMap;

import data.util.ColumnTypes;

/**
 *
 * Created by Hamza on 4/26/2016.
 */
public class GCE_PHY_QUESTIONSDB extends DataClass {


    public static final String QUESTION_CODE = "_id";
    public static final String QUESTION_TEXT = "QUESTION_TEXT";
    public static final String QUESTION_IMG_URL = "QUESTION_IMG";
    public static final String TABLE_NAME = "GCE_PHY_QUESTIONS";

    public GCE_PHY_QUESTIONSDB() {
        pkColumns = new HashMap<>();
        pkColumns.put(QUESTION_CODE, ColumnTypes.INTEGER);
        nonPkColumns = new HashMap<>();
        nonPkColumns.put(QUESTION_TEXT, ColumnTypes.TEXT + " " + ColumnTypes.UNIQUE);
        nonPkColumns.put(QUESTION_IMG_URL, ColumnTypes.TEXT + " " + ColumnTypes.UNIQUE);
        tableName = TABLE_NAME;
    }
}
