package the.onlineservice.campusmart.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import the.onlineservice.campusmart.Adapters.ChatListAdapter;
import the.onlineservice.campusmart.Fragments.ChatDetails;
import the.onlineservice.campusmart.Models.ChatModel;
import the.onlineservice.campusmart.R;

public class Chat extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    public static RecyclerView recyclerView;
    List<ChatModel> chatList;
    ChatListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//        bottomNavigationView = findViewById(R.id.bottom_navigation_chat);
//        bottomNavigationView.setSelectedItemId(R.id.chat);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.home:
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        overridePendingTransition(0,0);
//                        finishAffinity();
//                        return true;
//                    case R.id.chat:
//                        startActivity(new Intent(getApplicationContext(), Chat.class));
//                        overridePendingTransition(0,0);
//                        finishAffinity();
//                        return true;
//                    case R.id.camera:
//                        startActivity(new Intent(getApplicationContext(), Camera.class));
//                        overridePendingTransition(0,0);
//                        finishAffinity();
//                        return true;
//                    case R.id.profile:
//                        startActivity(new Intent(getApplicationContext(), Profile.class));
//                        overridePendingTransition(0,0);
//                        finishAffinity();
//                        return true;
//                    case R.id.setting:
//                        startActivity(new Intent(getApplicationContext(), Setting.class));
//                        overridePendingTransition(0,0);
//                        finishAffinity();
//                        return true;
//                }
//                return false;
//            }
//        });

        chatList = new ArrayList<>();

        recyclerView = findViewById(R.id.chatRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new ChatListAdapter(chatList,getApplicationContext());
        recyclerView.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        chatList.add(new ChatModel("10:20","Madhusmita Nayak"));
        chatList.add(new ChatModel("10:20","Madhusmita Nayak"));
        chatList.add(new ChatModel("10:20","Madhusmita Nayak"));
        chatList.add(new ChatModel("10:20","Madhusmita Nayak"));
        chatList.add(new ChatModel("10:20","Madhusmita Nayak"));
        chatList.add(new ChatModel("10:20","Madhusmita Nayak"));
        chatList.add(new ChatModel("10:20","Madhusmita Nayak"));
    }
}
