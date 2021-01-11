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
import the.onlineservice.campusmart.Adapters.RecommendedListAdapter;
import the.onlineservice.campusmart.Apis.BrowseCategoriesManager;
import the.onlineservice.campusmart.Models.recommendedListModel;
import the.onlineservice.campusmart.R;

public class recommendedList extends Fragment {

    RecyclerView recommendedRecyclerView;
    BrowseCategoriesManager browseCategoriesManager;
    recommendedListModel model;
    RecommendedListAdapter adapter;
    List<recommendedListModel> list;
    private static String JSON_URL = "https://www.json-generator.com/api/json/get/cghNllfiYy?indent=2";

    public recommendedList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recommended_list, container, false);

        Toast.makeText(getContext(),"browseCategoriesList",Toast.LENGTH_SHORT).show();

        recommendedRecyclerView = v.findViewById(R.id.recommendedRecyclerView);

        list = new ArrayList<>();

        loadData();

        return v;
    }

    private void loadData() {
//        browseCategoriesManager = new BrowseCategoriesManager(getContext(), new CustomeListener() {
//            @Override
//            public void onVollyResponce(String responce) {
//                //Toast.makeText(getContext(),"Res"+responce, Toast.LENGTH_SHORT).show();
//
//                try {
//                    JSONObject obj = new JSONObject(responce);
//
//                    JSONArray heroArray = obj.getJSONArray("Details");
//
//                    //Toast.makeText(getContext(),"Res"+ heroArray, Toast.LENGTH_SHORT).show();
//                    for(int i=0;i<heroArray.length();i++)
//                    {
//                        JSONObject object1 = heroArray.getJSONObject(i);
//                        JSONArray jsonArray1 = object1.getJSONArray("Mon");
//
//                        for (int j = 0; j < jsonArray1.length(); j++) {
//                            JSONObject object2 = jsonArray1.getJSONObject(j);
//
//                            String period2=object2.getString("Period");
//
//                            Toast.makeText(getContext(),"Res"+ period2, Toast.LENGTH_SHORT).show();
//
//                            model =new browseCategoriesListModel(period2);
//                            list.add(model);
//                        }
//                        adapter = new BrowseCategoriesListAdapter(list, getContext());
//                        browseCategoriesRecyclerView.setAdapter(adapter);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void onErrorResponce(VolleyError errorResponce) {
//                Toast.makeText(getContext(),"Res"+errorResponce.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        browseCategoriesManager.postRequest();

        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject songObject = response.getJSONObject(i);

                        recommendedListModel model = new recommendedListModel();
                        model.setTxt(songObject.getString("song").toString());
                        //model.setImg(songObject.getString("cover_img"));
                        //model.setUrl(songObject.getString("url"));
                        list.add(model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recommendedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new RecommendedListAdapter(list,getContext());
                recommendedRecyclerView.setAdapter(adapter);
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