package the.onlineservice.campusmart.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import the.onlineservice.campusmart.Models.NotificationModel;
import the.onlineservice.campusmart.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private static final String TAG = "NotificationAdapter";
    List<NotificationModel> notificationList;

    public NotificationAdapter(List<NotificationModel> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationModel model = notificationList.get(position);
        holder.titleTextView.setText(model.getTitle());
        holder.timingTextView.setText(model.getTiming());
        holder.addressTextView.setText(model.getAddress());
        holder.yearTextView.setText(model.getYear());
        holder.ratingTextView.setText(model.getRating());
        holder.plotTextView.setText(model.getPlot());

        boolean isExpanded = notificationList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ViewHolder";
        TextView titleTextView,timingTextView,addressTextView,yearTextView,ratingTextView,plotTextView;
        ConstraintLayout expandableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            timingTextView = itemView.findViewById(R.id.timingTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            plotTextView = itemView.findViewById(R.id.plotTextView);

            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NotificationModel model = notificationList.get(getAdapterPosition());
                    model.setExpanded(!model.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
