package the.onlineservice.campusmart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.util.List;

import the.onlineservice.campusmart.Models.DashboardBrowseModel;
import the.onlineservice.campusmart.Models.DashboardRecommendedModel;
import the.onlineservice.campusmart.R;

public class DashboardRecommendedAdapter extends BaseAdapter {
    public Context context;
    private List<DashboardRecommendedModel> listitems;

    public DashboardRecommendedAdapter(Context context, List<DashboardRecommendedModel> listitems) {
        this.context = context;
        this.listitems = listitems;
    }

    @Override
    public int getCount() {
        return listitems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View grid;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.dashboard_recommended_item,viewGroup,false);
        }
        else {
            grid =(View) view;
        }

        final DashboardRecommendedModel getCategoriesModelClass = listitems.get(i);

        TextView textView = (TextView) grid.findViewById(R.id.dashboard_recommended_itemTv);
        //TextView textView1 = (TextView) grid.findViewById(R.id.product_price);
        ImageView imageView = (ImageView) grid.findViewById(R.id.dashboard_recommended_img);
        //CardView card_custom_getcategory = (CardView) grid.findViewById(R.id.card_custom_getcategory);

        textView.setText(getCategoriesModelClass.getTitle());
        //textView1.setText(getCategoriesModelClass.getPrice());
        Glide.with(context).load(getCategoriesModelClass.getImage()).into(imageView);

        return grid;
    }
}
