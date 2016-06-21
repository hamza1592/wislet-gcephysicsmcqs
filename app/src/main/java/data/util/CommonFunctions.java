package data.util;

import java.util.ArrayList;

import beans.QuestionAnswerBean;

/**

 * Created by Hamza on 6/10/2016.
 */
public class CommonFunctions {


    public static String[] getQuestionsArray(ArrayList<QuestionAnswerBean> questionAnswerBeanList) {
        ArrayList<String> questionDesc = new ArrayList<>();
        for (QuestionAnswerBean bigBean : questionAnswerBeanList) {
            questionDesc.add(bigBean.getQuestionBean().getQuestion());
        }
        return questionDesc.toArray(new String[questionAnswerBeanList.size()]);
    }

}
