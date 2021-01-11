package the.onlineservice.campusmart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
import the.onlineservice.campusmart.Models.DashboardFeaturedModel;
import the.onlineservice.campusmart.R;

public class DashboardFeaturedAdapter extends RecyclerView.Adapter<DashboardFeaturedAdapter.ViewHolder>  {
    List<DashboardFeaturedModel> horizontalImgList;
    Context context;
    LayoutInflater inflater;

    public DashboardFeaturedAdapter(List<DashboardFeaturedModel> horizontalImgList, Context context) {
        this.horizontalImgList = horizontalImgList;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public DashboardFeaturedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_featured_item,parent,false);

        DashboardFeaturedAdapter.ViewHolder holder = new DashboardFeaturedAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardFeaturedAdapter.ViewHolder holder, int position) {
        DashboardFeaturedModel model = horizontalImgList.get(position);
        //holder.bcTxt.setText(browseCategoryList.get(position).getTxt());
        Picasso.get().load(horizontalImgList.get(position).getImg()).into(holder.horizontalImg);
    }

    @Override
    public int getItemCount() {
        return horizontalImgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView horizontalImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalImg = itemView.findViewById(R.id.dashboard_featured_img);
        }
    }
}
