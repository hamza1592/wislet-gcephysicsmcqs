package beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * Created by Hamza on 5/3/2016.
 */
public class QuestionAnswerBean implements Parcelable{
    QuestionBean questionBean;
    AnswerBean answerBean1,answerBean2,answerBean3,answerBean4;
    boolean answered;
    AnswerBean selectedAnswer;

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public AnswerBean getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(AnswerBean selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public QuestionAnswerBean() {
    }

    public QuestionAnswerBean(QuestionBean questionBean, AnswerBean answerBean1, AnswerBean answerBean2, AnswerBean answerBean3, AnswerBean answerBean4) {
        this.questionBean = questionBean;
        this.answerBean1 = answerBean1;
        this.answerBean2 = answerBean2;
        this.answerBean3 = answerBean3;
        this.answerBean4 = answerBean4;
    }

    public QuestionBean getQuestionBean() {
        return questionBean;
    }

    public void setQuestionBean(QuestionBean questionBean) {
        this.questionBean = questionBean;
    }

    public AnswerBean getAnswerBean1() {
        return answerBean1;
    }

    public void setAnswerBean1(AnswerBean answerBean1) {
        this.answerBean1 = answerBean1;
    }

    public AnswerBean getAnswerBean2() {
        return answerBean2;
    }

    public void setAnswerBean2(AnswerBean answerBean2) {
        this.answerBean2 = answerBean2;
    }

    public AnswerBean getAnswerBean3() {
        return answerBean3;
    }

    public void setAnswerBean3(AnswerBean answerBean3) {
        this.answerBean3 = answerBean3;
    }

    public AnswerBean getAnswerBean4() {
        return answerBean4;
    }

    public void setAnswerBean4(AnswerBean answerBean4) {
        this.answerBean4 = answerBean4;
    }

    public ArrayList<AnswerBean> getListOfAnswerBeans(){
        ArrayList<AnswerBean> answerBeanArrayList = new ArrayList<>();
        answerBeanArrayList.add(getAnswerBean1());
        answerBeanArrayList.add(getAnswerBean2());
        answerBeanArrayList.add(getAnswerBean3());
        answerBeanArrayList.add(getAnswerBean4());
        return answerBeanArrayList;
    }
    /*Stuff to make this parcelable*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    protected QuestionAnswerBean(Parcel in) {
    }

    public static final Creator<QuestionAnswerBean> CREATOR = new Creator<QuestionAnswerBean>() {
        @Override
        public QuestionAnswerBean createFromParcel(Parcel in) {
            return new QuestionAnswerBean(in);
        }

        @Override
        public QuestionAnswerBean[] newArray(int size) {
            return new QuestionAnswerBean[size];
        }
    };


}
