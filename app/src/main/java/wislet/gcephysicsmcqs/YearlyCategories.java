package wislet.gcephysicsmcqs;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapters.YearlyCategoriesAdapter;
import beans.QuestionAnswerBean;
import beans.YearlyCategoryBean;
import business.QuestionAnswerDataHandler;
import components.WisletCountDownTimer;
import data.DataHolder;
import data.DatabaseHandler;
import data.GCE_PHY_CATEGORIESDB;
import data.GCE_PHY_YEARSDB;

public class YearlyCategories extends AppCompatActivity {

    private List<YearlyCategoryBean> yearlyCategoryBeanList = new ArrayList<>();
    public static String VALUE = "Value";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yearly_categories);
        mAdapter = new YearlyCategoriesAdapter(yearlyCategoryBeanList);
        mRecyclerView = (RecyclerView) findViewById(R.id.yearlyCategoriesRecycler);
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
        DatabaseHandler dbHandler = new DatabaseHandler(YearlyCategories.this);
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        ArrayList<String> selectCols = new ArrayList<>();
        selectCols.add(GCE_PHY_YEARSDB.YEAR_CODE);
        selectCols.add(GCE_PHY_YEARSDB.YEAR_DESC);
        selectCols.add(GCE_PHY_YEARSDB.YEAR_DETAIL);

        Cursor cursor = (new GCE_PHY_YEARSDB()).selectData(db, selectCols, new ContentValues());


        while (cursor.moveToNext()){
            final YearlyCategoryBean bean = new YearlyCategoryBean();
            bean.setYearCode(cursor.getString(0));
            bean.setYearDesc(cursor.getString(1));
            bean.setYearDetail(cursor.getString(2));
            bean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("wislet.gcephysicsmcqs.QuestionAnswer");
                    Bundle args = new Bundle();
                    args.putString("Type", "Yearly");
                    args.putString("Value", bean.getYearCode());
                    args.putInt("question_index", 0);
                    DataHolder.getInstance().setQuestionAnswerBeans(fetchQuestionAnswers(args, YearlyCategories.this));
                    intent.putExtras(args);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_next_enter, R.anim.activity_next_exit);
                }
            });

            yearlyCategoryBeanList.add(bean);
        }
        cursor.close();
        mAdapter.notifyDataSetChanged();
    }

    public static ArrayList<QuestionAnswerBean> fetchQuestionAnswers(Bundle args,Context context){
        return QuestionAnswerDataHandler.getQuestionAnswersByYear((new DatabaseHandler(context)).getReadableDatabase(), args.getString(VALUE));

    }
}
