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
import java.util.List;
import the.onlineservice.campusmart.Fragments.productDetails;
import the.onlineservice.campusmart.Models.recommendedListModel;
import the.onlineservice.campusmart.R;

public class RecommendedListAdapter extends RecyclerView.Adapter<RecommendedListAdapter.ViewHolder>{
    List<recommendedListModel> recommendedList;
    Context context;
    LayoutInflater inflater;

    public RecommendedListAdapter(List<recommendedListModel> recommendedList, Context context) {
        this.recommendedList = recommendedList;
        //this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public RecommendedListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_list_item,parent,false);

        RecommendedListAdapter.ViewHolder holder = new RecommendedListAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedListAdapter.ViewHolder holder, int position) {
        recommendedListModel model = recommendedList.get(position);
        holder.bcTxt.setText(recommendedList.get(position).getTxt());
        //Picasso.get().load(recommendedList.get(position).getImg()).into(holder.bcImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                AppCompatActivity appCompatActivity = (AppCompatActivity)view.getContext();

                productDetails fragment = new productDetails();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.recommended_container,fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bcTxt;
        ImageView bcImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bcTxt = itemView.findViewById(R.id.songTitleRecommended);
            bcImg = itemView.findViewById(R.id.coverImageRecommended);
        }
    }
}
