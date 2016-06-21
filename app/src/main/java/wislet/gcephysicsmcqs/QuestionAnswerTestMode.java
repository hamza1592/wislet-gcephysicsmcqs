package wislet.gcephysicsmcqs;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import beans.AnswerBean;
import beans.QuestionAnswerBean;
import beans.QuestionBean;
import components.WisletCountDownTimer;
import data.DataHolder;
import data.util.CommonFunctions;


public class QuestionAnswerTestMode extends AppCompatActivity {

    private static ArrayList<QuestionAnswerBean> questionAnswerBeanList;
    private static String QUESTION_INDEX_ARG = "question_number";
    private static int CURRENT_QUESTION_INDEX = 0;
    private static WisletCountDownTimer questionCountDownTimer;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private YearlySectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;

    /**
     *
     * variables for the working of navigation drawer
     */
    String[] mQuestionsList;
    static DrawerLayout mDrawerLayout;
    ListView mDrawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer_testmode_drawer);


        /*Here i fill the questionAnswerBeanList with fetched question answers*/
        Bundle args = getIntent().getExtras();
        questionAnswerBeanList = DataHolder.getInstance().getQuestionAnswerBeans();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questionCountDownTimer = DataHolder.getInstance().getCountDownTimer();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new YearlySectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        assert mViewPager != null;
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        assert tabs != null;
        tabs.setVisibility(View.GONE);

        /**
         * The following code is for the navigation drawer
         */
        mQuestionsList = CommonFunctions.getQuestionsArray(questionAnswerBeanList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.question_answer_navdrawer);
        mDrawerList = (ListView) findViewById(R.id.qa_navdrawer_list);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.navigation_drawer_list_item, mQuestionsList));
        // Set the list's click listener

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != CURRENT_QUESTION_INDEX) {
                    Intent intent = new Intent("wislet.gcephysicsmcqs.QuestionAnswerTestMode");
                    Bundle bundle = new Bundle();
                    bundle.putInt(QUESTION_INDEX_ARG, position);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_next_enter, R.anim.activity_next_exit);
                } else {
                    Snackbar.make(view, "Currently on the same question", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        RelativeLayout homeButton = (RelativeLayout) findViewById(R.id.navHomeButton);
        assert homeButton != null;
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionAnswerTestMode.this, ApplicationModeActivity.class));
            }
        });

        RelativeLayout catButton = (RelativeLayout) findViewById(R.id.navCategoryButton);
        assert catButton != null;
        catButton.setVisibility(View.GONE);
        
        RelativeLayout timerLayout = (RelativeLayout) findViewById(R.id.timerLayout);
        timerLayout.setVisibility(View.VISIBLE);

        ProgressBar questionTimerProgressBar = (ProgressBar) findViewById(R.id.questionTimer);
        questionTimerProgressBar.setVisibility(View.VISIBLE);
        questionCountDownTimer.setProgressBar(questionTimerProgressBar);

        TextView questionTimerText = (TextView) findViewById(R.id.questionTimerText);
        String timerStringFormat = getResources().getString(R.string.timeCounter);
        questionCountDownTimer.setTextView(questionTimerText, timerStringFormat);
        questionTimerText.setVisibility(View.VISIBLE);
        questionCountDownTimer.setContext(QuestionAnswerTestMode.this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class YearlyPlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public YearlyPlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static YearlyPlaceholderFragment newInstance(int sectionNumber) {
            YearlyPlaceholderFragment fragment = new YearlyPlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            CURRENT_QUESTION_INDEX = (int) Math.floor((sectionNumber) / 2);
            QuestionAnswerBean bean = questionAnswerBeanList.get(CURRENT_QUESTION_INDEX);
            View rootView = null;
            if (((sectionNumber) % 2) == 0) {
                rootView = inflater.inflate(R.layout.fragment_question, container, false);
                /**
                 * The following snippet of code is to set the current question number in the question
                 * fragment
                 */
                ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.questionProgressBar);
                progressBar.setProgress((100 / questionAnswerBeanList.size()) * (CURRENT_QUESTION_INDEX + 1));
                TextView progressBarText = (TextView) rootView.findViewById(R.id.questionProgressBarText);
                String progressBarStrFormat = getResources().getString(R.string.questionCounter);
                String counterMessage = String.format(progressBarStrFormat, CURRENT_QUESTION_INDEX + 1, questionAnswerBeanList.size());
                progressBarText.setText(counterMessage);
                QuestionBean questionBean = bean.getQuestionBean();

                if (questionBean.getQuestion() != null) {
                    TextView questionText = (TextView) rootView.findViewById(R.id.questionText);
                    questionText.setText(questionBean.getQuestion());
                    questionText.setVisibility(View.VISIBLE);
                }
                if (questionBean.getImagePath() != null) {
                    ImageView questionImage = (ImageView) rootView.findViewById(R.id.questionImage);
                }


            } else if (((sectionNumber) % 2) == 1) {

                rootView = inflater.inflate(R.layout.fragment_answer, container, false);

                AnswerBean answerBean1 = bean.getAnswerBean1();
                AnswerBean answerBean2 = bean.getAnswerBean2();
                AnswerBean answerBean3 = bean.getAnswerBean3();
                AnswerBean answerBean4 = bean.getAnswerBean4();
                ArrayList<AnswerBean> answerBeanList = new ArrayList<>();
                answerBeanList.add(answerBean1);
                answerBeanList.add(answerBean2);
                answerBeanList.add(answerBean3);
                answerBeanList.add(answerBean4);

                Boolean isTextQuestion = answerBean1.getAnswer() != null;
                if (isTextQuestion) {
                    int[] answerTextsIdArray = {R.id.answerText1, R.id.answerText2, R.id.answerText3, R.id.answerText4};
                    int index = 0;
                    for (AnswerBean answerBean : answerBeanList) {
                        Button answerButton = (Button) rootView.findViewById(answerTextsIdArray[index]);
                        answerButton.setText(answerBean.getAnswer());
                        answerButton.setVisibility(View.VISIBLE);
                        answerButton.setOnClickListener(new AnswersOnClickListener(answerBean, getContext(), answerBeanList,bean));
                        answerBean.setButton(answerButton);
                        if(bean.isAnswered() && bean.getSelectedAnswer().equals(answerBean)){
                            answerBean.getButton().performClick();
                        }
                        index++;
                    }
                } else {
                    int index = 0;
                    int[] answerImageIdArray = {R.id.answerImage1, R.id.answerImage2, R.id.answerImage3, R.id.answerImage4};
                    for (AnswerBean answerBean : answerBeanList) {
                        ImageButton answerImageButton = (ImageButton) rootView.findViewById(answerImageIdArray[index]);
                        answerImageButton.setImageDrawable(ResourcesCompat.getDrawable(rootView.getResources(), R.mipmap.ic_launcher, null));
                        answerImageButton.setVisibility(View.VISIBLE);
                        answerBean.setImageButton(answerImageButton);
                        if(bean.isAnswered() && bean.getSelectedAnswer().equals(answerBean)){
                            answerBean.getImageButton().performClick();
                        }
                        index++;
                    }
                }
            }
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }


    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class YearlySectionsPagerAdapter extends FragmentPagerAdapter {

        public YearlySectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return YearlyPlaceholderFragment.newInstance((position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            //return questionAnswerBeanList == null ? 0 : 2;
            return questionAnswerBeanList.size() * 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Question";
                case 1:
                    return "Answer";
            }
            return null;
        }


    }


    public static class AnswersOnClickListener implements View.OnClickListener {


        AnswerBean bean;
        Context context;
        ArrayList<AnswerBean> answerBeanList;
        QuestionAnswerBean qaBean;
        public AnswersOnClickListener(AnswerBean bean, Context context, ArrayList<AnswerBean> answerBeanList,QuestionAnswerBean qaBean) {
            this.bean = bean;
            this.context = context;
            this.answerBeanList = answerBeanList;
            this.qaBean = qaBean;
        }

        @Override
        public void onClick(View v) {
            if(bean == qaBean.getSelectedAnswer()){
                bean.getButton().setBackground(ContextCompat.getDrawable(context, android.R.drawable.btn_default));
                qaBean.setAnswered(false);
                qaBean.setSelectedAnswer(null);
            }
            else {
                bean.getButton().setBackground(ContextCompat.getDrawable(context, R.drawable.answer_test_selected));
                bean.getButton().setTextColor(ContextCompat.getColor(context, R.color.answerText));
                qaBean.setAnswered(true);
                qaBean.setSelectedAnswer(bean);
            }
            for (AnswerBean answerBean : answerBeanList) {
                if(!(answerBean == bean)) {
                    Button button = answerBean.getButton();
                    button.setBackground(ContextCompat.getDrawable(context, android.R.drawable.btn_default));
                }
            }

            View.OnClickListener nextBtnOnclick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("wislet.gcephysicsmcqs.QuestionAnswerTestMode");
                    Bundle bundle = new Bundle();
                    bundle.putInt(QUESTION_INDEX_ARG, (CURRENT_QUESTION_INDEX + 1) % questionAnswerBeanList.size());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            };

        }
    }
}



