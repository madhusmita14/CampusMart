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
import the.onlineservice.campusmart.Models.featuredListModel;
import the.onlineservice.campusmart.R;

public class FeaturedListAdapter  extends RecyclerView.Adapter<FeaturedListAdapter.ViewHolder> {
    List<featuredListModel> featuredList;
    Context context;
    LayoutInflater inflater;

    public FeaturedListAdapter(List<featuredListModel> featuredList, Context context) {
        this.featuredList = featuredList;
        //this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public FeaturedListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_list_item,parent,false);

        FeaturedListAdapter.ViewHolder holder = new FeaturedListAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedListAdapter.ViewHolder holder, int position) {
        featuredListModel model = featuredList.get(position);
        holder.bcTxt.setText(featuredList.get(position).getTxt());
        Picasso.get().load(featuredList.get(position).getImg()).into(holder.bcImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                AppCompatActivity appCompatActivity = (AppCompatActivity)view.getContext();

                productDetails fragment = new productDetails();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.featured_container,fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return featuredList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bcTxt;
        ImageView bcImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bcTxt = itemView.findViewById(R.id.songTitleFeatured);
            bcImg = itemView.findViewById(R.id.coverImageFeatured);
        }
    }
}
