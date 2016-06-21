package wislet.gcephysicsmcqs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.YearlyCategoryBean;
import business.QuestionAnswerDataHandler;
import components.WisletCountDownTimer;
import data.DataHolder;
import data.DatabaseHandler;
import data.GCE_PHY_YEARSDB;

public class ApplicationModeActivity extends AppCompatActivity {


    private RelativeLayout menuLayout;
    private Spinner yearSelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_mode);
        try {
            (new DatabaseHandler(ApplicationModeActivity.this)).createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            (new DatabaseHandler(ApplicationModeActivity.this)).openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LinearLayout yearSelectionLayout = (LinearLayout)findViewById(R.id.year_selection_layout);
        assert yearSelectionLayout != null;
        yearSelector = (Spinner) yearSelectionLayout.getChildAt(1);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(ApplicationModeActivity.this,android.R.layout.simple_spinner_item,getListOfYears());
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSelector.setAdapter(yearAdapter);

        CardView practiceModeCard = (CardView) findViewById(R.id.practiceModeCard);
        assert practiceModeCard != null;
        practiceModeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("wislet.gcephysicsmcqs.HomeActivity"));
                overridePendingTransition(R.anim.activity_next_enter, R.anim.activity_next_exit);
            }
        });
        final CardView testModeCard = (CardView) findViewById(R.id.testModeCard);
        menuLayout = (RelativeLayout)findViewById(R.id.menu_layout);
        assert testModeCard != null;
        testModeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLayout.setVisibility(View.VISIBLE);


            }
        });

        CardView menuLayoutCard =(CardView) findViewById (R.id.menu_layout_card);
        assert menuLayoutCard != null;
        menuLayoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        assert menuLayout != null;
        menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuLayout.getVisibility() == View.VISIBLE) {
                    menuLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        Button startTestBtn = (Button) findViewById(R.id.start_test_btn);
        assert startTestBtn != null;
        startTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout explanationItemLayout = (LinearLayout)findViewById(R.id.explanation_menu_item);
                assert explanationItemLayout != null;
                SwitchCompat explanationSwitch = (SwitchCompat) explanationItemLayout.getChildAt(1);
                boolean showExplanation = explanationSwitch.isChecked();
                int yearID = yearSelector.getSelectedItemPosition()+1;
                DataHolder.getInstance().setQuestionAnswerBeans(QuestionAnswerDataHandler.getQuestionAnswersByCategory((new DatabaseHandler(ApplicationModeActivity.this)).getReadableDatabase(), String.valueOf(yearID)));
                Bundle args = new Bundle();
                args.putBoolean("explanation", showExplanation);
                Intent intent = new Intent("wislet.gcephysicsmcqs.QuestionAnswerTestMode");
                intent.putExtras(args);
                WisletCountDownTimer timer = new WisletCountDownTimer(10*1000,1000,ApplicationModeActivity.this);
                timer.start();
                DataHolder.getInstance().setCountDownTimer(timer);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_next_enter, R.anim.activity_next_exit);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(menuLayout!=null && menuLayout.getVisibility()==View.VISIBLE)
            menuLayout.setVisibility(View.INVISIBLE);
        else
            super.onBackPressed();
    }

    public List<String> getListOfYears(){
        DatabaseHandler dbHandler = new DatabaseHandler(ApplicationModeActivity.this);
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        ArrayList<String> selectCols = new ArrayList<>();
        selectCols.add(GCE_PHY_YEARSDB.YEAR_CODE);
        selectCols.add(GCE_PHY_YEARSDB.YEAR_DESC);
        Cursor cursor = (new GCE_PHY_YEARSDB()).selectData(db, selectCols, new ContentValues());
        List<String> listOfYears = new ArrayList<>();
        while (cursor.moveToNext()){
            listOfYears.add(cursor.getString(1));
        }
        cursor.close();
        return listOfYears;
    }

}
