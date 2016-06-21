package wislet.gcephysicsmcqs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import adapters.ReportRecyclerAdapter;
import adapters.topicalCategoriesAdapter;
import beans.QuestionAnswerBean;
import data.DataHolder;

public class TestReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_report);

        ArrayList<QuestionAnswerBean> questionAnswerBeanList = DataHolder.getInstance().getQuestionAnswerBeans();
        RecyclerView.Adapter mAdapter = new ReportRecyclerAdapter(questionAnswerBeanList);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.reportRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
