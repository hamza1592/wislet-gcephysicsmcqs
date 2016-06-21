package data;

import java.util.HashMap;

import data.util.ColumnTypes;

/**
 *
 * Created by Hamza on 4/25/2016.
 */
public class GCE_PHY_QUESTIONMAPPINGSDB extends DataClass {
    public static final String MAPPING_CODE = "_id";
    public static final String QUESTION_CODE = "QUESTION_CODE";
    public static final String CATEGORY_CODE = "CAT_CODE";
    public static final String ANSWER_CODE1 = "ANSWER_CODE1";
    public static final String ANSWER_CODE2 = "ANSWER_CODE2";
    public static final String ANSWER_CODE3 = "ANSWER_CODE3";
    public static final String ANSWER_CODE4 = "ANSWER_CODE4";
    public static final String CORRECT_ANSWER_CODE = "ANSWER_CODE5";
    public static final String YEAR_CODE = "YEAR_CODE";
    public static final String TABLE_NAME = "GCE_PHY_QUESTIONMAPPINGS";


    public GCE_PHY_QUESTIONMAPPINGSDB() {
        pkColumns = new HashMap<>();
        pkColumns.put(MAPPING_CODE, ColumnTypes.INTEGER);

        nonPkColumns = new HashMap<>();
        nonPkColumns.put(QUESTION_CODE, ColumnTypes.INTEGER);
        nonPkColumns.put(CATEGORY_CODE, ColumnTypes.INTEGER);
        nonPkColumns.put(YEAR_CODE, ColumnTypes.INTEGER);
        nonPkColumns.put(CORRECT_ANSWER_CODE, ColumnTypes.INTEGER);
        nonPkColumns.put(ANSWER_CODE1, ColumnTypes.INTEGER);
        nonPkColumns.put(ANSWER_CODE2, ColumnTypes.INTEGER);
        nonPkColumns.put(ANSWER_CODE3, ColumnTypes.INTEGER);
        nonPkColumns.put(ANSWER_CODE4, ColumnTypes.INTEGER);
        tableName = TABLE_NAME;
    }
}
