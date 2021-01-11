package the.onlineservice.campusmart.Apis;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CollegeSpinnerManager {
    private String URL ="";
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

    public CollegeSpinnerManager(Context c, CustomeListener listener) {
        this.c = c;
        this.listener = listener;
        queue = Volley.newRequestQueue(c);
    }

    public void postRequest() {
        /*pDialog.show();*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, new Response.Listener<String>() {
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
