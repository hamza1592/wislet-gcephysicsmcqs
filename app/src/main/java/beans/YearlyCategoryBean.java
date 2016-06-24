package beans;

import android.view.View;

/**
 * Created by Hamza on 5/15/2016.
 */
public class YearlyCategoryBean {
    private String yearCode;
    private String yearDesc;
    private String yearDetail;
    private View.OnClickListener onClickListener;

    public YearlyCategoryBean() {
    }

    public YearlyCategoryBean(String yearCode, String yearDesc, String yearDetail) {
        this.yearCode = yearCode;
        this.yearDesc = yearDesc;
        this.yearDetail = yearDetail;
    }

    public String getYearCode() {
        return yearCode;
    }

    public void setYearCode(String yearCode) {
        this.yearCode = yearCode;
    }

    public String getYearDesc() {
        return yearDesc;
    }

    public void setYearDesc(String yearDesc) {
        this.yearDesc = yearDesc;
    }

    public String getYearDetail() {
        return yearDetail;
    }

    public void setYearDetail(String yearDetail) {
        this.yearDetail = yearDetail;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
