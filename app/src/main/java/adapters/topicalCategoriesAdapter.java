package adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import beans.TopicalCategoryBean;
import wislet.gcephysicsmcqs.R;
import wislet.gcephysicsmcqs.TopicalCategories;


/**
 *
 * Created by Hamza on 4/19/2016.
 */
public class topicalCategoriesAdapter extends RecyclerView.Adapter<topicalCategoriesAdapter.CategoriesViewHolder>{


    private List<TopicalCategoryBean> topicalCategoryBeanList;
    private View mView;

    public topicalCategoriesAdapter(List<TopicalCategoryBean> topicalCategoryBeanList) {
        this.topicalCategoryBeanList = topicalCategoryBeanList;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topical_category_row,parent,false);
        mView = view;
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        TopicalCategoryBean topicalCategoryBean = topicalCategoryBeanList.get(position);
        holder.topicalCategoryName.setText(topicalCategoryBean.getCategoryDesc());
        holder.topicalCategoryName.setTextColor(ContextCompat.getColor(mView.getContext(), R.color.colorAccent));
        holder.topicalCategoryDesc.setText(topicalCategoryBean.getCategoryDetail());
        holder.topicalCategoryDesc.setTextColor(ContextCompat.getColor(mView.getContext(), R.color.colorAccentLight));

        holder.itemView.setOnClickListener(topicalCategoryBean.getOnClickListener());

    }

    @Override
    public int getItemCount() {
        return topicalCategoryBeanList.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder{
        public TextView topicalCategoryName, topicalCategoryDesc;
        public CategoriesViewHolder(View itemView) {
            super(itemView);
            topicalCategoryName = (TextView) itemView.findViewById(R.id.categoryName);
            topicalCategoryDesc = (TextView) itemView.findViewById(R.id.categoryDesc);
            }

    }
}
