package adapters;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import beans.AnswerBean;
import beans.QuestionAnswerBean;
import beans.QuestionBean;
import wislet.gcephysicsmcqs.R;

/**
 *
 * Created by Hamza on 6/19/2016.
 */
public class ReportRecyclerAdapter extends RecyclerView.Adapter<ReportRecyclerAdapter.QuestionReportViewHolder> {

    ArrayList<QuestionAnswerBean> listOfQuestionAnswers = new ArrayList<>();

    public ReportRecyclerAdapter(ArrayList<QuestionAnswerBean> listOfQuestionAnswers) {
        this.listOfQuestionAnswers = listOfQuestionAnswers;
    }

    @Override
    public QuestionReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_report_recycler_row, parent, false);
        return new QuestionReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QuestionReportViewHolder holder, int position) {
        QuestionAnswerBean bigBean = listOfQuestionAnswers.get(position);
        QuestionBean questionBean = bigBean.getQuestionBean();
        AnswerBean selectedAnswer = bigBean.getSelectedAnswer();
        if (selectedAnswer == null) {
            selectedAnswer = new AnswerBean();
            selectedAnswer.setAnswer("None selected");
        }
        AnswerBean correctAnswer = null;
        for (AnswerBean answer : bigBean.getListOfAnswerBeans()) {
            if (answer.isCorrectAnswer())
                correctAnswer = answer;
        }
        holder.questionText.setText(questionBean.getQuestion());
        Drawable icon;

        if (selectedAnswer.equals(correctAnswer)) {
            icon = ResourcesCompat.getDrawable(holder.itemView.getResources(), R.mipmap.ic_correct_answer, null);
        } else {
            icon = ResourcesCompat.getDrawable(holder.itemView.getResources(), R.mipmap.ic_wrong_answer, null);
        }
        holder.answerResultIcon.setImageDrawable(icon);

        holder.selectedAnswer.setText(selectedAnswer.getAnswer());
        assert correctAnswer != null;
        holder.correctAnswer.setText(correctAnswer.getAnswer());

        holder.showExplanationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.explanation.setVisibility(View.VISIBLE);


            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.isExtended) {
                    holder.secondaryDetailsLayout.setVisibility(View.VISIBLE);
                    holder.isExtended = true;
                } else {
                    holder.explanation.setVisibility(View.GONE);
                    holder.secondaryDetailsLayout.setVisibility(View.GONE);
                    holder.isExtended = false;
                }
            }
        });
        holder.explanation.setText(questionBean.getAnswerExplanation());


    }

    @Override
    public int getItemCount() {
        return listOfQuestionAnswers.size();
    }

    public class QuestionReportViewHolder extends RecyclerView.ViewHolder {
        boolean isExtended = false;

        /**
         * The following two are for the recycler view visible initially
         */
        TextView questionText;
        ImageView answerResultIcon;

        /**
         * The following are for the panel that opens when the recycler row is clicked
         */
        TextView selectedAnswer;
        TextView correctAnswer;
        Button showExplanationBtn;

        /**
         * The explanation that opens
         */
        TextView explanation;

        /**
         * Layouts which we need to show hide
         */
        LinearLayout secondaryDetailsLayout;

        View.OnClickListener showDetailsOnClickListener;
        View.OnClickListener showExplanationOnClickListener;

        public QuestionReportViewHolder(View itemView) {
            super(itemView);
            questionText = (TextView) itemView.findViewById(R.id.questionText);
            selectedAnswer = (TextView) itemView.findViewById(R.id.selectedAnswer);
            correctAnswer = (TextView) itemView.findViewById(R.id.correctAnswer);
            explanation = (TextView) itemView.findViewById(R.id.answerExplanation);
            showExplanationBtn = (Button) itemView.findViewById(R.id.showExplanationBtn);
            answerResultIcon = (ImageView) itemView.findViewById(R.id.resultIcon);
            itemView.setOnClickListener(showDetailsOnClickListener);
            showExplanationBtn.setOnClickListener(showExplanationOnClickListener);
            secondaryDetailsLayout = (LinearLayout) itemView.findViewById(R.id.secondaryDetailsLayout);
        }
    }
}
