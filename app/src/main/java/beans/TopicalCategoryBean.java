package beans;

import android.view.View;

/**
 *
 * Created by Hamza on 4/19/2016.
 */
public class TopicalCategoryBean {
    String categoryCode;
    String categoryDesc;
    String categoryDetail;
    View.OnClickListener onClickListener;



    public TopicalCategoryBean(){
    }

    public TopicalCategoryBean(String categoryCode,String categoryDesc,String categoryDetail ) {
        this.categoryDesc = categoryDesc;
        this.categoryCode = categoryCode;
        this.categoryDetail = categoryDetail;
    }


    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryDetail() {
        return categoryDetail;
    }

    public void setCategoryDetail(String categoryDetail) {
        this.categoryDetail = categoryDetail;
    }
}
