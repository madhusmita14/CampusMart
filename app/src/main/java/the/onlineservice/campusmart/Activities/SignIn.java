package the.onlineservice.campusmart.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import the.onlineservice.campusmart.Adapters.SpinnerAdapter;
import the.onlineservice.campusmart.HttpsTrustManager;
import the.onlineservice.campusmart.MainActivity;
import the.onlineservice.campusmart.Models.SpinnerModel;
import the.onlineservice.campusmart.R;

public class SignIn extends AppCompatActivity {

    ImageView imageView3_signin;
    TextView do_forgot_password,go_signup;
    EditText mobileno_signin;
    AwesomeValidation awesomeValidation;
    Spinner spinner_signin;
    ArrayList<SpinnerModel> spinnerListSignin;
    String spinnerName;

    String Urls ="https://rentopool.com/olx/api/college";
    String sendOTPUrls = "https://rentopool.com/olx/api/sendOTPLogin";

    String is_signed_in="";
    SharedPreferences mPreferences;
    String sharedprofFile="com.protocoderspoint.registration_login";
    SharedPreferences.Editor preferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mPreferences=getSharedPreferences(sharedprofFile,MODE_PRIVATE);
        preferencesEditor = mPreferences.edit();

        is_signed_in = mPreferences.getString("issignedin","false");
        if(is_signed_in.equals("true"))
        {
            Intent i = new Intent(SignIn.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        mPreferences=getSharedPreferences(sharedprofFile,MODE_PRIVATE);
        preferencesEditor = mPreferences.edit();

        spinnerListSignin = new ArrayList<>();

        spinner_signin = findViewById(R.id.spinner_signin);
        mobileno_signin = findViewById(R.id.mobileno_signin);

        //awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //awesomeValidation.addValidation(this, R.id.mobileno_signin, "^[2-9]{2}[0-9]{8}$", R.string.mobilenoerror);

        imageView3_signin = findViewById(R.id.imageView3_signin);
        imageView3_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate();
                callApi();
                //startActivity(new Intent(getApplicationContext(), OTP.class));
            }
        });

        do_forgot_password = findViewById(R.id.do_forgot_password);
        do_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
            }
        });

        go_signup = findViewById(R.id.go_signup);
        go_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });

        loadSpinner();
    }

//    private void validate() {
//        if(awesomeValidation.validate()){
//            Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
//            callApi();
//            startActivity(new Intent(getApplicationContext(), OTP.class));
//        }
//    }

    private void loadSpinner() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    for (int i = 0 ; i< jsonArray.length(); i++){
                        JSONObject o = jsonArray.getJSONObject(i);

                        SpinnerModel user = new SpinnerModel();
                        user.setSpinnerId(o.getString("id"));
                        user.setSpinnerName(o.getString("college_name"));

                        spinnerListSignin.add(user);

                        final SpinnerAdapter adapter = new SpinnerAdapter(SignIn.this, android.R.layout.simple_spinner_dropdown_item,spinnerListSignin);
                        spinner_signin.setAdapter(adapter);
                        spinner_signin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String selected=spinner_signin.getSelectedItem().toString();
                                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);

                                SpinnerModel model = (SpinnerModel)adapterView.getSelectedItem();
                                spinnerName = model.getSpinnerName();

                                //Toast.makeText(getApplicationContext(),spinnerName,Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    // btn_personalinfo.setEnabled(false);
                }
//                spinner_StateId.setSelection(-1,true);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //btn_personalinfo.setEnabled(false);
                Toast.makeText(SignIn.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

//    private void callApi() {
//        HttpsTrustManager.allowAllSSL();
//
//        final String mobile = mobileno_signin.getText().toString();
//
//        final Map<String, String> params = new HashMap();
//        params.put("mobilenumber", mobile);
//        //params.put("college_name", spinnerName);
//
//        JSONObject parameters = new JSONObject(params);
//
//        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,sendOTPUrls, parameters,new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_SHORT).show();
//                //Toast.makeText(SignIn.this, "Login successful", Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(SignIn.this, OTP.class);
//                intent.putExtra("mobile",mobile);
//                startActivity(intent);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                //Log.d("signinerror",""+error);
//                Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_SHORT).show();
//            }
//        });
//        Volley.newRequestQueue(SignIn.this).add(jsonRequest);
//    }

    private void callApi()
    {
        //pdDialog.show();
        HttpsTrustManager.allowAllSSL();
        final String mobile = mobileno_signin.getText().toString();

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, sendOTPUrls,
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

                            //String token = jsonObject.getString("access_token");

//                                Toast.makeText(getApplicationContext(),"Logged In  Success",Toast.LENGTH_LONG).show();
//                                pdDialog.dismiss();

                            //preferencesEditor.putString("issignedin", "true");
                            //preferencesEditor.putString("SignedInUserID", token);
//                            preferencesEditor.putString("SignedInName", name);
//                            preferencesEditor.putString("SignedInusername", username);
                            //preferencesEditor.apply();

//                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
//                                startActivity(i);
//                                finish();

                            Intent intent = new Intent(SignIn.this, OTP.class);
                            intent.putExtra("mobile",mobile);
                            startActivity(intent);

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
                params.put("mobilenumber",mobile);
                params.put("college_name",spinnerName);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}