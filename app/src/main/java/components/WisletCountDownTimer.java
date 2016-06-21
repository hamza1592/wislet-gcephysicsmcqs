package components;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by Hamza on 5/15/2016.
 */

public class WisletCountDownTimer extends CountDownTimer {

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */

    public WisletCountDownTimer(long millisInFuture, long countDownInterval, Context context) {
        super(millisInFuture, countDownInterval);
        this.millisInFuture = millisInFuture;
        this.context = context;
    }

    long millisInFuture;
    long millisElapsed;
    ProgressBar progressBar;
    Context context;
    TextView textView;
    String stringFormat;

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView, String stringFormat) {

        this.stringFormat = stringFormat;
        this.textView = textView;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public long getMillisInFuture() {
        return millisInFuture;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        final int WARNING_TIME = 75;
        millisElapsed = millisInFuture - millisUntilFinished;
        int progress = (int) Math.ceil(((double) millisElapsed) / ((double) millisInFuture) * 100.0);
        if (progressBar != null)
            progressBar.setProgress(progress);
        if (textView != null) {
            String value = String.format(stringFormat, (int) ((double) millisUntilFinished / (1000.0)));
            textView.setText(value);
        }
        if(progressBar!=null && progress > WARNING_TIME)
            progressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        if(textView !=null && progress > WARNING_TIME){
            textView.setTextColor(Color.RED);
        }
    }


    @Override
    public void onFinish() {
        String value = String.format(stringFormat, 0);
        textView.setText(value);
        progressBar.setProgress(100);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Time over").setMessage("Would you like to see the report?")
                .setPositiveButton("See report", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        context.startActivity(new Intent("wislet.gcephysicsmcqs.TestReportActivity"));
                    }
                })
                .setNeutralButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        context.startActivity(new Intent("wislet.gcephysicsmcqs.ApplicationModeActivity"));
                    }
                }).setCancelable(false);
        alertDialog.show();

    }

}
