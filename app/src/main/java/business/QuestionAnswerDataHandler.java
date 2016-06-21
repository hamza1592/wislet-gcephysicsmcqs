package business;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import beans.AnswerBean;
import beans.QuestionAnswerBean;
import beans.QuestionBean;
import data.GCE_PHY_ANSWERSDB;
import data.GCE_PHY_QUESTIONSDB;
import data.GCE_PHY_QUESTIONMAPPINGSDB;

/**
 *
 * Created by Hamza on 5/3/2016.
 */
public class QuestionAnswerDataHandler {

    public static ArrayList<QuestionAnswerBean> getQuestionAnswersByCategory(SQLiteDatabase db,String categoryCode){
        ContentValues contentValues = new ContentValues();
        contentValues.put(GCE_PHY_QUESTIONMAPPINGSDB.CATEGORY_CODE,categoryCode);
        return fetchQuestionAnswersDual(db,contentValues);
    }

    public static ArrayList<QuestionAnswerBean> getQuestionAnswersByYear(SQLiteDatabase db, String year){

        ContentValues contentValues = new ContentValues();
        contentValues.put(GCE_PHY_QUESTIONMAPPINGSDB.YEAR_CODE, year);
        return fetchQuestionAnswersDual(db,contentValues);
    }

    public static ArrayList<QuestionAnswerBean> fetchQuestionAnswersDual(SQLiteDatabase db,ContentValues contentValues){
        Cursor cursor = (new GCE_PHY_QUESTIONMAPPINGSDB()).selectData(db,new ArrayList<String>(),contentValues);
        ArrayList<QuestionAnswerBean> questionAnswerBeanList = new ArrayList<>();
        ArrayList<Integer> answerCodeList = new ArrayList<>();
        ArrayList<Integer> questionCodeList = new ArrayList<>();
        while(cursor.moveToNext()){
            int questionCode = cursor.getInt(cursor.getColumnIndex(GCE_PHY_QUESTIONMAPPINGSDB.QUESTION_CODE));
            int answerCode1 = cursor.getInt(cursor.getColumnIndex(GCE_PHY_QUESTIONMAPPINGSDB.ANSWER_CODE1));
            int answerCode2 = cursor.getInt(cursor.getColumnIndex(GCE_PHY_QUESTIONMAPPINGSDB.ANSWER_CODE2));
            int answerCode3 = cursor.getInt(cursor.getColumnIndex(GCE_PHY_QUESTIONMAPPINGSDB.ANSWER_CODE3));
            int answerCode4 = cursor.getInt(cursor.getColumnIndex(GCE_PHY_QUESTIONMAPPINGSDB.ANSWER_CODE4));
            int correctAnswerCode = cursor.getInt(cursor.getColumnIndex(GCE_PHY_QUESTIONMAPPINGSDB.CORRECT_ANSWER_CODE));
            QuestionBean questionBean = new QuestionBean(questionCode);
            AnswerBean answerBean1 = new AnswerBean(answerCode1,answerCode1==correctAnswerCode);
            AnswerBean answerBean2 = new AnswerBean(answerCode2,answerCode2==correctAnswerCode);
            AnswerBean answerBean3 = new AnswerBean(answerCode3,answerCode3==correctAnswerCode);
            AnswerBean answerBean4 = new AnswerBean(answerCode4,answerCode4==correctAnswerCode);
            questionAnswerBeanList.add(new QuestionAnswerBean(questionBean,answerBean1,answerBean2,answerBean3,answerBean4));
            answerCodeList.add(answerCode1);
            answerCodeList.add(answerCode2);
            answerCodeList.add(answerCode3);
            answerCodeList.add(answerCode4);
            questionCodeList.add(questionCode);
        }

        HashMap<Integer,HashMap<String,String>> questionsMap = fetchQuestionsMap(db,questionCodeList);
        HashMap<Integer,HashMap<String,String>> answersMap = fetchAnswersMap(db, answerCodeList);
        setValuesInBeans(questionAnswerBeanList,questionsMap,answersMap);
        return questionAnswerBeanList;
    }

    public static HashMap<Integer,HashMap<String,String>> fetchAnswersMap(SQLiteDatabase db, ArrayList<Integer> answerCodeList){
        HashMap<String,Object> inClause = new HashMap<>();
        inClause.put(GCE_PHY_ANSWERSDB.ANSWER_CODE,answerCodeList);
        ArrayList<String> selectCols = new ArrayList<>();
        selectCols.add(GCE_PHY_ANSWERSDB.ANSWER_CODE);
        selectCols.add(GCE_PHY_ANSWERSDB.ANSWER_TEXT);
        selectCols.add(GCE_PHY_ANSWERSDB.ANSWER_IMG_URL);
        Cursor cursor = (new GCE_PHY_ANSWERSDB()).selectData(db,selectCols,inClause);
        HashMap<Integer,HashMap<String,String>> answersMap = new HashMap<>();
        while(cursor.moveToNext()){
            HashMap<String,String> answerCodeTextMap = new HashMap<>();
            answerCodeTextMap.put(GCE_PHY_ANSWERSDB.ANSWER_TEXT,cursor.getString(cursor.getColumnIndex(GCE_PHY_ANSWERSDB.ANSWER_TEXT)));
            answerCodeTextMap.put(GCE_PHY_ANSWERSDB.ANSWER_IMG_URL,cursor.getString(cursor.getColumnIndex(GCE_PHY_ANSWERSDB.ANSWER_IMG_URL)));
            answersMap.put(cursor.getInt(cursor.getColumnIndex(GCE_PHY_ANSWERSDB.ANSWER_CODE)),answerCodeTextMap);
        }
        return answersMap;
    }

    public static HashMap<Integer,HashMap<String,String>> fetchQuestionsMap(SQLiteDatabase db, ArrayList<Integer> questionCodeList){
        HashMap<String,Object> inClause = new HashMap<>();
        inClause.put(GCE_PHY_QUESTIONSDB.QUESTION_CODE,questionCodeList);
        ArrayList<String> selectCols = new ArrayList<>();
        selectCols.add(GCE_PHY_QUESTIONSDB.QUESTION_CODE);
        selectCols.add(GCE_PHY_QUESTIONSDB.QUESTION_TEXT);
        selectCols.add(GCE_PHY_QUESTIONSDB.QUESTION_IMG_URL);
        Cursor cursor = (new GCE_PHY_QUESTIONSDB()).selectData(db,selectCols,inClause);
        HashMap<Integer,HashMap<String,String>> answersMap = new HashMap<>();
        while(cursor.moveToNext()){
            HashMap<String,String> answerCodeTextMap = new HashMap<>();
            answerCodeTextMap.put(GCE_PHY_QUESTIONSDB.QUESTION_TEXT,cursor.getString(cursor.getColumnIndex(GCE_PHY_QUESTIONSDB.QUESTION_TEXT)));
            answerCodeTextMap.put(GCE_PHY_QUESTIONSDB.QUESTION_IMG_URL,cursor.getString(cursor.getColumnIndex(GCE_PHY_QUESTIONSDB.QUESTION_IMG_URL)));
            answersMap.put(cursor.getInt(cursor.getColumnIndex(GCE_PHY_QUESTIONSDB.QUESTION_CODE)),answerCodeTextMap);
        }
        return answersMap;

    }

    public static void setValuesInBeans(ArrayList<QuestionAnswerBean> listOfQuestionAnswerBeans,HashMap<Integer,HashMap<String,String>> questionsMap,HashMap<Integer,HashMap<String,String>> answersMap){
        for (QuestionAnswerBean questionAnswerBean: listOfQuestionAnswerBeans){

            QuestionBean questionBean = questionAnswerBean.getQuestionBean();
            AnswerBean answerBean1 = questionAnswerBean.getAnswerBean1();
            AnswerBean answerBean2 = questionAnswerBean.getAnswerBean2();
            AnswerBean answerBean3 = questionAnswerBean.getAnswerBean3();
            AnswerBean answerBean4 = questionAnswerBean.getAnswerBean4();

            questionBean.setQuestion(questionsMap.get(questionBean.getQuestionCode()).get(GCE_PHY_QUESTIONSDB.QUESTION_TEXT));
            questionBean.setImagePath(questionsMap.get(questionBean.getQuestionCode()).get(GCE_PHY_QUESTIONSDB.QUESTION_IMG_URL));

            answerBean1.setAnswer(answersMap.get(answerBean1.getAnswerCode()).get(GCE_PHY_ANSWERSDB.ANSWER_TEXT));
            answerBean1.setImagePath(answersMap.get(answerBean1.getAnswerCode()).get(GCE_PHY_ANSWERSDB.ANSWER_IMG_URL));

            answerBean2.setAnswer(answersMap.get(answerBean2.getAnswerCode()).get(GCE_PHY_ANSWERSDB.ANSWER_TEXT));
            answerBean2.setImagePath(answersMap.get(answerBean2.getAnswerCode()).get(GCE_PHY_ANSWERSDB.ANSWER_IMG_URL));

            answerBean3.setAnswer(answersMap.get(answerBean3.getAnswerCode()).get(GCE_PHY_ANSWERSDB.ANSWER_TEXT));
            answerBean3.setImagePath(answersMap.get(answerBean3.getAnswerCode()).get(GCE_PHY_ANSWERSDB.ANSWER_IMG_URL));

            answerBean4.setAnswer(answersMap.get(answerBean4.getAnswerCode()).get(GCE_PHY_ANSWERSDB.ANSWER_TEXT));
            answerBean4.setImagePath(answersMap.get(answerBean4.getAnswerCode()).get(GCE_PHY_ANSWERSDB.ANSWER_IMG_URL));

        }
    }

}
