package the.onlineservice.campusmart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import the.onlineservice.campusmart.Models.HorizontalModel;
import the.onlineservice.campusmart.Models.VerticalModel;
import the.onlineservice.campusmart.R;

public class VerticalRecyclerviewAdapter extends RecyclerView.Adapter<VerticalRecyclerviewAdapter.ViewHolder> {
    Context context;
    ArrayList<VerticalModel> arrayList;

    public VerticalRecyclerviewAdapter(Context context,ArrayList<VerticalModel> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final VerticalModel verticalModel = arrayList.get(position);
        String title = verticalModel.getTitle();
        ArrayList<HorizontalModel> singleItem = verticalModel.getArrayList();

        holder.textViewTitle.setText(title);
        HorizontalRecyclerviewAdapter horizontalRecyclerviewAdapter = new HorizontalRecyclerviewAdapter(context,singleItem);

        holder.recyclerView1.setHasFixedSize(true);
        holder.recyclerView1.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView1.setAdapter(horizontalRecyclerviewAdapter);

        holder.btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,verticalModel.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView1;
        TextView textViewTitle;
        TextView btnmore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView1 = itemView.findViewById(R.id.recyclerview1);
            textViewTitle = itemView.findViewById(R.id.txtTitle1);
            btnmore = itemView.findViewById(R.id.btnmore);
        }
    }
}
