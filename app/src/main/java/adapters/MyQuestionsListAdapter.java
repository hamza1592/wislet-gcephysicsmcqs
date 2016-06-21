package adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import beans.QuestionAnswerBean;
import beans.QuestionBean;
import wislet.gcephysicsmcqs.QuestionAnswer;
import wislet.gcephysicsmcqs.R;

/**
 * This class is created inside the QuestionListActivity because its only an adapter for it and
 * needs the context of that class inside its inner methods
 * Created by Hamza on 5/17/2016.
 */
public class MyQuestionsListAdapter extends RecyclerView.Adapter<MyQuestionsListAdapter.QuestionsViewHolder> {

    ArrayList<QuestionAnswerBean> questionAnswerBeanList;

    public MyQuestionsListAdapter(ArrayList<QuestionAnswerBean> questionAnswerBeanList) {
        this.questionAnswerBeanList = questionAnswerBeanList;
    }

    @Override
    public QuestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_row,parent,false);
        return new QuestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionsViewHolder holder, final int position) {
        QuestionBean bean = questionAnswerBeanList.get(position).getQuestionBean();
        String questionNumber = "Question " + Integer.valueOf(position+1);
        holder.questionText.setText(questionNumber);
        holder.questionDetail.setText(bean.getQuestion());
        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt(QuestionAnswer.QUESTION_INDEX_ARG, position);
                Intent intent = new Intent("wislet.gcephysicsmcqs.QuestionAnswer");
                intent.putExtras(args);
            }
        };
    }

    @Override
    public int getItemCount() {
        return questionAnswerBeanList.size();
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView questionText, questionDetail;
        public View.OnClickListener onClickListener;
        public QuestionsViewHolder(View itemView) {
            super(itemView);
            questionText = (TextView) itemView.findViewById(R.id.questionText);
            questionDetail = (TextView) itemView.findViewById(R.id.questionDetail);
            itemView.setOnClickListener(onClickListener);
        }
    }
}
