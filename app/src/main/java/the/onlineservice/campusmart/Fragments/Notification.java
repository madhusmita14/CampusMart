package the.onlineservice.campusmart.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import the.onlineservice.campusmart.Adapters.NotificationAdapter;
import the.onlineservice.campusmart.Models.NotificationModel;
import the.onlineservice.campusmart.R;

public class Notification extends Fragment {

    RecyclerView recyclerView;
    List<NotificationModel> notificationList;

    public Notification() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = v.findViewById(R.id.recyclerview);

        initData();
        initRecyclerView();

        return v;
    }

    private void initRecyclerView() {
        NotificationAdapter adapter = new NotificationAdapter(notificationList);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        notificationList = new ArrayList<>();

        notificationList.add(new NotificationModel("Iron man","just now","sailashree vihar","7.9","2008","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        notificationList.add(new NotificationModel("Iron man","just now","sailashree vihar","7.9","2008","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        notificationList.add(new NotificationModel("Iron man","just now","sailashree vihar","7.9","2008","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        notificationList.add(new NotificationModel("Iron man","just now","sailashree vihar","7.9","2008","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        notificationList.add(new NotificationModel("Iron man","just now","sailashree vihar","7.9","2008","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

    }
}