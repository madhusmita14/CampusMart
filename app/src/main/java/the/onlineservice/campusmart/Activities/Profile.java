package the.onlineservice.campusmart.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import the.onlineservice.campusmart.Adapters.VerticalRecyclerviewAdapter;
import the.onlineservice.campusmart.MainActivity;
import the.onlineservice.campusmart.Models.HorizontalModel;
import the.onlineservice.campusmart.Models.VerticalModel;
import the.onlineservice.campusmart.R;

public class Profile extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView profileRecyclerview;
    VerticalRecyclerviewAdapter adapter;
    ArrayList<VerticalModel> arrayListVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView = findViewById(R.id.bottom_navigation_profile);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(), Chat.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return true;
                    case R.id.camera:
                        startActivity(new Intent(getApplicationContext(), Camera.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(), Setting.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return true;
                }
                return false;
            }
        });

        arrayListVertical = new ArrayList<>();

        profileRecyclerview = findViewById(R.id.profileRecyclerview);
        profileRecyclerview.setHasFixedSize(true);
        profileRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new VerticalRecyclerviewAdapter(this,arrayListVertical);

        profileRecyclerview.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        for(int i=1;i<=2;i++){
            VerticalModel verticalModel = new VerticalModel();
            verticalModel.setTitle("Title"+i);
            ArrayList<HorizontalModel> arrayListHorizontal = new ArrayList<>();

            for(int j=1;j<=5;j++){
                HorizontalModel horizontalModel = new HorizontalModel();
                horizontalModel.setDesc("Desc"+j);
                horizontalModel.setName("Name"+j);

                arrayListHorizontal.add(horizontalModel);
            }
            verticalModel.setArrayList(arrayListHorizontal);
            arrayListVertical.add(verticalModel);
        }
        adapter.notifyDataSetChanged();
    }
}