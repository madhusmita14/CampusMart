package the.onlineservice.campusmart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import the.onlineservice.campusmart.Models.HorizontalModel;
import the.onlineservice.campusmart.R;

public class HorizontalRecyclerviewAdapter extends RecyclerView.Adapter<HorizontalRecyclerviewAdapter.ViewHolder> {
    Context context;
    ArrayList<HorizontalModel> arrayList;

    public HorizontalRecyclerviewAdapter(Context context, ArrayList<HorizontalModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HorizontalRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRecyclerviewAdapter.ViewHolder holder, int position) {
        final HorizontalModel model = arrayList.get(position);
        holder.txtTitleHorizontal.setText(model.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,model.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleHorizontal;
        ImageView ivThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleHorizontal = itemView.findViewById(R.id.txtTitleHorizontal);
            ivThumb = itemView.findViewById(R.id.ivThumb);
        }
    }
}
