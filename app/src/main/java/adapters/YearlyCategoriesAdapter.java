package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import beans.YearlyCategoryBean;
import wislet.gcephysicsmcqs.R;

/**
 *
 * Created by Hamza on 5/15/2016.
 */
public class YearlyCategoriesAdapter extends RecyclerView.Adapter<YearlyCategoriesAdapter.YearlyCategoriesViewHolder>{

    private List<YearlyCategoryBean> yearlyCategoryBeanList;

    public YearlyCategoriesAdapter(List<YearlyCategoryBean> yearlyCategoryBeanList) {
        this.yearlyCategoryBeanList = yearlyCategoryBeanList;
    }

    @Override
    public YearlyCategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topical_category_row,parent,false);
        return new YearlyCategoriesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(YearlyCategoriesViewHolder holder, int position) {
        YearlyCategoryBean yearlyCategoryBean = yearlyCategoryBeanList.get(position);
        holder.yearlyCategoryName.setText(yearlyCategoryBean.getYearDesc());
        holder.yearlyCategoryDesc.setText(yearlyCategoryBean.getYearDetail());
        holder.itemView.setOnClickListener(yearlyCategoryBean.getOnClickListener());

    }

    @Override
    public int getItemCount() {
        return yearlyCategoryBeanList.size();
    }

    public class YearlyCategoriesViewHolder extends RecyclerView.ViewHolder{
        public TextView yearlyCategoryName, yearlyCategoryDesc;
        public YearlyCategoriesViewHolder(View itemView) {
            super(itemView);
            yearlyCategoryName = (TextView) itemView.findViewById(R.id.categoryName);
            yearlyCategoryDesc = (TextView) itemView.findViewById(R.id.categoryDesc);
        }

    }
}
