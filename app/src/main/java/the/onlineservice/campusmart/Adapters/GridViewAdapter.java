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
import the.onlineservice.campusmart.Models.GridModel;
import the.onlineservice.campusmart.R;

public class GridViewAdapter extends BaseAdapter {
    public Context context;
    private List<GridModel> listitems;

    public GridViewAdapter(Context context, List<GridModel> listitems) {
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
            grid = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }
        else {
            grid =(View) view;
        }

        final GridModel getCategoriesModelClass = listitems.get(i);

        TextView textView = (TextView) grid.findViewById(R.id.product_title);
        TextView textView1 = (TextView) grid.findViewById(R.id.product_price);
        ImageView imageView = (ImageView) grid.findViewById(R.id.product_image);
        CardView card_custom_getcategory = (CardView) grid.findViewById(R.id.card_custom_getcategory);

        textView.setText(getCategoriesModelClass.getTitle());
        textView1.setText(getCategoriesModelClass.getPrice());
        Glide.with(context).load(getCategoriesModelClass.getImage()).into(imageView);
//            Picasso.with(context)
//                    .load(getTopServicesForHomepage_modelClass.getFullPath())
//                    // .placeholder(getActivity().getResources().getDrawable(R.drawable.ic_person_black_24dp))
//                    .into(imageView);

//        card_custom_getcategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SharedPreferences sp = context.getSharedPreferences("key", 0);
//                SharedPreferences.Editor sedt = sp.edit();
//                sedt.putString("CategoryName",getCategoriesModelClass.getCatName());
//                sedt.commit();
//
//                Fragment newFragment = new Fragment_All_Items();
//                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, newFragment);
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                transaction.addToBackStack(null);
//                transaction.commit();
//
//            }
//        });

        return grid;
    }
}
