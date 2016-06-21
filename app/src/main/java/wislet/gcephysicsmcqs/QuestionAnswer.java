package wislet.gcephysicsmcqs;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
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
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
import data.DataHolder;
import data.util.CommonFunctions;


public class QuestionAnswer extends AppCompatActivity {

    private static ArrayList<QuestionAnswerBean> questionAnswerBeanList;
    public static String QUESTION_INDEX_ARG = "question_number";
    private static int CURRENT_QUESTION_INDEX = 0;
    private static boolean isCardFront = false;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;
    String[] mQuestionsList;
    static DrawerLayout mDrawerLayout;
    ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getIntent().getExtras();
        CURRENT_QUESTION_INDEX = args.getInt(QUESTION_INDEX_ARG);

        setContentView(R.layout.activity_question_answer_drawer);
        /*Here i fill the questionAnswerBeanList with fetched question answers*/
        questionAnswerBeanList = DataHolder.getInstance().getQuestionAnswerBeans();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        tabs.setupWithViewPager(mViewPager);


        /**
         * The following code is for the navigation drawer
         */

        mQuestionsList = CommonFunctions.getQuestionsArray(questionAnswerBeanList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.question_answer_navdrawer);
        mDrawerList = (ListView) findViewById(R.id.qa_navdrawer_list);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.navigation_drawer_list_item, mQuestionsList));
        // Set the list's click listener

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != CURRENT_QUESTION_INDEX) {
                    Intent intent = new Intent("wislet.gcephysicsmcqs.QuestionAnswer");
                    Bundle bundle = new Bundle();
                    bundle.putInt(QUESTION_INDEX_ARG, position);
                    intent.putExtras(bundle);
                    startActivity(intent);
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
                startActivity(new Intent(QuestionAnswer.this,HomeActivity.class));
            }
        });

        RelativeLayout catButton = (RelativeLayout) findViewById(R.id.navCategoryButton);
        assert catButton != null;
        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("wislet.gcephysicsmcqs.TopicalCategories"));
            }
        });


        /*For card*/
        isCardFront = true;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        DataHolder.getInstance().getActivitySavedStateMap().put(String.valueOf(CURRENT_QUESTION_INDEX),outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.question_answer_navdrawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            QuestionAnswerBean bean = questionAnswerBeanList.get(CURRENT_QUESTION_INDEX);
            View rootView = null;
            if (((sectionNumber) % 2) == 0) {
                rootView = inflater.inflate(R.layout.fragment_question, container, false);
                ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.questionProgressBar);
                progressBar.setProgress((100 / questionAnswerBeanList.size()) * (CURRENT_QUESTION_INDEX + 1));
                TextView progressBarText = (TextView) rootView.findViewById(R.id.questionProgressBarText);
                String stringFormat = getResources().getString(R.string.questionCounter);
                String counterMessage = String.format(stringFormat, CURRENT_QUESTION_INDEX + 1, questionAnswerBeanList.size());
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
                /*slidingLayout = (SlidingUpPanelLayout) rootView.findViewById(R.id.sliding_layout);
                View explanationPanel = rootView.findViewById(R.id.slidingPanelLayout);
                explanationPanel.setVisibility(View.VISIBLE);
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                */
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
                        answerButton.setOnClickListener(new AnswersOnClickListener(answerBean,bean.getQuestionBean(),getContext(), answerBeanList));
                        answerBean.setButton(answerButton);
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
                        index++;
                    }
                }
            }

            return rootView;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }


        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance((position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return questionAnswerBeanList == null ? 0 : 2;
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
        QuestionBean question;
        Context context;
        ArrayList<AnswerBean> answerBeanList;

        public AnswersOnClickListener(AnswerBean bean, QuestionBean question, Context context, ArrayList<AnswerBean> answerBeanList) {
            this.bean = bean;
            this.context = context;
            this.question = question;
            this.answerBeanList = answerBeanList;

        }

        @Override
        public void onClick(View v) {
            bean.getButton().setBackground(ContextCompat.getDrawable(context, bean.isCorrectAnswer() ? R.drawable.answer_correct_selector : R.drawable.answer_wrong_selector));
            for (AnswerBean answerBean : answerBeanList) {
                Button button = answerBean.getButton();
                if (answerBean.isCorrectAnswer()) {
                    button.setBackground(ContextCompat.getDrawable(context, R.drawable.answer_correct_selector));
                }
                button.setEnabled(false);
                button.setTextColor(ContextCompat.getColor(context, R.color.answerText));

            }
            /*slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);*/
            RelativeLayout explanationCardLayout = (RelativeLayout) ((Activity) context).findViewById(R.id.explanationCardLayout);
            final CardView explanationCardFront = (CardView) ((Activity) context).findViewById(R.id.explanationCardFront);
            final Button nextQuestionFrontBtn = (Button) ((Activity) context).findViewById(R.id.explanationCardFrontBtn);
            final TextView explanationCardFrontText = (TextView) ((Activity) context).findViewById(R.id.explanationCardFrontText);
            explanationCardFrontText.setText(bean.isCorrectAnswer() ? R.string.correct_answer : R.string.wrong_answer);
            nextQuestionFrontBtn.setText(R.string.next_question_btn);
            explanationCardLayout.setVisibility(View.VISIBLE);
            final AnimatorSet flipRightIn = (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.card_flip_right_in);
            final AnimatorSet flipRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_out);

            /*All the work for the back of card*/
            final CardView explanationCardBack = (CardView) ((Activity) context).findViewById(R.id.explanationCardBack);
            final Button nextQuestionBackBtn = (Button) ((Activity) context).findViewById(R.id.explanationCardBackBtn);
            final TextView explanationCardBackText = (TextView) ((Activity) context).findViewById(R.id.explanationCardBackText);

            explanationCardBackText.setText(question.getAnswerExplanation());
            explanationCardFront.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isCardFront) {
                        flipRightIn.setTarget(explanationCardBack);
                        flipRightOut.setTarget(explanationCardFront);
                        flipRightIn.start();
                        flipRightOut.start();
                        isCardFront = false;
                    }
                }
            });


            View.OnClickListener nextBtnOnclick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("wislet.gcephysicsmcqs.QuestionAnswer");
                    Bundle bundle = new Bundle();
                    bundle.putInt(QUESTION_INDEX_ARG, (CURRENT_QUESTION_INDEX + 1) % questionAnswerBeanList.size());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            };
            nextQuestionBackBtn.setOnClickListener(nextBtnOnclick);
            nextQuestionFrontBtn.setOnClickListener(nextBtnOnclick);

        }
    }

}
