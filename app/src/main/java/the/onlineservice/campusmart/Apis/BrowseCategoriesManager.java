package the.onlineservice.campusmart.Apis;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class BrowseCategoriesManager {

    private String URL ="https://www.json-generator.com/api/json/get/cghNllfiYy?indent=2";//"https://www.json-generator.com/api/json/get/cpVQkqtgya?indent=2";
    private RequestQueue queue;
    private Context c;
    private CustomeListener listener = null;
    //private ProgressDialog pDialog;
    private String strJSON;

    public String getStrJSON() {
        return strJSON;
    }

    public void setStrJSON(String strJSON) {
        this.strJSON = strJSON;
    }

    public BrowseCategoriesManager(Context c, CustomeListener customeListener) {
        this.c=c;
        this.listener=customeListener;
        queue= Volley.newRequestQueue(c);
    }

    public void postRequest() {
        /*pDialog.show();*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /*  pDialog.dismiss();*/
                listener.onVollyResponce(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponce(error);
            }
        });
        queue.add(stringRequest);
    }
}
