package wislet.gcephysicsmcqs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.QuestionAnswerBean;
import business.QuestionAnswerDataHandler;
import data.DatabaseHandler;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CardView topicalQuestionsCard = (CardView) findViewById(R.id.topicalQuestionsCard);
        CardView yearlyQuestionsCard = (CardView) findViewById(R.id.yearlyQuestionsCard);
        if (topicalQuestionsCard != null) {
            topicalQuestionsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent("wislet.gcephysicsmcqs.TopicalCategories"));
                    overridePendingTransition(R.anim.activity_next_enter,R.anim.activity_next_exit);
                }
            });
        }

        if (yearlyQuestionsCard != null) {
            yearlyQuestionsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent("wislet.gcephysicsmcqs.YearlyCategories"));
                    overridePendingTransition(R.anim.activity_next_enter, R.anim.activity_next_exit);
                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_prev_enter, R.anim.activity_prev_exit);
    }
}
