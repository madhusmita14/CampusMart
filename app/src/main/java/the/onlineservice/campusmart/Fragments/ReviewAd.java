package the.onlineservice.campusmart.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
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

import the.onlineservice.campusmart.Adapters.GridViewAdapter;
import the.onlineservice.campusmart.Adapters.Horizontal_recyclerview_adapter;
import the.onlineservice.campusmart.Models.GridModel;
import the.onlineservice.campusmart.Models.horizontal_recyclerview_model;
import the.onlineservice.campusmart.R;

public class ReviewAd extends Fragment {

    RecyclerView img_recyclerView;
    Horizontal_recyclerview_adapter adapter;
    List<horizontal_recyclerview_model> pdList;
    private static String JSON_URL = "https://www.json-generator.com/api/json/get/cghNllfiYy?indent=2";
    Button do_chat_btn,do_call_btn;

    private List<GridModel> grids;
    GridView gridView;
    private GridViewAdapter gridViewAdapter;

    public ReviewAd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_review_ad, container, false);

        Toast.makeText(getContext(),"review ad",Toast.LENGTH_SHORT).show();

        do_chat_btn = v.findViewById(R.id.do_chat);
        do_call_btn = v.findViewById(R.id.do_call);

        img_recyclerView = v.findViewById(R.id.image_recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        img_recyclerView.setLayoutManager(llm);

        gridView = v.findViewById(R.id.gridViewReview);

        pdList = new ArrayList<>();
        grids = new ArrayList<>();

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

                        horizontal_recyclerview_model model = new horizontal_recyclerview_model();
                        model.setImg(songObject.getString("cover_img"));
                        pdList.add(model);

                        String title = songObject.getString("song");
                        String price = songObject.getString("artist");
                        String rating = songObject.getString("song");
                        String image = songObject.getString("cover_img");

                        GridModel model1 = new GridModel(title,image,price,rating);
                        grids.add(model1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //img_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new Horizontal_recyclerview_adapter(pdList,getContext());
                img_recyclerView.setAdapter(adapter);

                gridViewAdapter = new GridViewAdapter(getContext(),grids);
                gridView.setAdapter(gridViewAdapter);

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