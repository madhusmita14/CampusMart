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
import the.onlineservice.campusmart.Models.horizontal_recyclerview_model;
import the.onlineservice.campusmart.R;

public class Horizontal_recyclerview_adapter extends RecyclerView.Adapter<Horizontal_recyclerview_adapter.ViewHolder> {
    List<horizontal_recyclerview_model> horizontalImgList;
    Context context;
    LayoutInflater inflater;

    public Horizontal_recyclerview_adapter(List<horizontal_recyclerview_model> horizontalImgList, Context context) {
        this.horizontalImgList = horizontalImgList;
        //this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_img_list_item,parent,false);

        Horizontal_recyclerview_adapter.ViewHolder holder = new Horizontal_recyclerview_adapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        horizontal_recyclerview_model model = horizontalImgList.get(position);
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
            horizontalImg = itemView.findViewById(R.id.horizontal_img);
        }
    }
}
