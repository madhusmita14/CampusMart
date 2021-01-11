package the.onlineservice.campusmart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
import the.onlineservice.campusmart.Fragments.productDetails;
import the.onlineservice.campusmart.Models.browseCategoriesListModel;
import the.onlineservice.campusmart.R;

public class BrowseCategoriesListAdapter extends RecyclerView.Adapter<BrowseCategoriesListAdapter.ViewHolder> {
    List<browseCategoriesListModel> browseCategoryList;
    Context context;
    LayoutInflater inflater;
    //Activity activity = (Activity)context;

    public BrowseCategoriesListAdapter(List<browseCategoriesListModel> browseCategoryList, Context context) {
        this.browseCategoryList = browseCategoryList;
        //this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse_categories_list_item,parent,false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        browseCategoriesListModel model = browseCategoryList.get(position);
        holder.bcTxt.setText(browseCategoryList.get(position).getTxt());
        //Picasso.get().load(browseCategoryList.get(position).getImg()).into(holder.bcImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                AppCompatActivity appCompatActivity = (AppCompatActivity)view.getContext();

                productDetails fragment = new productDetails();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.browseCategory_container,fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return browseCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bcTxt;
        ImageView bcImg;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            bcTxt = itemView.findViewById(R.id.songTitle);
            bcImg = itemView.findViewById(R.id.coverImage);
        }
    }
}
