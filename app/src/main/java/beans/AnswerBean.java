package beans;

import android.widget.Button;
import android.widget.ImageButton;

/**
 *
 * Created by Hamza on 5/3/2016.
 */
public class AnswerBean {

    public AnswerBean() {
    }

    public AnswerBean(int answerCode, boolean isCorrectAnswer) {
        this.answerCode = answerCode;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    int answerCode;
    String answer,imagePath;
    boolean isCorrectAnswer;
    Button button;
    ImageButton imageButton;

    public int getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(int answerCode) {
        this.answerCode = answerCode;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
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
