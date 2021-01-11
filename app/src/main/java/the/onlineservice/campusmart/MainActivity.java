package the.onlineservice.campusmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import the.onlineservice.campusmart.Activities.Camera;
import the.onlineservice.campusmart.Activities.Chat;
import the.onlineservice.campusmart.Activities.Profile;
import the.onlineservice.campusmart.Activities.Setting;
import the.onlineservice.campusmart.Adapters.DashboardBrowseAdapter;
import the.onlineservice.campusmart.Adapters.DashboardFeaturedAdapter;
import the.onlineservice.campusmart.Adapters.DashboardRecommendedAdapter;
import the.onlineservice.campusmart.Fragments.Notification;
import the.onlineservice.campusmart.Fragments.browseCategoriesList;
import the.onlineservice.campusmart.Fragments.featuredList;
import the.onlineservice.campusmart.Fragments.recommendedList;
import the.onlineservice.campusmart.Models.DashboardBrowseModel;
import the.onlineservice.campusmart.Models.DashboardFeaturedModel;
import the.onlineservice.campusmart.Models.DashboardRecommendedModel;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView browse_viewAll,featured_viewAll,recommended_viewAll;
    ScrollView scrollView;
    ImageView notification_icon;

    RecyclerView featured_recyclerView;
    GridView browseGridview,recommendGridview;
    List<DashboardBrowseModel> pdList;
    List<DashboardFeaturedModel> pdList1;
    List<DashboardRecommendedModel> pdList2;

    DashboardBrowseAdapter adapter;
    DashboardFeaturedAdapter adapter1;
    DashboardRecommendedAdapter adapter2;

    private static String JSON_URL = "https://www.json-generator.com/api/json/get/bTXVHgNFiW?indent=2";

    //private List<GridModel> grids;
    //private GridViewAdapter gridViewAdapter;

    SharedPreferences mPreferences;
    String sharedprofFile="com.protocoderspoint.registration_login";
    SharedPreferences.Editor preferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mPreferences=getSharedPreferences(sharedprofFile,MODE_PRIVATE);
        //preferencesEditor = mPreferences.edit();

        //String token =mPreferences.getString("SignedInUserID","null");

        scrollView = findViewById(R.id.scrollview);

        browseGridview = findViewById(R.id.browseGridview);
        featured_recyclerView = findViewById(R.id.featuredRecyclerView);
        recommendGridview = findViewById(R.id.recommendGridview);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        featured_recyclerView.setLayoutManager(llm);

        pdList = new ArrayList<>();
        pdList1 = new ArrayList<>();
        pdList2 = new ArrayList<>();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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

        browse_viewAll = findViewById(R.id.browse_viewAll);
        browse_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new browseCategoriesList()).commit();
            }
        });

        featured_viewAll = findViewById(R.id.featured_viewAll);
        featured_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new featuredList()).commit();
            }
        });

        recommended_viewAll = findViewById(R.id.recommended_viewAll);
        recommended_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new recommendedList()).commit();
            }
        });

        notification_icon = findViewById(R.id.notification_icon);
        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new Notification()).commit();
            }
        });

        loadBrowseData();
    }

    private void loadBrowseData() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_SHORT).show();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject songObject = response.getJSONObject(i);

                        DashboardBrowseModel model = new DashboardBrowseModel();
                        //model.setTitle(songObject.getString("cover_img"));
                        model.setTitle(songObject.getString("category_name"));
                        pdList.add(model);

                        String title = songObject.getString("song");
                        String price = songObject.getString("category_name");
                        String rating = songObject.getString("song");
                        String image = songObject.getString("cover_img");

                        //DashboardFeaturedModel model1 = new DashboardFeaturedModel(title,image,price,rating);
                        DashboardFeaturedModel model1 = new DashboardFeaturedModel();
                        //model1.setImg(songObject.getString("cover_img"));
                        pdList1.add(model1);

                        DashboardRecommendedModel model2 = new DashboardRecommendedModel(title,image,price,rating);
                        //model1.setImg(songObject.getString("cover_img"));
                        pdList2.add(model2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapter = new DashboardBrowseAdapter(getApplicationContext(), pdList);
                browseGridview.setAdapter(adapter);

                adapter1 = new DashboardFeaturedAdapter(pdList1, getApplicationContext());
                featured_recyclerView.setAdapter(adapter1);

                adapter2 = new DashboardRecommendedAdapter(getApplicationContext(), pdList2);
                recommendGridview.setAdapter(adapter2);

                //gridViewAdapter = new GridViewAdapter(getApplicationContext(),grids);
                //gridView.setAdapter(gridViewAdapter);

                //gridView1.setAdapter(gridViewAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }
}