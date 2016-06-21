package data;

import java.util.HashMap;

import data.util.ColumnTypes;

/**
 *
 * Created by Hamza on 5/15/2016.
 */
public class GCE_PHY_YEARSDB extends DataClass{

    public static final String YEAR_CODE = "_id";
    public static final String YEAR_DESC = "YEAR_DESC";
    public static final String YEAR_DETAIL = "YEAR_DETAIL";
    public static final String TABLE_NAME = "GCE_PHY_YEARS";


    public GCE_PHY_YEARSDB() {
        pkColumns = new HashMap<>();
        pkColumns.put(YEAR_CODE, ColumnTypes.INTEGER);

        nonPkColumns = new HashMap<>();
        nonPkColumns.put(YEAR_DESC,ColumnTypes.TEXT);
        nonPkColumns.put(YEAR_DETAIL,ColumnTypes.TEXT);
        tableName = TABLE_NAME;
    }
}
