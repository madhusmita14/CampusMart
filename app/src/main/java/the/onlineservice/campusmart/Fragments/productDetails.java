package the.onlineservice.campusmart.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

public class productDetails extends Fragment {

    Activity activity;
    Context context;
    RecyclerView img_recyclerView,grid_recyclerView;
    Horizontal_recyclerview_adapter adapter;
    List<horizontal_recyclerview_model> pdList;
    private static String JSON_URL = "https://www.json-generator.com/api/json/get/cghNllfiYy?indent=2";

    Button do_chat_btn,do_call_btn,cancel_pop,confirm_pop;
    LinearLayout linear_pop,overbox;
    ImageView pop_up_icon,backDetails;
    Animation fromsmall,fromnothing,forloci,togo;

    private List<GridModel> grids;
    private ProgressBar progressBar;

    GridView gridView;
    private GridViewAdapter gridViewAdapter;

    public productDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_details, container, false);

        Toast.makeText(getContext(),"product details",Toast.LENGTH_SHORT).show();

        do_chat_btn = v.findViewById(R.id.do_chat_btn);
        do_call_btn = v.findViewById(R.id.do_call_btn);
        cancel_pop = v.findViewById(R.id.cancel_pop);
        confirm_pop = v.findViewById(R.id.confirm_pop);

//        do_chat_btn.setOnClickListener(this);
//        do_call_btn.setOnClickListener(this);
//        cancel_pop.setOnClickListener(this);
//        confirm_pop.setOnClickListener(this);

        linear_pop = v.findViewById(R.id.linear_pop);
        overbox = v.findViewById(R.id.overbox);
        pop_up_icon = v.findViewById(R.id.pop_up_icon);
        backDetails = v.findViewById(R.id.backDetails);
        backDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new browseCategoriesList()).commit();
            }
        });

        img_recyclerView = v.findViewById(R.id.img_recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        img_recyclerView.setLayoutManager(llm);

        gridView = v.findViewById(R.id.gridView);

        pdList = new ArrayList<>();
        grids = new ArrayList<>();

        loadData();

        fromsmall = AnimationUtils.loadAnimation(getContext(),R.anim.fromsmall);
        fromnothing = AnimationUtils.loadAnimation(getContext(),R.anim.fromnothing);
        forloci = AnimationUtils.loadAnimation(getContext(),R.anim.forloci);
        togo = AnimationUtils.loadAnimation(getContext(),R.anim.togo);

        linear_pop.setAlpha(0);
        overbox.setAlpha(0);
        pop_up_icon.setVisibility(View.GONE);

        do_chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop_up_icon.setVisibility(View.VISIBLE);
                pop_up_icon.startAnimation(forloci);

                overbox.setAlpha(1);
                overbox.startAnimation(fromnothing);

                linear_pop.setAlpha(1);
                linear_pop.startAnimation(fromsmall);
            }
        });

        do_call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop_up_icon.setVisibility(View.VISIBLE);
                pop_up_icon.startAnimation(forloci);

                overbox.setAlpha(1);
                overbox.startAnimation(fromnothing);

                linear_pop.setAlpha(1);
                linear_pop.startAnimation(fromsmall);
            }
        });

        cancel_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_pop.startAnimation(togo);
                pop_up_icon.startAnimation(togo);
                pop_up_icon.setVisibility(View.GONE);

                ViewCompat.animate(linear_pop).setStartDelay(1000).alpha(0).start();
                ViewCompat.animate(overbox).setStartDelay(1000).alpha(0).start();
            }
        });

        confirm_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //overbox.startAnimation(togo);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new ChatDetails()).commit();
            }
        });

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

//    @Override
//    public void onClick(View view) {
//        if(view.getId() == R.id.do_chat_btn){
//            pop_up_icon.setVisibility(View.VISIBLE);
//            pop_up_icon.startAnimation(forloci);
//            overbox.setAlpha(1);
//            overbox.startAnimation(fromnothing);
//            linear_pop.setAlpha(1);
//            linear_pop.startAnimation(fromsmall);
//
//            if(view.getId() == R.id.confirm_pop){
//                Toast.makeText(getContext(),"confirm clicked",Toast.LENGTH_SHORT).show();
//
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.container,new ChatDetails()).commit();
//            }
//        }
//        else if (view.getId() == R.id.do_call_btn){
//            pop_up_icon.setVisibility(View.VISIBLE);
//            pop_up_icon.startAnimation(forloci);
//            overbox.setAlpha(1);
//            overbox.startAnimation(fromnothing);
//            linear_pop.setAlpha(1);
//            linear_pop.startAnimation(fromsmall);
//
//            if(view.getId() == R.id.confirm_pop){
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:7978010115"));
//                startActivity(intent);
//            }
//        }
//        else if(view.getId() == R.id.cancel_pop){
//            linear_pop.startAnimation(togo);
//            pop_up_icon.startAnimation(togo);
//            pop_up_icon.setVisibility(View.GONE);
//
//            ViewCompat.animate(linear_pop).setStartDelay(1000).alpha(0).start();
//            ViewCompat.animate(overbox).setStartDelay(1000).alpha(0).start();
//        }
//    }
}