package data;

import java.util.HashMap;

import data.util.ColumnTypes;

/**
 *
 * Created by Hamza on 4/25/2016.
 */
public class GCE_PHY_CATEGORIESDB extends DataClass{
    public static final String CATEGORY_CODE = "_id";
    public static final String CATEGORY_DESC = "CAT_DESC";
    public static final String CATEGORY_DETAIL = "CAT_DETAIL";
    public static final String TABLE_NAME = "GCE_PHY_CATEGORIES";


    public GCE_PHY_CATEGORIESDB() {
        pkColumns = new HashMap<>();
        pkColumns.put(CATEGORY_CODE, ColumnTypes.INTEGER);

        nonPkColumns = new HashMap<>();
        nonPkColumns.put(CATEGORY_DESC,ColumnTypes.TEXT);
        nonPkColumns.put(CATEGORY_DETAIL,ColumnTypes.TEXT);
        tableName = TABLE_NAME;
    }
}
