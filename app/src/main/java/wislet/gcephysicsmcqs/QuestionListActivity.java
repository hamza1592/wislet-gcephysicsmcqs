package wislet.gcephysicsmcqs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import adapters.MyQuestionsListAdapter;
import beans.QuestionAnswerBean;
import beans.QuestionBean;
import data.DataHolder;

public class QuestionListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        final Bundle args = getIntent().getExtras();
        ArrayList<QuestionAnswerBean> questionAnswerBeans = DataHolder.getInstance().getQuestionAnswerBeans();
        if (questionAnswerBeans.size() <= 0){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuestionListActivity.this);
            alertDialog.setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent intent = null;
                    String previousActivity = String.valueOf(args.get("Type"));
                    if (previousActivity.equalsIgnoreCase("topical")) {
                        intent = new Intent("wislet.gcephysicsmcqs.TopicalCategories");

                    } else if (previousActivity.equalsIgnoreCase("yearly")) {
                        intent = new Intent("wislet.gcephysicsmcqs.YearlyCategories");
                    }
                    assert intent != null;
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
            });
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.questionsRecycleList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyQuestionsListAdapter(questionAnswerBeans);
        mRecyclerView.setAdapter(mAdapter);
    }



}
