package data;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import beans.QuestionAnswerBean;
import components.WisletCountDownTimer;

/**
 *
 * Created by Hamza on 5/7/2016.
 */
public class DataHolder {

        private ArrayList<QuestionAnswerBean> questionAnswerBeans;

        public ArrayList<QuestionAnswerBean> getQuestionAnswerBeans() {
            return questionAnswerBeans;
        }
        public void setQuestionAnswerBeans(ArrayList<QuestionAnswerBean> questionAnswerBeans) {
            this.questionAnswerBeans = questionAnswerBeans;
        }

        private WisletCountDownTimer countDownTimer;

    public WisletCountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(WisletCountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    private static final DataHolder dataHolder = new DataHolder();
        public static DataHolder getInstance(){return dataHolder;}

    private HashMap<String,Bundle> activitySavedStateMap = new HashMap<>();

    public HashMap<String, Bundle> getActivitySavedStateMap() {
        return activitySavedStateMap;
    }

    public void setActivitySavedStateMap(HashMap<String, Bundle> activitySavedStateMap) {
        this.activitySavedStateMap = activitySavedStateMap;
    }
}

