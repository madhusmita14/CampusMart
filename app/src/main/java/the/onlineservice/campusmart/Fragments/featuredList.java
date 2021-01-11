package the.onlineservice.campusmart.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import the.onlineservice.campusmart.Adapters.BrowseCategoriesListAdapter;
import the.onlineservice.campusmart.Adapters.FeaturedListAdapter;
import the.onlineservice.campusmart.Apis.BrowseCategoriesManager;
import the.onlineservice.campusmart.Models.browseCategoriesListModel;
import the.onlineservice.campusmart.Models.featuredListModel;
import the.onlineservice.campusmart.R;

public class featuredList extends Fragment {

    RecyclerView featuredRecyclerView;
    BrowseCategoriesManager browseCategoriesManager;
    featuredListModel model;
    FeaturedListAdapter adapter;
    List<featuredListModel> list;
    private static String JSON_URL = "https://www.json-generator.com/api/json/get/cghNllfiYy?indent=2";

    public featuredList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_featured_list, container, false);

        Toast.makeText(getContext(),"featuredList",Toast.LENGTH_SHORT).show();

        featuredRecyclerView = v.findViewById(R.id.featuredRecyclerView);

        list = new ArrayList<>();

        loadData();

        return v;
    }

    private void loadData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject songObject = response.getJSONObject(i);

                        featuredListModel model = new featuredListModel();
                        model.setTxt(songObject.getString("song").toString());
                        //model.setImg(songObject.getString("cover_img"));
                        //model.setUrl(songObject.getString("url"));
                        list.add(model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                featuredRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new FeaturedListAdapter(list,getContext());
                featuredRecyclerView.setAdapter(adapter);
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