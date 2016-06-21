package wislet.gcephysicsmcqs;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapters.topicalCategoriesAdapter;
import beans.QuestionAnswerBean;
import beans.TopicalCategoryBean;
import business.QuestionAnswerDataHandler;
import data.DataHolder;
import data.DatabaseHandler;
import data.GCE_PHY_CATEGORIESDB;

public class TopicalCategories extends AppCompatActivity {

    private List<TopicalCategoryBean> topicalCategoryBeanList = new ArrayList<>();
    public static String VALUE = "Value";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topical_categories);
        mAdapter = new topicalCategoriesAdapter(topicalCategoryBeanList);
        mRecyclerView = (RecyclerView) findViewById(R.id.topicalCategoriesRecycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        prepareCategories();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_prev_enter, R.anim.activity_prev_exit);
    }

    private void prepareCategories(){
        DatabaseHandler dbHandler = new DatabaseHandler(TopicalCategories.this);
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        ArrayList<String> selectCols = new ArrayList<>();
        selectCols.add(GCE_PHY_CATEGORIESDB.CATEGORY_CODE);
        selectCols.add(GCE_PHY_CATEGORIESDB.CATEGORY_DESC);
        selectCols.add(GCE_PHY_CATEGORIESDB.CATEGORY_DETAIL);

        Cursor cursor = (new GCE_PHY_CATEGORIESDB()).selectData(db, selectCols, new ContentValues());


        while (cursor.moveToNext()){
            final TopicalCategoryBean bean = new TopicalCategoryBean();
            bean.setCategoryCode(cursor.getString(0));
            bean.setCategoryDesc(cursor.getString(1));
            bean.setCategoryDetail(cursor.getString(2));
            bean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("wislet.gcephysicsmcqs.QuestionAnswer");
                    Bundle args = new Bundle();
                    args.putString("Type", "Topical");
                    args.putString("Value", bean.getCategoryCode());
                    args.putInt("question_index", 0);
                    DataHolder.getInstance().setQuestionAnswerBeans(fetchQuestionAnswers(args, TopicalCategories.this));
                    intent.putExtras(args);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_next_enter, R.anim.activity_next_exit);
                }
            });

            topicalCategoryBeanList.add(bean);
        }
        cursor.close();
        mAdapter.notifyDataSetChanged();
    }

    public static ArrayList<QuestionAnswerBean> fetchQuestionAnswers(Bundle args,Context context){
            return QuestionAnswerDataHandler.getQuestionAnswersByCategory((new DatabaseHandler(context)).getReadableDatabase(), args.getString(VALUE));
    }

}
