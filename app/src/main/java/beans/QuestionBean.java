package beans;

import android.widget.Button;
import android.widget.ImageButton;

/**
 *
 * Created by Hamza on 5/3/2016.
 */
public class QuestionBean {

    public QuestionBean() {
    }

    public QuestionBean(int questionCode) {
        this.questionCode = questionCode;
    }

    private int questionCode;
    private String question,imagePath;
    private Button button;
    private ImageButton imageButton;
    private String answerExplanation;


    public String getAnswerExplanation() {
        return answerExplanation;
    }

    public void setAnswerExplanation(String answerExplanation) {
        this.answerExplanation = answerExplanation;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(int questionCode) {
        this.questionCode = questionCode;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }
}
