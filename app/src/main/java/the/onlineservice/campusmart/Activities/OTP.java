package the.onlineservice.campusmart.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.view.PinView;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import the.onlineservice.campusmart.HttpsTrustManager;
import the.onlineservice.campusmart.MainActivity;
import the.onlineservice.campusmart.R;

public class OTP extends AppCompatActivity {

    ImageView imageView7;
    PinView pinView;
    String mobile,otpStore;

    String verifyOTPUrls = "https://rentopool.com/olx/api/verifyOTP";

    String is_signed_in="";
    SharedPreferences mPreferences;
    String sharedprofFile="com.protocoderspoint.registration_login";
    SharedPreferences.Editor preferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        mPreferences=getSharedPreferences(sharedprofFile,MODE_PRIVATE);
        preferencesEditor = mPreferences.edit();

        is_signed_in = mPreferences.getString("issignedin","false");
        if(is_signed_in.equals("true"))
        {
            Intent i = new Intent(OTP.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        mPreferences=getSharedPreferences(sharedprofFile,MODE_PRIVATE);
        preferencesEditor = mPreferences.edit();

        pinView = findViewById(R.id.pinView);

        imageView7 = findViewById(R.id.imageView7);
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otpStore = pinView.getText().toString();
                //Toast.makeText(getApplicationContext(),""+otpStore,Toast.LENGTH_SHORT).show();
                //callApi();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        Intent i = getIntent();
        mobile = i.getStringExtra("mobile");
    }

//    private void callApi() {
//        HttpsTrustManager.allowAllSSL();
//
//        final Map<String, String> params = new HashMap();
//        params.put("mobilenumber","+91"+mobile);
//        params.put("otp_code", otpStore);
//
//        JSONObject parameters = new JSONObject(params);
//
//        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,verifyOTPUrls, parameters,new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_SHORT).show();
//                //Toast.makeText(SignIn.this, "Login successful", Toast.LENGTH_LONG).show();
//                Log.d("signinresponseOTP",""+response);
//
//                Intent intent = new Intent(OTP.this, MainActivity.class);
//                startActivity(intent);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_SHORT).show();
//            }
//        });
//        Volley.newRequestQueue(OTP.this).add(jsonRequest);
//    }

    private void callApi(){
        HttpsTrustManager.allowAllSSL();
        //pdDialog.show();
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, verifyOTPUrls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("anyText",response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);

//                            String success = jsonObject.getString("success");
//                            String message = jsonObject.getString("message");
//                            String id= jsonObject.getString("id");
//                            String name = jsonObject.getString("name");
//                            String username = jsonObject.getString("username");

//                            if(success.equals("1")){
//                                Toast.makeText(getApplicationContext(),"Logged In  Success",Toast.LENGTH_LONG).show();
//                                pdDialog.dismiss();
//                                preferencesEditor.putString("issignedin","true");
//                                preferencesEditor.putString("SignedInUserID",id);
//                                preferencesEditor.putString("SignedInName",name);
//                                preferencesEditor.putString("SignedInusername",username);
//                                preferencesEditor.apply();
//                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
//                                startActivity(i);
//                                finish();
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Registration Error !1"+e,Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pdDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Registration Error !2"+error,Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("mobilenumber","+91"+mobile);
                params.put("otp_code",otpStore);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}